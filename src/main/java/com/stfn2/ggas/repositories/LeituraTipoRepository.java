package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.LeituraTipo;
import com.stfn2.ggas.domain.dtos.filter.LeituraTipoFilterDTO;

@Repository
public interface LeituraTipoRepository extends BaseRepository<LeituraTipo, LeituraTipoFilterDTO> {

}
