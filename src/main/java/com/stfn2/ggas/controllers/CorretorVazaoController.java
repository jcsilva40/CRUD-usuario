package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.CorretorVazao;
import com.stfn2.ggas.domain.dtos.CorretorVazaoDTO;
import com.stfn2.ggas.domain.dtos.filter.CorretorVazaoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.CorretorVazaoBasicDTO;
import com.stfn2.ggas.repositories.CorretorVazaoRepository;
import com.stfn2.ggas.services.CorretorVazaoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/corretorVazao")
@Tag(name="CorretorVazao", description="EndPoints para gerenciamento de CorretorVazao")
public class CorretorVazaoController extends BaseController<CorretorVazao, CorretorVazaoDTO, CorretorVazaoBasicDTO, CorretorVazaoFilterDTO,
		CorretorVazaoRepository, CorretorVazaoService> {
}