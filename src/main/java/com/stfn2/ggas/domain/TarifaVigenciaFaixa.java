package com.stfn2.ggas.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.annotation.Order;

import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TARIFA_VIGENCIA_FAIXA")
public class TarifaVigenciaFaixa  extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "TAVF_CD")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TAVF")
    @SequenceGenerator(name = "SQ_TAVF", sequenceName = "SQ_TAVF_CD", allocationSize = 1)
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "TAVI_CD", referencedColumnName = "TAVI_CD")
    private TarifaVigencia tarifaVigencia;

    @Column(name = "TAVF_VL_MARGEM_VALOR_AGREGADO")
    private BigDecimal valorMargem;

    @Column(name = "TAVF_VL_COMPRA")
    private BigDecimal valorCompra;

    @Column(name = "TAVF_VL_FIXO")
    private BigDecimal valorFixo;

    @Column(name = "TAVF_VL_VARIAVEL")
    private BigDecimal valorVariavel;

    @Column(name = "TAVF_VL_TERMO_FIXO")
    private BigDecimal valorTermoFixo;

    @Column(name = "TAVF_MD_INICIO")
    @Order
    private Long volumeInicialFaixa;

    @Column(name = "TAVF_MD_FIM")
    private Long volumeFinalFaixa;

    @Column(name = "TAVF_IN_USO")
    private Boolean habilitado = true;

    @Column(name = "TAVF_NR_VERSAO")
    private Integer versao = 0;

    @Column(name = "TAVF_TM_ULTIMA_ALTERACAO")
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
