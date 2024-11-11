package com.stfn2.ggas.domain.projection;


import com.stfn2.ggas.domain.enumTypes.ModalidadeMedicaoEnum;
import java.math.BigDecimal;
import java.time.LocalDate;

public interface CampanhaDescontoBasicProjection{
    
    Long getId();
    String getDescricao();
    BigDecimal getPorcentagem();
    Long getSegmentoPaiId();
    ModalidadeMedicaoEnum getModalidadeMedicao();
    LocalDate getInicioAdesao();
    LocalDate getEncerramentoAdesao();
    Short getPeriodoVigencia();
    Integer getQuantidadeParticipante();
    String getMensagemFatura();
    Boolean getHabilitado();
}  