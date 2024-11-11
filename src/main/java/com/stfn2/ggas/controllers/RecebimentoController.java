package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.Recebimento;
import com.stfn2.ggas.domain.dtos.RecebimentoDTO;
import com.stfn2.ggas.domain.dtos.basic.RecebimentoBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.RecebimentoFilterDTO;
import com.stfn2.ggas.repositories.RecebimentoRepository;
import com.stfn2.ggas.services.RecebimentoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/recebimento")
@Tag(name="Recebimento", description="EndPoints para gerenciamento de Recebimento")
public class RecebimentoController extends BaseController<Recebimento, RecebimentoDTO, RecebimentoBasicDTO, RecebimentoFilterDTO,
        RecebimentoRepository, RecebimentoService> {

}
