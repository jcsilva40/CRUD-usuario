package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.NaturezaOperacao;
import com.stfn2.ggas.domain.dtos.NaturezaOperacaoDTO;
import com.stfn2.ggas.domain.dtos.filter.NaturezaOperacaoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.NaturezaOperacaoBasicDTO;
import com.stfn2.ggas.repositories.NaturezaOperacaoRepository;

@Service
public class NaturezaOperacaoService extends BaseService<NaturezaOperacao, NaturezaOperacaoDTO, NaturezaOperacaoBasicDTO, NaturezaOperacaoFilterDTO, NaturezaOperacaoRepository> {

}