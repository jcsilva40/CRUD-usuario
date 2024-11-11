package com.stfn2.ggas.domain.projection;

import com.stfn2.ggas.domain.enumTypes.SituacaoPagamentoEnum;
import com.stfn2.ggas.domain.enumTypes.StatusFaturaEnum;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface FaturaBasicProjection {

    Long getId();
    Integer getAnoMes();
    Integer getCiclo();
    Integer getNumeroFatura();
    BigDecimal getValorTotal();
    String getPeriodoCiclo();
    String getPontoConsumoDescricao();
    String getSegmentoDescricao();
    LocalDate getDataEmissao();
    LocalDate getDataVencimento();
    StatusFaturaEnum getStatusFatura();
    SituacaoPagamentoEnum getSituacaoPagamento();
}
