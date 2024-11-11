package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.FaturamentoAnomaliaHistorico;
import com.stfn2.ggas.domain.dtos.FaturamentoAnomaliaHistoricoDTO;
import com.stfn2.ggas.domain.dtos.filter.FaturamentoAnomaliaHistoricoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.FaturamentoAnomaliaHistoricoBasicDTO;
import com.stfn2.ggas.repositories.FaturamentoAnomaliaHistoricoRepository;

@Service
public class FaturamentoAnomaliaHistoricoService extends BaseService<FaturamentoAnomaliaHistorico, FaturamentoAnomaliaHistoricoDTO, FaturamentoAnomaliaHistoricoBasicDTO, FaturamentoAnomaliaHistoricoFilterDTO, FaturamentoAnomaliaHistoricoRepository> {

}