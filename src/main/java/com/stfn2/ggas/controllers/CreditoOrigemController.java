package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.CreditoOrigem;
import com.stfn2.ggas.domain.dtos.CreditoOrigemDTO;
import com.stfn2.ggas.domain.dtos.filter.CreditoOrigemFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.CreditoOrigemBasicDTO;
import com.stfn2.ggas.repositories.CreditoOrigemRepository;
import com.stfn2.ggas.services.CreditoOrigemService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/creditoOrigem")
@Tag(name="CreditoOrigem", description="EndPoints para gerenciamento de CreditoOrigem")
public class CreditoOrigemController extends BaseController<CreditoOrigem, CreditoOrigemDTO, CreditoOrigemBasicDTO, CreditoOrigemFilterDTO,
		CreditoOrigemRepository, CreditoOrigemService> {
}