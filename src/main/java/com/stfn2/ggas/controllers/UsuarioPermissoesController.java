package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.UsuarioPermissoes;
import com.stfn2.ggas.domain.dtos.UsuarioPermissoesDTO;
import com.stfn2.ggas.domain.dtos.basic.UsuarioPermissoesBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.UsuarioPermissoesFilterDTO;
import com.stfn2.ggas.repositories.UsuarioPermissoesRepository;
import com.stfn2.ggas.services.UsuarioPermissoesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/usuarioPermissoes")
@Tag(name="UsuarioPermissoes", description="EndPoints para gerenciamento de UsuarioPermissoes")
public class UsuarioPermissoesController extends BaseController<UsuarioPermissoes, UsuarioPermissoesDTO, UsuarioPermissoesBasicDTO, UsuarioPermissoesFilterDTO,
		UsuarioPermissoesRepository,UsuarioPermissoesService> {
}