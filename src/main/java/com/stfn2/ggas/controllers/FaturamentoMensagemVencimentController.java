package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.FaturamentoMensagemVenciment;
import com.stfn2.ggas.domain.dtos.FaturamentoMensagemVencimentDTO;
import com.stfn2.ggas.domain.dtos.filter.FaturamentoMensagemVencimentFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.FaturamentoMensagemVencimentBasicDTO;
import com.stfn2.ggas.repositories.FaturamentoMensagemVencimentRepository;
import com.stfn2.ggas.services.FaturamentoMensagemVencimentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/faturamentoMensagemVenciment")
@Tag(name="FaturamentoMensagemVenciment", description="EndPoints para gerenciamento de FaturamentoMensagemVenciment")
public class FaturamentoMensagemVencimentController extends BaseController<FaturamentoMensagemVenciment, FaturamentoMensagemVencimentDTO, FaturamentoMensagemVencimentBasicDTO, FaturamentoMensagemVencimentFilterDTO,
		FaturamentoMensagemVencimentRepository, FaturamentoMensagemVencimentService> {
}