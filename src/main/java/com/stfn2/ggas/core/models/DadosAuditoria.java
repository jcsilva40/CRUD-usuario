package com.stfn2.ggas.core.models;

import com.stfn2.ggas.config.security.domain.User;

import java.time.LocalDate;

public record DadosAuditoria(User usuario, String operacao, String ip,
                             LocalDate dataHora, Long chaveAuditoriaPai) {
}
