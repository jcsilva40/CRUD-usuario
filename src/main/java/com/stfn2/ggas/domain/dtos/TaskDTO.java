package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO extends BaseDTO {

    private Long id;
    private Boolean habilitado;
    private Integer versao;
    private LocalDateTime ultimaAlteracao;
    private String titulo;
    private String descricao;
    private Integer prioridade;
}
