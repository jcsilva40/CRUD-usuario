package com.stfn2.ggas.services;

import com.stfn2.ggas.domain.dtos.filter.IndiceFinanceiroFilterDTO;
import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.IndiceFinanceiro;
import com.stfn2.ggas.domain.dtos.IndiceFinanceiroDTO;
import com.stfn2.ggas.domain.dtos.basic.IndiceFinanceiroBasicDTO;
import com.stfn2.ggas.repositories.IndiceFinanceiroRepository;

@Service
public class IndiceFinanceiroService extends BaseService<IndiceFinanceiro, IndiceFinanceiroDTO, IndiceFinanceiroBasicDTO,
        IndiceFinanceiroFilterDTO, IndiceFinanceiroRepository> {

}
