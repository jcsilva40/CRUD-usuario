package com.stfn2.ggas.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.TributoDTO;
import com.stfn2.ggas.domain.enumTypes.EsferaPoderEnum;
import com.stfn2.ggas.domain.enumTypes.converter.EsferaPoderConverter;
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
@Table(name = "TRIBUTO")
public class Tributo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "TRIB_CD")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TRIB")
    @SequenceGenerator(name = "SQ_TRIB", sequenceName = "SQ_TRIB_CD", allocationSize = 1)
    private Long id;

    @Column(name = "TRIB_DS")
    private String descricao;

    @Column(name = "TRIB_DS_ABREVIADA")
    private String abreviacao;

    @Column(name = "ESPO_CD")
    @Convert(converter = EsferaPoderConverter.class)
    private EsferaPoderEnum esferaPoder;

    @JsonManagedReference
    @OneToMany(mappedBy = "tributo")
    private List<TributoAliquota> tributoAliquota = new ArrayList<>();

    @Column(name = "TRIB_IN_PADRAO")
    private Boolean padrao;

    @Column(name = "TRIB_IN_SERVICO")
    private Boolean servico;

    @Column(name = "TRIB_IN_PRODUTO")
    private Boolean produto;

    @Column(name = "TRIB_IN_USO")
    private Boolean habilitado;

    @Column(name = "TRIB_NR_VERSAO")
    private Integer versao = 0;

    @Column(name = "TRIB_TM_ULTIMA_ALTERACAO")
    private LocalDateTime ultimaAlteracao = LocalDateTime.now();

    public Tributo(Long id){
        super(id);
        this.id = id;
    }

    @Override
    public BaseDTO convert() {
        return MapperImpl.parseObject(this, TributoDTO.class);
    }
}
