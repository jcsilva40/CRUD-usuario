package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.ParametroSistema;
import com.stfn2.ggas.domain.dtos.ParametroSistemaDTO;
import com.stfn2.ggas.domain.dtos.basic.ParametroSistemaBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.ParametroSistemaFilterDTO;
import com.stfn2.ggas.repositories.ParametroSistemaRepository;
import com.stfn2.ggas.services.ParametroSistemaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/parametroSistema")
@Tag(name="ParametroSistema", description="EndPoints para gerenciamento de ParametroSistema")
public class ParametroSistemaController extends BaseController<ParametroSistema, ParametroSistemaDTO, ParametroSistemaBasicDTO, ParametroSistemaFilterDTO,
		ParametroSistemaRepository,ParametroSistemaService> {
}