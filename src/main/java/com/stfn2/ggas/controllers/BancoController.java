package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;


import com.stfn2.ggas.domain.Banco;
import com.stfn2.ggas.domain.dtos.BancoDTO;
import com.stfn2.ggas.domain.dtos.basic.BancoBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.BancoFilterDTO;
import com.stfn2.ggas.repositories.BancoRepository;
import com.stfn2.ggas.services.BancoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/banco")
@Tag(name="Banco", description="EndPoints para gerenciamento de Banco")
public class BancoController extends BaseController<Banco, BancoDTO, BancoBasicDTO, BancoFilterDTO,
		BancoRepository, BancoService> {
}