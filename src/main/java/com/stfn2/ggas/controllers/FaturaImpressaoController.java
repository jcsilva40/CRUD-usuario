package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.FaturaImpressao;
import com.stfn2.ggas.domain.dtos.FaturaImpressaoDTO;
import com.stfn2.ggas.domain.dtos.filter.FaturaImpressaoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.FaturaImpressaoBasicDTO;
import com.stfn2.ggas.repositories.FaturaImpressaoRepository;
import com.stfn2.ggas.services.FaturaImpressaoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/faturaImpressao")
@Tag(name="FaturaImpressao", description="EndPoints para gerenciamento de FaturaImpressao")
public class FaturaImpressaoController extends BaseController<FaturaImpressao, FaturaImpressaoDTO, FaturaImpressaoBasicDTO, FaturaImpressaoFilterDTO,
		FaturaImpressaoRepository, FaturaImpressaoService> {
}