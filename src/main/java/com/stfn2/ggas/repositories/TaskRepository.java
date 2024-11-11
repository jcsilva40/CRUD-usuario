package com.stfn2.ggas.repositories;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.Task;
import com.stfn2.ggas.domain.dtos.filter.TaskFilterDTO;

public interface TaskRepository extends BaseRepository<Task, TaskFilterDTO>{
}
