package com.stfn2.ggas.core.abstractClasses;

import lombok.Data;

@Data
public abstract class SuperDTO<Entidade> {
    private Long id;

    public abstract Class<Entidade> getTypeClass();

    public SuperDTO(Entidade entidade){

    }
}
