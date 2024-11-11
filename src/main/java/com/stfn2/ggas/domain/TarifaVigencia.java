package com.stfn2.ggas.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.stfn2.ggas.config.security.domain.User;
import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.enumTypes.BaseApuracaoEnum;
import com.stfn2.ggas.domain.enumTypes.StatusAutorizacaoEnum;
import com.stfn2.ggas.domain.enumTypes.TipoCalculoEnum;
import com.stfn2.ggas.domain.enumTypes.UnidadeMonetariaEnum;
import com.stfn2.ggas.domain.enumTypes.converter.BaseApuracaoConverter;
import com.stfn2.ggas.domain.enumTypes.converter.StatusAutorizacaoConverter;
import com.stfn2.ggas.domain.enumTypes.converter.TipoCalculoConverter;
import com.stfn2.ggas.domain.enumTypes.converter.UnidadeMonetariaConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TARIFA_VIGENCIA")
public class TarifaVigencia  extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "TAVI_CD")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TAVI")
    @SequenceGenerator(name = "SQ_TAVI", sequenceName = "SQ_TAVI_CD", allocationSize = 1)
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "TARI_CD", referencedColumnName = "TARI_CD")
    private Tarifa tarifa;

    @Column(name = "ENCO_CD_BASE_APURACAO")
    @Convert(converter = BaseApuracaoConverter.class)
    private BaseApuracaoEnum baseApuracao;

    @Column(name = "ENCO_CD_UNIDADE_MONETARIA")
    @Convert(converter = UnidadeMonetariaConverter.class)
    private UnidadeMonetariaEnum unidadeMonetaria;

    @Column(name = "ENCO_CD_STATUS")
    @Convert(converter = StatusAutorizacaoConverter.class)
    private StatusAutorizacaoEnum status;

    @Column(name = "ENCO_CD_TIPO_CALCULO")
    @Convert(converter = TipoCalculoConverter.class)
    private TipoCalculoEnum tipoCalculo;

    @ManyToOne
    @JoinColumn(name = "RUBR_CD", referencedColumnName = "RUBR_CD")
    private Rubrica rubricaTermoFixo;

    @ManyToOne
    @JoinColumn(name = "USSI_CD", referencedColumnName = "USSI_CD")
    private User usuario;

    @JsonManagedReference
    @Cascade(CascadeType.ALL)
    @OneToMany(mappedBy = "tarifaVigencia")
    private List<TarifaVigenciaTributo> tributos = new ArrayList<>();

    @JsonManagedReference
    @Cascade(CascadeType.ALL)
    @OneToMany(mappedBy = "tarifaVigencia")
    private List<TarifaVigenciaFaixa> faixasConsumo = new ArrayList<>();

    @Column(name = "TAVI_DT_VIGENCIA")
    private LocalDate dataVigencia;

    @Column(name = "TAVI_DESCONSIDERAR_MARGEM")
    private Boolean desconsiderarMargem;

    @Column(name = "TAVI_USO_PGU")
    private Boolean usarPGU;

    @Column(name = "TAVI_IN_MSG_ICMS_SUBS")
    private Boolean msgICMSSubs;

    @Column(name = "TAVI_MSG_ICMS_SUBS")
    private String mensagemICMSSubs;

    @Column(name = "TAVI_DS_COMENTARIO")
    private String comentario;

    @Column(name = "TAVI_VL_BASE_CALCULO_SUBS")
    private BigDecimal valorBaseCalculoSubst = BigDecimal.ZERO;

    @Column(name = "TAVI_VL_MOLECULA")
    private BigDecimal valorMolecula = BigDecimal.ZERO;

    @Column(name = "TAVI_VL_CUSTO_FIXO")
    private BigDecimal valorTermoFixo = BigDecimal.ZERO;

    @Column(name = "TAVI_VL_ICMS_SUBS")
    private BigDecimal valorIcmsSubs = BigDecimal.ZERO;

    @Column(name = "TAVI_VL_TRANSPORTE")
    private BigDecimal valorTransporte = BigDecimal.ZERO;

    @Column(name = "TAVI_VL_RECUPERACAO")
    private BigDecimal valorRecuperacao = BigDecimal.ZERO;

    @Column(name = "TAVI_IN_USO")
    private Boolean habilitado = true;

    @Column(name = "TAVI_NR_VERSAO")
    private Integer versao = 0;

    @Column(name = "TAVI_TM_ULTIMA_ALTERACAO")
    private LocalDateTime ultimaAlteracao = LocalDateTime.now();

    public TarifaVigencia(Long id){
        super(id);
        this.id = id;
    }

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
