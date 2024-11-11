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
public class BIPlayDTO extends BaseDTO {
    private Long id;
    private String descricao;
    private Boolean isPDF;

    private List<BIPlayParametrosDTO> parametros = new ArrayList<BIPlayParametrosDTO>();

    public BIPlayDTO(BIStefanini e) {
        super();
        setId(e.getId());
        setDescricao(e.getDescricao());
        e.getParametros().forEach(i -> parametros.add(new BIPlayParametrosDTO(i)));
     //   Collections.sort(parametros);
        setIsPDF(e.getQuery().getJasper() != null);
    }

    @Override
    public Long getId() {
        return this.id;
    }
}