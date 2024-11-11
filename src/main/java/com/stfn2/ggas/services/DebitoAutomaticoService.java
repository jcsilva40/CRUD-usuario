package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.DebitoAutomatico;
import com.stfn2.ggas.domain.dtos.DebitoAutomaticoDTO;
import com.stfn2.ggas.domain.dtos.basic.DebitoAutomaticoBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.DebitoAutomaticoFilterDTO;
import com.stfn2.ggas.repositories.DebitoAutomaticoRepository;

@Service
public class DebitoAutomaticoService extends BaseService<DebitoAutomatico, DebitoAutomaticoDTO, DebitoAutomaticoBasicDTO, DebitoAutomaticoFilterDTO, DebitoAutomaticoRepository> {

}