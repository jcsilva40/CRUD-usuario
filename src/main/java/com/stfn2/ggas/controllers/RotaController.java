package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.Rota;
import com.stfn2.ggas.domain.dtos.RotaDTO;
import com.stfn2.ggas.domain.dtos.filter.RotaFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.RotaBasicDTO;
import com.stfn2.ggas.repositories.RotaRepository;
import com.stfn2.ggas.services.RotaService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/rota")
@Tag(name="Rota", description="EndPoints para gerenciamento de Rota")
public class RotaController extends BaseController<Rota, RotaDTO, RotaBasicDTO, RotaFilterDTO,
		RotaRepository, RotaService> {
}

