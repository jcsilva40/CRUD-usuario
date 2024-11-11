package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.ConstanteSistema;
import com.stfn2.ggas.domain.dtos.ConstanteSistemaDTO;
import com.stfn2.ggas.domain.dtos.basic.ConstanteSistemaBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.ConstanteSistemaFilterDTO;
import com.stfn2.ggas.repositories.ConstanteSistemaRepository;
import com.stfn2.ggas.services.ConstanteSistemaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/constanteSistema")
@Tag(name="ConstanteSistema", description="EndPoints para gerenciamento de ConstanteSistema")
public class ConstanteSistemaController extends BaseController<ConstanteSistema, ConstanteSistemaDTO, ConstanteSistemaBasicDTO, ConstanteSistemaFilterDTO,
		ConstanteSistemaRepository,ConstanteSistemaService> {
}