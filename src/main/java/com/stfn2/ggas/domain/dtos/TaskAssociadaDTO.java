package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.config.security.domain.User;
import com.stfn2.ggas.core.interfaces.BaseDTO;
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
public class TaskAssociadaDTO extends BaseDTO {

    private Long id;
    private User usuario;
    private Task task;
    private Boolean habilitado;
    private Integer versao;
    private LocalDateTime ultimaAlteracao;
    private LocalDate dataEntrega;

}
