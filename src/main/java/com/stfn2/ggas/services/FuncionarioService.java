package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.Funcionario;
import com.stfn2.ggas.domain.dtos.FuncionarioDTO;
import com.stfn2.ggas.domain.dtos.filter.FuncionarioFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.FuncionarioBasicDTO;
import com.stfn2.ggas.repositories.FuncionarioRepository;

@Service
public class FuncionarioService extends BaseService<Funcionario, FuncionarioDTO, FuncionarioBasicDTO, FuncionarioFilterDTO, FuncionarioRepository> {

}

