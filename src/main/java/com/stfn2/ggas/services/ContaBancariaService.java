package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.ContaBancaria;
import com.stfn2.ggas.domain.dtos.ContaBancariaDTO;
import com.stfn2.ggas.domain.dtos.filter.ContaBancariaFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.ContaBancariaBasicDTO;
import com.stfn2.ggas.repositories.ContaBancariaRepository;

@Service
public class ContaBancariaService extends BaseService<ContaBancaria, ContaBancariaDTO, ContaBancariaBasicDTO, ContaBancariaFilterDTO, ContaBancariaRepository> {

    public ContaBancaria getContaBancariaPorAgenciaId(Long agenciaId){
        return this.repository.getContaBancariaPorAgenciaId(agenciaId);
    }
}