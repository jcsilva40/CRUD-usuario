package com.stfn2.ggas.controllers;

import com.stfn2.ggas.config.security.domain.User;
import com.stfn2.ggas.config.security.domain.dto.UserBasicDTO;
import com.stfn2.ggas.config.security.domain.dto.UserDTO;
import com.stfn2.ggas.config.security.domain.dto.UsuarioFilterDTO;
import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.repositories.UsuarioRepository;
import com.stfn2.ggas.services.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/usuarios")
@Tag(name="Usuário", description="EndPoints para gerenciamento de Usuarios")
public class UsuarioController extends BaseController<User, UserDTO,
        UserBasicDTO, UsuarioFilterDTO,
        UsuarioRepository, UsuarioService> {
}