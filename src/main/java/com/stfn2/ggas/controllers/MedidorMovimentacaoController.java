package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.MedidorMovimentacao;
import com.stfn2.ggas.domain.dtos.MedidorMovimentacaoDTO;
import com.stfn2.ggas.domain.dtos.filter.MedidorMovimentacaoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.MedidorMovimentacaoBasicDTO;
import com.stfn2.ggas.repositories.MedidorMovimentacaoRepository;
import com.stfn2.ggas.services.MedidorMovimentacaoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/medidorMovimentacao")
@Tag(name="MedidorMovimentacao", description="EndPoints para gerenciamento de MedidorMovimentacao")
public class MedidorMovimentacaoController extends BaseController<MedidorMovimentacao, MedidorMovimentacaoDTO, MedidorMovimentacaoBasicDTO, MedidorMovimentacaoFilterDTO,
		MedidorMovimentacaoRepository, MedidorMovimentacaoService> {
}