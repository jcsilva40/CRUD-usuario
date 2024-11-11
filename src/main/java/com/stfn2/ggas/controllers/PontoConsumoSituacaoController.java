package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.PontoConsumoSituacao;
import com.stfn2.ggas.domain.dtos.PontoConsumoSituacaoDTO;
import com.stfn2.ggas.domain.dtos.filter.PontoConsumoSituacaoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.PontoConsumoSituacaoBasicDTO;
import com.stfn2.ggas.repositories.PontoConsumoSituacaoRepository;
import com.stfn2.ggas.services.PontoConsumoSituacaoService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/pontoConsumoSituacao")
@Tag(name="PontoConsumoSituacao", description="EndPoints para gerenciamento de PontoConsumoSituacao")
public class PontoConsumoSituacaoController extends BaseController<PontoConsumoSituacao, PontoConsumoSituacaoDTO, PontoConsumoSituacaoBasicDTO, PontoConsumoSituacaoFilterDTO,
		PontoConsumoSituacaoRepository, PontoConsumoSituacaoService> {
}

