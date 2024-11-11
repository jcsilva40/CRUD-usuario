package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.UsuarioGrupo;
import com.stfn2.ggas.domain.dtos.UsuarioGrupoDTO;
import com.stfn2.ggas.domain.dtos.basic.UsuarioGrupoBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.UsuarioGrupoFilterDTO;
import com.stfn2.ggas.repositories.UsuarioGrupoRepository;

@Service
public class UsuarioGrupoService extends BaseService<UsuarioGrupo, UsuarioGrupoDTO, UsuarioGrupoBasicDTO, UsuarioGrupoFilterDTO, UsuarioGrupoRepository> {

}