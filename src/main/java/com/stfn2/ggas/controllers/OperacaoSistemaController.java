package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.OperacaoSistema;
import com.stfn2.ggas.domain.dtos.OperacaoSistemaDTO;
import com.stfn2.ggas.domain.dtos.filter.OperacaoSistemaFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.OperacaoSistemaBasicDTO;
import com.stfn2.ggas.repositories.OperacaoSistemaRepository;
import com.stfn2.ggas.services.OperacaoSistemaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/operacaoSistema")
@Tag(name="OperacaoSistema", description="EndPoints para gerenciamento de OperacaoSistema")
public class OperacaoSistemaController extends BaseController<OperacaoSistema, OperacaoSistemaDTO, OperacaoSistemaBasicDTO, OperacaoSistemaFilterDTO,
		OperacaoSistemaRepository, OperacaoSistemaService> {
}