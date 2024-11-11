package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.FaturaItemImpressao;
import com.stfn2.ggas.domain.dtos.FaturaItemImpressaoDTO;
import com.stfn2.ggas.domain.dtos.filter.FaturaItemImpressaoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.FaturaItemImpressaoBasicDTO;
import com.stfn2.ggas.repositories.FaturaItemImpressaoRepository;
import com.stfn2.ggas.services.FaturaItemImpressaoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/faturaItemImpressao")
@Tag(name="FaturaItemImpressao", description="EndPoints para gerenciamento de FaturaItemImpressao")
public class FaturaItemImpressaoController extends BaseController<FaturaItemImpressao, FaturaItemImpressaoDTO, FaturaItemImpressaoBasicDTO, FaturaItemImpressaoFilterDTO,
		FaturaItemImpressaoRepository, FaturaItemImpressaoService> {
}