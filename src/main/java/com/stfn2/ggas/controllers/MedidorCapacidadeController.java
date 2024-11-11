package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.MedidorCapacidade;
import com.stfn2.ggas.domain.dtos.MedidorCapacidadeDTO;
import com.stfn2.ggas.domain.dtos.filter.MedidorCapacidadeFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.MedidorCapacidadeBasicDTO;
import com.stfn2.ggas.repositories.MedidorCapacidadeRepository;
import com.stfn2.ggas.services.MedidorCapacidadeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/medidorCapacidade")
@Tag(name="MedidorCapacidade", description="EndPoints para gerenciamento de MedidorCapacidade")
public class MedidorCapacidadeController extends BaseController<MedidorCapacidade, MedidorCapacidadeDTO, MedidorCapacidadeBasicDTO, MedidorCapacidadeFilterDTO,
		MedidorCapacidadeRepository, MedidorCapacidadeService> {
}