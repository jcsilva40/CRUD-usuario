package com.stfn2.ggas.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "RAMO_ATIVIDADE")
public class
RamoAtividade extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "RAAT_CD")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_RAAT")
    @SequenceGenerator(name = "SQ_RAAT", sequenceName = "SQ_RAAT_CD", allocationSize = 1)
    private Long id;

    public RamoAtividade(Long id) {
        super(id);
        this.id = id;
    }

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "SEGM_CD", referencedColumnName = "SEGM_CD")
    private Segmento segmento;

    @JsonManagedReference
    @OneToMany(mappedBy = "ramoAtividade", cascade = CascadeType.ALL, orphanRemoval = true)
    List<RamoAtividadeSubstituicaoTributaria> ramoAtividadeSubsTrib = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERI_CD", referencedColumnName = "PERI_CD")
    private Periodicidade periodicidade;

    @Column(name = "RAAT_DS")
    private String descricao;

    @Column(name = "SEGM_TIPO_CONSUMO_FATURAMENTO")
    private BigDecimal tipoVisualizacaoFatura;

    @Column(name = "RAAT_IN_USO")
    private Boolean habilitado = true;

    @Column(name = "RAAT_NR_VERSAO")
    private Integer versao = 0;

    @Column(name = "RAAT_TM_ULTIMA_ALTERACAO")
    private LocalDateTime ultimaAlteracao = LocalDateTime.now();

    @Override
    public BaseDTO convert() {
        return null;
    }

}
