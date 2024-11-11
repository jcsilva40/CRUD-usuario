package com.stfn2.ggas.domain.dtos.filter;


import com.stfn2.ggas.core.abstractClasses.FilterDTO;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IndiceFinanceiroValorNominalFilterDTO extends FilterDTO {

    private Long idIndice;

    private Date dataReferencia;

}
