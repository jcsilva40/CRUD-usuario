package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.Arrecadador;
import com.stfn2.ggas.domain.dtos.ArrecadadorDTO;
import com.stfn2.ggas.domain.dtos.basic.ArrecadadorBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.ArrecadadorFilterDTO;
import com.stfn2.ggas.repositories.ArrecadadorRepository;
import com.stfn2.ggas.services.ArrecadadorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/arrecadador")
@Tag(name="Arrecadador", description="EndPoints para gerenciamento de Arrecadador")

public class ArrecadadorController extends BaseController<Arrecadador, ArrecadadorDTO, ArrecadadorBasicDTO, ArrecadadorFilterDTO,
        ArrecadadorRepository, ArrecadadorService> {

}