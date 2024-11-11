package com.stfn2.ggas.services;

import com.stfn2.ggas.domain.enumTypes.CorretorMarcaEnum;
import com.stfn2.ggas.domain.enumTypes.MedidorSituacaoEnum;
import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.CorretorVazao;
import com.stfn2.ggas.domain.dtos.CorretorVazaoDTO;
import com.stfn2.ggas.domain.dtos.filter.CorretorVazaoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.CorretorVazaoBasicDTO;
import com.stfn2.ggas.repositories.CorretorVazaoRepository;

@Service
public class CorretorVazaoService extends BaseService<CorretorVazao, CorretorVazaoDTO, CorretorVazaoBasicDTO, CorretorVazaoFilterDTO, CorretorVazaoRepository> {

    @Override
    public CorretorVazaoDTO createOrUpdate(CorretorVazaoDTO dto) {
        CorretorVazao corretorVazao = getById(dto.getId());
        if(corretorVazao == null){
            corretorVazao = new CorretorVazao();
            corretorVazao.setDescricao(dto.getNumeroSerie());
            corretorVazao.setCorretorMarcaEnum(CorretorMarcaEnum.COMPAGAS);
            corretorVazao.setMedidorSituacaoEnum(MedidorSituacaoEnum.INSTALADO);
            super.save(corretorVazao);
        }else{
            corretorVazao.setDescricao(dto.getNumeroSerie());
            corretorVazao.setCorretorMarcaEnum(CorretorMarcaEnum.COMPAGAS);
            corretorVazao.setMedidorSituacaoEnum(MedidorSituacaoEnum.INSTALADO);
            super.save(corretorVazao);
        }

        return entityToDTO(corretorVazao);
    }
}