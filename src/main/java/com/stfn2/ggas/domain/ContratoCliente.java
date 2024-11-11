package com.stfn2.ggas.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.ContratoClienteDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CONTRATO_CLIENTE")
public class ContratoCliente extends BaseEntity {
   @Serial
   private static final long serialVersionUID = 1L;

   @Id
   @Column(name = "COCL_CD")
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_COCL")
   @SequenceGenerator(name = "SQ_COCL", sequenceName = "SQ_COCL_CD", allocationSize = 1)
   private Long id;

   @Column(name = "COCL_IN_USO")
   private Boolean habilitado = true;

   @Column(name = "COCL_NR_VERSAO")
   private Integer versao = 0;

   @Column(name = "COCL_TM_ULTIMA_ALTERACAO")
   private LocalDateTime ultimaAlteracao = LocalDateTime.now();

   @Column(name = "COCL_DT_RELACAO_INICIO")
   private LocalDate dataRelacaoInicio;

        /*@Column(name = "COCL_DT_RELACAO_FIM")
	private LocalDate relacaoFim;*/ //Todos os valores null

        /*@Column(name = "COCL_IN_PRINCIPAL")
	private Boolean indicadorPrincipal;*/ //Todos os valores null

   @ManyToOne
   @JoinColumn(name = "CONT_CD", nullable = false)
   @JsonBackReference
   private Contrato contrato;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "CLIE_CD", nullable = false)
   private Cliente cliente;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "POCN_CD", nullable = false)
   private PontoConsumo pontoConsumo;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "ENCO_CD_RESPONSABILIDADE")
   private EntidadeConteudo responsabilidade;

   public ContratoCliente(Long id) {
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
      return MapperImpl.parseObject(this, ContratoClienteDTO.class);
   }
}