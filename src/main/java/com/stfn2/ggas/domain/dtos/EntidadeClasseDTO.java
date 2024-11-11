package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EntidadeClasseDTO extends BaseDTO {

    String descricao;
    String abreviado;

}
