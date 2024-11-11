package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.Permissao;
import com.stfn2.ggas.domain.dtos.PermissaoDTO;
import com.stfn2.ggas.domain.dtos.basic.PermissaoBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.PermissaoFilterDTO;
import com.stfn2.ggas.repositories.PermissaoRepository;

@Service
public class PermissaoService extends BaseService<Permissao, PermissaoDTO, PermissaoBasicDTO, PermissaoFilterDTO, PermissaoRepository> {

}