package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.OrgaoExpedidor;
import com.stfn2.ggas.domain.dtos.OrgaoExpedidorDTO;
import com.stfn2.ggas.domain.dtos.filter.OrgaoExpedidorFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.OrgaoExpedidorBasicDTO;
import com.stfn2.ggas.repositories.OrgaoExpedidorRepository;

@Service
public class OrgaoExpedidorService extends BaseService<OrgaoExpedidor, OrgaoExpedidorDTO, OrgaoExpedidorBasicDTO, OrgaoExpedidorFilterDTO, OrgaoExpedidorRepository> {

}