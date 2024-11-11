package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.RubricaValorRegulamentado;
import com.stfn2.ggas.domain.dtos.RubricaValorRegulamentadoDTO;
import com.stfn2.ggas.domain.dtos.filter.RubricaValorRegulamentadoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.RubricaValorRegulamentadoBasicDTO;
import com.stfn2.ggas.repositories.RubricaValorRegulamentadoRepository;
import com.stfn2.ggas.services.RubricaValorRegulamentadoService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/rubricaValorRegulamentado")
@Tag(name="RubricaValorRegulamentado", description="EndPoints para gerenciamento de RubricaValorRegulamentado")
public class RubricaValorRegulamentadoController extends BaseController<RubricaValorRegulamentado, RubricaValorRegulamentadoDTO, RubricaValorRegulamentadoBasicDTO, RubricaValorRegulamentadoFilterDTO,
		RubricaValorRegulamentadoRepository, RubricaValorRegulamentadoService> {
}

