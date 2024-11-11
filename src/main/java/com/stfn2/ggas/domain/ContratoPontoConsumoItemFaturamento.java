package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.ContratoPontoConsumoItemFaturamentoDTO;
import com.stfn2.ggas.domain.enumTypes.FaseReferenciaEnum;
import com.stfn2.ggas.domain.enumTypes.ItemFaturaEnum;
import com.stfn2.ggas.domain.enumTypes.OpcaoFaseReferenciaEnum;
import com.stfn2.ggas.domain.enumTypes.converter.FaseReferenciaConverter;
import com.stfn2.ggas.domain.enumTypes.converter.ItemFaturaConverter;
import com.stfn2.ggas.domain.enumTypes.converter.OpcaoFaseReferenciaConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CONTRATO_PONTO_CONS_ITEM_FATUR")
public class ContratoPontoConsumoItemFaturamento extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "COIF_CD")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_COIF")
    @SequenceGenerator(name = "SQ_COIF", sequenceName = "SQ_COIF_CD", allocationSize = 1)
    private Long id;

    @Column(name = "COIF_NR_VERSAO")
    private Integer versao = 0;

    @Column(name = "COIF_TM_ULTIMA_ALTERACAO")
    private LocalDateTime ultimaAlteracao = LocalDateTime.now();

    @Column(name = "COIF_NR_DIA_VENCIMENTO")
    private Integer numeroDiaVencimento;

    @Column(name = "COIF_IN_VENCIMENTO_DIA_UTIL")
    private Boolean vencimentoDiaUtil;

    @Column(name = "COIF_PR_QDC")
    private BigDecimal percentualMinimoQDC;

    @Column(name = "COIF_IN_DEPOSITO_IDENTIFICADO")
    private Boolean indicadorDepositoIdentificado = false;

    @Convert(converter = ItemFaturaConverter.class)
    @Column(name = "ENCO_CD_ITEM_FATURA")
    private ItemFaturaEnum itemFatura;

    @Convert(converter = FaseReferenciaConverter.class)
    @Column(name = "ENCO_CD_FASE_REFERENCIA")
    private FaseReferenciaEnum faseReferencia;

    @Convert(converter = OpcaoFaseReferenciaConverter.class)
    @Column(name = "ENCO_CD_OPCAO_FASE_REFERENCIA")
    private OpcaoFaseReferenciaEnum opcaoFaseReferencia;

    //private EntidadeConteudo dataReferenciaCambial; Todos os valores estão null
    //private EntidadeConteudo diaCotacao; Todos os valores estão null
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COPC_CD", nullable = false)
    private ContratoPontoConsumo contratoPontoConsumo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="TARI_CD", nullable=false)
    private Tarifa tarifa;
    public ContratoPontoConsumoItemFaturamento(Long id) {
        super(id);
        this.id = id;
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
        return MapperImpl.parseObject(this, ContratoPontoConsumoItemFaturamentoDTO.class);
    }

    @Override
    protected Boolean getHabilitado() {
        return null;
    }
}
