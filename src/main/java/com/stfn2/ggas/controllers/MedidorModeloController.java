package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.MedidorModelo;
import com.stfn2.ggas.domain.dtos.MedidorModeloDTO;
import com.stfn2.ggas.domain.dtos.filter.MedidorModeloFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.MedidorModeloBasicDTO;
import com.stfn2.ggas.repositories.MedidorModeloRepository;
import com.stfn2.ggas.services.MedidorModeloService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/medidorModelo")
@Tag(name="MedidorModelo", description="EndPoints para gerenciamento de MedidorModelo")
public class MedidorModeloController extends BaseController<MedidorModelo, MedidorModeloDTO, MedidorModeloBasicDTO, MedidorModeloFilterDTO,
		MedidorModeloRepository, MedidorModeloService> {
}