package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.AtividadeSistema;
import com.stfn2.ggas.domain.dtos.AtividadeSistemaDTO;
import com.stfn2.ggas.domain.dtos.filter.AtividadeSistemaFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.AtividadeSistemaBasicDTO;
import com.stfn2.ggas.repositories.AtividadeSistemaRepository;

@Service
public class AtividadeSistemaService extends BaseService<AtividadeSistema, AtividadeSistemaDTO, AtividadeSistemaBasicDTO, AtividadeSistemaFilterDTO, AtividadeSistemaRepository> {

}