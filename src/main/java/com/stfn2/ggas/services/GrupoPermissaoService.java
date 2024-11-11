package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.GrupoPermissao;
import com.stfn2.ggas.domain.dtos.GrupoPermissaoDTO;
import com.stfn2.ggas.domain.dtos.basic.GrupoPermissaoBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.GrupoPermissaoFilterDTO;
import com.stfn2.ggas.repositories.GrupoPermissaoRepository;

@Service
public class GrupoPermissaoService extends BaseService<GrupoPermissao, GrupoPermissaoDTO, GrupoPermissaoBasicDTO, GrupoPermissaoFilterDTO, GrupoPermissaoRepository> {

}