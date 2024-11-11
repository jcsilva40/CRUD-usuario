package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.TemperaturaTrabalhoFaixa;
import com.stfn2.ggas.domain.dtos.TemperaturaTrabalhoFaixaDTO;
import com.stfn2.ggas.domain.dtos.filter.TemperaturaTrabalhoFaixaFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.TemperaturaTrabalhoFaixaBasicDTO;
import com.stfn2.ggas.repositories.TemperaturaTrabalhoFaixaRepository;
import com.stfn2.ggas.services.TemperaturaTrabalhoFaixaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/temperaturaTrabalhoFaixa")
@Tag(name="TemperaturaTrabalhoFaixa", description="EndPoints para gerenciamento de TemperaturaTrabalhoFaixa")
public class TemperaturaTrabalhoFaixaController extends BaseController<TemperaturaTrabalhoFaixa, TemperaturaTrabalhoFaixaDTO, TemperaturaTrabalhoFaixaBasicDTO, TemperaturaTrabalhoFaixaFilterDTO,
		TemperaturaTrabalhoFaixaRepository, TemperaturaTrabalhoFaixaService> {
}