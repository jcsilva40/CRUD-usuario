package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.MedicaoHistorico;
import com.stfn2.ggas.domain.dtos.MedicaoHistoricoDTO;
import com.stfn2.ggas.domain.dtos.basic.MedicaoHistoricoBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.MedicaoHistoricoFilterDTO;
import com.stfn2.ggas.repositories.MedicaoHistoricoRepository;
import com.stfn2.ggas.services.MedicaoHistoricoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/medicaoHistorico")
@Tag(name="MedicaoHistorico", description="EndPoints para gerenciamento de MedicaoHistorico")
public class MedicaoHistoricoController extends BaseController<MedicaoHistorico, MedicaoHistoricoDTO, MedicaoHistoricoBasicDTO, MedicaoHistoricoFilterDTO,
		MedicaoHistoricoRepository,MedicaoHistoricoService> {
}