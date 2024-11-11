package com.stfn2.ggas.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.enumTypes.TipoAplicacaoEnum;
import com.stfn2.ggas.domain.enumTypes.TipoAplicacaoReducaoEnum;
import com.stfn2.ggas.domain.enumTypes.converter.TipoAplicacaoConverter;
import com.stfn2.ggas.domain.enumTypes.converter.TipoAplicacaoReducaoConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TRIBUTO_ALIQUOTA")
public class TributoAliquota  extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "TRAL_CD")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TRAL")
    @SequenceGenerator(name = "SQ_TRAL", sequenceName = "SQ_TRAL_CD", allocationSize = 1)
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "TRIB_CD", referencedColumnName = "TRIB_CD")
    private Tributo tributo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MUNI_CD", referencedColumnName = "MUNI_CD")
    private Municipio municipio;

    @Column(name = "ENCO_CD_TIPO_APL_TRIB_ALIQ")
    @Convert(converter = TipoAplicacaoConverter.class)
    private TipoAplicacaoEnum tipoAplicacao;

    @Column(name = "ENCO_CD_TIPO_APL_RED_BASE_CALC")
    @Convert(converter = TipoAplicacaoReducaoConverter.class)
    private TipoAplicacaoReducaoEnum tipoAplicacaoReducaoBaseCalculo;

    @Column(name = "TRAL_DT_VIGENCIA")
    private LocalDate dataVigencia;

    @Column(name = "TRAL_VL_ALIQUOTA")
    private BigDecimal valorAliquota;

    @Column(name = "TRAL_VL_RED_BASE_CALC")
    private BigDecimal valorReducaoBaseCalculo;

    @Column(name = "TRAL_IN_RED_BASE_CALC")
    private Boolean reducaoBaseCalculo;

    @Column(name = "TRAL_IN_USO")
    private Boolean habilitado;

    @JsonIgnore
    @Column(name = "TRAL_NR_VERSAO")
    private Integer versao = 0;

    @JsonIgnore
    @Column(name = "TRAL_TM_ULTIMA_ALTERACAO")
    private LocalDateTime ultimaAlteracao = LocalDateTime.now();


    @Override
    public String getDescricao() {
        return tributo.getDescricao();
    }

    @Override
    public void setDescricao(String descricao) {
    }

    @Override
    public BaseDTO convert() {
        return null;
    }
}
