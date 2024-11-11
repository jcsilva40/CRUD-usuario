package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.AtividadeSistema;
import com.stfn2.ggas.domain.dtos.filter.AtividadeSistemaFilterDTO;

@Repository
public interface AtividadeSistemaRepository extends BaseRepository<AtividadeSistema, AtividadeSistemaFilterDTO> {

}