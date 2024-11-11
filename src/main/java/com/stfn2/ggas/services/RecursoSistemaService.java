package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.RecursoSistema;
import com.stfn2.ggas.domain.dtos.RecursoSistemaDTO;
import com.stfn2.ggas.domain.dtos.filter.RecursoSistemaFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.RecursoSistemaBasicDTO;
import com.stfn2.ggas.repositories.RecursoSistemaRepository;

@Service
public class RecursoSistemaService extends BaseService<RecursoSistema, RecursoSistemaDTO, RecursoSistemaBasicDTO, RecursoSistemaFilterDTO, RecursoSistemaRepository> {

}