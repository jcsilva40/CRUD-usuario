package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.Leiturista;
import com.stfn2.ggas.domain.dtos.LeituristaDTO;
import com.stfn2.ggas.domain.dtos.filter.LeituristaFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.LeituristaBasicDTO;
import com.stfn2.ggas.repositories.LeituristaRepository;

@Service
public class LeituristaService extends BaseService<Leiturista, LeituristaDTO, LeituristaBasicDTO, LeituristaFilterDTO, LeituristaRepository> {

}

