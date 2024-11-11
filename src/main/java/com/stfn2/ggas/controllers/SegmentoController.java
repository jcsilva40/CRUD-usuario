package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.Segmento;
import com.stfn2.ggas.domain.dtos.SegmentoDTO;
import com.stfn2.ggas.domain.dtos.basic.SegmentoBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.SegmentoFilterDTO;
import com.stfn2.ggas.repositories.SegmentoRepository;
import com.stfn2.ggas.services.SegmentoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/segmentos")
@Tag(name="segmento", description = "EndPoints para gerenciamento de Segmento")
public class SegmentoController
        extends BaseController<Segmento, SegmentoDTO, SegmentoBasicDTO, SegmentoFilterDTO,
        SegmentoRepository, SegmentoService> {

}


