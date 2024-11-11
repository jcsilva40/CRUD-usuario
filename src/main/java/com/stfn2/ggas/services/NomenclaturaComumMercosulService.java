package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.NomenclaturaComumMercosul;
import com.stfn2.ggas.domain.dtos.NomenclaturaComumMercosulDTO;
import com.stfn2.ggas.domain.dtos.filter.NomenclaturaComumMercosulFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.NomenclaturaComumMercosulBasicDTO;
import com.stfn2.ggas.repositories.NomenclaturaComumMercosulRepository;

@Service
public class NomenclaturaComumMercosulService extends BaseService<NomenclaturaComumMercosul, NomenclaturaComumMercosulDTO, NomenclaturaComumMercosulBasicDTO, NomenclaturaComumMercosulFilterDTO, NomenclaturaComumMercosulRepository> {

}

