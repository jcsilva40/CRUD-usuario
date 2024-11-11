package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.Fatura;
import com.stfn2.ggas.domain.dtos.FaturaDTO;
import com.stfn2.ggas.domain.dtos.filter.FaturaFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.FaturaBasicDTO;
import com.stfn2.ggas.repositories.FaturaRepository;
import com.stfn2.ggas.services.FaturaService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/fatura")
@Tag(name="Fatura", description="EndPoints para gerenciamento de Fatura")
public class FaturaController extends BaseController<Fatura, FaturaDTO, FaturaBasicDTO, FaturaFilterDTO,
		FaturaRepository, FaturaService> {
}

