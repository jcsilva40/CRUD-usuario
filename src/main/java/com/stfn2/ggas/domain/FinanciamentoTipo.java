package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FINANCIAMENTO_TIPO")
public class FinanciamentoTipo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "FITI_CD")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_FITI")
    @SequenceGenerator(name = "SQ_FITI", sequenceName = "SQ_FITI_CD", allocationSize = 1)
    private Long id;

    @Column(name = "FITI_DS")
    private String descricao;

    @Column(name = "FITI_DS_ABREVIADA")
    private String abreviado;

    @Column(name = "FITI_ESPECIE_TITULO")
    private String especieTitulo;

    @Column(name = "FITI_IN_USO")
    private Boolean habilitado;

    @Column(name = "FITI_NR_VERSAO")
    private Integer versao = 0;

    @Column(name = "FITI_TM_ULTIMA_ALTERACAO")
    private LocalDateTime ultimaAlteracao = LocalDateTime.now();

    @Override
    public BaseDTO convert() {
        return null;
    }
}
