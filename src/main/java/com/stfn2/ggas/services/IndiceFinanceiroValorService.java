package com.stfn2.ggas.services;

import com.stfn2.ggas.domain.IndiceFinanceiro;
import com.stfn2.ggas.domain.dtos.IndiceFinanceiroDTO;
import com.stfn2.ggas.domain.dtos.filter.IndiceFinanceiroValorNominalFilterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.IndiceFinanceiroValorNominal;
import com.stfn2.ggas.domain.dtos.IndiceFinanceiroValorNominalDTO;
import com.stfn2.ggas.domain.dtos.basic.IndiceFinanceiroValorNominalBasicDTO;
import com.stfn2.ggas.repositories.IndiceFinanceiroValorNominalRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import com.stfn2.ggas.tools.ToolDate;
@Service
public class IndiceFinanceiroValorService extends BaseService<IndiceFinanceiroValorNominal,
        IndiceFinanceiroValorNominalDTO, IndiceFinanceiroValorNominalBasicDTO, IndiceFinanceiroValorNominalFilterDTO,
        IndiceFinanceiroValorNominalRepository> {


    @Autowired
    IndiceFinanceiroService indiceFinanceiro;

    @Override
    public void validDto(IndiceFinanceiroValorNominalDTO dto) {
        IndiceFinanceiro indice = indiceFinanceiro.getById(dto.getIndiceFinanceiroId());
        if(!indice.getNegativo() && dto.getValor().compareTo(BigDecimal.ZERO) < 0){
            addErro("","Esse tipo de Índice Financeiro não permite valores negativos!");
        }

        super.validDto(dto);
    }



    public List<IndiceFinanceiroValorNominalDTO> createOrUpdateAll (List<IndiceFinanceiroValorNominalDTO> dtos){

        List<IndiceFinanceiroValorNominalDTO> updatedDtos = new ArrayList<>();
        for (IndiceFinanceiroValorNominalDTO dto : dtos) {
            if(dto.getValor()!=null){
                dto.setUtilizado(Boolean.FALSE);
                IndiceFinanceiroValorNominalDTO updatedDto = createOrUpdate(dto);
                updatedDtos.add(updatedDto);
            }
        }
        return updatedDtos;
    }

    public List<IndiceFinanceiroValorNominalDTO> getListEntreData(IndiceFinanceiro indice, LocalDate inicio, LocalDate fim) {
        List<IndiceFinanceiroValorNominalDTO> response = new ArrayList<>();
        List<IndiceFinanceiroValorNominal>  listVlrNominal = repository.findByIndiceFinanceiroIdAndDataReferenciaBetween(indice.getId(), inicio, fim);
        listVlrNominal.forEach(i -> response.add(this.entityToDTO(i)));
        List<LocalDate> datas = new ArrayList<LocalDate>();

        if(indice.getMensal()){
            LocalDate proxData = inicio;
            while(proxData.isBefore(fim)){
                datas.add(proxData);
                proxData = ToolDate.getDayInNextMonth(proxData, 1);
            }

            datas.forEach(i -> {
                Optional<IndiceFinanceiroValorNominalDTO> op = response.stream().filter(f -> f.getDataReferencia().equals(i)).findFirst();
                if(op.isEmpty()) {
                    IndiceFinanceiroValorNominalDTO nominal = new IndiceFinanceiroValorNominalDTO();
                    nominal.setDataReferencia(i);
                    response.add(nominal);
                }
            });
        } else{
            LocalDate proxData = inicio;
            while(proxData.isBefore(fim) || proxData.equals(fim)){
                datas.add(proxData);
                proxData = ToolDate.addOneDay(proxData);
            }

            datas.forEach(i -> {
                Optional<IndiceFinanceiroValorNominalDTO> op = response.stream().filter(f -> f.getDataReferencia().equals(i)).findFirst();
                if(op.isEmpty()) {
                    IndiceFinanceiroValorNominalDTO nominal = new IndiceFinanceiroValorNominalDTO();
                    nominal.setDataReferencia(i);
                    response.add(nominal);
                }
            });

        }
        Comparator<IndiceFinanceiroValorNominalDTO> ordenadorList = new Comparator<IndiceFinanceiroValorNominalDTO>(){
            @Override
            public int compare(IndiceFinanceiroValorNominalDTO o1, IndiceFinanceiroValorNominalDTO o2) {
                return o1.getDataReferencia().compareTo(o2.getDataReferencia());
            }

        };

        Collections.sort(response, ordenadorList);
        response.forEach(ind -> ind.setIndiceFinanceiroId(indice.getId()));
        return response;
    }

    private IndiceFinanceiroValorNominal obterIndiceFinanceiroValorNominalMaisRecente(IndiceFinanceiro indiceFinanceiro, LocalDate dataReferenciaFinal) {
        return repository.findMaisRecenteValorNominalByIndiceFinanceiroId(indiceFinanceiro.getId(), dataReferenciaFinal).stream().findFirst().orElse(null);
    }


    public List<IndiceFinanceiroValorNominalDTO> listaValorNominalIndiceFinanceiro(Long indiceFinanceiroId, LocalDate dataInicial, LocalDate dataFinal) {
        IndiceFinanceiro indice = indiceFinanceiro.getById(indiceFinanceiroId);
        boolean isMensal = indice.getMensal();
        List<IndiceFinanceiroValorNominal> valoresNominais = this.repository.findByIndiceFinanceiroIdAndDataReferenciaBetween(indiceFinanceiroId, dataInicial, dataFinal);
        List<IndiceFinanceiroValorNominalDTO> resultados = new ArrayList<>();

        LocalDate current = dataInicial;
        IndiceFinanceiroValorNominalDTO ultimoDTO = null; // Para manter o valor do último DTO adicionado

        while (!current.isAfter(dataFinal)) {
            IndiceFinanceiroValorNominalDTO dto;

            if (isMensal) {
                dto = processarMensal(valoresNominais, current, indice);
            } else {
                dto = processarDiario(valoresNominais, current, indice);
            }

            resultados.add(dto);
            ultimoDTO = dto;
            current = isMensal ? current.plusMonths(1) : current.plusDays(1);
        }

        return resultados;
    }
    public List<IndiceFinanceiroValorNominalDTO> listaValorNominalIndiceFinanceiro(Long indiceFinanceiroId, LocalDate dataReferenciaInicial, LocalDate dataReferenciaFinal, Boolean mesAnterior) {
        IndiceFinanceiro indice = indiceFinanceiro.getById(indiceFinanceiroId);
        List<IndiceFinanceiroValorNominalDTO> resultados = new ArrayList<>();

        if (mesAnterior) {
            //LocalDate dataReferencia = dataReferenciaInicial.minusDays(1);
            LocalDate dataReferencia = dataReferenciaInicial;
            IndiceFinanceiroValorNominal ultimoValor = obterIndiceFinanceiroValorNominalMaisRecente(indice, dataReferencia);
            if (ultimoValor != null) {
                resultados.add(entityToDTO(ultimoValor));
            }
        } else {

            resultados = listaValorNominalIndiceFinanceiro(indiceFinanceiroId,dataReferenciaInicial,dataReferenciaFinal);

        }

        return resultados;
    }

    private IndiceFinanceiroValorNominalDTO processarMensal(List<IndiceFinanceiroValorNominal> valoresNominais, LocalDate current, IndiceFinanceiro indice) {
        Optional<IndiceFinanceiroValorNominal> valorNominalOpt = valoresNominais.stream()
                .filter(v -> v.getDataReferencia().getYear() == current.getYear() && v.getDataReferencia().getMonth() == current.getMonth())
                .findFirst();

        if (valorNominalOpt.isPresent()) {
            return entityToDTO(valorNominalOpt.get());
        } else {
            IndiceFinanceiroValorNominal ultimoValor = obterIndiceFinanceiroValorNominalMaisRecente(indice, current.minusDays(1));
            return criarDTOParaDataAtual(ultimoValor, current);
        }
    }

    private IndiceFinanceiroValorNominalDTO processarDiario(List<IndiceFinanceiroValorNominal> valoresNominais, LocalDate current, IndiceFinanceiro indice) {
        Optional<IndiceFinanceiroValorNominal> valorNominalOpt = valoresNominais.stream()
                .filter(v -> v.getDataReferencia().isEqual(current))
                .findFirst();

        if (valorNominalOpt.isPresent()) {
            return entityToDTO(valorNominalOpt.get());
        } else {
            IndiceFinanceiroValorNominal ultimoValor = obterIndiceFinanceiroValorNominalMaisRecente(indice, current.minusDays(1));
            return criarDTOParaDataAtual(ultimoValor, current);
        }
    }

    private IndiceFinanceiroValorNominalDTO criarDTOParaDataAtual(IndiceFinanceiroValorNominal ultimoValor, LocalDate current) {
        IndiceFinanceiroValorNominalDTO dto = new IndiceFinanceiroValorNominalDTO();
        dto.setIndiceFinanceiroId(ultimoValor.getIndiceFinanceiro().getId());
        dto.setUtilizado(Boolean.FALSE);
        dto.setDataReferencia(current);
        dto.setValor(ultimoValor != null ? ultimoValor.getValor() : BigDecimal.ZERO);
        return dto;
    }

    @Override
    public IndiceFinanceiroValorNominal dtoToEntity(IndiceFinanceiroValorNominalDTO dto) {
        IndiceFinanceiroValorNominal ob = super.dtoToEntity(dto);
        ob.setDataReferencia(dto.getDataReferencia());
        return ob;
    }

    @Override
    public IndiceFinanceiroValorNominalDTO entityToDTO(IndiceFinanceiroValorNominal ob) {
        IndiceFinanceiroValorNominalDTO dto = super.entityToDTO(ob);
        dto.setDataReferencia(ob.getDataReferencia());
        return dto;
    }
}
