package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.CorretorTempMaxTransdutor;
import com.stfn2.ggas.domain.dtos.CorretorTempMaxTransdutorDTO;
import com.stfn2.ggas.domain.dtos.filter.CorretorTempMaxTransdutorFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.CorretorTempMaxTransdutorBasicDTO;
import com.stfn2.ggas.repositories.CorretorTempMaxTransdutorRepository;
import com.stfn2.ggas.services.CorretorTempMaxTransdutorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/corretorTempMaxTransdutor")
@Tag(name="CorretorTempMaxTransdutor", description="EndPoints para gerenciamento de CorretorTempMaxTransdutor")
public class CorretorTempMaxTransdutorController extends BaseController<CorretorTempMaxTransdutor, CorretorTempMaxTransdutorDTO, CorretorTempMaxTransdutorBasicDTO, CorretorTempMaxTransdutorFilterDTO,
		CorretorTempMaxTransdutorRepository, CorretorTempMaxTransdutorService> {
}