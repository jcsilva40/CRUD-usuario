package com.stfn2.ggas.domain.dtos.filter;

import com.stfn2.ggas.config.security.domain.User;
import com.stfn2.ggas.core.abstractClasses.FilterDTO;
import com.stfn2.ggas.domain.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskAssociadaFilterDTO extends FilterDTO {

    private User user;
    private Task task;
    private LocalDate dataEntrega;
}
