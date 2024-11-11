package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.PessoaSexo;
import com.stfn2.ggas.domain.dtos.PessoaSexoDTO;
import com.stfn2.ggas.domain.dtos.filter.PessoaSexoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.PessoaSexoBasicDTO;
import com.stfn2.ggas.repositories.PessoaSexoRepository;
import com.stfn2.ggas.services.PessoaSexoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/pessoaSexo")
@Tag(name="PessoaSexo", description="EndPoints para gerenciamento de PessoaSexo")
public class PessoaSexoController extends BaseController<PessoaSexo, PessoaSexoDTO, PessoaSexoBasicDTO, PessoaSexoFilterDTO,
		PessoaSexoRepository, PessoaSexoService> {
}