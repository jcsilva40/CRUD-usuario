package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.Cep;
import com.stfn2.ggas.domain.dtos.CepDTO;
import com.stfn2.ggas.domain.dtos.filter.CepFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.CepBasicDTO;
import com.stfn2.ggas.repositories.CepRepository;
import com.stfn2.ggas.services.CepService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/ceps")
@Tag(name="Cep", description="EndPoints para gerenciamento de Cep")
public class CepController extends BaseController<Cep, CepDTO, CepBasicDTO, CepFilterDTO, CepRepository, CepService> {

}

