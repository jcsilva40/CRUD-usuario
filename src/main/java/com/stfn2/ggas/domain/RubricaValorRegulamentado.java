package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "RUBRICA_VALOR_REGULAMENTADO")
public class RubricaValorRegulamentado extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "RUVR_CD")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_RUVR")
    @SequenceGenerator(name = "SQ_RUVR", sequenceName = "SQ_RUVR_CD", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RUBR_CD", referencedColumnName = "RUBR_CD")
    private Rubrica rubrica;

    @Column(name = "RUVR_DS_OBSERVACAO")
    private String observacao;

    @Column(name = "RUVR_VL_REFERENCIA")
    private BigDecimal valorReferencia;

    @Column(name = "RUVR_DT_INICIO_VIGENCIA")
    private LocalDateTime inicioVigencia;

    @Column(name = "RUVR_DT_FIM_VIGENCIA")
    private LocalDateTime fimVigencia;

    @Column(name = "RUVR_IN_USO")
    private Boolean habilitado = true;

    @Column(name = "RUVR_NR_VERSAO")
    private Integer versao = 0;

    @Column(name = "RUVR_TM_ULTIMA_ALTERACAO")
    private LocalDateTime ultimaAlteracao = LocalDateTime.now();

    @Override
    public String getDescricao() {
        return rubrica.getDescricao();
    }

    @Override
    public void setDescricao(String descricao) {

    }

    @Override
    public BaseDTO convert() {
        return null;
    }
}
