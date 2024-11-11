package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.TarifaFaixaDesconto;
import com.stfn2.ggas.domain.dtos.TarifaFaixaDescontoDTO;
import com.stfn2.ggas.domain.dtos.filter.TarifaFaixaDescontoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.TarifaFaixaDescontoBasicDTO;
import com.stfn2.ggas.repositories.TarifaFaixaDescontoRepository;
import com.stfn2.ggas.services.TarifaFaixaDescontoService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/tarifasFaixaDesconto")
@Tag(name="TarifaFaixaDesconto", description="EndPoints para gerenciamento de TarifaFaixaDesconto")
public class TarifaFaixaDescontoController extends BaseController<TarifaFaixaDesconto, TarifaFaixaDescontoDTO, TarifaFaixaDescontoBasicDTO, TarifaFaixaDescontoFilterDTO,
		TarifaFaixaDescontoRepository, TarifaFaixaDescontoService> {
}

