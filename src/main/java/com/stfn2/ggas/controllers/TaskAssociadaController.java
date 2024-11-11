package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.TaskAssociada;
import com.stfn2.ggas.domain.dtos.TaskAssociadaDTO;
import com.stfn2.ggas.domain.dtos.basic.TaskAssociadaBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.TaskAssociadaFilterDTO;
import com.stfn2.ggas.repositories.TaskAssociadaRepository;
import com.stfn2.ggas.services.TaskAssociadaService;

public class TaskAssociadaController extends BaseController<TaskAssociada,TaskAssociadaDTO,TaskAssociadaBasicDTO, TaskAssociadaFilterDTO,TaskAssociadaRepository,TaskAssociadaService> {
}
