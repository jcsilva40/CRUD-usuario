package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.UnidadeFederacaoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "UNIDADE_FEDERACAO")
public class UnidadeFederacao extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "UNFE_CD")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_UNFE")
    @SequenceGenerator(name = "SQ_UNFE", sequenceName = "SQ_UNFE_CD", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PAIS_CD", referencedColumnName = "PAIS_CD")
    private Pais pais;

    @Column(name = "UNFE_DS")
    private String descricao;

    @Column(name = "UNFE_SG")
    private String sigla;

    @Column(name = "UNFE_IN_USO")
    private Boolean habilitado = true;

    @Column(name = "UNFE_NR_VERSAO")
    private Integer versao = 0;

    @Column(name = "UNFE_TM_ULTIMA_ALTERACAO")
    private LocalDateTime ultimaAlteracao = LocalDateTime.now();

    public UnidadeFederacao(Long id) {
        super(id);
        this.id = id;
    }

    public UnidadeFederacao(String sigla) {
        this.sigla = sigla;
    }

    @Override
    public BaseDTO convert() {
        return MapperImpl.parseObject(this, UnidadeFederacaoDTO.class);
    }

}
