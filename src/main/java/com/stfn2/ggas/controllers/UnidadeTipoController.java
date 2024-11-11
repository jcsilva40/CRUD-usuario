package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.UnidadeTipo;
import com.stfn2.ggas.domain.dtos.UnidadeTipoDTO;
import com.stfn2.ggas.domain.dtos.filter.UnidadeTipoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.UnidadeTipoBasicDTO;
import com.stfn2.ggas.repositories.UnidadeTipoRepository;
import com.stfn2.ggas.services.UnidadeTipoService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/unidadeTipo")
@Tag(name="UnidadeTipo", description="EndPoints para gerenciamento de UnidadeTipo")
public class UnidadeTipoController extends BaseController<UnidadeTipo, UnidadeTipoDTO, UnidadeTipoBasicDTO, UnidadeTipoFilterDTO,
		UnidadeTipoRepository, UnidadeTipoService> {
}

