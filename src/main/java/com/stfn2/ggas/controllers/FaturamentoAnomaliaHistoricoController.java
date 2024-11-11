package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.FaturamentoAnomaliaHistorico;
import com.stfn2.ggas.domain.dtos.FaturamentoAnomaliaHistoricoDTO;
import com.stfn2.ggas.domain.dtos.filter.FaturamentoAnomaliaHistoricoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.FaturamentoAnomaliaHistoricoBasicDTO;
import com.stfn2.ggas.repositories.FaturamentoAnomaliaHistoricoRepository;
import com.stfn2.ggas.services.FaturamentoAnomaliaHistoricoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/faturamentoAnomaliaHistorico")
@Tag(name="FaturamentoAnomaliaHistorico", description="EndPoints para gerenciamento de FaturamentoAnomaliaHistorico")
public class FaturamentoAnomaliaHistoricoController extends BaseController<FaturamentoAnomaliaHistorico, FaturamentoAnomaliaHistoricoDTO, FaturamentoAnomaliaHistoricoBasicDTO, FaturamentoAnomaliaHistoricoFilterDTO,
		FaturamentoAnomaliaHistoricoRepository, FaturamentoAnomaliaHistoricoService> {
}