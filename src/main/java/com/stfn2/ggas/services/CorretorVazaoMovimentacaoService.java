package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.CorretorVazaoMovimentacao;
import com.stfn2.ggas.domain.dtos.CorretorVazaoMovimentacaoDTO;
import com.stfn2.ggas.domain.dtos.filter.CorretorVazaoMovimentacaoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.CorretorVazaoMovimentacaoBasicDTO;
import com.stfn2.ggas.repositories.CorretorVazaoMovimentacaoRepository;

@Service
public class CorretorVazaoMovimentacaoService extends BaseService<CorretorVazaoMovimentacao, CorretorVazaoMovimentacaoDTO, CorretorVazaoMovimentacaoBasicDTO, CorretorVazaoMovimentacaoFilterDTO, CorretorVazaoMovimentacaoRepository> {

}