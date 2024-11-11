package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.CreditoDebitoDetalhamento;
import com.stfn2.ggas.domain.dtos.filter.CreditoDebitoDetalhamentoFilterDTO;
import com.stfn2.ggas.domain.dtos.CreditoDebitoDetalhamentoDTO;
import com.stfn2.ggas.domain.dtos.basic.CreditoDebitoDetalhamentoBasicDTO;
import com.stfn2.ggas.repositories.CreditoDebitoDetalhamentoRepository;
import com.stfn2.ggas.services.CreditoDebitoDetalhamentoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/creditoDebitoDetalhado")
@Tag(name="CreditoDebitoDetalhado", description="EndPoints para gerenciamento de CreditoDebitoDetalhado")
public class CreditoDebitoDetalhamentoController extends BaseController<CreditoDebitoDetalhamento,
        CreditoDebitoDetalhamentoDTO, CreditoDebitoDetalhamentoBasicDTO, CreditoDebitoDetalhamentoFilterDTO,
        CreditoDebitoDetalhamentoRepository, CreditoDebitoDetalhamentoService> {
}