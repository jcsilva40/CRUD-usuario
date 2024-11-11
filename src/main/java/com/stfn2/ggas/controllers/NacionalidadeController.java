package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.Nacionalidade;
import com.stfn2.ggas.domain.dtos.NacionalidadeDTO;
import com.stfn2.ggas.domain.dtos.filter.NacionalidadeFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.NacionalidadeBasicDTO;
import com.stfn2.ggas.repositories.NacionalidadeRepository;
import com.stfn2.ggas.services.NacionalidadeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/nacionalidade")
@Tag(name="Nacionalidade", description="EndPoints para gerenciamento de Nacionalidade")
public class NacionalidadeController extends BaseController<Nacionalidade, NacionalidadeDTO, NacionalidadeBasicDTO, NacionalidadeFilterDTO,
		NacionalidadeRepository, NacionalidadeService> {
}