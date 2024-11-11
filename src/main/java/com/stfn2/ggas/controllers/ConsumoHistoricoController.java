package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.ConsumoHistorico;
import com.stfn2.ggas.domain.dtos.ConsumoHistoricoDTO;
import com.stfn2.ggas.domain.dtos.basic.ConsumoHistoricoBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.ConsumoHistoricoFilterDTO;
import com.stfn2.ggas.repositories.ConsumoHistoricoRepository;
import com.stfn2.ggas.services.ConsumoHistoricoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/consumoHistorico")
@Tag(name="ConsumoHistorico", description="EndPoints para gerenciamento de ConsumoHistorico")
public class ConsumoHistoricoController extends BaseController<ConsumoHistorico, ConsumoHistoricoDTO, ConsumoHistoricoBasicDTO, ConsumoHistoricoFilterDTO,
		ConsumoHistoricoRepository,ConsumoHistoricoService> {
}