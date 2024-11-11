package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.TarifaVigenciaFaixa;
import com.stfn2.ggas.domain.dtos.TarifaVigenciaFaixaDTO;
import com.stfn2.ggas.domain.dtos.filter.TarifaVigenciaFaixaFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.TarifaVigenciaFaixaBasicDTO;
import com.stfn2.ggas.repositories.TarifaVigenciaFaixaRepository;

@Service
public class TarifaVigenciaFaixaService extends BaseService<TarifaVigenciaFaixa, TarifaVigenciaFaixaDTO, TarifaVigenciaFaixaBasicDTO, TarifaVigenciaFaixaFilterDTO, TarifaVigenciaFaixaRepository> {

}

