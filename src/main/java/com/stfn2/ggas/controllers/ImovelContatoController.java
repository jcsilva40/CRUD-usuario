package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.ImovelContato;
import com.stfn2.ggas.domain.dtos.ImovelContatoDTO;
import com.stfn2.ggas.domain.dtos.filter.ImovelContatoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.ImovelContatoBasicDTO;
import com.stfn2.ggas.repositories.ImovelContatoRepository;
import com.stfn2.ggas.services.ImovelContatoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/imovelContato")
@Tag(name="ImovelContato", description="EndPoints para gerenciamento de ImovelContato")
public class ImovelContatoController extends BaseController<ImovelContato, ImovelContatoDTO, ImovelContatoBasicDTO, ImovelContatoFilterDTO,
		ImovelContatoRepository, ImovelContatoService> {
}