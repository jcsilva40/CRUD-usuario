package com.stfn2.ggas.domain.dtos.basic;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskBasicDTO extends BaseDTO {

    private Long id;

    public TaskBasicDTO (TaskBasicDTO entity) {super(); 	}

    @Override
    public Long getId() {
        return this.id;
    }
}
