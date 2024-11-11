package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.RotaCronograma;
import com.stfn2.ggas.domain.dtos.RotaCronogramaDTO;
import com.stfn2.ggas.domain.dtos.basic.RotaCronogramaBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.RotaCronogramaFilterDTO;
import com.stfn2.ggas.repositories.RotaCronogramaRepository;
import com.stfn2.ggas.services.RotaCronogramaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/rotaCronograma")
@Tag(name="RotaCronograma", description="EndPoints para gerenciamento de RotaCronograma")
public class RotaCronogramaController extends BaseController<RotaCronograma, RotaCronogramaDTO, RotaCronogramaBasicDTO, RotaCronogramaFilterDTO,
		RotaCronogramaRepository,RotaCronogramaService> {
}