package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.TarifaPontoConsumo;
import com.stfn2.ggas.domain.dtos.TarifaPontoConsumoDTO;
import com.stfn2.ggas.domain.dtos.filter.TarifaPontoConsumoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.TarifaPontoConsumoBasicDTO;
import com.stfn2.ggas.repositories.TarifaPontoConsumoRepository;

@Service
public class TarifaPontoConsumoService extends BaseService<TarifaPontoConsumo, TarifaPontoConsumoDTO, TarifaPontoConsumoBasicDTO, TarifaPontoConsumoFilterDTO, TarifaPontoConsumoRepository> {

}

