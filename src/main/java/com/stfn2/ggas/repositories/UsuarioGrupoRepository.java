package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.UsuarioGrupo;
import com.stfn2.ggas.domain.dtos.filter.UsuarioGrupoFilterDTO;

@Repository
public interface UsuarioGrupoRepository extends BaseRepository<UsuarioGrupo, UsuarioGrupoFilterDTO> {

}