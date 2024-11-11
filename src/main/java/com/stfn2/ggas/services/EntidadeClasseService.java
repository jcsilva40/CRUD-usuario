package com.stfn2.ggas.services;

import com.stfn2.ggas.domain.dtos.filter.EntidadeClasseFilterDTO;
import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.EntidadeClasse;
import com.stfn2.ggas.domain.dtos.EntidadeClasseDTO;
import com.stfn2.ggas.domain.dtos.basic.EntidadeClasseBasicDTO;
import com.stfn2.ggas.repositories.EntidadeClasseRepository;

@Service
public class EntidadeClasseService extends BaseService<EntidadeClasse, EntidadeClasseDTO, EntidadeClasseBasicDTO,
        EntidadeClasseFilterDTO, EntidadeClasseRepository> {

}
