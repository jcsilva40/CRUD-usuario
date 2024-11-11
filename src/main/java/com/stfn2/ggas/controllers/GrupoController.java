package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.Grupo;
import com.stfn2.ggas.domain.dtos.GrupoDTO;
import com.stfn2.ggas.domain.dtos.basic.GrupoBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.GrupoFilterDTO;
import com.stfn2.ggas.repositories.GrupoRepository;
import com.stfn2.ggas.services.GrupoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/grupo")
@Tag(name="Grupo", description="EndPoints para gerenciamento de Grupo")
public class GrupoController extends BaseController<Grupo, GrupoDTO, GrupoBasicDTO, GrupoFilterDTO,
		GrupoRepository,GrupoService> {
}