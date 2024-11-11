package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.ContaBancariaDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CONTA_BANCARIA")
public class ContaBancaria extends BaseEntity {
   @Serial
   private static final long serialVersionUID = 1L;

   @Id
   @Column(name = "COBA_CD")
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_COBA")
   @SequenceGenerator(name = "SQ_COBA", sequenceName = "SQ_COBA_CD", allocationSize = 1)
   private Long id;

   @Column(name = "COBA_IN_USO")
   private Boolean habilitado = true;

   @Column(name = "COBA_NR_VERSAO")
   private Integer versao = 0;

   @Column(name = "COBA_TM_ULTIMA_ALTERACAO")
   private LocalDateTime ultimaAlteracao = LocalDateTime.now();

   @Column(name = "COBA_DS", nullable = false)
   private String descricao;

   @Column(name = "COBA_NR_CONTA", nullable = false)
   private String numeroConta;

   @Column(name = "COBA_NR_DIGITO", nullable = false)
   private String numeroDigito;

        /*@Column(name = "COBA_NR_CONTA_COBRANCA")
	private String numeroContaCobranca;*/ //Todos os valores null

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "AGEN_CD", nullable = false)
   private Agencia agencia;

   public ContaBancaria(Long id) {
      super(id);
      this.id = id;
   }

   @Override
   public String getDescricao() {
      return this.descricao;
   }

   @Override
   public void setDescricao(String descricao) {
       this.descricao = descricao;
   }

   @Override
   public BaseDTO convert() {
      return MapperImpl.parseObject(this, ContaBancariaDTO.class);
   }
}