package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.MedidorModelo;
import com.stfn2.ggas.domain.dtos.MedidorModeloDTO;
import com.stfn2.ggas.domain.dtos.filter.MedidorModeloFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.MedidorModeloBasicDTO;
import com.stfn2.ggas.repositories.MedidorModeloRepository;

@Service
public class MedidorModeloService extends BaseService<MedidorModelo, MedidorModeloDTO, MedidorModeloBasicDTO, MedidorModeloFilterDTO, MedidorModeloRepository> {

}