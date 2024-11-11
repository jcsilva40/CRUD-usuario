package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.Devolucao;
import com.stfn2.ggas.domain.dtos.DevolucaoDTO;
import com.stfn2.ggas.domain.dtos.filter.DevolucaoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.DevolucaoBasicDTO;
import com.stfn2.ggas.repositories.DevolucaoRepository;

@Service
public class DevolucaoService extends BaseService<Devolucao, DevolucaoDTO, DevolucaoBasicDTO, DevolucaoFilterDTO, DevolucaoRepository> {

}