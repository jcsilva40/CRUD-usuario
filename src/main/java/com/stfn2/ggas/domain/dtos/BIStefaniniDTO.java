package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.BIStefanini;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BIStefaniniDTO extends BaseDTO {
    private String descricao;

    private List<ParametroBIDTO> parametros = new ArrayList<>();

    private BIQueryDTO query;

    public BIStefaniniDTO(BIStefanini e) {
        super();
        setId(e.getId());
        setDescricao(e.getDescricao());
        e.getParametros().forEach(pOb -> getParametros().add(new ParametroBIDTO(pOb)));
        setQuery(new BIQueryDTO(e.getQuery()));
    }
}