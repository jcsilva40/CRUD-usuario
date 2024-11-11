package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.RamoAtividade;
import com.stfn2.ggas.domain.dtos.filter.RamoAtividadeFilterDTO;
import com.stfn2.ggas.domain.dtos.RamoAtividadeDTO;
import com.stfn2.ggas.domain.dtos.basic.RamoAtividadeBasicDTO;
import com.stfn2.ggas.repositories.RamoAtividadeRepository;
import com.stfn2.ggas.services.RamoAtividadeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/ramoAtividade")
@Tag(name="RamoAtividade", description="EndPoints para gerenciamento de RamoAtividade")
public class RamoAtividadeController extends BaseController<RamoAtividade, RamoAtividadeDTO, RamoAtividadeBasicDTO, RamoAtividadeFilterDTO,
        RamoAtividadeRepository, RamoAtividadeService> {
}
