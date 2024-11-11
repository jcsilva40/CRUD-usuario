package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.CertificadoMedidor;
import com.stfn2.ggas.domain.dtos.CertificadoMedidorDTO;
import com.stfn2.ggas.domain.dtos.filter.CertificadoMedidorFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.CertificadoMedidorBasicDTO;
import com.stfn2.ggas.repositories.CertificadoMedidorRepository;

@Service
public class CertificadoMedidorService extends BaseService<CertificadoMedidor, CertificadoMedidorDTO, CertificadoMedidorBasicDTO, CertificadoMedidorFilterDTO, CertificadoMedidorRepository> {

}