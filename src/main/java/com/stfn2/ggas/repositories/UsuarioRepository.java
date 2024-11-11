package com.stfn2.ggas.repositories;

import com.stfn2.ggas.config.security.domain.User;
import com.stfn2.ggas.config.security.domain.dto.UsuarioFilterDTO;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends BaseRepository<User, UsuarioFilterDTO> {

}