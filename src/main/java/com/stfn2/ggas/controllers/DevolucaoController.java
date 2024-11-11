package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.Devolucao;
import com.stfn2.ggas.domain.dtos.DevolucaoDTO;
import com.stfn2.ggas.domain.dtos.filter.DevolucaoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.DevolucaoBasicDTO;
import com.stfn2.ggas.repositories.DevolucaoRepository;
import com.stfn2.ggas.services.DevolucaoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/devolucao")
@Tag(name="Devolucao", description="EndPoints para gerenciamento de Devolucao")
public class DevolucaoController extends BaseController<Devolucao, DevolucaoDTO, DevolucaoBasicDTO, DevolucaoFilterDTO,
		DevolucaoRepository, DevolucaoService> {
}