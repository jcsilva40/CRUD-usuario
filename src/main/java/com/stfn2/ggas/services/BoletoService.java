package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.Boleto;
import com.stfn2.ggas.domain.dtos.BoletoDTO;
import com.stfn2.ggas.domain.dtos.basic.BoletoBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.BoletoFilterDTO;
import com.stfn2.ggas.repositories.BoletoRepository;

@Service
public class BoletoService extends BaseService<Boleto, BoletoDTO, BoletoBasicDTO, BoletoFilterDTO, BoletoRepository> {

}