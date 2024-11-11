package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.AgenciaDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "AGENCIA")
public class Agencia extends BaseEntity {
   @Serial
   private static final long serialVersionUID = 1L;

   @Id
   @Column(name = "AGEN_CD")
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_AGEN")
   @SequenceGenerator(name = "SQ_AGEN", sequenceName = "SQ_AGEN_CD", allocationSize = 1)
   private Long id;

   @Column(name = "AGEN_IN_USO")
   private Boolean habilitado = true;

   @Column(name = "AGEN_NR_VERSAO")
   private Integer versao = 0;

   @Column(name = "AGEN_TM_ULTIMA_ALTERACAO")
   private LocalDateTime ultimaAlteracao = LocalDateTime.now();

   @Column(name = "AGEN_CD_AGENCIA", nullable = false)
   private String codigo;

   @Column(name = "AGEN_NM")
   private String nome;

        /*@Column(name = "AGEN_NR_FONE")
	private String telefone;*/ //Todos os valores null

        /*@Column(name = "AGEN_NR_RAMAL")
	private String ramal;*/ //Todos os valores null

        /*@Column(name = "AGEN_NR_FAX")
	private String fax;*/ //Todos os valores null

        /*@Column(name = "AGEN_DS_EMAIL")
	private String email;*/ //Todos os valores null

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "BANC_CD")
   private Banco banco;
        
        /*@ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "CEP_CD")
	private Cep cep;*/ //Todos os valores null

   @OneToMany(mappedBy = "agencia", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
   private List<ContaBancaria> listaContaBancaria;


   public Agencia(Long id) {
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
      return MapperImpl.parseObject(this, AgenciaDTO.class);
   }
}