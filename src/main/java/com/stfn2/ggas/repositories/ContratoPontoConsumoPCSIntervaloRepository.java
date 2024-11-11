package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.ContratoPontoConsumoPCSIntervalo;
import com.stfn2.ggas.domain.dtos.filter.ContratoPontoConsumoPCSIntervaloFilterDTO;

@Repository
public interface ContratoPontoConsumoPCSIntervaloRepository extends BaseRepository<ContratoPontoConsumoPCSIntervalo, ContratoPontoConsumoPCSIntervaloFilterDTO> {

}