package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.CreditoDebitoNegociado;
import com.stfn2.ggas.domain.dtos.CreditoDebitoNegociadoDTO;
import com.stfn2.ggas.domain.dtos.filter.CreditoDebitoNegociadoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.CreditoDebitoNegociadoBasicDTO;
import com.stfn2.ggas.repositories.CreditoDebitoNegociadoRepository;
import com.stfn2.ggas.services.CreditoDebitoNegociadoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/creditoDebitoNegociado")
@Tag(name="CreditoDebitoNegociado", description="EndPoints para gerenciamento de CreditoDebitoNegociado")
public class CreditoDebitoNegociadoController extends BaseController<CreditoDebitoNegociado, CreditoDebitoNegociadoDTO, CreditoDebitoNegociadoBasicDTO, CreditoDebitoNegociadoFilterDTO,
		CreditoDebitoNegociadoRepository, CreditoDebitoNegociadoService> {
}