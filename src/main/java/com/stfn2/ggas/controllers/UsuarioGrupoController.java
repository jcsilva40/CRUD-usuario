package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.UsuarioGrupo;
import com.stfn2.ggas.domain.dtos.UsuarioGrupoDTO;
import com.stfn2.ggas.domain.dtos.basic.UsuarioGrupoBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.UsuarioGrupoFilterDTO;
import com.stfn2.ggas.repositories.UsuarioGrupoRepository;
import com.stfn2.ggas.services.UsuarioGrupoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/usuarioGrupo")
@Tag(name="UsuarioGrupo", description="EndPoints para gerenciamento de UsuarioGrupo")
public class UsuarioGrupoController extends BaseController<UsuarioGrupo, UsuarioGrupoDTO, UsuarioGrupoBasicDTO, UsuarioGrupoFilterDTO,
		UsuarioGrupoRepository,UsuarioGrupoService> {
}