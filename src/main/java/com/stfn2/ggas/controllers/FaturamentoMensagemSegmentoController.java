package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.FaturamentoMensagemSegmento;
import com.stfn2.ggas.domain.dtos.FaturamentoMensagemSegmentoDTO;
import com.stfn2.ggas.domain.dtos.filter.FaturamentoMensagemSegmentoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.FaturamentoMensagemSegmentoBasicDTO;
import com.stfn2.ggas.repositories.FaturamentoMensagemSegmentoRepository;
import com.stfn2.ggas.services.FaturamentoMensagemSegmentoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/faturamentoMensagemSegmento")
@Tag(name="FaturamentoMensagemSegmento", description="EndPoints para gerenciamento de FaturamentoMensagemSegmento")
public class FaturamentoMensagemSegmentoController extends BaseController<FaturamentoMensagemSegmento, FaturamentoMensagemSegmentoDTO, FaturamentoMensagemSegmentoBasicDTO, FaturamentoMensagemSegmentoFilterDTO,
		FaturamentoMensagemSegmentoRepository, FaturamentoMensagemSegmentoService> {
}