package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.Task;
import com.stfn2.ggas.domain.dtos.TaskDTO;
import com.stfn2.ggas.domain.dtos.basic.TaskBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.TaskFilterDTO;
import com.stfn2.ggas.repositories.TaskRepository;
import com.stfn2.ggas.services.TaskService;

public class TaskController extends BaseController<Task, TaskDTO, TaskBasicDTO,TaskFilterDTO,TaskRepository, TaskService> {
}
