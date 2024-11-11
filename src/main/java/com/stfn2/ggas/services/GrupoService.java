package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.Grupo;
import com.stfn2.ggas.domain.dtos.GrupoDTO;
import com.stfn2.ggas.domain.dtos.basic.GrupoBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.GrupoFilterDTO;
import com.stfn2.ggas.repositories.GrupoRepository;

@Service
public class GrupoService extends BaseService<Grupo, GrupoDTO, GrupoBasicDTO, GrupoFilterDTO, GrupoRepository> {

}