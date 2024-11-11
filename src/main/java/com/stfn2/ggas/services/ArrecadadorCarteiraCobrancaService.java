package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.ArrecadadorCarteiraCobranca;
import com.stfn2.ggas.domain.dtos.ArrecadadorCarteiraCobrancaDTO;
import com.stfn2.ggas.domain.dtos.filter.ArrecadadorCarteiraCobrancaFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.ArrecadadorCarteiraCobrancaBasicDTO;
import com.stfn2.ggas.repositories.ArrecadadorCarteiraCobrancaRepository;

@Service
public class ArrecadadorCarteiraCobrancaService extends BaseService<ArrecadadorCarteiraCobranca, ArrecadadorCarteiraCobrancaDTO, ArrecadadorCarteiraCobrancaBasicDTO, ArrecadadorCarteiraCobrancaFilterDTO, ArrecadadorCarteiraCobrancaRepository> {

}