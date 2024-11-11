package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.Funcionario;
import com.stfn2.ggas.domain.dtos.FuncionarioDTO;
import com.stfn2.ggas.domain.dtos.filter.FuncionarioFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.FuncionarioBasicDTO;
import com.stfn2.ggas.repositories.FuncionarioRepository;
import com.stfn2.ggas.services.FuncionarioService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/funcionario")
@Tag(name="Funcionario", description="EndPoints para gerenciamento de Funcionario")
public class FuncionarioController extends BaseController<Funcionario, FuncionarioDTO, FuncionarioBasicDTO, FuncionarioFilterDTO,
		FuncionarioRepository, FuncionarioService> {
}

