package com.stfn2.ggas.services;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.Cep;
import com.stfn2.ggas.domain.Municipio;
import com.stfn2.ggas.domain.dtos.CepDTO;
import com.stfn2.ggas.domain.dtos.basic.CepBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.CepFilterDTO;
import com.stfn2.ggas.repositories.CepRepository;
import com.stfn2.ggas.webservices.cep.interfaces.CepInsterfaceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CepService extends BaseService<Cep, CepDTO, CepBasicDTO, CepFilterDTO, CepRepository> {

    @Autowired
    private MunicipioService municipioService;

    public Cep getOrCreate(CepInsterfaceDTO endereco) {
        Long idCep = Long.parseLong(endereco.getCep().replaceAll("-", ""));
        Optional<Cep> cepOp = repository.findById(idCep);
        if (cepOp.isPresent()) {
            return cepOp.get();
        }

        Cep cep = new Cep();
        cep.setId(idCep);
        cep.setNumeroCep(endereco.getCep());
        cep.setLogradouro(endereco.getLogradouro());
        cep.setBairro(endereco.getBairro());
        Municipio municipio = municipioService.getOrCreate(endereco);
        cep.setMunicipio(municipio);
        return save(cep);
    }

    public Cep findByCep(String cepStr) {
        Cep cep = repository.findByCep(Long.parseLong(cepStr.replace("-", "")));
        return cep;
    }
}

