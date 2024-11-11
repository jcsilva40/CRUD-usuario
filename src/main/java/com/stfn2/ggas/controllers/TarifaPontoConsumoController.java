package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.TarifaPontoConsumo;
import com.stfn2.ggas.domain.dtos.TarifaPontoConsumoDTO;
import com.stfn2.ggas.domain.dtos.filter.TarifaPontoConsumoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.TarifaPontoConsumoBasicDTO;
import com.stfn2.ggas.repositories.TarifaPontoConsumoRepository;
import com.stfn2.ggas.services.TarifaPontoConsumoService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/tarifasPontoConsumo")
@Tag(name="TarifaPontoConsumo", description="EndPoints para gerenciamento de TarifaPontoConsumo")
public class TarifaPontoConsumoController extends BaseController<TarifaPontoConsumo, TarifaPontoConsumoDTO, TarifaPontoConsumoBasicDTO, TarifaPontoConsumoFilterDTO,
		TarifaPontoConsumoRepository, TarifaPontoConsumoService> {
}

