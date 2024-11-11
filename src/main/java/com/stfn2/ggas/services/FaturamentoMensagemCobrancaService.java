package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.FaturamentoMensagemCobranca;
import com.stfn2.ggas.domain.dtos.FaturamentoMensagemCobrancaDTO;
import com.stfn2.ggas.domain.dtos.filter.FaturamentoMensagemCobrancaFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.FaturamentoMensagemCobrancaBasicDTO;
import com.stfn2.ggas.repositories.FaturamentoMensagemCobrancaRepository;

@Service
public class FaturamentoMensagemCobrancaService extends BaseService<FaturamentoMensagemCobranca, FaturamentoMensagemCobrancaDTO, FaturamentoMensagemCobrancaBasicDTO, FaturamentoMensagemCobrancaFilterDTO, FaturamentoMensagemCobrancaRepository> {

}