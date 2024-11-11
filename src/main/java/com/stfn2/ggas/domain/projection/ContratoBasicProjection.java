package com.stfn2.ggas.domain.projection;

import com.stfn2.ggas.domain.enumTypes.ContratoSituacaoEnum;
import java.time.LocalDate;

public interface ContratoBasicProjection{
    
    Long getId();
    //String getDescricaoContrato();
    Long getIdCliente();
    String getNomeCliente();
    Long getIdPontoConsumo();
    String getDescricaoPontoConsumo();
    String getCpfCnpjCliente();
    Integer getNumeroContrato();
    ContratoSituacaoEnum getContratoSituacao();
    LocalDate getDataAssinatura();
    LocalDate getDataVencimento();    
    
}  