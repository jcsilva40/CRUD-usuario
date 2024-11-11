package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.FaturaHistorico;
import com.stfn2.ggas.domain.dtos.FaturaHistoricoDTO;
import com.stfn2.ggas.domain.dtos.basic.FaturaHistoricoBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.FaturaHistoricoFilterDTO;
import com.stfn2.ggas.repositories.FaturaHistoricoRepository;
import com.stfn2.ggas.services.FaturaHistoricoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/faturaHistorico")
@Tag(name="FaturaHistorico", description="EndPoints para gerenciamento de FaturaHistorico")
public class FaturaHistoricoController extends BaseController<FaturaHistorico, FaturaHistoricoDTO, FaturaHistoricoBasicDTO, FaturaHistoricoFilterDTO,
		FaturaHistoricoRepository,FaturaHistoricoService> {
}