package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.CreditoDebitoDetalhamento;
import com.stfn2.ggas.domain.dtos.CreditoDebitoDetalhamentoDTO;
import com.stfn2.ggas.domain.dtos.filter.CreditoDebitoDetalhamentoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.CreditoDebitoDetalhamentoBasicDTO;
import com.stfn2.ggas.repositories.CreditoDebitoDetalhamentoRepository;

@Service
public class CreditoDebitoDetalhamentoService extends BaseService<CreditoDebitoDetalhamento, CreditoDebitoDetalhamentoDTO, CreditoDebitoDetalhamentoBasicDTO,
        CreditoDebitoDetalhamentoFilterDTO, CreditoDebitoDetalhamentoRepository> {

}