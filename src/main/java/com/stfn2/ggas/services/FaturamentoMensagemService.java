package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.FaturamentoMensagem;
import com.stfn2.ggas.domain.dtos.FaturamentoMensagemDTO;
import com.stfn2.ggas.domain.dtos.filter.FaturamentoMensagemFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.FaturamentoMensagemBasicDTO;
import com.stfn2.ggas.repositories.FaturamentoMensagemRepository;

@Service
public class FaturamentoMensagemService extends BaseService<FaturamentoMensagem, FaturamentoMensagemDTO, FaturamentoMensagemBasicDTO, FaturamentoMensagemFilterDTO, FaturamentoMensagemRepository> {

}