package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.TarifaVigenciaDesconto;
import com.stfn2.ggas.domain.dtos.TarifaVigenciaDescontoDTO;
import com.stfn2.ggas.domain.dtos.filter.TarifaVigenciaDescontoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.TarifaVigenciaDescontoBasicDTO;
import com.stfn2.ggas.repositories.TarifaVigenciaDescontoRepository;
import com.stfn2.ggas.services.TarifaVigenciaDescontoService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/tarifasVigenciaDesconto")
@Tag(name="TarifaVigenciaDesconto", description="EndPoints para gerenciamento de TarifaVigenciaDesconto")
public class TarifaVigenciaDescontoController extends BaseController<TarifaVigenciaDesconto, TarifaVigenciaDescontoDTO, TarifaVigenciaDescontoBasicDTO, TarifaVigenciaDescontoFilterDTO,
		TarifaVigenciaDescontoRepository, TarifaVigenciaDescontoService> {
}

