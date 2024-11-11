package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.ServicoPrestado;
import com.stfn2.ggas.domain.dtos.ServicoPrestadoDTO;
import com.stfn2.ggas.domain.dtos.filter.ServicoPrestadoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.ServicoPrestadoBasicDTO;
import com.stfn2.ggas.repositories.ServicoPrestadoRepository;
import com.stfn2.ggas.services.ServicoPrestadoService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/servicoPrestado")
@Tag(name="ServicoPrestado", description="EndPoints para gerenciamento de ServicoPrestado")
public class ServicoPrestadoController extends BaseController<ServicoPrestado, ServicoPrestadoDTO, ServicoPrestadoBasicDTO, ServicoPrestadoFilterDTO,
		ServicoPrestadoRepository, ServicoPrestadoService> {
}

