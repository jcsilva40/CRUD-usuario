package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.MedidorMarca;
import com.stfn2.ggas.domain.dtos.MedidorMarcaDTO;
import com.stfn2.ggas.domain.dtos.filter.MedidorMarcaFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.MedidorMarcaBasicDTO;
import com.stfn2.ggas.repositories.MedidorMarcaRepository;
import com.stfn2.ggas.services.MedidorMarcaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/medidorMarca")
@Tag(name="MedidorMarca", description="EndPoints para gerenciamento de MedidorMarca")
public class MedidorMarcaController extends BaseController<MedidorMarca, MedidorMarcaDTO, MedidorMarcaBasicDTO, MedidorMarcaFilterDTO,
		MedidorMarcaRepository, MedidorMarcaService> {
}