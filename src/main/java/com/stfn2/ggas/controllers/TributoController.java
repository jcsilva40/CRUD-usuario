package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.Tributo;
import com.stfn2.ggas.domain.dtos.TributoDTO;
import com.stfn2.ggas.domain.dtos.filter.TributoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.TributoBasicDTO;
import com.stfn2.ggas.repositories.TributoRepository;
import com.stfn2.ggas.services.TributoService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/tributos")
@Tag(name="Tributo", description="EndPoints para gerenciamento de Tributo")
public class TributoController extends BaseController<Tributo, TributoDTO, TributoBasicDTO, TributoFilterDTO,
		TributoRepository, TributoService> {
}

