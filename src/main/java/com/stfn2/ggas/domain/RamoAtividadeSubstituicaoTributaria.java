package com.stfn2.ggas.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.RamoAtividadeSubstituicaoTributariaDTO;
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
@Table(name = "RAMO_ATIVIDADE_SUBS_TRIB")
public class RamoAtividadeSubstituicaoTributaria extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "RAST_CD")
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_RAST")
//    @SequenceGenerator(name = "SQ_RAST", sequenceName = "SQ_RAST_CD", allocationSize = 1)
    private Long id;

    @Column(name = "ENCO_CD_TIPO_SUBS_TRIB")
    private Long tipoSubstituicao;

//    @Column(name = "RAAT_CD")
//    private Long idRamoAtividade;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "RAAT_CD", referencedColumnName = "RAAT_CD")
    private RamoAtividade ramoAtividade;

    @Column(name = "RAST_DT_VIGENCIA_INICIAL")
    private LocalDate dataInicioVigencia;

    @Column(name = "RAST_DT_VIGENCIA_FINAL")
    private LocalDate dataFimVigencia;

    @Column(name = "RAST_VL_SUBSTITUTO")
    private BigDecimal valorSubstituto;

    @Column(name = "RAST_PR_SUBSTITUTO")
    private BigDecimal percentualSubstituto;

    @Column(name = "RAST_IN_USO")
    private Boolean habilitado = true;

    @Column(name = "RAST_NR_VERSAO")
    private Integer versao = 0;

    @Column(name = "RAST_TM_ULTIMA_ALTERACAO")
    private LocalDateTime ultimaAlteracao = LocalDateTime.now();

    public RamoAtividadeSubstituicaoTributaria(Long id) {
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
        return MapperImpl.parseObject(this, RamoAtividadeSubstituicaoTributariaDTO.class);
    }
}