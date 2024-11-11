package com.stfn2.ggas.services;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.Task;
import com.stfn2.ggas.domain.dtos.TaskDTO;
import com.stfn2.ggas.domain.dtos.basic.TaskBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.TaskFilterDTO;
import com.stfn2.ggas.repositories.TaskRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/Task",produces = {"applications/json"})
@Tag(name="Task", description="EndPoints para gerenciamento de Tasks")
public class TaskService extends BaseService<Task,TaskDTO, TaskBasicDTO, TaskFilterDTO,TaskRepository> {
}
