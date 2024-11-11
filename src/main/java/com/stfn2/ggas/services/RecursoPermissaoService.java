package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.RecursoPermissao;
import com.stfn2.ggas.domain.dtos.RecursoPermissaoDTO;
import com.stfn2.ggas.domain.dtos.basic.RecursoPermissaoBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.RecursoPermissaoFilterDTO;
import com.stfn2.ggas.repositories.RecursoPermissaoRepository;

@Service
public class RecursoPermissaoService extends BaseService<RecursoPermissao, RecursoPermissaoDTO, RecursoPermissaoBasicDTO, RecursoPermissaoFilterDTO, RecursoPermissaoRepository> {

}