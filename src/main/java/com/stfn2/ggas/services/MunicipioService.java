package com.stfn2.ggas.services;

import com.stfn2.ggas.domain.UnidadeFederacao;
import com.stfn2.ggas.webservices.cep.interfaces.CepInsterfaceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.Municipio;
import com.stfn2.ggas.domain.dtos.MunicipioDTO;
import com.stfn2.ggas.domain.dtos.filter.MunicipioFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.MunicipioBasicDTO;
import com.stfn2.ggas.repositories.MunicipioRepository;

import java.util.Optional;

@Service
public class MunicipioService extends BaseService<Municipio, MunicipioDTO, MunicipioBasicDTO, MunicipioFilterDTO, MunicipioRepository> {

    @Autowired
    private UnidadeFederacaoService unidadeFederacaoService;

    public Municipio getOrCreate(CepInsterfaceDTO endereco) {
        Integer codIbge = Integer.parseInt(endereco.getIbge());
        Optional<Municipio> op = repository.findByCodIbge(codIbge);
        if (op.isPresent()) {
            return op.get();
        }

        Municipio municipio = new Municipio();
        municipio.setDDD(Integer.parseInt(endereco.getDdd()));
        municipio.setDescricao(endereco.getCidade());

        UnidadeFederacao unidadeFederacao = unidadeFederacaoService.findbySigla(endereco.getUf());
        municipio.setUf(unidadeFederacao);
        return save(municipio);

    }
}

