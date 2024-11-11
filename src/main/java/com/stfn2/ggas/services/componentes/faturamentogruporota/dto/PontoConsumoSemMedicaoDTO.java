package com.stfn2.ggas.services.componentes.faturamentogruporota.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PontoConsumoSemMedicaoDTO {
    private String faturamentoDescricao;
    private Integer anoMes;
    private Integer ciclo;
    private String cil;
    private String pontoConsumoDescricao;
    private Long rotaId;
    private String rotaDescricao;
}
