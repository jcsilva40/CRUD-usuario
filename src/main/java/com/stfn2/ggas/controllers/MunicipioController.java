package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.Municipio;
import com.stfn2.ggas.domain.dtos.MunicipioDTO;
import com.stfn2.ggas.domain.dtos.filter.MunicipioFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.MunicipioBasicDTO;
import com.stfn2.ggas.repositories.MunicipioRepository;
import com.stfn2.ggas.services.MunicipioService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/municipios")
@Tag(name="Municipio", description="EndPoints para gerenciamento de Municipio")
public class MunicipioController extends BaseController<Municipio, MunicipioDTO, MunicipioBasicDTO,
	MunicipioFilterDTO, MunicipioRepository,	MunicipioService> {

}

