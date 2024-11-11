package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.ContratoPontoConsumoPCSIntervalo;
import com.stfn2.ggas.domain.dtos.ContratoPontoConsumoPCSIntervaloDTO;
import com.stfn2.ggas.domain.dtos.filter.ContratoPontoConsumoPCSIntervaloFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.ContratoPontoConsumoPCSIntervaloBasicDTO;
import com.stfn2.ggas.repositories.ContratoPontoConsumoPCSIntervaloRepository;

@Service
public class ContratoPontoConsumoPCSIntervaloService extends BaseService<ContratoPontoConsumoPCSIntervalo, ContratoPontoConsumoPCSIntervaloDTO, ContratoPontoConsumoPCSIntervaloBasicDTO, ContratoPontoConsumoPCSIntervaloFilterDTO, ContratoPontoConsumoPCSIntervaloRepository> {

}