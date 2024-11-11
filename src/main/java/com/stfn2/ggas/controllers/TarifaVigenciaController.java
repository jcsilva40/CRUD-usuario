package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.TarifaVigencia;
import com.stfn2.ggas.domain.dtos.TarifaVigenciaDTO;
import com.stfn2.ggas.domain.dtos.filter.TarifaVigenciaFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.TarifaVigenciaBasicDTO;
import com.stfn2.ggas.repositories.TarifaVigenciaRepository;
import com.stfn2.ggas.services.TarifaVigenciaService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/tarifasVigencia")
@Tag(name="TarifaVigencia", description="EndPoints para gerenciamento de TarifaVigencia")
public class TarifaVigenciaController extends BaseController<TarifaVigencia, TarifaVigenciaDTO, TarifaVigenciaBasicDTO, TarifaVigenciaFilterDTO,
		TarifaVigenciaRepository, TarifaVigenciaService> {
}

