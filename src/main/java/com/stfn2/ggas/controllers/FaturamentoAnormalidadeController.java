package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.FaturamentoAnormalidade;
import com.stfn2.ggas.domain.dtos.FaturamentoAnormalidadeDTO;
import com.stfn2.ggas.domain.dtos.filter.FaturamentoAnormalidadeFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.FaturamentoAnormalidadeBasicDTO;
import com.stfn2.ggas.repositories.FaturamentoAnormalidadeRepository;
import com.stfn2.ggas.services.FaturamentoAnormalidadeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/faturamentoAnormalidade")
@Tag(name="FaturamentoAnormalidade", description="EndPoints para gerenciamento de FaturamentoAnormalidade")
public class FaturamentoAnormalidadeController extends BaseController<FaturamentoAnormalidade, FaturamentoAnormalidadeDTO, FaturamentoAnormalidadeBasicDTO, FaturamentoAnormalidadeFilterDTO,
		FaturamentoAnormalidadeRepository, FaturamentoAnormalidadeService> {
}