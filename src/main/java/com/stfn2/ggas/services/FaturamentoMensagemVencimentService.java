package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.FaturamentoMensagemVenciment;
import com.stfn2.ggas.domain.dtos.FaturamentoMensagemVencimentDTO;
import com.stfn2.ggas.domain.dtos.filter.FaturamentoMensagemVencimentFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.FaturamentoMensagemVencimentBasicDTO;
import com.stfn2.ggas.repositories.FaturamentoMensagemVencimentRepository;

@Service
public class FaturamentoMensagemVencimentService extends BaseService<FaturamentoMensagemVenciment, FaturamentoMensagemVencimentDTO, FaturamentoMensagemVencimentBasicDTO, FaturamentoMensagemVencimentFilterDTO, FaturamentoMensagemVencimentRepository> {

}