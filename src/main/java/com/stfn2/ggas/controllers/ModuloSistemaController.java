package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.ModuloSistema;
import com.stfn2.ggas.domain.dtos.ModuloSistemaDTO;
import com.stfn2.ggas.domain.dtos.filter.ModuloSistemaFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.ModuloSistemaBasicDTO;
import com.stfn2.ggas.repositories.ModuloSistemaRepository;
import com.stfn2.ggas.services.ModuloSistemaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/moduloSistema")
@Tag(name="ModuloSistema", description="EndPoints para gerenciamento de ModuloSistema")
public class ModuloSistemaController extends BaseController<ModuloSistema, ModuloSistemaDTO, ModuloSistemaBasicDTO, ModuloSistemaFilterDTO,
		ModuloSistemaRepository, ModuloSistemaService> {
}