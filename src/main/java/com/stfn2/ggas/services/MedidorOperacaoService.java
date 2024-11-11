package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.MedidorOperacao;
import com.stfn2.ggas.domain.dtos.MedidorOperacaoDTO;
import com.stfn2.ggas.domain.dtos.filter.MedidorOperacaoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.MedidorOperacaoBasicDTO;
import com.stfn2.ggas.repositories.MedidorOperacaoRepository;

@Service
public class MedidorOperacaoService extends BaseService<MedidorOperacao, MedidorOperacaoDTO, MedidorOperacaoBasicDTO, MedidorOperacaoFilterDTO, MedidorOperacaoRepository> {

}