package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.TarifaFaixaDesconto;
import com.stfn2.ggas.domain.dtos.TarifaFaixaDescontoDTO;
import com.stfn2.ggas.domain.dtos.filter.TarifaFaixaDescontoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.TarifaFaixaDescontoBasicDTO;
import com.stfn2.ggas.repositories.TarifaFaixaDescontoRepository;

@Service
public class TarifaFaixaDescontoService extends BaseService<TarifaFaixaDesconto, TarifaFaixaDescontoDTO, TarifaFaixaDescontoBasicDTO, TarifaFaixaDescontoFilterDTO, TarifaFaixaDescontoRepository> {

}

