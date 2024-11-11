package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.MedidorCapacidade;
import com.stfn2.ggas.domain.dtos.MedidorCapacidadeDTO;
import com.stfn2.ggas.domain.dtos.filter.MedidorCapacidadeFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.MedidorCapacidadeBasicDTO;
import com.stfn2.ggas.repositories.MedidorCapacidadeRepository;

@Service
public class MedidorCapacidadeService extends BaseService<MedidorCapacidade, MedidorCapacidadeDTO, MedidorCapacidadeBasicDTO, MedidorCapacidadeFilterDTO, MedidorCapacidadeRepository> {

}