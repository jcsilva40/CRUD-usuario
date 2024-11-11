package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.Grupo;
import com.stfn2.ggas.domain.dtos.filter.GrupoFilterDTO;

@Repository
public interface GrupoRepository extends BaseRepository<Grupo, GrupoFilterDTO> {

}