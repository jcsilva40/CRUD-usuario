package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.Boleto;
import com.stfn2.ggas.domain.dtos.BoletoDTO;
import com.stfn2.ggas.domain.dtos.basic.BoletoBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.BoletoFilterDTO;
import com.stfn2.ggas.repositories.BoletoRepository;
import com.stfn2.ggas.services.BoletoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/boleto")
@Tag(name="Boleto", description="EndPoints para gerenciamento de Boleto")
public class BoletoController extends BaseController<Boleto, BoletoDTO, BoletoBasicDTO, BoletoFilterDTO,
		BoletoRepository,BoletoService> {
}