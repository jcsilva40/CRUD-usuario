package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.ValorDefinidoFixoBI;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValorDefinidoFixoBIDTO extends BaseDTO {

    private String descricao;
    private String valor;

    public ValorDefinidoFixoBIDTO(ValorDefinidoFixoBI e) {
        super();
        setId(e.getId());
        setDescricao(e.getDescricao());
        setValor(e.getValor());
    }
}