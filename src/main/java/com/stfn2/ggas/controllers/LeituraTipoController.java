package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.LeituraTipo;
import com.stfn2.ggas.domain.dtos.LeituraTipoDTO;
import com.stfn2.ggas.domain.dtos.filter.LeituraTipoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.LeituraTipoBasicDTO;
import com.stfn2.ggas.repositories.LeituraTipoRepository;
import com.stfn2.ggas.services.LeituraTipoService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/leituraTipo")
@Tag(name="LeituraTipo", description="EndPoints para gerenciamento de LeituraTipo")
public class LeituraTipoController extends BaseController<LeituraTipo, LeituraTipoDTO, LeituraTipoBasicDTO, LeituraTipoFilterDTO,
		LeituraTipoRepository, LeituraTipoService> {
}

