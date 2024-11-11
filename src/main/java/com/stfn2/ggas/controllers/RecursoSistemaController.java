package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.RecursoSistema;
import com.stfn2.ggas.domain.dtos.RecursoSistemaDTO;
import com.stfn2.ggas.domain.dtos.filter.RecursoSistemaFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.RecursoSistemaBasicDTO;
import com.stfn2.ggas.repositories.RecursoSistemaRepository;
import com.stfn2.ggas.services.RecursoSistemaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/recursoSistema")
@Tag(name="RecursoSistema", description="EndPoints para gerenciamento de RecursoSistema")
public class RecursoSistemaController extends BaseController<RecursoSistema, RecursoSistemaDTO, RecursoSistemaBasicDTO, RecursoSistemaFilterDTO,
		RecursoSistemaRepository, RecursoSistemaService> {
}