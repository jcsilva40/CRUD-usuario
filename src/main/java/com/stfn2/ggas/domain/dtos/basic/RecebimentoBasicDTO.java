package com.stfn2.ggas.domain.dtos.basic;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.Recebimento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecebimentoBasicDTO extends BaseDTO {
    private Long id;
    private String pontoConsumoDescricao;
    private Long pontoConsumoCil;
    private Integer faturaGeralFaturaNumeroFatura;
    private String pontoConsumoSegmentoDescricao;
    private BigDecimal valorCobranca;
    private Integer anoMesContabil;
    private Integer faturaGeralFaturaCiclo;
    private BigDecimal valorRecebimento;
    private LocalDate dataRecebimento;
    private boolean baixado;

    public RecebimentoBasicDTO(Recebimento entity){super(); }

    @Override
    public Long getId() {
        return this.id;
    }
}
