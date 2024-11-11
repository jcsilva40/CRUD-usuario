package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.UnidadeFederacao;
import com.stfn2.ggas.domain.dtos.UnidadeFederacaoDTO;
import com.stfn2.ggas.domain.dtos.filter.UnidadeFederacaoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.UnidadeFederacaoBasicDTO;
import com.stfn2.ggas.repositories.UnidadeFederacaoRepository;
import com.stfn2.ggas.services.UnidadeFederacaoService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/ufs")
@Tag(name="UnidadeFederacao", description="EndPoints para gerenciamento de UnidadeFederacao")
public class UnidadeFederacaoController extends BaseController<UnidadeFederacao, UnidadeFederacaoDTO, UnidadeFederacaoBasicDTO, UnidadeFederacaoFilterDTO,
		UnidadeFederacaoRepository, UnidadeFederacaoService> {

}

