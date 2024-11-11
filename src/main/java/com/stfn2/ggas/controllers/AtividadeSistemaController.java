package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.AtividadeSistema;
import com.stfn2.ggas.domain.dtos.AtividadeSistemaDTO;
import com.stfn2.ggas.domain.dtos.filter.AtividadeSistemaFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.AtividadeSistemaBasicDTO;
import com.stfn2.ggas.repositories.AtividadeSistemaRepository;
import com.stfn2.ggas.services.AtividadeSistemaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/atividadeSistema")
@Tag(name="AtividadeSistema", description="EndPoints para gerenciamento de AtividadeSistema")
public class AtividadeSistemaController extends BaseController<AtividadeSistema, AtividadeSistemaDTO, AtividadeSistemaBasicDTO, AtividadeSistemaFilterDTO,
		AtividadeSistemaRepository, AtividadeSistemaService> {
}