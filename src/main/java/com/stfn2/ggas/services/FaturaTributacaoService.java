package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.FaturaTributacao;
import com.stfn2.ggas.domain.dtos.FaturaTributacaoDTO;
import com.stfn2.ggas.domain.dtos.filter.FaturaTributacaoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.FaturaTributacaoBasicDTO;
import com.stfn2.ggas.repositories.FaturaTributacaoRepository;

@Service
public class FaturaTributacaoService extends BaseService<FaturaTributacao, FaturaTributacaoDTO, FaturaTributacaoBasicDTO, FaturaTributacaoFilterDTO, FaturaTributacaoRepository> {

}