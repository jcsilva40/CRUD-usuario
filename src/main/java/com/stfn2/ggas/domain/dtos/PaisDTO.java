package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaisDTO extends BaseDTO {

    String descricao;
    Integer codBacen;
    Boolean habilitado;

}
