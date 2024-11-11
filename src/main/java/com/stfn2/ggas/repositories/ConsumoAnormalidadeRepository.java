package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.ConsumoAnormalidade;
import com.stfn2.ggas.domain.dtos.filter.ConsumoAnormalidadeFilterDTO;

@Repository
public interface ConsumoAnormalidadeRepository extends BaseRepository<ConsumoAnormalidade, ConsumoAnormalidadeFilterDTO> {

}