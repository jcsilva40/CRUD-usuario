package com.stfn2.ggas.core.abstractClasses;

import lombok.Data;

@Data
public abstract class FilterDTO {

    private Long id;

    private Boolean habilitado;

    private String descricao;
}
