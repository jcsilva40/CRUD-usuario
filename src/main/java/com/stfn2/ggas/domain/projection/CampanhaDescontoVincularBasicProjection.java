package com.stfn2.ggas.domain.projection;

import com.stfn2.ggas.domain.enumTypes.SegmentoCampanhaEnum;
import com.stfn2.ggas.domain.enumTypes.StatusCampanhaEnum;
import java.time.LocalDate;
import java.time.LocalDateTime;

public interface CampanhaDescontoVincularBasicProjection{
    
    Long getId();
    String getDescricaoCampanha();
    LocalDate getInicioAdesao();
    LocalDate getEncerramentoAdesao();
    String getSegmentoPaiDescricao();
    Long getContratoId();
    String getCil();
    String getClienteNome();
    String getCnpjOrCpf();
    String getClienteNomeFantasia();
    String getImovelNome();
    Integer getImovelNip();
    String getSolicitante();
    LocalDateTime getDataSolicitacao();
    String getAprovador();
    LocalDateTime getDataAprovacao();
    StatusCampanhaEnum getStatusCampanha();
    Short getPeriodoFaturado();
    Boolean getHabilitado();
}  