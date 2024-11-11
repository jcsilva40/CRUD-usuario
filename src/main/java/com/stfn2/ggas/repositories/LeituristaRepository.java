package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.Leiturista;
import com.stfn2.ggas.domain.dtos.filter.LeituristaFilterDTO;

@Repository
public interface LeituristaRepository extends BaseRepository<Leiturista, LeituristaFilterDTO> {

}
