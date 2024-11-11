package com.stfn2.ggas.domain;

import com.stfn2.ggas.config.security.domain.User;
import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.SuperMedicaoHorariaDTO;
import com.stfn2.ggas.domain.enumTypes.SuperMedicaoAnormalidadeEnum;
import com.stfn2.ggas.domain.enumTypes.converter.SuperMedicaoAnormalidadeConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serial;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SUPER_MEDICAO_HORARIA")
public class SuperMedicaoHoraria extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "SUMH_CD")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_SUMH")
    @SequenceGenerator(name = "SQ_SUMH", sequenceName = "SQ_SUMH_CD", allocationSize = 1)
    private Long id;

    @Column(name = "SUMH_NR_VERSAO")
    private Integer versao;

    @Column(name = "SUMH_TM_ULTIMA_ALTERACAO")
    private LocalDateTime ultimaAlteracao;

    @Column(name = "SUMH_IN_USO")
    private Boolean habilitado;

    @Column(name = "SUMH_CD_SUPERVISORIO")
    private Long codigoPontoConsumoSupervisorio;

    @Column(name = "SUMH_IN_TIPO_MEDICAO")
    private String indicadorTipoMedicao;

    @Column(name = "SUMH_TM_LEITURA")
    private LocalDateTime dataRealizacaoLeitura;

    @Column(name = "SUMH_MD_LEITURA")
    private Long leituraSemCorrecaoFatorPTZ;

    @Column(name = "SUMH_MD_LEITURA_CORRIGIDA")
    private Long leituraComCorrecaoFatorPTZ;

    @Column(name = "SUMH_MD_PRESSAO")
    private Long pressao;

    @Column(name = "SUMH_MD_TEMPERATURA")
    private Long temperatura;

    @Column(name = "SUMH_IN_CONSOLIDADA")
    private Boolean indicadorConsolidada;

    @Column(name = "SUMH_TM_PROCESSAMENTO")
    private LocalDateTime dataRegistroLeitura;

    @Column(name = "SUMH_IN_TIPO_INCLUSAO_MEDICAO")
    private String indicadorTipoInclusaoMedicao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SUMD_CD", referencedColumnName = "SUMD_CD")
    private SuperMedicaoDiaria superMedicaoDiaria;

    @Column(name = "SUMA_CD")
    @Convert(converter = SuperMedicaoAnormalidadeConverter.class)
    private SuperMedicaoAnormalidadeEnum superMedicaoAnormalidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USSI_CD", referencedColumnName = "USSI_CD")
    private User ultimoUsuarioAlteracao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ENCO_CD_STATUS", referencedColumnName = "ENCO_CD")
    private EntidadeConteudo status;

    @Transient
    private String descricao;

    public SuperMedicaoHoraria(Long id) {
        super(id);
        this.id = id;
    }

    @Override
    public BaseDTO convert() {
        return MapperImpl.parseObject(this, SuperMedicaoHorariaDTO.class);
    }
}
