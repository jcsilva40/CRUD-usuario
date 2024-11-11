package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.Serie;
import com.stfn2.ggas.domain.dtos.SerieDTO;
import com.stfn2.ggas.domain.dtos.basic.SerieBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.SerieFilterDTO;
import com.stfn2.ggas.repositories.SerieRepository;
import com.stfn2.ggas.services.SerieService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/serie")
@Tag(name="Serie", description="EndPoints para gerenciamento de Serie")
public class SerieController extends BaseController<Serie, SerieDTO, SerieBasicDTO, SerieFilterDTO,
		SerieRepository,SerieService> {
}