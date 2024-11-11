package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.BaseCep;
import com.stfn2.ggas.domain.dtos.filter.BaseCepFilterDTO;

import java.util.Optional;

@Repository
public interface BaseCepRepository extends BaseRepository<BaseCep, BaseCepFilterDTO> {

    Optional<BaseCep> findByCep(String cep);
}

