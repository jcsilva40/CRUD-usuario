package com.stfn2.ggas.core.abstractClasses.combo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ComboDTO {
    private Long id;
    private String descricao;    
    
    public ComboDTO(Long id, Object descricao) {
        this.id = id;
        this.descricao = descricao != null ? descricao.toString() : null;
    }
}