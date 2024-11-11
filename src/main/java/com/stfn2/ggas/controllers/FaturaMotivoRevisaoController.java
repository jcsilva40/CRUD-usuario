package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.FaturaMotivoRevisao;
import com.stfn2.ggas.domain.dtos.FaturaMotivoRevisaoDTO;
import com.stfn2.ggas.domain.dtos.filter.FaturaMotivoRevisaoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.FaturaMotivoRevisaoBasicDTO;
import com.stfn2.ggas.repositories.FaturaMotivoRevisaoRepository;
import com.stfn2.ggas.services.FaturaMotivoRevisaoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/faturaMotivoRevisao")
@Tag(name="FaturaMotivoRevisao", description="EndPoints para gerenciamento de FaturaMotivoRevisao")
public class FaturaMotivoRevisaoController extends BaseController<FaturaMotivoRevisao, FaturaMotivoRevisaoDTO, FaturaMotivoRevisaoBasicDTO, FaturaMotivoRevisaoFilterDTO,
		FaturaMotivoRevisaoRepository, FaturaMotivoRevisaoService> {
}