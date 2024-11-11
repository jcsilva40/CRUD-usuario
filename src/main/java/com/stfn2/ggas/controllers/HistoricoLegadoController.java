package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.HistoricoLegado;
import com.stfn2.ggas.domain.dtos.HistoricoLegadoDTO;
import com.stfn2.ggas.domain.dtos.basic.HistoricoLegadoBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.HistoricoLegadoFilterDTO;
import com.stfn2.ggas.repositories.HistoricoLegadoRepository;
import com.stfn2.ggas.services.HistoricoLegadoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/historicoLegado")
@Tag(name="HistoricoLegado", description="EndPoints para gerenciamento de HistoricoLegado")
public class HistoricoLegadoController extends BaseController<HistoricoLegado, HistoricoLegadoDTO, HistoricoLegadoBasicDTO, HistoricoLegadoFilterDTO,
		HistoricoLegadoRepository,HistoricoLegadoService> {
}