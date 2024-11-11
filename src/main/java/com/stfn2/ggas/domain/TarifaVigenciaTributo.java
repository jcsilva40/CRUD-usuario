package com.stfn2.ggas.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.io.Serial;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TARIFA_VIGENCIA_TRIBUTO")
public class TarifaVigenciaTributo  extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "TAVT_CD")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TAVT")
    @SequenceGenerator(name = "SQ_TAVT", sequenceName = "SQ_TAVT_CD", allocationSize = 1)
    private Long id;

    @ManyToOne
    @Cascade(CascadeType.PERSIST)
    @JoinColumn(name = "TRIB_CD", referencedColumnName = "TRIB_CD")
    private Tributo tributo;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "TAVI_CD", referencedColumnName = "TAVI_CD")
    private TarifaVigencia tarifaVigencia;

    @Column(name = "TAVT_DT_INICIO_VIGENCIA")
    private LocalDate inicioVigencia;

    @Column(name = "TAVT_DT_FIM_VIGENCIA")
    private LocalDateTime fimVigencia;

    @Column(name = "TAVT_IN_USO")
    private Boolean habilitado = true;

    @Column(name = "TAVT_NR_VERSAO")
    private Integer versao = 0;

    @Column(name = "TAVT_TM_ULTIMA_ALTERACAO")
    private LocalDateTime ultimaAlteracao = LocalDateTime.now();

    @Override
    public String getDescricao() {
        return "";
    }

    @Override
    public void setDescricao(String descricao) {
    }

    @Override
    public BaseDTO convert() {
        return null;
    }

}
