package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.SegmentoPai;
import com.stfn2.ggas.domain.dtos.SegmentoPaiDTO;
import com.stfn2.ggas.domain.dtos.basic.SegmentoPaiBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.SegmentoPaiFilterDTO;
import com.stfn2.ggas.domain.enumTypes.TipoInfoCadeEnum;
import com.stfn2.ggas.repositories.SegmentoPaiRepository;
import java.util.Objects;

@Service
public class SegmentoPaiService extends BaseService<SegmentoPai, SegmentoPaiDTO, SegmentoPaiBasicDTO, SegmentoPaiFilterDTO, SegmentoPaiRepository> {

    @Override
    public SegmentoPaiDTO entityToDTO(SegmentoPai segmentoPai){
        SegmentoPaiDTO segmentoPaiDTO;
        segmentoPaiDTO = super.entityToDTO(segmentoPai);        
        if(!Objects.isNull(segmentoPai.getTipoInfoCade())){
            Long tipoInfoCadeId = segmentoPai.getTipoInfoCade().getId();
            segmentoPaiDTO.setTipoInfoCadeId(tipoInfoCadeId);
        }
        return segmentoPaiDTO;
    }
    
    @Override
    public SegmentoPai dtoToEntity(SegmentoPaiDTO segmentoPaiDTO){
        SegmentoPai segmentoPai;
        segmentoPai = super.dtoToEntity(segmentoPaiDTO);        
        if(!Objects.isNull(segmentoPaiDTO.getTipoInfoCadeId())){
            TipoInfoCadeEnum tipoInfoCade = TipoInfoCadeEnum.toEnum(segmentoPaiDTO.getTipoInfoCadeId());
            segmentoPai.setTipoInfoCade(tipoInfoCade);
        }
        return segmentoPai;
    }
}