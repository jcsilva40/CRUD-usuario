package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.NomenclaturaComumMercosul;
import com.stfn2.ggas.domain.dtos.NomenclaturaComumMercosulDTO;
import com.stfn2.ggas.domain.dtos.filter.NomenclaturaComumMercosulFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.NomenclaturaComumMercosulBasicDTO;
import com.stfn2.ggas.repositories.NomenclaturaComumMercosulRepository;
import com.stfn2.ggas.services.NomenclaturaComumMercosulService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/nomenclaturaComumMercosul")
@Tag(name="NomenclaturaComumMercosul", description="EndPoints para gerenciamento de NomenclaturaComumMercosul")
public class NomenclaturaComumMercosulController extends BaseController<NomenclaturaComumMercosul, NomenclaturaComumMercosulDTO, NomenclaturaComumMercosulBasicDTO, NomenclaturaComumMercosulFilterDTO,
		NomenclaturaComumMercosulRepository, NomenclaturaComumMercosulService> {
}

