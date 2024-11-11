package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.ContratoCliente;
import com.stfn2.ggas.domain.dtos.ContratoClienteDTO;
import com.stfn2.ggas.domain.dtos.filter.ContratoClienteFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.ContratoClienteBasicDTO;
import com.stfn2.ggas.repositories.ContratoClienteRepository;

@Service
public class ContratoClienteService extends BaseService<ContratoCliente, ContratoClienteDTO, ContratoClienteBasicDTO, ContratoClienteFilterDTO, ContratoClienteRepository> {

}