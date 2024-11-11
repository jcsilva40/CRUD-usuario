package com.stfn2.ggas.services;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.*;
import com.stfn2.ggas.domain.dtos.MedidorInstalacaoDTO;
import com.stfn2.ggas.domain.dtos.basic.MedidorInstalacaoBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.MedidorInstalacaoFilterDTO;
import com.stfn2.ggas.domain.enumTypes.CorretorMarcaEnum;
import com.stfn2.ggas.domain.enumTypes.MedidorSituacaoEnum;
import com.stfn2.ggas.domain.enumTypes.PontoConsumoSituacaoEnum;
import com.stfn2.ggas.repositories.MedidorInstalacaoRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class MedidorInstalacaoService extends BaseService<MedidorInstalacao, MedidorInstalacaoDTO, MedidorInstalacaoBasicDTO, MedidorInstalacaoFilterDTO, MedidorInstalacaoRepository> {

    @Autowired
    protected MedidorService serviceMedidor;

    @Autowired
    protected PontoConsumoService servicePontoConsumo;

    @Autowired
    protected MedidorOperacaoHistoricoService serviceMedidorOpHistorico;

    @Autowired
    protected CorretorVazaoService serviceCorretorVazao;

    private boolean retirada;
    private BigDecimal leituraFinal;
    private LocalDate dataRetirada;
    private BigDecimal consumoAFaturar;
    private Long corretorVazaoPadraoId = 1736L;

    @Override
    public MedidorInstalacaoBasicDTO entityToBasicDTO(MedidorInstalacao entity) {
        MedidorInstalacaoBasicDTO dto = super.entityToBasicDTO(entity);
        dto.setMedidorDescricao(entity.getMedidor().getDescricao());
        dto.setPontoConsumoDescricao(entity.getPontoConsumo().getDescricao());
        dto.setPontoConsumoCil(Long.valueOf(entity.getPontoConsumo().getCil()));
        dto.setData(entity.getData());

        return dto;
    }

    @Override
    public MedidorInstalacaoDTO createOrUpdate(MedidorInstalacaoDTO dto) {

        MedidorInstalacao medidorInstalacao = getById(dto.getId());
        if(medidorInstalacao == null){

            medidorInstalacao = new MedidorInstalacao();
            Medidor novoMedidor = serviceMedidor.getById(dto.getNovoMedidorId());
            medidorInstalacao.setMedidor(novoMedidor);

            PontoConsumo p = servicePontoConsumo.getById(dto.getPontoConsumoId());
            medidorInstalacao.setPontoConsumo(p);
            medidorInstalacao.setDataAtivacao(dto.getData());
            medidorInstalacao.setData(dto.getData());
            medidorInstalacao.setLeitura(dto.getLeituraInicial());
            medidorInstalacao.setLeituraAtivacao(dto.getLeituraInicial());
            medidorInstalacao.setSequenciaLeitura(dto.getPontoConsumoSequenciaLeitura());
            if(dto.getFatorCorrecao() != null){
                medidorInstalacao.setFatorCorrecao(dto.getFatorCorrecao());
            }

            if(dto.getCorretorVazaoId() != 0){
                CorretorVazao cova = serviceCorretorVazao.getById(dto.getCorretorVazaoId());
                medidorInstalacao.setCorretorVazao(cova);
            }else{
                CorretorVazao cova = new CorretorVazao();
                cova.setDescricao("CKFAKE"+medidorInstalacao.getPontoConsumo().getCil());
                cova.setCorretorMarcaEnum(CorretorMarcaEnum.COMPAGAS);
                cova.setMedidorSituacaoEnum(MedidorSituacaoEnum.INSTALADO);
                serviceCorretorVazao.save(cova);
                medidorInstalacao.setCorretorVazao(cova);
            }

            medidorInstalacao.setDataCorretorVazao(dto.getData());
            retirada = false;
            super.save(medidorInstalacao);

        }else{
            if (dto.isRetirada()) {
                retirada = true;
                leituraFinal = dto.getLeituraFinal();
                dataRetirada = dto.getDataRetirada();

                Medidor m = serviceMedidor.getById(dto.getMedidorId());
                m.setMedidorSituacaoEnum(MedidorSituacaoEnum.DESMOBILIZADO);

                PontoConsumo pontoConsumo = servicePontoConsumo.getById(dto.getPontoConsumoId());
                pontoConsumo.setMedidorInstalacao(null);
                pontoConsumo.setPontoConsumoSituacao(PontoConsumoSituacaoEnum.AGUARDANDO_ATIVACAO);
                pontoConsumo.setFatorCorrecao(null);
                consumoAFaturar = dto.getConsumoAFaturar();
                medidorInstalacao.setConsumoFinal(consumoAFaturar);
                medidorInstalacao.setCorretorVazao(null);
                medidorInstalacao.setDataCorretorVazao(null);

                super.save(medidorInstalacao);

            }else{
                //é uma troca de medidor - gera um novo registro de instalação

                medidorInstalacao = new MedidorInstalacao();
                consumoAFaturar = dto.getConsumoAFaturar();
                Medidor novoMedidor = serviceMedidor.getById(dto.getNovoMedidorId());
                medidorInstalacao.setMedidor(novoMedidor);

                PontoConsumo p = servicePontoConsumo.getById(dto.getPontoConsumoId());
                medidorInstalacao.setPontoConsumo(p);

                medidorInstalacao.setDataAtivacao(dto.getDataAtivacao());
                medidorInstalacao.setData(dto.getDataAtivacao());
                medidorInstalacao.setLeitura(dto.getLeituraInicial());
                medidorInstalacao.setLeituraAtivacao(dto.getLeituraInicial());
                medidorInstalacao.setSequenciaLeitura(dto.getPontoConsumoSequenciaLeitura());
                if(dto.getFatorCorrecao() != null){
                    medidorInstalacao.setFatorCorrecao(dto.getFatorCorrecao());
                }
                if(dto.getCorretorVazaoId() != null){
                    CorretorVazao cova = serviceCorretorVazao.getById(dto.getCorretorVazaoId());
                    medidorInstalacao.setCorretorVazao(cova);
                }else{
                    CorretorVazao cova = new CorretorVazao();
                    cova.setDescricao("CKFAKE"+medidorInstalacao.getPontoConsumo().getCil());
                    cova.setCorretorMarcaEnum(CorretorMarcaEnum.COMPAGAS);
                    cova.setMedidorSituacaoEnum(MedidorSituacaoEnum.INSTALADO);
                    serviceCorretorVazao.save(cova);
                    medidorInstalacao.setCorretorVazao(cova);
                }

                medidorInstalacao.setDataCorretorVazao(dto.getDataAtivacao());
                retirada = false;
                super.save(medidorInstalacao);

            }

        }

        return entityToDTO(medidorInstalacao);
    }

    @Override
    public MedidorInstalacaoDTO entityToDTO(MedidorInstalacao entity) {

        MedidorInstalacaoDTO dto = super.entityToDTO(entity);
        dto.setId(entity.getId());
        dto.setMedidorId(entity.getMedidor().getId());
        dto.setMedidorDescricao(entity.getMedidor().getDescricao());
        dto.setMedidorModeloDescricao(entity.getMedidor().getMedidorModelo().getDescricao());
        dto.setMedidorMarcaDescricao(entity.getMedidor().getMedidorMarca().getDescricao());
        dto.setPontoConsumoDescricao(entity.getPontoConsumo().getDescricao());
        dto.setPontoConsumoId(entity.getPontoConsumo().getId());
        dto.setPontoConsumoCil(entity.getPontoConsumo().getCil());
        dto.setPontoConsumoSequenciaLeitura(entity.getPontoConsumo().getSequenciaLeitura());
        dto.setLeituraAnterior(entity.getLeitura());

        return dto;
    }

    @Override
    public MedidorInstalacao beforeSave(MedidorInstalacao entity) {

        List<MedidorInstalacao> listaInstalacoes = this.repository.obterInstalacaoPorPontoConsumo(entity.getPontoConsumo().getId());

        for (MedidorInstalacao i : listaInstalacoes) {

            if(i.getHabilitado()){
                i.setHabilitado(false);
                i.setConsumoFinal(consumoAFaturar);
                i.getMedidor().setMedidorSituacaoEnum(MedidorSituacaoEnum.DESMOBILIZADO);
                serviceMedidor.save(i.getMedidor());
            }


        }

        this.repository.saveAll(listaInstalacoes);

        return entity;

    }

    @Override
    public MedidorInstalacao afterSave(MedidorInstalacao entity) {

        Medidor medidor = serviceMedidor.getById(entity.getMedidor().getId());

        if (retirada) {

            entity.setHabilitado(false);
            MedidorOperacaoHistorico medidorOpHistorico = getMedidorOperacaoHistorico(entity);
            serviceMedidorOpHistorico.save(medidorOpHistorico);

            return super.afterSave(entity);
        }

        medidor.setMedidorSituacaoEnum(MedidorSituacaoEnum.INSTALADO);

        PontoConsumo pontoConsumo = servicePontoConsumo.getById(entity.getPontoConsumo().getId());
        pontoConsumo.setMedidorInstalacao(entity);
        pontoConsumo.setPontoConsumoSituacao(PontoConsumoSituacaoEnum.ATIVO);
        //pontoConsumo.setPontoConsumoSituacao(entity.getPontoConsumo().getPontoConsumoSituacao());
        pontoConsumo.setSequenciaLeitura(entity.getSequenciaLeitura());
        pontoConsumo.setNumeroSequencia(entity.getSequenciaLeitura());
        servicePontoConsumo.save(pontoConsumo);

        MedidorOperacaoHistorico medidorOpHistorico = getMedidorOperacaoHistorico(entity);
        serviceMedidorOpHistorico.save(medidorOpHistorico);

        MedidorOperacaoHistorico medidorOpHistoricoAtivacao = getMedidorOperacaoHistoricoAtivacao(entity);
        serviceMedidorOpHistorico.save(medidorOpHistoricoAtivacao);

        return super.afterSave(entity);

    }

    private @NotNull MedidorOperacaoHistorico getMedidorOperacaoHistorico(MedidorInstalacao entity) {
        String instalacaoMedidor = "Instalação";
        String retiradaMedidor = "Retirada";

        Long medidorOperacaoInstalacao = 1L;
        Long medidorOperacaoRetirada = 5L;
        Long medidorMotivoOperacaoInstalacao = 4L;
        Long medidorMotivoOperacaoRetirada = 2L;
        Long funcionarioId = 43L;

        MedidorOperacaoHistorico medidorOpHistorico = new MedidorOperacaoHistorico();

        medidorOpHistorico.setMedidor(entity.getMedidor());
        medidorOpHistorico.setPontoConsumo(entity.getPontoConsumo());
        medidorOpHistorico.setMedidorInstalacao(entity);
        medidorOpHistorico.setNumeroLeitura(entity.getLeituraAtivacao());

        if (retirada) {
            medidorOpHistorico.setMedidorOperacaoId(medidorOperacaoRetirada);
            medidorOpHistorico.setDescricaoHistoricoOperacoes(retiradaMedidor);
            medidorOpHistorico.setDataPlanejada(dataRetirada);
            medidorOpHistorico.setDataRealizada(dataRetirada);
            medidorOpHistorico.setMedidorMotivoOperacaoId(medidorMotivoOperacaoRetirada);
            medidorOpHistorico.setFuncionarioId(funcionarioId);
            medidorOpHistorico.setNumeroLeitura(leituraFinal);
            return medidorOpHistorico;
        }

        medidorOpHistorico.setMedidorOperacaoId(medidorOperacaoInstalacao);
        medidorOpHistorico.setDescricaoHistoricoOperacoes(instalacaoMedidor);
        medidorOpHistorico.setDataPlanejada(entity.getData());
        medidorOpHistorico.setDataRealizada(entity.getData());
        medidorOpHistorico.setMedidorMotivoOperacaoId(medidorMotivoOperacaoInstalacao);
        medidorOpHistorico.setFuncionarioId(funcionarioId);
        return medidorOpHistorico;
    }

    private @NotNull MedidorOperacaoHistorico getMedidorOperacaoHistoricoAtivacao(MedidorInstalacao entity) {

        String instalacaoMedidor = "Ativação";
        Long medidorOperacaoInstalacao = 7L;
        Long funcionarioId = 43L;

        MedidorOperacaoHistorico medidorOpHistoricoAtivacao = new MedidorOperacaoHistorico();

        medidorOpHistoricoAtivacao.setMedidor(entity.getMedidor());
        medidorOpHistoricoAtivacao.setPontoConsumo(entity.getPontoConsumo());
        medidorOpHistoricoAtivacao.setMedidorInstalacao(entity);
        medidorOpHistoricoAtivacao.setMedidorOperacaoId(medidorOperacaoInstalacao);
        medidorOpHistoricoAtivacao.setDescricaoHistoricoOperacoes(instalacaoMedidor);
        medidorOpHistoricoAtivacao.setDataPlanejada(entity.getData());
        medidorOpHistoricoAtivacao.setDataRealizada(entity.getData());
        medidorOpHistoricoAtivacao.setFuncionarioId(funcionarioId);
        medidorOpHistoricoAtivacao.setNumeroLeitura(entity.getLeituraAtivacao());

        return medidorOpHistoricoAtivacao;
        }

}