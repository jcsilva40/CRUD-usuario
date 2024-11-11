package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.LeituraTipo;
import com.stfn2.ggas.domain.dtos.LeituraTipoDTO;
import com.stfn2.ggas.domain.dtos.filter.LeituraTipoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.LeituraTipoBasicDTO;
import com.stfn2.ggas.repositories.LeituraTipoRepository;

@Service
public class LeituraTipoService extends BaseService<LeituraTipo, LeituraTipoDTO, LeituraTipoBasicDTO, LeituraTipoFilterDTO, LeituraTipoRepository> {

}

