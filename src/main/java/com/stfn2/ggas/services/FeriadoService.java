package com.stfn2.ggas.services;

import com.stfn2.ggas.config.exceptions.types.NegocioException;
import com.stfn2.ggas.config.exceptions.types.ObjectNotFoundException;
import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.Feriado;
import com.stfn2.ggas.domain.dtos.FeriadoDTO;
import com.stfn2.ggas.domain.dtos.basic.FeriadoBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.FeriadoFilterDTO;
import com.stfn2.ggas.repositories.FeriadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class FeriadoService extends BaseService<Feriado, FeriadoDTO, FeriadoBasicDTO, FeriadoFilterDTO, FeriadoRepository> {

    @Autowired
    private FeriadoRepository feriadoRepository;

    @Override
    public void validDto(FeriadoDTO dto) {
        if (isNull(dto.getDataFeriado())) addErro("dataFeriado", "Data do feriado é obrigatório!");
        if(!hasContent(dto.getDescricao())) addErro("descricao", "Nome/Descrição do feriado é obrigatório!");
        if(dto.getFeriadoMunicipal()){
            if(isNull(dto.getMunicipioId())){
                addErro("municipioId", "Quando feriado é municipal, municipio é obrigatório");
            }
        }
    }

    public List<FeriadoDTO> gerarListaFeriadosRecorrentes(FeriadoDTO obj) {
        List<FeriadoDTO> listaFeriados = new ArrayList<>();
        LocalDate dataBase = obj.getDataFeriado();
        String descricao = obj.getDescricao();
        boolean isFeriadoMunicipal = obj.getFeriadoMunicipal();
        boolean sempreMesmoDia = obj.getSempreMesmoDia();
        Long municipioId = obj.getMunicipioId();



        for (int i = 0; i < obj.getAnosFeriado(); i++) {
            LocalDate newDate = dataBase.plusYears(i);

            FeriadoDTO novoFeriado = new FeriadoDTO();
            feriadoRepository.findByDataFeriadoAndDescricao(newDate, descricao)
                    .ifPresent(feriado -> novoFeriado.setId(feriado.getId()));

            novoFeriado.setDescricao(descricao);
            novoFeriado.setDataFeriado(newDate);
            novoFeriado.setFeriadoMunicipal(isFeriadoMunicipal);
            novoFeriado.setSempreMesmoDia(sempreMesmoDia);

            if (isFeriadoMunicipal) {
                novoFeriado.setMunicipioId(municipioId);
            }

            listaFeriados.add(novoFeriado);
        }

        return listaFeriados;
    }


    public void deleteFeriadoRecorrente( Long id,  Long anosFeriado) {

        Feriado feriado = feriadoRepository.findById(id).orElseThrow();

        LocalDate dataBase = feriado.getDataFeriado();
        for (int i = 0; i < anosFeriado; i++) {
            LocalDate newDate = dataBase.plusYears(i);
            Optional<Feriado> feriadoExistente = feriadoRepository.findByDataFeriadoAndDescricao(newDate, feriado.getDescricao());

            feriadoExistente.ifPresent(feriadoDeletar -> this.delete(feriadoDeletar.getId()));

        }

    }

    @Override
    public FeriadoDTO findById(Long id) {
        Feriado entity = repository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException(id.toString()));
        FeriadoDTO dto = entityToDTO(entity);
        Integer anosFeriado = feriadoRepository.countAnosFeriadoAfterData(dto.getDescricao(), dto.getDataFeriado());
        dto.setAnosFeriado(anosFeriado );
        return dto;
    }

    public boolean isDiaUtil(LocalDate data, Long idMunicipio) throws NegocioException {

        boolean retorno = true;
        Calendar calendar = Calendar.getInstance();

        if(data.getDayOfWeek() == DayOfWeek.SATURDAY || data.getDayOfWeek() == DayOfWeek.SUNDAY){
            retorno = false;
        }

        if(retorno) {
            Long resultado = repository.isDiaUtil(data); //(Long) query.uniqueResult();
            if(resultado != null && resultado > 0) {
                retorno = false;
            }
        }

        return retorno;
    }

    public LocalDate obterProximoDiaUtil(LocalDate data) throws NegocioException {

        LocalDate proximaDataUtil = data;
        while(!this.isDiaUtil(proximaDataUtil, null)) {
            proximaDataUtil = proximaDataUtil.plusDays(1);
        }

        return proximaDataUtil;
    }

}