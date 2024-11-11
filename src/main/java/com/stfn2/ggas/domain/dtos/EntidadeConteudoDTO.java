package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.EntidadeConteudo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EntidadeConteudoDTO extends BaseDTO {

    EntidadeClasseDTO entidadeClasse;
    String descricao;
    String abreviado;
    Boolean padrao;
    String codigo;

    public EntidadeConteudoDTO(EntidadeConteudo obj) {
        super();
    }

}
