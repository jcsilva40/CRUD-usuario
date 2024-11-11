package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.Unidade;
import com.stfn2.ggas.domain.dtos.UnidadeDTO;
import com.stfn2.ggas.domain.dtos.filter.UnidadeFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.UnidadeBasicDTO;
import com.stfn2.ggas.repositories.UnidadeRepository;

@Service
public class UnidadeService extends BaseService<Unidade, UnidadeDTO, UnidadeBasicDTO, UnidadeFilterDTO, UnidadeRepository> {

}