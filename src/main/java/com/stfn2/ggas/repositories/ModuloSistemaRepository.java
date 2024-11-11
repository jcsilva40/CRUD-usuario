package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.ModuloSistema;
import com.stfn2.ggas.domain.dtos.filter.ModuloSistemaFilterDTO;

@Repository
public interface ModuloSistemaRepository extends BaseRepository<ModuloSistema, ModuloSistemaFilterDTO> {

}