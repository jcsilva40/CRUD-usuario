package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.dtos.filter.PeriodicidadeFilterDTO;
import com.stfn2.ggas.domain.Periodicidade;
import com.stfn2.ggas.domain.dtos.PeriodicidadeDTO;
import com.stfn2.ggas.domain.dtos.basic.PeriodicidadeBasicDTO;
import com.stfn2.ggas.repositories.PeriodicidadeRepository;
import org.springframework.data.repository.query.Param;

@Service
public class PeriodicidadeService extends BaseService<Periodicidade, PeriodicidadeDTO, PeriodicidadeBasicDTO,
        PeriodicidadeFilterDTO, PeriodicidadeRepository> {
    
    public Long periodicidadePorPontoConsumo(@Param("pontoConsumoId") Long pontoConsumoId){
        return this.repository.periodicidadePorPontoConsumo(pontoConsumoId);
    }

}
