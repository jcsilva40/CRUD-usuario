package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.Recurso;
import com.stfn2.ggas.domain.dtos.filter.RecursoFilterDTO;

@Repository
public interface RecursoRepository extends BaseRepository<Recurso, RecursoFilterDTO> {

}