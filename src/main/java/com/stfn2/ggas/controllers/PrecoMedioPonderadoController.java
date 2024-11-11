package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.PrecoMedioPonderado;
import com.stfn2.ggas.domain.dtofilter.PrecoMedioPonderadoFilterDTO;
import com.stfn2.ggas.domain.dtos.PrecoMedioPonderadoDTO;
import com.stfn2.ggas.domain.dtos.basic.PrecoMedioPonderadoBasicDTO;
import com.stfn2.ggas.repositories.PrecoMedioPonderadoRepository;
import com.stfn2.ggas.services.PrecoMedioPonderadoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/precoMedioPonderado")
@Tag(name="PrecoMedioPonderado", description="EndPoints para gerenciamento de PrecoMedioPonderado")
public class PrecoMedioPonderadoController extends BaseController<PrecoMedioPonderado, PrecoMedioPonderadoDTO,
		PrecoMedioPonderadoBasicDTO,	PrecoMedioPonderadoFilterDTO,	PrecoMedioPonderadoRepository,
		PrecoMedioPonderadoService> {

}