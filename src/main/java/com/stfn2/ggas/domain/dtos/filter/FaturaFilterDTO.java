package com.stfn2.ggas.domain.dtos.filter;


import com.stfn2.ggas.core.abstractClasses.FilterDTO;
import com.stfn2.ggas.domain.enumTypes.StatusFaturaEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FaturaFilterDTO extends FilterDTO {
    private Integer anoMes;
    private Integer ciclo;
    private Integer numeroFatura;
    private Long segmentoId;
    private Long pontoConsumoId;
    private Long clienteId;
    private StatusFaturaEnum statusFaturaId;
    private Long rotaId;
    private String periodoCiclo;
    private String pontoConsumoDescricao;
    private String segmentoDescricao;
    private Long statusFatura;
    private Long situacaoPagamento;
}
