package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.Profissao;
import com.stfn2.ggas.domain.dtos.ProfissaoDTO;
import com.stfn2.ggas.domain.dtos.basic.ProfissaoBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.ProfissaoFilterDTO;
import com.stfn2.ggas.repositories.ProfissaoRepository;
import com.stfn2.ggas.services.ProfissaoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/profissao")
@Tag(name="Profissao", description="EndPoints para gerenciamento de Profissões")
public class ProfissaoController extends BaseController<Profissao, ProfissaoDTO, ProfissaoBasicDTO,
		ProfissaoFilterDTO, ProfissaoRepository, ProfissaoService> {

}