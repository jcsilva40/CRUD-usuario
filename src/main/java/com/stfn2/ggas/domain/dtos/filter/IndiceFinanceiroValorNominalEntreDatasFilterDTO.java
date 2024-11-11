package com.stfn2.ggas.domain.dtos.filter;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IndiceFinanceiroValorNominalEntreDatasFilterDTO {
    private Long indiceFinanceiroId;
    private String dataInicio;
    private String dataFim;

}
