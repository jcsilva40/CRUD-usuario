package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.Recurso;
import com.stfn2.ggas.domain.dtos.RecursoDTO;
import com.stfn2.ggas.domain.dtos.basic.RecursoBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.RecursoFilterDTO;
import com.stfn2.ggas.repositories.RecursoRepository;
import com.stfn2.ggas.services.RecursoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/recurso")
@Tag(name="Recurso", description="EndPoints para gerenciamento de Recurso")
public class RecursoController extends BaseController<Recurso, RecursoDTO, RecursoBasicDTO, RecursoFilterDTO,
		RecursoRepository,RecursoService> {
}