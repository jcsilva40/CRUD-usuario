package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.UnidadeTipo;
import com.stfn2.ggas.domain.dtos.UnidadeTipoDTO;
import com.stfn2.ggas.domain.dtos.filter.UnidadeTipoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.UnidadeTipoBasicDTO;
import com.stfn2.ggas.repositories.UnidadeTipoRepository;

@Service
public class UnidadeTipoService extends BaseService<UnidadeTipo, UnidadeTipoDTO, UnidadeTipoBasicDTO, UnidadeTipoFilterDTO, UnidadeTipoRepository> {

}

