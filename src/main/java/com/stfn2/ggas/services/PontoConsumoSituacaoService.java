package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.PontoConsumoSituacao;
import com.stfn2.ggas.domain.dtos.PontoConsumoSituacaoDTO;
import com.stfn2.ggas.domain.dtos.filter.PontoConsumoSituacaoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.PontoConsumoSituacaoBasicDTO;
import com.stfn2.ggas.repositories.PontoConsumoSituacaoRepository;

@Service
public class PontoConsumoSituacaoService extends BaseService<PontoConsumoSituacao, PontoConsumoSituacaoDTO, PontoConsumoSituacaoBasicDTO, PontoConsumoSituacaoFilterDTO, PontoConsumoSituacaoRepository> {

}

