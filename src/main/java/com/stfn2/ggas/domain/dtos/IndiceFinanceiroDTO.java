package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IndiceFinanceiroDTO extends BaseDTO {

    Long id;
    String descricao;
    String abreviado;
    Boolean mensal;
    Boolean negativo;
    Boolean habilitado;
    Boolean percentual;

}