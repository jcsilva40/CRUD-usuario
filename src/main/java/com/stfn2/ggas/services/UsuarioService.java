package com.stfn2.ggas.services;

import com.stfn2.ggas.config.security.domain.User;
import com.stfn2.ggas.config.security.domain.dto.UserBasicDTO;
import com.stfn2.ggas.config.security.domain.dto.UserDTO;
import com.stfn2.ggas.config.security.domain.dto.UsuarioFilterDTO;
import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService extends BaseService<User, UserDTO, UserBasicDTO, UsuarioFilterDTO,
        UsuarioRepository> {
}