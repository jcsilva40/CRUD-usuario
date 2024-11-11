package com.stfn2.ggas.config.security.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PERFIL_PERMISSAO_USUARIO")
public class PerfilPermissaoUsuario implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PEPS")
    @SequenceGenerator(name = "SQ_PEPS", sequenceName = "SQ_PEPS_CD", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String descricao;
}
