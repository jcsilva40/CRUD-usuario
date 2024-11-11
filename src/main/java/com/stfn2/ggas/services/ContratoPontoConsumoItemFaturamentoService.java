package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.ContratoPontoConsumoItemFaturamento;
import com.stfn2.ggas.domain.dtos.ContratoPontoConsumoItemFaturamentoDTO;
import com.stfn2.ggas.domain.dtos.filter.ContratoPontoConsumoItemFaturamentoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.ContratoPontoConsumoItemFaturamentoBasicDTO;
import com.stfn2.ggas.repositories.ContratoPontoConsumoItemFaturamentoRepository;

@Service
public class ContratoPontoConsumoItemFaturamentoService extends BaseService<ContratoPontoConsumoItemFaturamento, ContratoPontoConsumoItemFaturamentoDTO, ContratoPontoConsumoItemFaturamentoBasicDTO, ContratoPontoConsumoItemFaturamentoFilterDTO, ContratoPontoConsumoItemFaturamentoRepository> {

}