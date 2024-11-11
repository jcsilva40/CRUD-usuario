package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.LogCampanhaDesconto;
import com.stfn2.ggas.domain.dtos.LogCampanhaDescontoDTO;
import com.stfn2.ggas.domain.dtos.basic.LogCampanhaDescontoBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.LogCampanhaDescontoFilterDTO;
import com.stfn2.ggas.repositories.LogCampanhaDescontoRepository;
import com.stfn2.ggas.services.LogCampanhaDescontoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/logCampanhaDesconto")
@Tag(name="LogCampanhaDesconto", description="EndPoints para gerenciamento de LogCampanhaDesconto")
public class LogCampanhaDescontoController extends BaseController<LogCampanhaDesconto, LogCampanhaDescontoDTO, LogCampanhaDescontoBasicDTO, LogCampanhaDescontoFilterDTO,
		LogCampanhaDescontoRepository,LogCampanhaDescontoService> {
}