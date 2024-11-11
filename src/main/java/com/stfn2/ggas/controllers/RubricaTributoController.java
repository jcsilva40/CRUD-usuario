package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.RubricaTributo;
import com.stfn2.ggas.domain.dtos.RubricaTributoDTO;
import com.stfn2.ggas.domain.dtos.filter.RubricaTributoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.RubricaTributoBasicDTO;
import com.stfn2.ggas.repositories.RubricaTributoRepository;
import com.stfn2.ggas.services.RubricaTributoService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/rubricaTributo")
@Tag(name="RubricaTributo", description="EndPoints para gerenciamento de RubricaTributo")
public class RubricaTributoController extends BaseController<RubricaTributo, RubricaTributoDTO, RubricaTributoBasicDTO, RubricaTributoFilterDTO,
		RubricaTributoRepository, RubricaTributoService> {
}

