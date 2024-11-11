package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.RubricaValorRegulamentado;
import com.stfn2.ggas.domain.dtos.RubricaValorRegulamentadoDTO;
import com.stfn2.ggas.domain.dtos.filter.RubricaValorRegulamentadoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.RubricaValorRegulamentadoBasicDTO;
import com.stfn2.ggas.repositories.RubricaValorRegulamentadoRepository;

@Service
public class RubricaValorRegulamentadoService extends BaseService<RubricaValorRegulamentado, RubricaValorRegulamentadoDTO, RubricaValorRegulamentadoBasicDTO, RubricaValorRegulamentadoFilterDTO, RubricaValorRegulamentadoRepository> {

}

