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
@Table(name = "NOMENCLATURA_COMUM_MERCOSUL")
public class NomenclaturaComumMercosul extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "NOCM_CD")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_NOCM")
    @SequenceGenerator(name = "SQ_NOCM", sequenceName = "SQ_NOCM_CD", allocationSize = 1)
    private Long id;

    @Column(name = "NOCM_DS")
    private String descricao;

    @Column(name = "NOCM_DS_POSICAO")
    private String posicaoNCM;

    @Column(name = "NOCM_CD_NCM")
    private Integer codigoMercosul;

    @Column(name = "NOCM_IN_USO")
    private Boolean habilitado;

    @Column(name = "NOCM_NR_VERSAO")
    private Integer versao = 0;

    @Column(name = "NOCM_TM_ULTIMA_ALTERACAO")
    private LocalDateTime ultimaAlteracao = LocalDateTime.now();

    @Override
    public BaseDTO convert() {
        return null;
    }
}
