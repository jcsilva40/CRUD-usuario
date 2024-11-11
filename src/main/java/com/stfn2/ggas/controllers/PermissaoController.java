package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.Permissao;
import com.stfn2.ggas.domain.dtos.PermissaoDTO;
import com.stfn2.ggas.domain.dtos.basic.PermissaoBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.PermissaoFilterDTO;
import com.stfn2.ggas.repositories.PermissaoRepository;
import com.stfn2.ggas.services.PermissaoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/permissao")
@Tag(name="Permissao", description="EndPoints para gerenciamento de Permissao")
public class PermissaoController extends BaseController<Permissao, PermissaoDTO, PermissaoBasicDTO, PermissaoFilterDTO,
		PermissaoRepository,PermissaoService> {
}