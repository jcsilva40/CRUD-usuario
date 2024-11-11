package com.stfn2.ggas.services;

import com.stfn2.ggas.domain.dtos.filter.EntidadeConteudoFilterDTO;
import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.EntidadeConteudo;
import com.stfn2.ggas.domain.dtos.EntidadeConteudoDTO;
import com.stfn2.ggas.domain.dtos.basic.EntidadeConteudoBasicDTO;
import com.stfn2.ggas.repositories.EntidadeConteudoRepository;

@Service
public class EntidadeConteudoService extends BaseService<EntidadeConteudo, EntidadeConteudoDTO, EntidadeConteudoBasicDTO,
        EntidadeConteudoFilterDTO, EntidadeConteudoRepository> {

}
