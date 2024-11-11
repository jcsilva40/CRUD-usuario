package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.CreditoDebitoSituacao;
import com.stfn2.ggas.domain.dtos.CreditoDebitoSituacaoDTO;
import com.stfn2.ggas.domain.dtos.filter.CreditoDebitoSituacaoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.CreditoDebitoSituacaoBasicDTO;
import com.stfn2.ggas.repositories.CreditoDebitoSituacaoRepository;
import com.stfn2.ggas.services.CreditoDebitoSituacaoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/creditoDebitoSituacao")
@Tag(name="CreditoDebitoSituacao", description="EndPoints para gerenciamento de CreditoDebitoSituacao")
public class CreditoDebitoSituacaoController extends BaseController<CreditoDebitoSituacao, CreditoDebitoSituacaoDTO, CreditoDebitoSituacaoBasicDTO, CreditoDebitoSituacaoFilterDTO,
		CreditoDebitoSituacaoRepository, CreditoDebitoSituacaoService> {
}