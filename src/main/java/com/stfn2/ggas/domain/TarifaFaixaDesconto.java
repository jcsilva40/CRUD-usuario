package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
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
@Table(name = "TARIFA_FAIXA_DESCONTO")
public class TarifaFaixaDesconto  extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "TAFD_CD")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TAFD")
    @SequenceGenerator(name = "SQ_TAFD", sequenceName = "SQ_TAFD_CD", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TAVF_CD", referencedColumnName = "TAVF_CD")
    private TarifaVigenciaFaixa tarifaVigenciaFaixa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TAVD_CD", referencedColumnName = "TAVD_CD")
    private TarifaVigenciaDesconto tarifaVigenciaDesconto;

    @Column(name = "TAFD_PR_DESCONTO_FIXO")
    private BigDecimal percentualDescontoFixo;

    @Column(name = "TAFD_PR_DESCONTO_VARIAVEL")
    private BigDecimal percentualDescontoVariavel;

    @Column(name = "TAFD_VL_DESCONTO_FIXO")
    private BigDecimal valorDescontoFixo;

    @Column(name = "TAFD_VL_DESCONTO_VARIAVEL")
    private BigDecimal valorDescontoVariavel;

    @Column(name = "TAFD_IN_USO")
    private Boolean habilitado = true;

    @Column(name = "TAFD_NR_VERSAO")
    private Integer versao = 0;

    @Column(name = "TAFD_TM_ULTIMA_ALTERACAO")
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
