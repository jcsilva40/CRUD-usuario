package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.CorretorVazaoMovimentacao;
import com.stfn2.ggas.domain.dtos.CorretorVazaoMovimentacaoDTO;
import com.stfn2.ggas.domain.dtos.filter.CorretorVazaoMovimentacaoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.CorretorVazaoMovimentacaoBasicDTO;
import com.stfn2.ggas.repositories.CorretorVazaoMovimentacaoRepository;
import com.stfn2.ggas.services.CorretorVazaoMovimentacaoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/corretorVazaoMovimentacao")
@Tag(name="CorretorVazaoMovimentacao", description="EndPoints para gerenciamento de CorretorVazaoMovimentacao")
public class CorretorVazaoMovimentacaoController extends BaseController<CorretorVazaoMovimentacao, CorretorVazaoMovimentacaoDTO, CorretorVazaoMovimentacaoBasicDTO, CorretorVazaoMovimentacaoFilterDTO,
		CorretorVazaoMovimentacaoRepository, CorretorVazaoMovimentacaoService> {
}