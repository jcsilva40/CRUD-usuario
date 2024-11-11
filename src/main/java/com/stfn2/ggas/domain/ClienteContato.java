package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.ClienteContatoDTO;
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
@Table(name = "CLIENTE_CONTATO")
public class ClienteContato extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CLCO_CD")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CLCO")
    @SequenceGenerator(name = "SQ_CLCO", sequenceName = "SQ_CLCO_CD", allocationSize = 1)
    private Long id;

    @Column(name = "CLCO_NM")
    private String nome;

    @Column(name = "CLCO_CD_DDD")
    private Integer codigoDDD;

    @Column(name = "CLCO_NR_FONE")
    private Integer fone;

    @Column(name = "CLCO_NR_RAMAL")
    private Integer ramal;

    @Column(name = "CLCO_CD_DDD2")
    private Integer codigoDDD2;

    @Column(name = "CLCO_NR_FONE2")
    private Integer fone2;

    @Column(name = "CLCO_NR_RAMAL2")
    private Integer ramal2;

    @Column(name = "CLCO_DS_AREA")
    private String observacao;

    @Column(name = "CLCO_DS_EMAIL")
    private String email;

    @Column(name = "CLCO_DS_CPF")
    private String cpf;

    @Column(name = "CLCO_DS_RG")
    private String rg;

    @Column(name = "CLCO_IN_PRINCIPAL")
    private Boolean principal = false;

    @JoinColumn(name = "CLIE_CD")
    @OneToOne(fetch = FetchType.LAZY)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COTI_CD", referencedColumnName = "COTI_CD")
    private TipoContato tipoContato;

    public ClienteContato(Long id) {
        super(id);
        this.id = id;
    }

    @Override
    public String getDescricao() {
        return getNome();
    }

    @Override
    public void setDescricao(String descricao) {

    }

    @Column(name = "CLCO_IN_USO")
    private Boolean habilitado = true;

    @Column(name = "CLCO_NR_VERSAO")
    private Integer versao = 0;

    @Column(name = "CLCO_TM_ULTIMA_ALTERACAO")
    private LocalDateTime ultimaAlteracao = LocalDateTime.now();

    @Override
    public BaseDTO convert() {
        return MapperImpl.parseObject(this, ClienteContatoDTO.class);
    }

}
