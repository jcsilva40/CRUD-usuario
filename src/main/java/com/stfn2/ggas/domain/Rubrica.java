package com.stfn2.ggas.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.enumTypes.AmortizacaoEnum;
import com.stfn2.ggas.domain.enumTypes.ItemFaturaEnum;
import com.stfn2.ggas.domain.enumTypes.converter.AmortizacaoConverter;
import com.stfn2.ggas.domain.enumTypes.converter.ItemFaturaConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "RUBRICA")
public class Rubrica extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "RUBR_CD")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_RUBR")
    @SequenceGenerator(name = "SQ_RUBR", sequenceName = "SQ_RUBR_CD", allocationSize = 1)
    private Long id;

    /*@ManyToOne
    @JoinColumn(name = "ENCO_CD_AMORTIZACAO", referencedColumnName = "ENCO_CD")
    private EntidadeConteudo amortizacao;*/

    @Column(name = "ENCO_CD_AMORTIZACAO")
    @Convert(converter = AmortizacaoConverter.class)
    private AmortizacaoEnum amortizacao;

    /*
    @ManyToOne
    @JoinColumn(name = "ENCO_CD_ITEM_FATURA", referencedColumnName = "ENCO_CD")
    private EntidadeConteudo itemFatura;*/

    @Column(name = "ENCO_CD_ITEM_FATURA")
    @Convert(converter = ItemFaturaConverter.class)
    private ItemFaturaEnum itemFatura;

    @ManyToOne
    @JoinColumn(name = "NOCM_CD", referencedColumnName = "NOCM_CD")
    private NomenclaturaComumMercosul nomeclaturaComumMercosul;

    @ManyToOne
    @JoinColumn(name = "FITI_CD", referencedColumnName = "FITI_CD")
    private FinanciamentoTipo financiamentoTipo;

    @ManyToOne
    @JoinColumn(name = "LAIC_CD", referencedColumnName = "LAIC_CD")
    private LancamentoItemContabil lancamentoItemContabil;

    @ManyToOne
    @JoinColumn(name = "UNID_CD", referencedColumnName = "UNID_CD")
    private Unidade unidade;

    @JsonManagedReference
    @OneToMany(mappedBy = "rubrica", cascade = CascadeType.ALL)
    private List<RubricaTributo> tributos;

    @Column(name = "RUBR_CD_CEST")
    private String codigoSubsTributario;

    @Column(name = "RUBR_DS")
    private String descricao;

    @Column(name = "RUBR_DS_IMPRESSAO")
    private String descricaoImpressao;

    @Column(name = "RUBR_IN_COBRANCA_JUROS")
    private Boolean cobrancaJuros;

    @Column(name = "RUBR_IN_COBRANCA_MULTA")
    private Boolean cobrancaMulta;

    @Column(name = "RUBR_IN_USO_SISTEMA")
    private Boolean usoSistema = true;

    @Column(name = "RUBR_IN_ITEM_FATURAMENTO")
    private Boolean itemFaturamento;

    @Column(name = "RUBR_IN_DESCRICAO_COMPLEMENTAR")
    private Boolean DescricaoComplementar;

    @Column(name = "RUBR_IN_OUTROS_SERVICOS")
    private Boolean outrosServicos;

    @Column(name = "RUBR_IN_COMPOSICAO_NF")
    private Boolean composicaoNF;

    @Column(name = "RUBR_IN_REGULAMENTADA")
    private Boolean regulamentada;

    @Column(name = "RUBR_IN_ENTRADA_OBRIGATORIA")
    private Boolean entradaObrigatoria;

    @Column(name = "RUBR_NR_MAXIMO_PARCELA")
    private Integer maximoParcela;

    @Column(name = "RUBR_PR_MINIMO_ENTRADA")
    private BigDecimal minimoEntrada;

    @Column(name = "RUBR_VL_REFERENCIA")
    private BigDecimal valorReferencia;

    @Column(name = "RUBR_VL_MAXIMO")
    private BigDecimal valorMaximo;

    @Column(name = "RUBR_IN_USO")
    private Boolean habilitado = true;

    @Column(name = "RUBR_NR_VERSAO")
    private Integer versao = 0;

    @Column(name = "RUBR_TM_ULTIMA_ALTERACAO")
    private LocalDateTime ultimaAlteracao = LocalDateTime.now();

    public Rubrica(Long id){
        super(id);
        this.id = id;
    }

    @Override
    public BaseDTO convert() {
        return null;
    }
}
