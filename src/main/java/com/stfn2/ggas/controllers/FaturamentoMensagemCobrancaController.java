package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.FaturamentoMensagemCobranca;
import com.stfn2.ggas.domain.dtos.FaturamentoMensagemCobrancaDTO;
import com.stfn2.ggas.domain.dtos.filter.FaturamentoMensagemCobrancaFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.FaturamentoMensagemCobrancaBasicDTO;
import com.stfn2.ggas.repositories.FaturamentoMensagemCobrancaRepository;
import com.stfn2.ggas.services.FaturamentoMensagemCobrancaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/faturamentoMensagemCobranca")
@Tag(name="FaturamentoMensagemCobranca", description="EndPoints para gerenciamento de FaturamentoMensagemCobranca")
public class FaturamentoMensagemCobrancaController extends BaseController<FaturamentoMensagemCobranca, FaturamentoMensagemCobrancaDTO, FaturamentoMensagemCobrancaBasicDTO, FaturamentoMensagemCobrancaFilterDTO,
		FaturamentoMensagemCobrancaRepository, FaturamentoMensagemCobrancaService> {
}