package com.stfn2.ggas.services;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.EntidadeConteudo;
import com.stfn2.ggas.domain.Segmento;
import com.stfn2.ggas.domain.Unidade;
import com.stfn2.ggas.domain.dtos.PressaoFornecimentoFaixaDTO;
import com.stfn2.ggas.domain.dtos.RamoAtividadeSubstituicaoTributariaDTO;
import com.stfn2.ggas.domain.dtos.SegmentoDTO;
import com.stfn2.ggas.domain.dtos.basic.SegmentoBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.SegmentoFilterDTO;
import com.stfn2.ggas.repositories.SegmentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class SegmentoService extends BaseService<Segmento, SegmentoDTO, SegmentoBasicDTO,
        SegmentoFilterDTO, SegmentoRepository> {

    @Autowired
    protected RamoAtividadeSubstituicaoTributariaService serviceRaSubsTrib;

    @Autowired
    protected PressaoFornecimentoFaixaService pressaoFornecimentoFaixaService;

    private Long tipoSubstituicao;
    private Long valorSubstituto;
    private BigDecimal percentualSubstituto;
    private LocalDate dataInicioVigencia;
    private LocalDate dataFimVigencia;

    private BigDecimal medidaMinimo;
    private BigDecimal medidaMaximo;
    private BigDecimal numeroFatorZ;
    private BigDecimal numeroFatorCorrecaoPTZPCS;

    @Override
    public void validDto(SegmentoDTO dto) {

        if (!dto.getRamoAtividadeSubsTrib().isEmpty()) {
            if(!dto.getRamosAtividade().isEmpty()) {
                tipoSubstituicao = dto.getRamoAtividadeSubsTrib().getFirst().getTipoSubstituicao();
                isNull(valorSubstituto = dto.getRamoAtividadeSubsTrib().getFirst().getValorSubstituto());
                isNull(percentualSubstituto = dto.getRamoAtividadeSubsTrib().getFirst().getPercentualSubstituto());
                isNull(dataInicioVigencia = dto.getRamoAtividadeSubsTrib().getFirst().getDataInicioVigencia());
                isNull(dataFimVigencia = dto.getRamoAtividadeSubsTrib().getFirst().getDataFimVigencia());
            }else{
                addErro("","Para inserir uma substituição tributária, é necessário inserir um Ramo de Atividade");
            }

        }

        if(!dto.getPressaoFornecimentoFaixa().isEmpty()) {
            medidaMinimo = dto.getPressaoFornecimentoFaixa().getFirst().getMedidaMinimo();
            medidaMaximo = dto.getPressaoFornecimentoFaixa().getFirst().getMedidaMaximo();
            numeroFatorZ = dto.getPressaoFornecimentoFaixa().getFirst().getNumeroFatorZ();
            numeroFatorCorrecaoPTZPCS = dto.getPressaoFornecimentoFaixa().getFirst().getNumeroFatorCorrecaoPTZPCS();

        }
        super.validDto(dto);
    }

    @Override
    public Segmento beforeSave(Segmento entity) {
        entity.getRamosAtividade().forEach(item -> item.setSegmento(entity));
        return entity;
    }

    @Override
    public Segmento afterSave(Segmento entity){

        if(tipoSubstituicao != null){
            RamoAtividadeSubstituicaoTributariaDTO raSubsTrib = new RamoAtividadeSubstituicaoTributariaDTO();
            raSubsTrib.setId(entity.getRamosAtividade().getFirst().getId());
            raSubsTrib.setIdRamoAtividade(entity.getRamosAtividade().getFirst().getId());
            raSubsTrib.setTipoSubstituicao(tipoSubstituicao);
            raSubsTrib.setPercentualSubstituto(percentualSubstituto);
            raSubsTrib.setValorSubstituto(valorSubstituto);
            raSubsTrib.setDataInicioVigencia(dataInicioVigencia);
            raSubsTrib.setDataFimVigencia(dataFimVigencia);
            serviceRaSubsTrib.createOrUpdate(raSubsTrib);
        }

        if(medidaMinimo != null){

            Unidade unidadePressao = new Unidade();
            Long unidadePadrao = 42L;
            unidadePressao.setId(unidadePadrao);

            EntidadeConteudo classePressao = new EntidadeConteudo();
            Long classePressaoPadrao = 407L;
            classePressao.setId(classePressaoPadrao);

            EntidadeConteudo indicadorCorrecaoPT = new EntidadeConteudo();
            Long indicadorCorrecaoPTPadrao = 543L;
            indicadorCorrecaoPT.setId(indicadorCorrecaoPTPadrao);

            EntidadeConteudo indicadorCorrecaoZ = new EntidadeConteudo();
            Long indicadorCorrecaoZPadrao = 546L;
            indicadorCorrecaoZ.setId(indicadorCorrecaoZPadrao);

            PressaoFornecimentoFaixaDTO pressaoFornecimentoFaixaDTO = new PressaoFornecimentoFaixaDTO();
            pressaoFornecimentoFaixaDTO.setMedidaMinimo(medidaMinimo);
            pressaoFornecimentoFaixaDTO.setMedidaMaximo(medidaMaximo);
            pressaoFornecimentoFaixaDTO.setNumeroFatorZ(numeroFatorZ);
            pressaoFornecimentoFaixaDTO.setNumeroFatorCorrecaoPTZPCS(numeroFatorCorrecaoPTZPCS);
            pressaoFornecimentoFaixaDTO.setUnidadePressao(unidadePressao);
            pressaoFornecimentoFaixaDTO.setClassePressao(classePressao);
            pressaoFornecimentoFaixaDTO.setIndicadorCorrecaoPT(indicadorCorrecaoPT);
            pressaoFornecimentoFaixaDTO.setIndicadorCorrecaoZ(indicadorCorrecaoZ);
            pressaoFornecimentoFaixaDTO.setSegmento(entity);

            pressaoFornecimentoFaixaService.createOrUpdate(pressaoFornecimentoFaixaDTO);

        }
        return super.afterSave(entity);
    }

}

