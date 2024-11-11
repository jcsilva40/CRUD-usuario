package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.TarifaVigenciaDesconto;
import com.stfn2.ggas.domain.dtos.TarifaVigenciaDescontoDTO;
import com.stfn2.ggas.domain.dtos.filter.TarifaVigenciaDescontoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.TarifaVigenciaDescontoBasicDTO;
import com.stfn2.ggas.repositories.TarifaVigenciaDescontoRepository;

@Service
public class TarifaVigenciaDescontoService extends BaseService<TarifaVigenciaDesconto, TarifaVigenciaDescontoDTO, TarifaVigenciaDescontoBasicDTO, TarifaVigenciaDescontoFilterDTO, TarifaVigenciaDescontoRepository> {

}

