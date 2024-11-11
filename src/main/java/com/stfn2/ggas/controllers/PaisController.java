package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.Pais;
import com.stfn2.ggas.domain.dtos.PaisDTO;
import com.stfn2.ggas.domain.dtos.filter.PaisFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.PaisBasicDTO;
import com.stfn2.ggas.repositories.PaisRepository;
import com.stfn2.ggas.services.PaisService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/paises")
@Tag(name="Pais", description="EndPoints para gerenciamento de Pais")
public class PaisController extends BaseController<Pais, PaisDTO, PaisBasicDTO, PaisFilterDTO,
		PaisRepository,	PaisService> {

}

