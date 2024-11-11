package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.FaturaItemTributacao;
import com.stfn2.ggas.domain.dtos.FaturaItemTributacaoDTO;
import com.stfn2.ggas.domain.dtos.filter.FaturaItemTributacaoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.FaturaItemTributacaoBasicDTO;
import com.stfn2.ggas.repositories.FaturaItemTributacaoRepository;
import com.stfn2.ggas.services.FaturaItemTributacaoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/faturaItemTributacao")
@Tag(name="FaturaItemTributacao", description="EndPoints para gerenciamento de FaturaItemTributacao")
public class FaturaItemTributacaoController extends BaseController<FaturaItemTributacao, FaturaItemTributacaoDTO, FaturaItemTributacaoBasicDTO, FaturaItemTributacaoFilterDTO,
		FaturaItemTributacaoRepository, FaturaItemTributacaoService> {
}