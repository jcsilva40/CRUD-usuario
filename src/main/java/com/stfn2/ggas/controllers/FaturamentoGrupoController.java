package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.FaturamentoGrupo;
import com.stfn2.ggas.domain.dtos.FaturamentoGrupoDTO;
import com.stfn2.ggas.domain.dtos.filter.FaturamentoGrupoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.FaturamentoGrupoBasicDTO;
import com.stfn2.ggas.repositories.FaturamentoGrupoRepository;
import com.stfn2.ggas.services.FaturamentoGrupoService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/faturamentoGrupo")
@Tag(name="FaturamentoGrupo", description="EndPoints para gerenciamento de FaturamentoGrupo")
public class FaturamentoGrupoController extends BaseController<FaturamentoGrupo, FaturamentoGrupoDTO, FaturamentoGrupoBasicDTO, FaturamentoGrupoFilterDTO,
		FaturamentoGrupoRepository, FaturamentoGrupoService> {
}

