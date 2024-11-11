package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.ModuloSistema;
import com.stfn2.ggas.domain.dtos.ModuloSistemaDTO;
import com.stfn2.ggas.domain.dtos.filter.ModuloSistemaFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.ModuloSistemaBasicDTO;
import com.stfn2.ggas.repositories.ModuloSistemaRepository;

@Service
public class ModuloSistemaService extends BaseService<ModuloSistema, ModuloSistemaDTO, ModuloSistemaBasicDTO, ModuloSistemaFilterDTO, ModuloSistemaRepository> {

}