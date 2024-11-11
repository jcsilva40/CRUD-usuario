package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.UnidadeOrganizacional;
import com.stfn2.ggas.domain.dtos.UnidadeOrganizacionalDTO;
import com.stfn2.ggas.domain.dtos.filter.UnidadeOrganizacionalFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.UnidadeOrganizacionalBasicDTO;
import com.stfn2.ggas.repositories.UnidadeOrganizacionalRepository;

@Service
public class UnidadeOrganizacionalService extends BaseService<UnidadeOrganizacional, UnidadeOrganizacionalDTO, UnidadeOrganizacionalBasicDTO, UnidadeOrganizacionalFilterDTO, UnidadeOrganizacionalRepository> {

}

