package com.stfn2.ggas.domain.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.IndiceFinanceiro;
import com.stfn2.ggas.domain.IndiceFinanceiroValorNominal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IndiceFinanceiroValorNominalDTO extends BaseDTO {
    Long id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate dataReferencia;
    Boolean utilizado;
    BigDecimal valor;
    Long indiceFinanceiroId;

    public IndiceFinanceiroValorNominalDTO(IndiceFinanceiroValorNominal valor) {
        super();
    }
}
