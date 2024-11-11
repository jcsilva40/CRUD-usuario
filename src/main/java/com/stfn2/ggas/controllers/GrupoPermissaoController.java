package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.GrupoPermissao;
import com.stfn2.ggas.domain.dtos.GrupoPermissaoDTO;
import com.stfn2.ggas.domain.dtos.basic.GrupoPermissaoBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.GrupoPermissaoFilterDTO;
import com.stfn2.ggas.repositories.GrupoPermissaoRepository;
import com.stfn2.ggas.services.GrupoPermissaoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/grupoPermissao")
@Tag(name="GrupoPermissao", description="EndPoints para gerenciamento de GrupoPermissao")
public class GrupoPermissaoController extends BaseController<GrupoPermissao, GrupoPermissaoDTO, GrupoPermissaoBasicDTO, GrupoPermissaoFilterDTO,
		GrupoPermissaoRepository,GrupoPermissaoService> {
}