package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.CertificadoMedidor;
import com.stfn2.ggas.domain.dtos.CertificadoMedidorDTO;
import com.stfn2.ggas.domain.dtos.filter.CertificadoMedidorFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.CertificadoMedidorBasicDTO;
import com.stfn2.ggas.repositories.CertificadoMedidorRepository;
import com.stfn2.ggas.services.CertificadoMedidorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/certificadoMedidor")
@Tag(name="CertificadoMedidor", description="EndPoints para gerenciamento de CertificadoMedidor")
public class CertificadoMedidorController extends BaseController<CertificadoMedidor, CertificadoMedidorDTO, CertificadoMedidorBasicDTO, CertificadoMedidorFilterDTO,
		CertificadoMedidorRepository, CertificadoMedidorService> {
}