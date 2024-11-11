package com.stfn2.ggas.services;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.ArrecadadorContrato;
import com.stfn2.ggas.domain.dtos.ArrecadadorContratoDTO;
import com.stfn2.ggas.domain.dtos.basic.ArrecadadorContratoBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.ArrecadadorContratoFilterDTO;
import com.stfn2.ggas.repositories.ArrecadadorContratoRepository;
import org.springframework.stereotype.Service;

@Service
public class ArrecadadorContratoService extends BaseService<ArrecadadorContrato, ArrecadadorContratoDTO, ArrecadadorContratoBasicDTO, ArrecadadorContratoFilterDTO, ArrecadadorContratoRepository> {
}