package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.UnidadeFederacao;
import com.stfn2.ggas.domain.dtos.UnidadeFederacaoDTO;
import com.stfn2.ggas.domain.dtos.filter.UnidadeFederacaoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.UnidadeFederacaoBasicDTO;
import com.stfn2.ggas.repositories.UnidadeFederacaoRepository;

@Service
public class UnidadeFederacaoService extends BaseService<UnidadeFederacao, UnidadeFederacaoDTO, UnidadeFederacaoBasicDTO, UnidadeFederacaoFilterDTO, UnidadeFederacaoRepository> {

    public UnidadeFederacao findbySigla(String uf) {
        return repository.findBySigla(uf);
    }
}

