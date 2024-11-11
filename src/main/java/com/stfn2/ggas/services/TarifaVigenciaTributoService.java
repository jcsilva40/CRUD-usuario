package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.TarifaVigenciaTributo;
import com.stfn2.ggas.domain.dtos.TarifaVigenciaTributoDTO;
import com.stfn2.ggas.domain.dtos.filter.TarifaVigenciaTributoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.TarifaVigenciaTributoBasicDTO;
import com.stfn2.ggas.repositories.TarifaVigenciaTributoRepository;

@Service
public class TarifaVigenciaTributoService extends BaseService<TarifaVigenciaTributo, TarifaVigenciaTributoDTO, TarifaVigenciaTributoBasicDTO, TarifaVigenciaTributoFilterDTO, TarifaVigenciaTributoRepository> {

}

