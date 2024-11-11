package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.CreditoDebitoSituacao;
import com.stfn2.ggas.domain.dtos.CreditoDebitoSituacaoDTO;
import com.stfn2.ggas.domain.dtos.filter.CreditoDebitoSituacaoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.CreditoDebitoSituacaoBasicDTO;
import com.stfn2.ggas.repositories.CreditoDebitoSituacaoRepository;

@Service
public class CreditoDebitoSituacaoService extends BaseService<CreditoDebitoSituacao, CreditoDebitoSituacaoDTO, CreditoDebitoSituacaoBasicDTO, CreditoDebitoSituacaoFilterDTO, CreditoDebitoSituacaoRepository> {

}