package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.FaturamentoMensagemSegmento;
import com.stfn2.ggas.domain.dtos.FaturamentoMensagemSegmentoDTO;
import com.stfn2.ggas.domain.dtos.filter.FaturamentoMensagemSegmentoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.FaturamentoMensagemSegmentoBasicDTO;
import com.stfn2.ggas.repositories.FaturamentoMensagemSegmentoRepository;

@Service
public class FaturamentoMensagemSegmentoService extends BaseService<FaturamentoMensagemSegmento, FaturamentoMensagemSegmentoDTO, FaturamentoMensagemSegmentoBasicDTO, FaturamentoMensagemSegmentoFilterDTO, FaturamentoMensagemSegmentoRepository> {

}