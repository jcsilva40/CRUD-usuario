package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.CampanhaDescontoVincular;
import com.stfn2.ggas.domain.dtos.CampanhaDescontoVincularDTO;
import com.stfn2.ggas.domain.dtos.basic.CampanhaDescontoVincularBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.CampanhaDescontoVincularFilterDTO;
import com.stfn2.ggas.repositories.CampanhaDescontoVincularRepository;
import com.stfn2.ggas.services.CampanhaDescontoVincularService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/campanhaDescontoVincular")
@Tag(name="CampanhaDescontoVincular", description="EndPoints para gerenciamento de CampanhaDescontoVincular")
public class CampanhaDescontoVincularController extends BaseController<CampanhaDescontoVincular, CampanhaDescontoVincularDTO, CampanhaDescontoVincularBasicDTO, CampanhaDescontoVincularFilterDTO,
		CampanhaDescontoVincularRepository,CampanhaDescontoVincularService> {
}