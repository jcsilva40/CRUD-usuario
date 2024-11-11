package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.AvisoBancarioDTO;
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
@Table(name = "AVISO_BANCARIO")
public class AvisoBancario extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "AVBA_CD")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_AVBA")
    @SequenceGenerator(name = "SQ_AVBA", sequenceName = "SQ_AVBA_CD", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ARRE_CD", referencedColumnName = "ARRE_CD")
    private Arrecadador arrecadador;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "ARMO_CD", referencedColumnName = "ARMO_CD")
//    private ArrecadadorMovimento arrecadadorMovimento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COBA_CD", referencedColumnName = "COBA_CD")
    private ContaBancaria contaBancaria;

    @Column(name = "AVBA_DT_AM_CONTABIL")
    private Integer anoMesContabil;

    @Column(name = "AVBA_IN_CREDITO_DEBITO")
    private boolean indicadorCreditoDebito;

    @Column(name = "AVBA_NR_DOCUMENTO")
    private Integer numeroDocumento;

    @Column(name = "AVBA_NR_SEQUENCIAL")
    private Integer numeroSequencial;

    @Column(name = "AVBA_DT_LANCAMENTO")
    private LocalDate dataLancamento;

    @Column(name = "AVBA_VL_CONTABILIZADO")
    private  BigDecimal valorContabilizado;

    @Column(name = "AVBA_VL_REALIZADO")
    private BigDecimal valorRealizado;

    @Column(name = "AVBA_DT_PREVISTA")
    private  LocalDate dataPrevista;

    @Column(name = "AVBA_DT_REALIZADA")
    private LocalDate dataRealizada;

    @Column(name = "AVBA_VL_ARRECADACAO_CALCULADO")
    private BigDecimal valorArrecadacaoCalculado;

    @Column(name = "AVBA_VL_DEVOLUCAO_CALCULADO")
    private  BigDecimal valorDevolucaoCalculado;

    @Column(name = "AVBA_VL_ARRECADACAO_INFORMADO")
    private BigDecimal valorArrecadacaoInformado;

    @Column(name = "AVBA_VL_DEVOLUCAO_INFORMADO")
    private  BigDecimal valorDevolucaoInformado;

    @Column(name = "AVBA_IN_USO")
    private Boolean habilitado = true;

    @Column(name = "AVBA_NR_VERSAO")
    private Integer versao = 0;

    @Column(name = "AVBA_TM_ULTIMA_ALTERACAO")
    private LocalDateTime ultimaAlteracao = LocalDateTime.now();

    @Override
    public String getDescricao() {
        return null;
    }

    @Override
    public void setDescricao(String descricao) {

    }

    @Override
    public BaseDTO convert() {
        return MapperImpl.parseObject(this, AvisoBancarioDTO.class);
    }

}
