package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.TipoContato;
import com.stfn2.ggas.domain.dtos.TipoContatoDTO;
import com.stfn2.ggas.domain.dtos.filter.TipoContatoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.TipoContatoBasicDTO;
import com.stfn2.ggas.repositories.TipoContatoRepository;
import com.stfn2.ggas.services.TipoContatoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/tipoContato")
@Tag(name="TipoContato", description="EndPoints para gerenciamento de TipoContato")
public class TipoContatoController extends BaseController<TipoContato, TipoContatoDTO, TipoContatoBasicDTO, TipoContatoFilterDTO,
		TipoContatoRepository, TipoContatoService> {
}