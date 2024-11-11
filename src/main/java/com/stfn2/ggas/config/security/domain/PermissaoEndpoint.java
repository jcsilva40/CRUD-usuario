package com.stfn2.ggas.config.security.domain;

import com.stfn2.ggas.domain.enumTypes.MetodoHttpEnum;
import com.stfn2.ggas.domain.enumTypes.converter.ClienteImovelRelacionamentoConverter;
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
@Table(name = "PERMISSAO_ENDPOINT")
public class PermissaoEndpoint implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PEED")
    @SequenceGenerator(name = "SQ_PEED", sequenceName = "SQ_PEED_CD", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String urlEndpoint;

    private String tipo;

    @ManyToOne
    @JoinColumn(name = "PEPS_CD")
    private PerfilPermissaoUsuario perfilPermissaoUsuario;

    @ManyToOne
    @JoinColumn(name = "FUUS_CD")
    private FuncaoUsuario funcaoUsuario;

    @Column(name = "ULTIMA_ALTERACAO", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime ultimaAlteracao = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Convert(converter = ClienteImovelRelacionamentoConverter.class)
    @Column(name = "HTTP_METHOD", nullable = false)
    private MetodoHttpEnum httpMethod;
}
