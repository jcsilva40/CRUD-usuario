package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.LancamentoItemContabil;
import com.stfn2.ggas.domain.dtos.LancamentoItemContabilDTO;
import com.stfn2.ggas.domain.dtos.filter.LancamentoItemContabilFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.LancamentoItemContabilBasicDTO;
import com.stfn2.ggas.repositories.LancamentoItemContabilRepository;
import com.stfn2.ggas.services.LancamentoItemContabilService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/lancamentoItemContabil")
@Tag(name="LancamentoItemContabil", description="EndPoints para gerenciamento de LancamentoItemContabil")
public class LancamentoItemContabilController extends BaseController<LancamentoItemContabil, LancamentoItemContabilDTO, LancamentoItemContabilBasicDTO, LancamentoItemContabilFilterDTO,
		LancamentoItemContabilRepository, LancamentoItemContabilService> {
}