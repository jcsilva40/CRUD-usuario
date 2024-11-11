package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.FaturamentoAtividadeCronograma;
import com.stfn2.ggas.domain.dtos.FaturamentoAtividadeCronogrDTO;
import com.stfn2.ggas.domain.dtos.filter.FaturamentoAtividadeCronogrFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.FaturamentoAtividadeCronogrBasicDTO;
import com.stfn2.ggas.repositories.FaturamentoAtividadeCronogrRepository;
import com.stfn2.ggas.services.FaturamentoAtividadeCronogrService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/faturamentoAtividadeCronogr")
@Tag(name="FaturamentoAtividadeCronogr", description="EndPoints para gerenciamento de FaturamentoAtividadeCronogr")
public class FaturamentoAtividadeCronogrController extends BaseController<FaturamentoAtividadeCronograma, FaturamentoAtividadeCronogrDTO, FaturamentoAtividadeCronogrBasicDTO, FaturamentoAtividadeCronogrFilterDTO,
		FaturamentoAtividadeCronogrRepository, FaturamentoAtividadeCronogrService> {
}