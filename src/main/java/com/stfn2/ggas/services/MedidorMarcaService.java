package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.MedidorMarca;
import com.stfn2.ggas.domain.dtos.MedidorMarcaDTO;
import com.stfn2.ggas.domain.dtos.filter.MedidorMarcaFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.MedidorMarcaBasicDTO;
import com.stfn2.ggas.repositories.MedidorMarcaRepository;

@Service
public class MedidorMarcaService extends BaseService<MedidorMarca, MedidorMarcaDTO, MedidorMarcaBasicDTO, MedidorMarcaFilterDTO, MedidorMarcaRepository> {

}