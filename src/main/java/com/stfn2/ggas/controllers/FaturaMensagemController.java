package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.FaturaMensagem;
import com.stfn2.ggas.domain.dtos.FaturaMensagemDTO;
import com.stfn2.ggas.domain.dtos.basic.FaturaMensagemBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.FaturaMensagemFilterDTO;
import com.stfn2.ggas.repositories.FaturaMensagemRepository;
import com.stfn2.ggas.services.FaturaMensagemService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/faturaMensagem")
@Tag(name="FaturaMensagem", description="EndPoints para gerenciamento de FaturaMensagem")
public class FaturaMensagemController extends BaseController<FaturaMensagem, FaturaMensagemDTO, FaturaMensagemBasicDTO, FaturaMensagemFilterDTO,
		FaturaMensagemRepository,FaturaMensagemService> {
}