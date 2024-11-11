package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.ClienteDebitoAutomatico;
import com.stfn2.ggas.domain.dtos.ClienteDebitoAutomaticoDTO;
import com.stfn2.ggas.domain.dtos.basic.ClienteDebitoAutomaticoBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.ClienteDebitoAutomaticoFilterDTO;
import com.stfn2.ggas.repositories.ClienteDebitoAutomaticoRepository;
import com.stfn2.ggas.services.ClienteDebitoAutomaticoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/clienteDebitoAutomatico")
@Tag(name="ClienteDebitoAutomatico", description="EndPoints para gerenciamento de ClienteDebitoAutomatico")
public class ClienteDebitoAutomaticoController extends BaseController<ClienteDebitoAutomatico, ClienteDebitoAutomaticoDTO, ClienteDebitoAutomaticoBasicDTO, ClienteDebitoAutomaticoFilterDTO,
		ClienteDebitoAutomaticoRepository,ClienteDebitoAutomaticoService> {
}