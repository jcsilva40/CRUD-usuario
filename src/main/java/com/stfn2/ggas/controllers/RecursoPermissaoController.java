package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.RecursoPermissao;
import com.stfn2.ggas.domain.dtos.RecursoPermissaoDTO;
import com.stfn2.ggas.domain.dtos.basic.RecursoPermissaoBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.RecursoPermissaoFilterDTO;
import com.stfn2.ggas.repositories.RecursoPermissaoRepository;
import com.stfn2.ggas.services.RecursoPermissaoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/recursoPermissao")
@Tag(name="RecursoPermissao", description="EndPoints para gerenciamento de RecursoPermissao")
public class RecursoPermissaoController extends BaseController<RecursoPermissao, RecursoPermissaoDTO, RecursoPermissaoBasicDTO, RecursoPermissaoFilterDTO,
		RecursoPermissaoRepository,RecursoPermissaoService> {
}