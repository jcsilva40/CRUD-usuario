package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.FaturamentoCronograma;
import com.stfn2.ggas.domain.dtos.FaturamentoCronogramaDTO;
import com.stfn2.ggas.domain.dtos.filter.FaturamentoCronogramaFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.FaturamentoCronogramaBasicDTO;
import com.stfn2.ggas.repositories.FaturamentoCronogramaRepository;
import com.stfn2.ggas.services.FaturamentoCronogramaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/faturamentoCronograma")
@Tag(name="FaturamentoCronograma", description="EndPoints para gerenciamento de FaturamentoCronograma")
public class FaturamentoCronogramaController extends BaseController<FaturamentoCronograma, FaturamentoCronogramaDTO, FaturamentoCronogramaBasicDTO, FaturamentoCronogramaFilterDTO,
		FaturamentoCronogramaRepository, FaturamentoCronogramaService> {
}