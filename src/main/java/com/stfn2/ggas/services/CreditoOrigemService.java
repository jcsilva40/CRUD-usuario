package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.CreditoOrigem;
import com.stfn2.ggas.domain.dtos.CreditoOrigemDTO;
import com.stfn2.ggas.domain.dtos.filter.CreditoOrigemFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.CreditoOrigemBasicDTO;
import com.stfn2.ggas.repositories.CreditoOrigemRepository;

@Service
public class CreditoOrigemService extends BaseService<CreditoOrigem, CreditoOrigemDTO, CreditoOrigemBasicDTO, CreditoOrigemFilterDTO, CreditoOrigemRepository> {

}