package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.MedidorMotivoOperacao;
import com.stfn2.ggas.domain.dtos.MedidorMotivoOperacaoDTO;
import com.stfn2.ggas.domain.dtos.filter.MedidorMotivoOperacaoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.MedidorMotivoOperacaoBasicDTO;
import com.stfn2.ggas.repositories.MedidorMotivoOperacaoRepository;

@Service
public class MedidorMotivoOperacaoService extends BaseService<MedidorMotivoOperacao, MedidorMotivoOperacaoDTO, MedidorMotivoOperacaoBasicDTO, MedidorMotivoOperacaoFilterDTO, MedidorMotivoOperacaoRepository> {

}