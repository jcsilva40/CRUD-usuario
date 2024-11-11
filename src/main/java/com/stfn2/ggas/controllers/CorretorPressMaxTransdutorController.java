package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.CorretorPressMaxTransdutor;
import com.stfn2.ggas.domain.dtos.CorretorPressMaxTransdutorDTO;
import com.stfn2.ggas.domain.dtos.filter.CorretorPressMaxTransdutorFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.CorretorPressMaxTransdutorBasicDTO;
import com.stfn2.ggas.repositories.CorretorPressMaxTransdutorRepository;
import com.stfn2.ggas.services.CorretorPressMaxTransdutorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/corretorPressMaxTransdutor")
@Tag(name="CorretorPressMaxTransdutor", description="EndPoints para gerenciamento de CorretorPressMaxTransdutor")
public class CorretorPressMaxTransdutorController extends BaseController<CorretorPressMaxTransdutor, CorretorPressMaxTransdutorDTO, CorretorPressMaxTransdutorBasicDTO, CorretorPressMaxTransdutorFilterDTO,
		CorretorPressMaxTransdutorRepository, CorretorPressMaxTransdutorService> {
}