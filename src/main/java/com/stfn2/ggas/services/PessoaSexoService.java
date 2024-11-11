package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.PessoaSexo;
import com.stfn2.ggas.domain.dtos.PessoaSexoDTO;
import com.stfn2.ggas.domain.dtos.filter.PessoaSexoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.PessoaSexoBasicDTO;
import com.stfn2.ggas.repositories.PessoaSexoRepository;

@Service
public class PessoaSexoService extends BaseService<PessoaSexo, PessoaSexoDTO, PessoaSexoBasicDTO, PessoaSexoFilterDTO, PessoaSexoRepository> {

}