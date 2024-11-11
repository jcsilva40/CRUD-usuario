package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.Empresa;
import com.stfn2.ggas.domain.dtos.filter.EmpresaFilterDTO;
import com.stfn2.ggas.domain.dtos.EmpresaDTO;
import com.stfn2.ggas.domain.dtos.basic.EmpresaBasicDTO;
import com.stfn2.ggas.repositories.EmpresaRepository;
import com.stfn2.ggas.services.EmpresaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/empresa")
@Tag(name="Empresa", description="EndPoints para gerenciamento de Empresa")
public class EmpresaController extends BaseController<Empresa, EmpresaDTO, EmpresaBasicDTO, EmpresaFilterDTO,
		EmpresaRepository, EmpresaService> {
}

