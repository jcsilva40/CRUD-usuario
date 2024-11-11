package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.MedidorDiametro;
import com.stfn2.ggas.domain.dtos.MedidorDiametroDTO;
import com.stfn2.ggas.domain.dtos.filter.MedidorDiametroFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.MedidorDiametroBasicDTO;
import com.stfn2.ggas.repositories.MedidorDiametroRepository;
import com.stfn2.ggas.services.MedidorDiametroService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/medidorDiametro")
@Tag(name="MedidorDiametro", description="EndPoints para gerenciamento de MedidorDiametro")
public class MedidorDiametroController extends BaseController<MedidorDiametro, MedidorDiametroDTO, MedidorDiametroBasicDTO, MedidorDiametroFilterDTO,
		MedidorDiametroRepository, MedidorDiametroService> {
}