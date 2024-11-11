package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.Pais;
import com.stfn2.ggas.domain.dtos.PaisDTO;
import com.stfn2.ggas.domain.dtos.filter.PaisFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.PaisBasicDTO;
import com.stfn2.ggas.repositories.PaisRepository;

@Service
public class PaisService extends BaseService<Pais, PaisDTO, PaisBasicDTO, PaisFilterDTO, PaisRepository> {

}

