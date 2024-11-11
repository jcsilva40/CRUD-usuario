package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.FinanciamentoTipo;
import com.stfn2.ggas.domain.dtos.FinanciamentoTipoDTO;
import com.stfn2.ggas.domain.dtos.filter.FinanciamentoTipoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.FinanciamentoTipoBasicDTO;
import com.stfn2.ggas.repositories.FinanciamentoTipoRepository;
import com.stfn2.ggas.services.FinanciamentoTipoService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/financiamentoTipo")
@Tag(name="FinanciamentoTipo", description="EndPoints para gerenciamento de FinanciamentoTipo")
public class FinanciamentoTipoController extends BaseController<FinanciamentoTipo, FinanciamentoTipoDTO, FinanciamentoTipoBasicDTO, FinanciamentoTipoFilterDTO,
		FinanciamentoTipoRepository, FinanciamentoTipoService> {
}

