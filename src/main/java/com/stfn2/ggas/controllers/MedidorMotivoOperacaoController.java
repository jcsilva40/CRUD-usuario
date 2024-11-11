package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.MedidorMotivoOperacao;
import com.stfn2.ggas.domain.dtos.MedidorMotivoOperacaoDTO;
import com.stfn2.ggas.domain.dtos.filter.MedidorMotivoOperacaoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.MedidorMotivoOperacaoBasicDTO;
import com.stfn2.ggas.repositories.MedidorMotivoOperacaoRepository;
import com.stfn2.ggas.services.MedidorMotivoOperacaoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/medidorMotivoOperacao")
@Tag(name="MedidorMotivoOperacao", description="EndPoints para gerenciamento de MedidorMotivoOperacao")
public class MedidorMotivoOperacaoController extends BaseController<MedidorMotivoOperacao, MedidorMotivoOperacaoDTO, MedidorMotivoOperacaoBasicDTO, MedidorMotivoOperacaoFilterDTO,
		MedidorMotivoOperacaoRepository, MedidorMotivoOperacaoService> {
}