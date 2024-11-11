package com.stfn2.ggas.core.abstractClasses.combo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ComboCustomDTO {
    private Object valueCustom;
    private String descricao;    
    
    public ComboCustomDTO(Object valueCustom, Object descricao) {
        this.valueCustom = valueCustom;
        this.descricao = descricao != null ? descricao.toString() : null;
    }
}