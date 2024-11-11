package com.stfn2.ggas.domain.dtos.filter;

import com.stfn2.ggas.core.abstractClasses.FilterDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecebimentoFilterDTO extends FilterDTO {
    //private boolean habilitado;
    private Integer anoMesContabil;
    private Integer faturaGeralFaturaCiclo;
    private String pontoConsumoSegmentoDescricao;
    private String faturaGeralFaturaSituacaoPagamento;
    private Integer faturaGeralFaturaNumeroFatura;
}
