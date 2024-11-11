package com.stfn2.ggas.domain.projection;

import com.stfn2.ggas.domain.enumTypes.CreditoDebitoSituacaoEnum;

import java.math.BigDecimal;

public interface CreditoDebitoARealizarBasicProjection {
     Long getId();
     String getPontoConsumoDescricao();
     String getPontoConsumoCil();
     Integer getAnoMesFaturamento();
     Integer getCiclo();
     String getCredDebNegComplemento();
     String getTipoCobrancaRubrDesc();
     BigDecimal getValor();
     CreditoDebitoSituacaoEnum getCreditoDebitoSituacao();
     Boolean getExecutada();
     Long getPontoConsumoId();
     String getPeriodicidade();

     Integer getNumeroPrestCobrada();
}
