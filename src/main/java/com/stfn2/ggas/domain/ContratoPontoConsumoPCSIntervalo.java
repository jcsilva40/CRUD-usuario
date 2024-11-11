package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.ContratoPontoConsumoPCSIntervaloDTO;
import com.stfn2.ggas.domain.enumTypes.PCSIntervaloEnum;
import com.stfn2.ggas.domain.enumTypes.converter.PCSIntervaloConverter;
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
@Table(name = "CONTRATO_PONTO_CONS_PCS_INTERV")
public class ContratoPontoConsumoPCSIntervalo extends BaseEntity {
   @Serial
   private static final long serialVersionUID = 1L;

   @Id
   @Column(name = "COPI_CD")
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_COPI")
   @SequenceGenerator(name = "SQ_COPI", sequenceName = "SQ_COPI_CD", allocationSize = 1)
   private Long id;
	
	/*@Column(name = "COPI_IN_USO")
	private Boolean habilitado = true;*/ //Não mapeado na tabela

   @Column(name = "COPI_NR_VERSAO")
   private Integer versao = 0;

   @Column(name = "COPI_TM_ULTIMA_ALTERACAO")
   private LocalDateTime ultimaAlteracao = LocalDateTime.now();

   @Column(name = "COPI_NR_PRIORIDADE")
   private Integer prioridade;

   @Column(name = "COPI_NR_TAMANHO")
   private Integer tamanho;

   @Convert(converter = PCSIntervaloConverter.class)
   @Column(name = "PCIN_CD", nullable = false)
   private PCSIntervaloEnum pcsIntervalo;

   /*@ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "COPC_CD", nullable = false)
   private ContratoPontoConsumo contratoPontoConsumo;*/
   
   @OneToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "COPC_CD", nullable = false)
   private ContratoPontoConsumo contratoPontoConsumo;

   public ContratoPontoConsumoPCSIntervalo(Long id) {
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
      return MapperImpl.parseObject(this, ContratoPontoConsumoPCSIntervaloDTO.class);
   }

   @Override
   public Boolean getHabilitado() {
      return null;
   }
}