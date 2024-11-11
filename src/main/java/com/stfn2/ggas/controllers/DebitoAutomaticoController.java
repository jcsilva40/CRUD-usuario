package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.DebitoAutomatico;
import com.stfn2.ggas.domain.dtos.DebitoAutomaticoDTO;
import com.stfn2.ggas.domain.dtos.basic.DebitoAutomaticoBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.DebitoAutomaticoFilterDTO;
import com.stfn2.ggas.repositories.DebitoAutomaticoRepository;
import com.stfn2.ggas.services.DebitoAutomaticoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/debitoAutomatico")
@Tag(name="DebitoAutomatico", description="EndPoints para gerenciamento de DebitoAutomatico")
public class DebitoAutomaticoController extends BaseController<DebitoAutomatico, DebitoAutomaticoDTO, DebitoAutomaticoBasicDTO, DebitoAutomaticoFilterDTO,
		DebitoAutomaticoRepository,DebitoAutomaticoService> {
}