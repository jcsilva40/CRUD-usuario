package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.ContratoPontoConsumoPCSAmostragem;
import com.stfn2.ggas.domain.dtos.ContratoPontoConsumoPCSAmostragemDTO;
import com.stfn2.ggas.domain.dtos.filter.ContratoPontoConsumoPCSAmostragemFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.ContratoPontoConsumoPCSAmostragemBasicDTO;
import com.stfn2.ggas.repositories.ContratoPontoConsumoPCSAmostragemRepository;

@Service
public class ContratoPontoConsumoPCSAmostragemService extends BaseService<ContratoPontoConsumoPCSAmostragem, ContratoPontoConsumoPCSAmostragemDTO, ContratoPontoConsumoPCSAmostragemBasicDTO, ContratoPontoConsumoPCSAmostragemFilterDTO, ContratoPontoConsumoPCSAmostragemRepository> {

}