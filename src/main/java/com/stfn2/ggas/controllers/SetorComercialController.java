package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.SetorComercial;
import com.stfn2.ggas.domain.dtos.SetorComercialDTO;
import com.stfn2.ggas.domain.dtos.basic.SetorComercialBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.SetorComercialFilterDTO;
import com.stfn2.ggas.repositories.SetorComercialRepository;
import com.stfn2.ggas.services.SetorComercialService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/setorComercial")
@Tag(name="SetorComercial", description="EndPoints para gerenciamento de SetorComercial")
public class SetorComercialController extends BaseController<SetorComercial, SetorComercialDTO, SetorComercialBasicDTO, SetorComercialFilterDTO,
		SetorComercialRepository,SetorComercialService> {
}