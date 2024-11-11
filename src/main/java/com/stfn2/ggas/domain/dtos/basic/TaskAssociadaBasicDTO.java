package com.stfn2.ggas.domain.dtos.basic;

import com.stfn2.ggas.config.security.domain.User;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.TarifaVigenciaDesconto;
import com.stfn2.ggas.domain.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskAssociadaBasicDTO extends BaseDTO {

    private Long id;

    public TaskAssociadaBasicDTO (TaskAssociadaBasicDTO entity) {super(); 	}

    @Override
    public Long getId() {
        return this.id;
    }
}
