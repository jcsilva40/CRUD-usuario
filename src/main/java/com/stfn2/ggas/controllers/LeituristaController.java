package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.Leiturista;
import com.stfn2.ggas.domain.dtos.LeituristaDTO;
import com.stfn2.ggas.domain.dtos.filter.LeituristaFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.LeituristaBasicDTO;
import com.stfn2.ggas.repositories.LeituristaRepository;
import com.stfn2.ggas.services.LeituristaService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/leiturista")
@Tag(name="Leiturista", description="EndPoints para gerenciamento de Leiturista")
public class LeituristaController extends BaseController<Leiturista, LeituristaDTO, LeituristaBasicDTO, LeituristaFilterDTO,
		LeituristaRepository, LeituristaService> {
}

