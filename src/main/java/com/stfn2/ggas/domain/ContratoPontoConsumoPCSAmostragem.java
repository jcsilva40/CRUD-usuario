package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.ContratoPontoConsumoPCSAmostragemDTO;
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
@Table(name = "CONTRATO_PONTO_CONS_PCS_AMOSTR")
public class ContratoPontoConsumoPCSAmostragem extends BaseEntity {
   @Serial
   private static final long serialVersionUID = 1L;

   @Id
   @Column(name = "COPA_CD")
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_COPA")
   @SequenceGenerator(name = "SQ_COPA", sequenceName = "SQ_COPA_CD", allocationSize = 1)
   private Long id;
	
	/*@Column(name = "COPA_IN_USO")
	private Boolean habilitado = true;*/ //Não mapeado na tabela

   @Column(name = "COPA_NR_VERSAO")
   private Integer versao = 0;

   @Column(name = "COPA_TM_ULTIMA_ALTERACAO")
   private LocalDateTime ultimaAlteracao = LocalDateTime.now();

   @Column(name = "COPA_NR_PRIORIDADE")
   private Integer prioridade;

   /*@ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "COPC_CD", nullable = false)
   private ContratoPontoConsumo contratoPontoConsumo;*/
   
   @OneToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "COPC_CD", nullable = false)
   private ContratoPontoConsumo contratoPontoConsumo;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "ENCO_CD_LOCAL_AMOSTRAGEM_PCS", nullable = false)
   private EntidadeConteudo localAmostragemPCS;

   public ContratoPontoConsumoPCSAmostragem(Long id) {
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
      return MapperImpl.parseObject(this, ContratoPontoConsumoPCSAmostragemDTO.class);
   }

   @Override
   public Boolean getHabilitado() {
      return null;
   }
}