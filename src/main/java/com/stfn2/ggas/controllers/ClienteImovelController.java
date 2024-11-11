package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.ClienteImovel;
import com.stfn2.ggas.domain.dtos.ClienteImovelDTO;
import com.stfn2.ggas.domain.dtos.filter.ClienteImovelFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.ClienteImovelBasicDTO;
import com.stfn2.ggas.repositories.ClienteImovelRepository;
import com.stfn2.ggas.services.ClienteImovelService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/clienteImovel")
@Tag(name="ClienteImovel", description="EndPoints para gerenciamento de ClienteImovel")
public class ClienteImovelController extends BaseController<ClienteImovel, ClienteImovelDTO, ClienteImovelBasicDTO, ClienteImovelFilterDTO,
		ClienteImovelRepository, ClienteImovelService> {
}