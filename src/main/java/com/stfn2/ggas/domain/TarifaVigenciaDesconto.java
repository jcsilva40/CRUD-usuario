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
@Table(name = "TARIFA_VIGENCIA_DESCONTO")
public class TarifaVigenciaDesconto extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "TAVD_CD")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TAVD")
    @SequenceGenerator(name = "SQ_TAVD", sequenceName = "SQ_TAVD_CD", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TARI_CD", referencedColumnName = "TARI_CD")
    private Tarifa tarifa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TAVI_CD", referencedColumnName = "TAVI_CD")
    private TarifaVigencia tarifaVigencia;

    @Column(name = "TAVD_DS")
    private String descricao;

    @Column(name = "TAVD_DT_INICIO_VIGENCIA")
    private LocalDateTime inicioVigencia;

    @Column(name = "TAVD_DT_FIM_VIGENCIA")
    private LocalDateTime fimVigencia;

    @Column(name = "TAVD_IN_IMPRESSAO_DESCRICAO")
    private Boolean impressaoDescricao;

    @Column(name = "TAVD_IN_IMPRESSAO_DESCONTO")
    private Boolean impressaoDesconto;

    @Column(name = "TAVD_IN_USO")
    private Boolean habilitado = true;

    @Column(name = "TAVD_NR_VERSAO")
    private Integer versao = 0;

    @Column(name = "TAVD_TM_ULTIMA_ALTERACAO")
    private LocalDateTime ultimaAlteracao = LocalDateTime.now();

    @Override
    public BaseDTO convert() {
        return null;
    }
}
