package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.EntidadeConteudo;
import com.stfn2.ggas.domain.dtos.filter.EntidadeConteudoFilterDTO;
import com.stfn2.ggas.domain.dtos.EntidadeConteudoDTO;
import com.stfn2.ggas.domain.dtos.basic.EntidadeConteudoBasicDTO;
import com.stfn2.ggas.repositories.EntidadeConteudoRepository;
import com.stfn2.ggas.services.EntidadeConteudoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/entidadesconteudo")
@Tag(name="EntidadeConteudo", description = "EndPoints para gerenciamento de EntidadeConteudo")
public class EntidadeConteudoController
        extends BaseController<EntidadeConteudo,
        EntidadeConteudoDTO, EntidadeConteudoBasicDTO, EntidadeConteudoFilterDTO,
        EntidadeConteudoRepository, EntidadeConteudoService> {

}
