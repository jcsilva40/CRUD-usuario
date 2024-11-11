package com.stfn2.ggas.services;

import com.stfn2.ggas.domain.dtos.filter.ContaContabilFilterDTO;
import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.ContaContabil;
import com.stfn2.ggas.domain.dtos.ContaContabilDTO;
import com.stfn2.ggas.domain.dtos.basic.ContaContabilBasicDTO;
import com.stfn2.ggas.repositories.ContaContabilRepository;

@Service
public class ContaContabilService extends BaseService<ContaContabil, ContaContabilDTO, ContaContabilBasicDTO,
        ContaContabilFilterDTO, ContaContabilRepository> {

}

