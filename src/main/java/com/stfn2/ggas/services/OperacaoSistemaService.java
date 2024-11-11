package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.OperacaoSistema;
import com.stfn2.ggas.domain.dtos.OperacaoSistemaDTO;
import com.stfn2.ggas.domain.dtos.filter.OperacaoSistemaFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.OperacaoSistemaBasicDTO;
import com.stfn2.ggas.repositories.OperacaoSistemaRepository;

@Service
public class OperacaoSistemaService extends BaseService<OperacaoSistema, OperacaoSistemaDTO, OperacaoSistemaBasicDTO, OperacaoSistemaFilterDTO, OperacaoSistemaRepository> {

}