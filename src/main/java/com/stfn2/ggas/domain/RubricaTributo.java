package com.stfn2.ggas.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "RUBRICA_TRIBUTO")
public class RubricaTributo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "RUTR_CD")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_RUTR")
    @SequenceGenerator(name = "SQ_RUTR", sequenceName = "SQ_RUTR_CD", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RUBR_CD", referencedColumnName = "RUBR_CD")
    @JsonBackReference
    private Rubrica rubrica;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TRIB_CD", referencedColumnName = "TRIB_CD")
    private Tributo tributo;

    @Column(name = "RUTR_DT_INICIO_VIGENCIA")
    private LocalDateTime inicioVigencia;

    @Column(name = "RUTR_DT_FIM_VIGENCIA")
    private LocalDateTime fimVigencia;

    @Column(name = "RUTR_IN_USO")
    private Boolean habilitado = true;

    @Column(name = "RUTR_NR_VERSAO")
    private Integer versao = 0;

    @Column(name = "RUTR_TM_ULTIMA_ALTERACAO")
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
