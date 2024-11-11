package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.MedidorCapacidade;
import com.stfn2.ggas.domain.dtos.filter.MedidorCapacidadeFilterDTO;

@Repository
public interface MedidorCapacidadeRepository extends BaseRepository<MedidorCapacidade, MedidorCapacidadeFilterDTO> {

}