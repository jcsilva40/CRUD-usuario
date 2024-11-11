package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.FinanciamentoTipo;
import com.stfn2.ggas.domain.dtos.FinanciamentoTipoDTO;
import com.stfn2.ggas.domain.dtos.filter.FinanciamentoTipoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.FinanciamentoTipoBasicDTO;
import com.stfn2.ggas.repositories.FinanciamentoTipoRepository;

@Service
public class FinanciamentoTipoService extends BaseService<FinanciamentoTipo, FinanciamentoTipoDTO, FinanciamentoTipoBasicDTO, FinanciamentoTipoFilterDTO, FinanciamentoTipoRepository> {

}

