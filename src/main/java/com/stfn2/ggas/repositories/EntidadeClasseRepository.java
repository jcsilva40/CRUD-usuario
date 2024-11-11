package com.stfn2.ggas.repositories;

import com.stfn2.ggas.domain.dtos.filter.EntidadeClasseFilterDTO;
import org.springframework.stereotype.Repository;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.EntidadeClasse;

@Repository
public interface EntidadeClasseRepository extends BaseRepository<EntidadeClasse, EntidadeClasseFilterDTO> {

}
