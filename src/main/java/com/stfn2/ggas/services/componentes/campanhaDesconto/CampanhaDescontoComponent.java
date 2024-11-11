package com.stfn2.ggas.services.componentes.campanhaDesconto;

import com.stfn2.ggas.domain.*;
import com.stfn2.ggas.domain.dtos.SegmentoPaiDTO;
import com.stfn2.ggas.repositories.CampanhaDescontoRepository;
import com.stfn2.ggas.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CampanhaDescontoComponent {

   @Autowired
   private SegmentoPaiService segmentoPaiService;
   
   @Autowired
   private CampanhaDescontoRepository campanhaDescontoRepository;
      
   public SegmentoPaiDTO findSegmentoPaiPorCampanhaDesconto(Long campanhaDescontoId){
        SegmentoPai segmentoPai = campanhaDescontoRepository.findSegmentoPaiPorCampanhaDesconto(campanhaDescontoId);
        SegmentoPaiDTO segmentoPaiDTO = segmentoPaiService.entityToDTO(segmentoPai);
        return segmentoPaiDTO;
    }
}
