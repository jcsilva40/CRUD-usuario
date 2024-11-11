package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TARIFA_PONTO_CONSUMO")
public class TarifaPontoConsumo  extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "TAPC_CD")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TAPC")
    @SequenceGenerator(name = "SQ_TAPC", sequenceName = "SQ_TAPC_CD", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TARI_CD", referencedColumnName = "TARI_CD")
    private Tarifa tarifa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POCN_CD", referencedColumnName = "POCN_CD")
    private PontoConsumo pontoConsumo;

    @Column(name = "TAPC_DT_INICIO_VIGENCIA")
    private LocalDateTime inicioVigencia;

    @Column(name = "TAPC_DT_FIM_VIGENCIA")
    private LocalDateTime fimVigencia;

    @Column(name = "TAPC_IN_USO")
    private Boolean habilitado = true;

    @Column(name = "TAPC_NR_VERSAO")
    private Integer versao = 0;

    @Column(name = "TAPC_TM_ULTIMA_ALTERACAO")
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
