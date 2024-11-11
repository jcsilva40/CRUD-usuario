package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.Municipio;
import com.stfn2.ggas.domain.dtos.filter.MunicipioFilterDTO;

import java.util.Optional;

@Repository
public interface MunicipioRepository extends BaseRepository<Municipio, MunicipioFilterDTO> {

    Optional<Municipio> findByCodIbge(Integer codIbge);

}
