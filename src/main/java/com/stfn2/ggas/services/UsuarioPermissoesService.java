package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.UsuarioPermissoes;
import com.stfn2.ggas.domain.dtos.UsuarioPermissoesDTO;
import com.stfn2.ggas.domain.dtos.basic.UsuarioPermissoesBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.UsuarioPermissoesFilterDTO;
import com.stfn2.ggas.repositories.UsuarioPermissoesRepository;

@Service
public class UsuarioPermissoesService extends BaseService<UsuarioPermissoes, UsuarioPermissoesDTO, UsuarioPermissoesBasicDTO, UsuarioPermissoesFilterDTO, UsuarioPermissoesRepository> {

}