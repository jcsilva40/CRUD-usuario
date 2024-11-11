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
public class ValorFixoPlayBIDTO extends BaseDTO {
    private Long id;
    private String descricao;
    private String valor;

    public ValorFixoPlayBIDTO(ValorDefinidoFixoBI e) {
        super();
        setDescricao(e.getDescricao());
        setValor(e.getValor());
    }

    @Override
    public Long getId() {
        return this.id;
    }
}