package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.ImovelRamoAtividade;
import com.stfn2.ggas.domain.dtos.ImovelRamoAtividadeDTO;
import com.stfn2.ggas.domain.dtos.filter.ImovelRamoAtividadeFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.ImovelRamoAtividadeBasicDTO;
import com.stfn2.ggas.repositories.ImovelRamoAtividadeRepository;
import com.stfn2.ggas.services.ImovelRamoAtividadeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/imovelRamoAtividade")
@Tag(name="ImovelRamoAtividade", description="EndPoints para gerenciamento de ImovelRamoAtividade")
public class ImovelRamoAtividadeController extends BaseController<ImovelRamoAtividade, ImovelRamoAtividadeDTO, ImovelRamoAtividadeBasicDTO, ImovelRamoAtividadeFilterDTO,
		ImovelRamoAtividadeRepository, ImovelRamoAtividadeService> {
}