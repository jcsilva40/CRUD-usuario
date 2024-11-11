package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.SegmentoAmostragemPCS;
import com.stfn2.ggas.domain.dtos.filter.SegmentoAmostragemPCSFilterDTO;
import com.stfn2.ggas.domain.dtos.SegmentoAmostragemPCSDTO;
import com.stfn2.ggas.domain.dtos.basic.SegmentoAmostragemPCSBasicDTO;
import com.stfn2.ggas.repositories.SegmentoAmostragemPCSRepository;
import com.stfn2.ggas.services.SegmentoAmostragemPCSService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/segmentoAmostragemPcs")
@Tag(name="SegmentoAmostragemPCS", description = "EndPoints para gerenciamento de SegmentoAmostragemPCS")
public class SegmentoAmostragemPCSController
        extends BaseController<SegmentoAmostragemPCS, SegmentoAmostragemPCSDTO, SegmentoAmostragemPCSBasicDTO, SegmentoAmostragemPCSFilterDTO,
        SegmentoAmostragemPCSRepository, SegmentoAmostragemPCSService> {

}

