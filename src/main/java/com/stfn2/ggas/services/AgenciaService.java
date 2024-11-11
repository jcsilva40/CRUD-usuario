package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.Agencia;
import com.stfn2.ggas.domain.dtos.AgenciaDTO;
import com.stfn2.ggas.domain.dtos.filter.AgenciaFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.AgenciaBasicDTO;
import com.stfn2.ggas.repositories.AgenciaRepository;

@Service
public class AgenciaService extends BaseService<Agencia, AgenciaDTO, AgenciaBasicDTO, AgenciaFilterDTO, AgenciaRepository> {

    public Agencia getAgenciaPorBancoId(Long bancoId){
        return this.repository.getAgenciaPorBancoId(bancoId);
    }
}