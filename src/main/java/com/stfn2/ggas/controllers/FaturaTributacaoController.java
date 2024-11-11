package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.FaturaTributacao;
import com.stfn2.ggas.domain.dtos.FaturaTributacaoDTO;
import com.stfn2.ggas.domain.dtos.filter.FaturaTributacaoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.FaturaTributacaoBasicDTO;
import com.stfn2.ggas.repositories.FaturaTributacaoRepository;
import com.stfn2.ggas.services.FaturaTributacaoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/faturaTributacao")
@Tag(name="FaturaTributacao", description="EndPoints para gerenciamento de FaturaTributacao")
public class FaturaTributacaoController extends BaseController<FaturaTributacao, FaturaTributacaoDTO, FaturaTributacaoBasicDTO, FaturaTributacaoFilterDTO,
		FaturaTributacaoRepository, FaturaTributacaoService> {
}