package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.MedidorDiametro;
import com.stfn2.ggas.domain.dtos.MedidorDiametroDTO;
import com.stfn2.ggas.domain.dtos.filter.MedidorDiametroFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.MedidorDiametroBasicDTO;
import com.stfn2.ggas.repositories.MedidorDiametroRepository;

@Service
public class MedidorDiametroService extends BaseService<MedidorDiametro, MedidorDiametroDTO, MedidorDiametroBasicDTO, MedidorDiametroFilterDTO, MedidorDiametroRepository> {

}