package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.MedidorOperacao;
import com.stfn2.ggas.domain.dtos.MedidorOperacaoDTO;
import com.stfn2.ggas.domain.dtos.filter.MedidorOperacaoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.MedidorOperacaoBasicDTO;
import com.stfn2.ggas.repositories.MedidorOperacaoRepository;
import com.stfn2.ggas.services.MedidorOperacaoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/medidorOperacao")
@Tag(name="MedidorOperacao", description="EndPoints para gerenciamento de MedidorOperacao")
public class MedidorOperacaoController extends BaseController<MedidorOperacao, MedidorOperacaoDTO, MedidorOperacaoBasicDTO, MedidorOperacaoFilterDTO,
		MedidorOperacaoRepository, MedidorOperacaoService> {
}