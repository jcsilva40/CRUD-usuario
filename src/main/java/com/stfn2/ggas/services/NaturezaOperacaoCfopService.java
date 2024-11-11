package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.NaturezaOperacaoCfop;
import com.stfn2.ggas.domain.dtos.NaturezaOperacaoCfopDTO;
import com.stfn2.ggas.domain.dtos.filter.NaturezaOperacaoCfopFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.NaturezaOperacaoCfopBasicDTO;
import com.stfn2.ggas.repositories.NaturezaOperacaoCfopRepository;

@Service
public class NaturezaOperacaoCfopService extends BaseService<NaturezaOperacaoCfop, NaturezaOperacaoCfopDTO, NaturezaOperacaoCfopBasicDTO, NaturezaOperacaoCfopFilterDTO, NaturezaOperacaoCfopRepository> {

}