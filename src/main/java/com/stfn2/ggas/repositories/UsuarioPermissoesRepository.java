package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.UsuarioPermissoes;
import com.stfn2.ggas.domain.dtos.filter.UsuarioPermissoesFilterDTO;

@Repository
public interface UsuarioPermissoesRepository extends BaseRepository<UsuarioPermissoes, UsuarioPermissoesFilterDTO> {

}