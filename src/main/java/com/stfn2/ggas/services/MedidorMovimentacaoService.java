package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.MedidorMovimentacao;
import com.stfn2.ggas.domain.dtos.MedidorMovimentacaoDTO;
import com.stfn2.ggas.domain.dtos.filter.MedidorMovimentacaoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.MedidorMovimentacaoBasicDTO;
import com.stfn2.ggas.repositories.MedidorMovimentacaoRepository;

@Service
public class MedidorMovimentacaoService extends BaseService<MedidorMovimentacao, MedidorMovimentacaoDTO, MedidorMovimentacaoBasicDTO, MedidorMovimentacaoFilterDTO, MedidorMovimentacaoRepository> {

}