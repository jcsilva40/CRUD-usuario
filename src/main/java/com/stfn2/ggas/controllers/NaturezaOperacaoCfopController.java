package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.NaturezaOperacaoCfop;
import com.stfn2.ggas.domain.dtos.NaturezaOperacaoCfopDTO;
import com.stfn2.ggas.domain.dtos.filter.NaturezaOperacaoCfopFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.NaturezaOperacaoCfopBasicDTO;
import com.stfn2.ggas.repositories.NaturezaOperacaoCfopRepository;
import com.stfn2.ggas.services.NaturezaOperacaoCfopService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/naturezaOperacaoCfop")
@Tag(name="NaturezaOperacaoCfop", description="EndPoints para gerenciamento de NaturezaOperacaoCfop")
public class NaturezaOperacaoCfopController extends BaseController<NaturezaOperacaoCfop, NaturezaOperacaoCfopDTO, NaturezaOperacaoCfopBasicDTO, NaturezaOperacaoCfopFilterDTO,
		NaturezaOperacaoCfopRepository, NaturezaOperacaoCfopService> {
}