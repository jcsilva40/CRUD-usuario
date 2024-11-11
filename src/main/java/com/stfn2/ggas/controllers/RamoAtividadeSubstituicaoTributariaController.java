package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.RamoAtividadeSubstituicaoTributaria;
import com.stfn2.ggas.domain.dtos.RamoAtividadeSubstituicaoTributariaDTO;
import com.stfn2.ggas.domain.dtos.basic.RamoAtividadeSubstituicaoTributariaBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.RamoAtividadeSubstituicaoTributariaFilterDTO;
import com.stfn2.ggas.repositories.RamoAtividadeSubstituicaoTributariaRepository;
import com.stfn2.ggas.services.RamoAtividadeSubstituicaoTributariaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/ramoAtividadeSubsTrib")
@Tag(name="RamoAtividadeSubsTrib", description="EndPoints para gerenciamento de RamoAtividadeSubsTrib")
public class RamoAtividadeSubstituicaoTributariaController extends BaseController<RamoAtividadeSubstituicaoTributaria, RamoAtividadeSubstituicaoTributariaDTO, RamoAtividadeSubstituicaoTributariaBasicDTO, RamoAtividadeSubstituicaoTributariaFilterDTO,
        RamoAtividadeSubstituicaoTributariaRepository, RamoAtividadeSubstituicaoTributariaService> {
}