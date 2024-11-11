package com.stfn2.ggas.domain;

import com.stfn2.ggas.config.security.domain.User;
import com.stfn2.ggas.config.security.domain.dto.UserDTO;
import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.EntidadeConteudoDTO;
import com.stfn2.ggas.domain.dtos.SuperMedicaoDiariaDTO;
import com.stfn2.ggas.domain.enumTypes.SuperMedicaoAnormalidadeEnum;
import com.stfn2.ggas.domain.enumTypes.converter.SuperMedicaoAnormalidadeConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SUPER_MEDICAO_DIARIA")
@Cacheable
public class SuperMedicaoDiaria extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "SUMD_CD")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_SUMD")
    @SequenceGenerator(name = "SQ_SUMD", sequenceName = "SQ_SUMD_CD", allocationSize = 1)
    private Long id;

    @Column(name = "SUMD_NR_VERSAO", nullable = false)
    private Integer versao;

    @Column(name = "SUMD_TM_ULTIMA_ALTERACAO", nullable = false)
    private LocalDateTime ultimaAlteracao;

    @Column(name = "SUMD_IN_USO", nullable = false)
    private Boolean habilitado;

    @Column(name = "SUMD_DT_AM_LEITURA")
    private Long anoMes;

    @Column(name = "SUMD_NR_CICLO")
    private Integer numeroCiclo;

    @Column(name = "SUMD_CD_SUPERVISORIO", nullable = false)
    private String cil;

    @Column(name = "SUMD_TM_LEITURA", nullable = false)
    private LocalDateTime dataRealizacaoLeitura;

    @Column(name = "SUMD_MD_LEITURA")
    private BigDecimal leituraSemCorrecaoFatorPTZ;

    @Column(name = "SUMD_MD_LEITURA_CORRIGIDA")
    private BigDecimal leituraComCorrecaoFatorPTZ;

    @Column(name = "SUMD_MD_CONSUMO")
    private BigDecimal consumoSemCorrecaoFatorPTZ;

    @Column(name = "SUMD_MD_CONSUMO_MEDIDO")
    private BigDecimal consumoComCorrecaoFatorPTZ;

    @Column(name = "SUMD_MD_PRESSAO")
    private BigDecimal pressao;

    @Column(name = "SUMD_MD_TEMPERATURA")
    private BigDecimal temperatura;

    @Column(name = "SUMD_NR_FATOR_PTZ")
    private BigDecimal fatorPTZ;

    @Column(name = "SUMD_NR_FATOR_Z")
    private BigDecimal fatorZ;

    @Column(name = "SUMD_IN_INTEGRADO", nullable = false)
    private Boolean indicadorIntegrado;

    @Column(name = "SUMD_IN_PROCESSADO", nullable = false)
    private Boolean indicadorProcessado;

    @Column(name = "SUMD_TM_PROCESSAMENTO", nullable = false)
    private LocalDateTime dataRegistroLeitura;

    @Column(name = "SUMD_IN_CONSOLIDADA", nullable = false)
    private Boolean indicadorConsolidada;

    @Column(name = "SUMD_IN_MEDIDOR")
    private Boolean indicadorMedidor;

    @Convert(converter = SuperMedicaoAnormalidadeConverter.class)
    @Column(name = "SUMA_CD")
    private SuperMedicaoAnormalidadeEnum superMedicaoAnormalidade;

    @OneToMany(mappedBy = "superMedicaoDiaria", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SuperMedicaoHoraria> listaSuperMedicaoHoraria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USSI_CD", nullable = false)
    private User ultimoUsuarioAlteracao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ENCO_CD_STATUS", nullable = false)
    private EntidadeConteudo status;

    @Transient
    private String descricao;

    @Override
    public BaseDTO convert() {
        return MapperImpl.parseObject(this, SuperMedicaoDiariaDTO.class);
    }
}