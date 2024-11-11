package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.ConsumoAnormalidade;
import com.stfn2.ggas.domain.dtos.ConsumoAnormalidadeDTO;
import com.stfn2.ggas.domain.dtos.basic.ConsumoAnormalidadeBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.ConsumoAnormalidadeFilterDTO;
import com.stfn2.ggas.repositories.ConsumoAnormalidadeRepository;
import com.stfn2.ggas.services.ConsumoAnormalidadeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/consumoAnormalidade")
@Tag(name="ConsumoAnormalidade", description="EndPoints para gerenciamento de ConsumoAnormalidade")
public class ConsumoAnormalidadeController extends BaseController<ConsumoAnormalidade, ConsumoAnormalidadeDTO, ConsumoAnormalidadeBasicDTO, ConsumoAnormalidadeFilterDTO,
		ConsumoAnormalidadeRepository,ConsumoAnormalidadeService> {
}