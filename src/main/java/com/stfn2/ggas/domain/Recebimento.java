package com.stfn2.ggas.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.stfn2.ggas.constante.Constantes;
import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.RecebimentoDTO;
import com.stfn2.ggas.domain.enumTypes.RecebimentoSituacaoEnum;
import com.stfn2.ggas.domain.enumTypes.converter.RecebimentoSituacaoConverter;
import com.stfn2.ggas.tools.ToolNumber;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "RECEBIMENTO")
public class Recebimento extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;
    private static final int LIMITE_CAMPO = 2;
    private static final int NUMEROS_DECIMAIS = 2;

    @Id
    @Column(name = "RECE_CD")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_RECE")
    @SequenceGenerator(name = "SQ_RECE", sequenceName = "SQ_RECE_CD", allocationSize = 1)
    private Long id;

    @Convert(converter = RecebimentoSituacaoConverter.class)
    @Column(name = "REST_CD")
    private RecebimentoSituacaoEnum recebimentoSituacaoEnum = RecebimentoSituacaoEnum.CLASSIFICADO;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIE_CD", referencedColumnName = "CLIE_CD")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POCN_CD", referencedColumnName = "POCN_CD")
    private PontoConsumo pontoConsumo;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "ARMI_CD", referencedColumnName = "ARMI_CD")
//    private ArrecadadorMovimentoItem arrecadadorMovimentoItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AVBA_CD", referencedColumnName = "AVBA_CD")
    private AvisoBancario avisoBancario;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FAGE_CD", referencedColumnName = "FAGE_CD")
    private FaturaGeral faturaGeral;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DOCI_CD", referencedColumnName = "DOCI_CD")
    private DocumentoCobrancaItem documentoCobrancaItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ENCO_CD_TIPO_CONVENIO", referencedColumnName = "ENCO_CD")
    private EntidadeConteudo formaArrecadacao;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "DEVO_CD", referencedColumnName = "DEVO_CD")
//    private Devolucao devolucao;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "LOCA_CD", referencedColumnName = "LOCA_CD")
//    private Localidade localidade;

    @Column(name = "RECE_DT_AM_CONTABIL")
    private Integer anoMesContabil;

    @Column(name = "RECE_VL_RECEBIMENTO")
    private BigDecimal valorRecebimento;

    @Column(name = "RECE_DT_RECEBIMENTO")
    private LocalDate dataRecebimento;

    @Column(name = "RECE_IN_BAIXADO")
    private boolean baixado;

//    @Column(name = "RECE_VL_EXCEDENTE")
//    private BigDecimal valorExcedente;

//    @Column(name = "RECE_DS_OBSERVACAO")
//    private String observacao;

    @Column(name = "RECE_VL_PRINCIPAL")
    private  BigDecimal valorPrincipal;

    @Column(name = "RECE_VL_JUROS_MULTA")
    private BigDecimal valorJurosMulta;

    @Column(name = "RECE_VL_DESCONTOS")
    private  BigDecimal valorDescontos;

    @Column(name = "RECE_VL_ABATIMENTO")
    private BigDecimal valorAbatimento;

    @Column(name = "RECE_VL_CREDITO")
    private BigDecimal valorCredito;

    @Column(name = "RECE_VL_IOF")
    private  BigDecimal valorIOF;

    @Column(name = "RECE_VL_COBRANCA")
    private BigDecimal valorCobranca;

    @Column(name = "RECE_NR_SEQUENCIAL")
    private  Integer numeroSequencial;

    @Column(name = "RECE_IN_USO")
    private Boolean habilitado = true;

    @Column(name = "RECE_NR_VERSAO")
    private Integer versao = 0;

    @Column(name = "RECE_TM_ULTIMA_ALTERACAO")
    private LocalDateTime ultimaAlteracao = LocalDateTime.now();

    Recebimento(Long id){
        super(id);
        this.id = id;
    }

    public String getValorRecebimentoFormatado() {
        return ToolNumber.converterCampoValorDecimalParaString("", this.valorRecebimento, Constantes.LOCALE_PADRAO,
                NUMEROS_DECIMAIS);
    }

    @Override
    public String getDescricao() {
        return null;
    }

    @Override
    public void setDescricao(String descricao) {

    }

    @Override
    public BaseDTO convert() {
        return MapperImpl.parseObject(this, RecebimentoDTO.class);}
}
