package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.MedidorOperacaoHistorico;
import com.stfn2.ggas.domain.dtos.MedidorOperacaoHistoricoDTO;
import com.stfn2.ggas.domain.dtos.filter.MedidorOperacaoHistoricoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.MedidorOperacaoHistoricoBasicDTO;
import com.stfn2.ggas.repositories.MedidorOperacaoHistoricoRepository;
import com.stfn2.ggas.services.MedidorOperacaoHistoricoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/medidorOperacaoHistorico")
@Tag(name="MedidorOperacaoHistorico", description="EndPoints para gerenciamento de MedidorOperacaoHistorico")
public class MedidorOperacaoHistoricoController extends BaseController<MedidorOperacaoHistorico, MedidorOperacaoHistoricoDTO, MedidorOperacaoHistoricoBasicDTO, MedidorOperacaoHistoricoFilterDTO,
		MedidorOperacaoHistoricoRepository, MedidorOperacaoHistoricoService> {
}