package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.Recurso;
import com.stfn2.ggas.domain.dtos.RecursoDTO;
import com.stfn2.ggas.domain.dtos.basic.RecursoBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.RecursoFilterDTO;
import com.stfn2.ggas.repositories.RecursoRepository;

@Service
public class RecursoService extends BaseService<Recurso, RecursoDTO, RecursoBasicDTO, RecursoFilterDTO, RecursoRepository> {

}