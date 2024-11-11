package com.stfn2.ggas.services;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.CampanhaDescontoVincular;
import com.stfn2.ggas.domain.LogCampanhaDesconto;
import com.stfn2.ggas.domain.PontoConsumo;
import com.stfn2.ggas.domain.dtos.CampanhaDescontoVincularDTO;
import com.stfn2.ggas.domain.dtos.basic.CampanhaDescontoVincularBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.CampanhaDescontoVincularFilterDTO;
import com.stfn2.ggas.domain.enumTypes.ModalidadeMedicaoEnum;
import com.stfn2.ggas.domain.enumTypes.StatusCampanhaEnum;
import com.stfn2.ggas.repositories.CampanhaDescontoVincularRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CampanhaDescontoVincularService extends BaseService<CampanhaDescontoVincular, CampanhaDescontoVincularDTO, CampanhaDescontoVincularBasicDTO, CampanhaDescontoVincularFilterDTO, CampanhaDescontoVincularRepository> {
    
    @Autowired
    private LogCampanhaDescontoService logCampanhaDescontoService;
    
    @Override
    public Page<CampanhaDescontoVincularBasicDTO> findAll(CampanhaDescontoVincularFilterDTO filter, Pageable pageable) {  
        String descricao = filter.getDescricao();        
        Long segmentoPaiId = filter.getSegmentoPaiId();                    
        Long modalidadeMedicao = filter.getModalidadeMedicao();
        ModalidadeMedicaoEnum modalidadeMedicaoEnum = null;
        if(!Objects.isNull(modalidadeMedicao)){
            modalidadeMedicaoEnum = ModalidadeMedicaoEnum.toEnum(modalidadeMedicao);
        }          
        Boolean habilitado = filter.getHabilitado();
        Page<CampanhaDescontoVincularBasicDTO> result = this.repository.findAll(descricao, segmentoPaiId, modalidadeMedicaoEnum, habilitado, pageable).map(projection -> new CampanhaDescontoVincularBasicDTO(projection));
        return result;
    }
    
    @Override
    public CampanhaDescontoVincularDTO entityToDTO(CampanhaDescontoVincular campanhaDescontoVincular) {
        CampanhaDescontoVincularDTO campanhaDescontoVincularDTO = super.entityToDTO(campanhaDescontoVincular);
        if(!Objects.isNull(campanhaDescontoVincular.getCampanhaDesconto())){
            campanhaDescontoVincularDTO.setCampanhaDescontoId(campanhaDescontoVincular.getCampanhaDesconto().getId());
        }
        if(!Objects.isNull(campanhaDescontoVincular.getContrato())){
            campanhaDescontoVincularDTO.setContratoId(campanhaDescontoVincular.getContrato().getId());
        }
        if(!Objects.isNull(campanhaDescontoVincular.getImovel())){
            campanhaDescontoVincularDTO.setImovelId(campanhaDescontoVincular.getImovel().getId());
        }
        if(!Objects.isNull(campanhaDescontoVincular.getSolicitante())){
            campanhaDescontoVincularDTO.setSolicitanteId(campanhaDescontoVincular.getSolicitante().getId());
        }
        if(!Objects.isNull(campanhaDescontoVincular.getAprovador())){
            campanhaDescontoVincularDTO.setAprovadorId(campanhaDescontoVincular.getAprovador().getId());
        }
        return campanhaDescontoVincularDTO;
    }    
    
    @Override
    public CampanhaDescontoVincularDTO createOrUpdate(CampanhaDescontoVincularDTO campanhaDescontoVincularDTO) {
        
        CampanhaDescontoVincular campanhaDescontoVincular = new CampanhaDescontoVincular();
        logCampanhaDescontoService.save(montarLogCampanhaDesconto(campanhaDescontoVincular));
        return null;
    }
    
    private LogCampanhaDesconto montarLogCampanhaDesconto(CampanhaDescontoVincular campanhaDescontoVincular){
        LogCampanhaDesconto logCampanhaDesconto = new LogCampanhaDesconto();
        return logCampanhaDesconto;
    }
    
    @Override
    public CampanhaDescontoVincular dtoToEntity(CampanhaDescontoVincularDTO campanhaDescontoVincularDTO) {
        CampanhaDescontoVincular campanhaDescontoVincular = super.dtoToEntity(campanhaDescontoVincularDTO);
        return campanhaDescontoVincular;
    }

    public CampanhaDescontoVincular obterDescontoAtivoPorPontoConsumo(PontoConsumo pontoConsumo, Integer anoMes,
                                                                      Integer ciclo){
        CampanhaDescontoVincular CDV = new CampanhaDescontoVincular();

        CDV = repository.obterDescontoAtivoPorPontoConsumo(pontoConsumo.getId(), anoMes, ciclo,
                StatusCampanhaEnum.ID_ENT_CONT_APROVADO, StatusCampanhaEnum.ID_ENT_CONT_PEND_ENCERRAMENTO,
                StatusCampanhaEnum.ID_ENT_CONT_ENCERRADO);

        return CDV;
    }


}