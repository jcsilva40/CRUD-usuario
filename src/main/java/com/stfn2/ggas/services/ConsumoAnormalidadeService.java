package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.ConsumoAnormalidade;
import com.stfn2.ggas.domain.dtos.ConsumoAnormalidadeDTO;
import com.stfn2.ggas.domain.dtos.basic.ConsumoAnormalidadeBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.ConsumoAnormalidadeFilterDTO;
import com.stfn2.ggas.repositories.ConsumoAnormalidadeRepository;

@Service
public class ConsumoAnormalidadeService extends BaseService<ConsumoAnormalidade, ConsumoAnormalidadeDTO, ConsumoAnormalidadeBasicDTO, ConsumoAnormalidadeFilterDTO, ConsumoAnormalidadeRepository> {

}