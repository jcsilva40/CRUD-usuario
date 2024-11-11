package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.UnidadeOrganizacional;
import com.stfn2.ggas.domain.dtos.UnidadeOrganizacionalDTO;
import com.stfn2.ggas.domain.dtos.filter.UnidadeOrganizacionalFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.UnidadeOrganizacionalBasicDTO;
import com.stfn2.ggas.repositories.UnidadeOrganizacionalRepository;
import com.stfn2.ggas.services.UnidadeOrganizacionalService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/unidadeOrganizacional")
@Tag(name="UnidadeOrganizacional", description="EndPoints para gerenciamento de UnidadeOrganizacional")
public class UnidadeOrganizacionalController extends BaseController<UnidadeOrganizacional, UnidadeOrganizacionalDTO, UnidadeOrganizacionalBasicDTO, UnidadeOrganizacionalFilterDTO,
		UnidadeOrganizacionalRepository, UnidadeOrganizacionalService> {
}

