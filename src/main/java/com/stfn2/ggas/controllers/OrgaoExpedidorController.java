package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.OrgaoExpedidor;
import com.stfn2.ggas.domain.dtos.OrgaoExpedidorDTO;
import com.stfn2.ggas.domain.dtos.filter.OrgaoExpedidorFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.OrgaoExpedidorBasicDTO;
import com.stfn2.ggas.repositories.OrgaoExpedidorRepository;
import com.stfn2.ggas.services.OrgaoExpedidorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/orgaoExpedidor")
@Tag(name="OrgaoExpedidor", description="EndPoints para gerenciamento de OrgaoExpedidor")
public class OrgaoExpedidorController extends BaseController<OrgaoExpedidor, OrgaoExpedidorDTO, OrgaoExpedidorBasicDTO, OrgaoExpedidorFilterDTO,
		OrgaoExpedidorRepository, OrgaoExpedidorService> {
}