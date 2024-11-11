package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.TarifaVigenciaTributo;
import com.stfn2.ggas.domain.dtos.TarifaVigenciaTributoDTO;
import com.stfn2.ggas.domain.dtos.filter.TarifaVigenciaTributoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.TarifaVigenciaTributoBasicDTO;
import com.stfn2.ggas.repositories.TarifaVigenciaTributoRepository;
import com.stfn2.ggas.services.TarifaVigenciaTributoService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/tarifasVigenciaTributo")
@Tag(name="TarifaVigenciaTributo", description="EndPoints para gerenciamento de TarifaVigenciaTributo")
public class TarifaVigenciaTributoController extends BaseController<TarifaVigenciaTributo, TarifaVigenciaTributoDTO, TarifaVigenciaTributoBasicDTO, TarifaVigenciaTributoFilterDTO,
		TarifaVigenciaTributoRepository, TarifaVigenciaTributoService> {
}

