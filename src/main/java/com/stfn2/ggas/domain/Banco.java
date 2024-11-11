package com.stfn2.ggas.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.BancoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BANCO")
public class Banco extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "BANC_CD")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_BANC")
    @SequenceGenerator(name = "SQ_BANC", sequenceName = "SQ_BANC_CD", allocationSize = 1)
    private Long id;

    @Column(name = "BANC_IN_USO")
    private Boolean habilitado = true;

    @Column(name = "BANC_NR_VERSAO")
    private Integer versao = 0;

    @Column(name = "BANC_TM_ULTIMA_ALTERACAO")
    private LocalDateTime ultimaAlteracao = LocalDateTime.now();

    @Column(name = "BANC_NM", nullable = false)
    private String nome;

    @Column(name = "BANC_NM_ABREVIADO")
    private String abreviado;

    @Column(name = "BANC_NR")
    private String codigoBanco;

    @JsonManagedReference
    @OneToMany(mappedBy = "banco", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Agencia> agencia = new ArrayList<>();


    /*@Column(name = "BANC_MM_LOGO")
        private byte[] logoBanco;*/ //Todos os valores null
    public Banco(Long id) {
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
        return MapperImpl.parseObject(this, BancoDTO.class);
    }

    @Override
    public String getDescricaoField() {
        return "nome";
    }
}
