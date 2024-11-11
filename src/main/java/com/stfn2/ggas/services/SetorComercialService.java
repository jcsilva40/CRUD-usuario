package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.SetorComercial;
import com.stfn2.ggas.domain.dtos.SetorComercialDTO;
import com.stfn2.ggas.domain.dtos.basic.SetorComercialBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.SetorComercialFilterDTO;
import com.stfn2.ggas.repositories.SetorComercialRepository;

@Service
public class SetorComercialService extends BaseService<SetorComercial, SetorComercialDTO, SetorComercialBasicDTO, SetorComercialFilterDTO, SetorComercialRepository> {

}