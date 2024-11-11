package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.FaturaDadosTecnicos;
import com.stfn2.ggas.domain.dtos.FaturaDadosTecnicosDTO;
import com.stfn2.ggas.domain.dtos.basic.FaturaDadosTecnicosBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.FaturaDadosTecnicosFilterDTO;
import com.stfn2.ggas.repositories.FaturaDadosTecnicosRepository;
import com.stfn2.ggas.services.FaturaDadosTecnicosService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/faturaDadosTecnicos")
@Tag(name="FaturaDadosTecnicos", description="EndPoints para gerenciamento de FaturaDadosTecnicos")
public class FaturaDadosTecnicosController extends BaseController<FaturaDadosTecnicos, FaturaDadosTecnicosDTO, FaturaDadosTecnicosBasicDTO, FaturaDadosTecnicosFilterDTO,
		FaturaDadosTecnicosRepository,FaturaDadosTecnicosService> {
}