package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.ClienteImovel;
import com.stfn2.ggas.domain.dtos.ClienteImovelDTO;
import com.stfn2.ggas.domain.dtos.filter.ClienteImovelFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.ClienteImovelBasicDTO;
import com.stfn2.ggas.repositories.ClienteImovelRepository;

@Service
public class ClienteImovelService extends BaseService<ClienteImovel, ClienteImovelDTO, ClienteImovelBasicDTO, ClienteImovelFilterDTO, ClienteImovelRepository> {

}