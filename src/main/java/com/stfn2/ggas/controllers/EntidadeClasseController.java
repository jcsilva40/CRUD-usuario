package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.EntidadeClasse;
import com.stfn2.ggas.domain.dtos.filter.EntidadeClasseFilterDTO;
import com.stfn2.ggas.domain.dtos.EntidadeClasseDTO;
import com.stfn2.ggas.domain.dtos.basic.EntidadeClasseBasicDTO;
import com.stfn2.ggas.repositories.EntidadeClasseRepository;
import com.stfn2.ggas.services.EntidadeClasseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/entidadesclasse")
@Tag(name="EntidadeClasse", description = "EndPoints para gerenciamento de EntidadeClasse")
public class EntidadeClasseController 	extends BaseController<EntidadeClasse,
		EntidadeClasseDTO, EntidadeClasseBasicDTO, EntidadeClasseFilterDTO, EntidadeClasseRepository,
		EntidadeClasseService> {

}
