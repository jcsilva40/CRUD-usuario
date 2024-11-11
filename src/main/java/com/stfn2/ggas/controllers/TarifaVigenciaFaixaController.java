package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.TarifaVigenciaFaixa;
import com.stfn2.ggas.domain.dtos.TarifaVigenciaFaixaDTO;
import com.stfn2.ggas.domain.dtos.filter.TarifaVigenciaFaixaFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.TarifaVigenciaFaixaBasicDTO;
import com.stfn2.ggas.repositories.TarifaVigenciaFaixaRepository;
import com.stfn2.ggas.services.TarifaVigenciaFaixaService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/tarifasVigenciaFaixa")
@Tag(name="TarifaVigenciaFaixa", description="EndPoints para gerenciamento de TarifaVigenciaFaixa")
public class TarifaVigenciaFaixaController extends BaseController<TarifaVigenciaFaixa, TarifaVigenciaFaixaDTO, TarifaVigenciaFaixaBasicDTO, TarifaVigenciaFaixaFilterDTO,
		TarifaVigenciaFaixaRepository, TarifaVigenciaFaixaService> {
}

