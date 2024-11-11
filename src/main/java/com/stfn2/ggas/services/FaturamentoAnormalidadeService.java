package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.FaturamentoAnormalidade;
import com.stfn2.ggas.domain.dtos.FaturamentoAnormalidadeDTO;
import com.stfn2.ggas.domain.dtos.filter.FaturamentoAnormalidadeFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.FaturamentoAnormalidadeBasicDTO;
import com.stfn2.ggas.repositories.FaturamentoAnormalidadeRepository;

@Service
public class FaturamentoAnormalidadeService extends BaseService<FaturamentoAnormalidade, FaturamentoAnormalidadeDTO, FaturamentoAnormalidadeBasicDTO, FaturamentoAnormalidadeFilterDTO, FaturamentoAnormalidadeRepository> {

}