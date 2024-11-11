package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.Pais;
import com.stfn2.ggas.domain.dtos.filter.PaisFilterDTO;

@Repository
public interface PaisRepository extends BaseRepository<Pais, PaisFilterDTO> {

}
