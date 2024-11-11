package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.Nacionalidade;
import com.stfn2.ggas.domain.dtos.NacionalidadeDTO;
import com.stfn2.ggas.domain.dtos.filter.NacionalidadeFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.NacionalidadeBasicDTO;
import com.stfn2.ggas.repositories.NacionalidadeRepository;

@Service
public class NacionalidadeService extends BaseService<Nacionalidade, NacionalidadeDTO, NacionalidadeBasicDTO, NacionalidadeFilterDTO, NacionalidadeRepository> {

}