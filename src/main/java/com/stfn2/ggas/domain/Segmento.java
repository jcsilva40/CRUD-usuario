package com.stfn2.ggas.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.SegmentoDTO;
import com.stfn2.ggas.domain.enumTypes.SegmentoTipoEnum;
import com.stfn2.ggas.domain.enumTypes.converter.SegmentoTipoConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SEGMENTO")
public class Segmento extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "SEGM_CD")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_SEGM")
    @SequenceGenerator(name = "SQ_SEGM", sequenceName = "SQ_SEGM_CD", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COCO_CD", referencedColumnName = "COCO_CD")
    private ContaContabil contaContabil;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERI_CD", referencedColumnName = "PERI_CD")
    private Periodicidade periodicidade;

    @JsonManagedReference
    @OneToMany(mappedBy = "segmento", cascade = CascadeType.ALL, orphanRemoval = true)
    List<RamoAtividade> ramosAtividade = new ArrayList<>();

    @Convert(converter = SegmentoTipoConverter.class)
    @Column(name = "SETI_CD")
    private SegmentoTipoEnum segmentoTipo;

    @Column(name = "SEGM_DS")
    private String descricao;

    @Column(name = "SEGM_DS_ABREVIADO")
    private String abreviado;

    @Column(name = "SEGM_DS_FATURA")
    private String descricaoFatura;

    @Column(name = "SEGM_NR_VEZES_MEDIA_ALTO")
    private Long fatorMultiplicacaoMediaAlto;

    @Column(name = "SEGM_NR_VEZES_MEDIA_ESTOURO")
    private Long fatorMultplicacaoMediaEstouro;

    @Column(name = "SEGM_NR_VEZES_MEDIA_VIRADA")
    private Long fatorMultiplicacaoMediaVirada;

    @Column(name = "SEGM_IN_EQPA_POTENCIA_FIXA")
    private Long tipoPotencia;

    @Column(name = "SEGM_IN_PROG_CONSUMO")
    private Boolean programacaoConsumo;

    @Column(name = "SEGM_NR_MEDIA_CICLOS")
    private Long numeroMediaCiclos;

    @Column(name = "SEGM_NR_DIAS_FAT_LIGACAO_NOVA")
    private Long numeroDiasParaFaturamento;

    @Column(name = "SEGM_PR_MEDIA_BAIXO_CONSUMO")
    private Long percentualMediaConsumo;

    @Column(name = "SEGM_NR_TAMANHO_REDUCAO")
    private Long tamanhoReducao;

    @Column(name = "SEGM_TIPO_CONSUMO_FATURAMENTO")
    private Long tipoVisualizacaoFatura;

    @Column(name = "SEGM_MD_CONSUMO_ALTO")
    private Long volumeConsumoAlto;

    @Column(name = "SEGM_MD_CONSUMO_BAIXO")
    private Long volumeConsumoBaixo;

    @Column(name = "SEGM_MD_CONSUMO_ESTOURO")
    private Long volumeEstouroConsumo;

    @Column(name = "SEGM_MD_VIRADA_MEDIDOR")
    private Long volumeViradaMedidor;

    @Column(name = "SEGM_NR_VOLUME_MEDIO_ESTOURO")
    private Long volumeMedioEstouro;

    @Column(name = "SEFAZ_NM")
    private String nomeSefaz;

    @Column(name = "SEGM_IN_USO")
    private Boolean habilitado = true;

    @Column(name = "SEGM_NR_VERSAO")
    private Integer versao = 0;

    @Column(name = "SEGM_TM_ULTIMA_ALTERACAO")
    private LocalDateTime ultimaAlteracao = LocalDateTime.now();
    
    @ManyToOne
    @JoinColumn(name = "SEGP_CD")
    private SegmentoPai segmentoPai;

    public Segmento(Long id) {
        super(id);
        this.id = id;
    }

    @Override
    public BaseDTO convert() {
        return MapperImpl.parseObject(this, SegmentoDTO.class);
    }
}
