package com.stfn2.ggas.services;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.TaskAssociada;
import com.stfn2.ggas.domain.dtos.TaskAssociadaDTO;
import com.stfn2.ggas.domain.dtos.basic.TaskAssociadaBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.TaskAssociadaFilterDTO;
import com.stfn2.ggas.repositories.TaskAssociadaRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/TaskAssociada",produces = {"applications/json"})
@Tag(name="TaskAssociada", description="EndPoints para gerenciamento de Tasks Associadas")
public class TaskAssociadaService extends BaseService<TaskAssociada, TaskAssociadaDTO, TaskAssociadaBasicDTO, TaskAssociadaFilterDTO, TaskAssociadaRepository>{
}
