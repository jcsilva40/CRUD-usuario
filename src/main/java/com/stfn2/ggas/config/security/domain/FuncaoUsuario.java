package com.stfn2.ggas.config.security.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FUNCAO_USUARIO")
public class FuncaoUsuario implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_FUUS")
    @SequenceGenerator(name = "SQ_FUUS", sequenceName = "SQ_FUUS_CD", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String descricao;

    @Column(name = "ULTIMA_ALTERACAO", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime ultimaAlteracao = LocalDateTime.now();
}

