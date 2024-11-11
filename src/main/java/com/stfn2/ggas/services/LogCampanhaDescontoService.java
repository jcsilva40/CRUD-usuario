package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.LogCampanhaDesconto;
import com.stfn2.ggas.domain.dtos.LogCampanhaDescontoDTO;
import com.stfn2.ggas.domain.dtos.basic.LogCampanhaDescontoBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.LogCampanhaDescontoFilterDTO;
import com.stfn2.ggas.repositories.LogCampanhaDescontoRepository;

@Service
public class LogCampanhaDescontoService extends BaseService<LogCampanhaDesconto, LogCampanhaDescontoDTO, LogCampanhaDescontoBasicDTO, LogCampanhaDescontoFilterDTO, LogCampanhaDescontoRepository> {

}