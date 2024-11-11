package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.FaturaItemTributacao;
import com.stfn2.ggas.domain.dtos.FaturaItemTributacaoDTO;
import com.stfn2.ggas.domain.dtos.filter.FaturaItemTributacaoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.FaturaItemTributacaoBasicDTO;
import com.stfn2.ggas.repositories.FaturaItemTributacaoRepository;

@Service
public class FaturaItemTributacaoService extends BaseService<FaturaItemTributacao, FaturaItemTributacaoDTO, FaturaItemTributacaoBasicDTO, FaturaItemTributacaoFilterDTO, FaturaItemTributacaoRepository> {

}