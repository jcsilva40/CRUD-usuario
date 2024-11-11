package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.MedidorInstalacao;
import com.stfn2.ggas.domain.dtos.filter.MedidorInstalacaoFilterDTO;
import com.stfn2.ggas.domain.dtos.MedidorInstalacaoDTO;
import com.stfn2.ggas.domain.dtos.basic.MedidorInstalacaoBasicDTO;
import com.stfn2.ggas.repositories.MedidorInstalacaoRepository;
import com.stfn2.ggas.services.MedidorInstalacaoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/medidorInstalacao")
@Tag(name="MedidorInstalacao", description="EndPoints para gerenciamento de MedidorInstalacao")
public class MedidorInstalacaoController extends BaseController<MedidorInstalacao, MedidorInstalacaoDTO, MedidorInstalacaoBasicDTO, MedidorInstalacaoFilterDTO,
		MedidorInstalacaoRepository, MedidorInstalacaoService> {
	}