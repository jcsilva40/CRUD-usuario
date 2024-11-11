package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.Unidade;
import com.stfn2.ggas.domain.dtos.UnidadeDTO;
import com.stfn2.ggas.domain.dtos.filter.UnidadeFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.UnidadeBasicDTO;
import com.stfn2.ggas.repositories.UnidadeRepository;
import com.stfn2.ggas.services.UnidadeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/unidade")
@Tag(name="Unidade", description="EndPoints para gerenciamento de Unidade")
public class UnidadeController extends BaseController<Unidade, UnidadeDTO, UnidadeBasicDTO, UnidadeFilterDTO,
		UnidadeRepository, UnidadeService> {
}