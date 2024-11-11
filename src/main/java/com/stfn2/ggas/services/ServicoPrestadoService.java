package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.ServicoPrestado;
import com.stfn2.ggas.domain.dtos.ServicoPrestadoDTO;
import com.stfn2.ggas.domain.dtos.filter.ServicoPrestadoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.ServicoPrestadoBasicDTO;
import com.stfn2.ggas.repositories.ServicoPrestadoRepository;

@Service
public class ServicoPrestadoService extends BaseService<ServicoPrestado, ServicoPrestadoDTO, ServicoPrestadoBasicDTO, ServicoPrestadoFilterDTO, ServicoPrestadoRepository> {

}

