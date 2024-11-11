package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.ArrecadadorContrato;
import com.stfn2.ggas.domain.dtos.filter.ArrecadadorContratoFilterDTO;

@Repository
public interface ArrecadadorContratoRepository extends BaseRepository<ArrecadadorContrato, ArrecadadorContratoFilterDTO> {

}