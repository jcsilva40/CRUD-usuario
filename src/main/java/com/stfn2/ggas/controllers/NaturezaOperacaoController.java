package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.NaturezaOperacao;
import com.stfn2.ggas.domain.dtos.NaturezaOperacaoDTO;
import com.stfn2.ggas.domain.dtos.filter.NaturezaOperacaoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.NaturezaOperacaoBasicDTO;
import com.stfn2.ggas.repositories.NaturezaOperacaoRepository;
import com.stfn2.ggas.services.NaturezaOperacaoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/naturezaOperacao")
@Tag(name="NaturezaOperacao", description="EndPoints para gerenciamento de NaturezaOperacao")
public class NaturezaOperacaoController extends BaseController<NaturezaOperacao, NaturezaOperacaoDTO, NaturezaOperacaoBasicDTO, NaturezaOperacaoFilterDTO,
		NaturezaOperacaoRepository, NaturezaOperacaoService> {
}