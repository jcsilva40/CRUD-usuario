package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.FaturamentoMensagem;
import com.stfn2.ggas.domain.dtos.FaturamentoMensagemDTO;
import com.stfn2.ggas.domain.dtos.filter.FaturamentoMensagemFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.FaturamentoMensagemBasicDTO;
import com.stfn2.ggas.repositories.FaturamentoMensagemRepository;
import com.stfn2.ggas.services.FaturamentoMensagemService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/faturamentoMensagem")
@Tag(name="FaturamentoMensagem", description="EndPoints para gerenciamento de FaturamentoMensagem")
public class FaturamentoMensagemController extends BaseController<FaturamentoMensagem, FaturamentoMensagemDTO, FaturamentoMensagemBasicDTO, FaturamentoMensagemFilterDTO,
		FaturamentoMensagemRepository, FaturamentoMensagemService> {
}