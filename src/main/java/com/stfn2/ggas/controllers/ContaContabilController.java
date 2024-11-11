package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.ContaContabil;
import com.stfn2.ggas.domain.dtos.filter.ContaContabilFilterDTO;
import com.stfn2.ggas.domain.dtos.ContaContabilDTO;
import com.stfn2.ggas.domain.dtos.basic.ContaContabilBasicDTO;
import com.stfn2.ggas.repositories.ContaContabilRepository;
import com.stfn2.ggas.services.ContaContabilService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/contaContabil")
@Tag(name="ContaContabil", description = "EndPoints para gerenciamento de ContaContabil")
public class ContaContabilController extends BaseController<ContaContabil, ContaContabilDTO, ContaContabilBasicDTO, ContaContabilFilterDTO,
        ContaContabilRepository, ContaContabilService> {

}

