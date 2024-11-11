package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.ArrecadadorContratoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ARRECADADOR_CONTRATO")
public class ArrecadadorContrato extends BaseEntity {
   @Serial
   private static final long serialVersionUID = 1L;

   @Id
   @Column(name = "ARCO_CD")
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ARCO")
   @SequenceGenerator(name = "SQ_ARCO", sequenceName = "SQ_ARCO_CD", allocationSize = 1)
   private Long id;

    @OneToMany(mappedBy = "arrecadadorContrato", cascade = CascadeType.MERGE, orphanRemoval = true)
    List<ArrecadadorContratoConvenio> arrecadadorContratoConvenio = new ArrayList<>();

   @Column(name = "ARCO_IN_USO")
   private Boolean habilitado = true;

   @Column(name = "ARCO_NR_VERSAO")
   private Integer versao = 0;

   @Column(name = "ARCO_TM_ULTIMA_ALTERACAO")
   private LocalDateTime ultimaAlteracao = LocalDateTime.now();

   @Column(name = "ARCO_NR_CONTRATO")
   private String numeroContrato;

   @Column(name = "ARCO_DT_CONTRATOINICIO")
   private LocalDate dataInicioContrato;

   @Column(name = "ARCO_DT_FIM_CONTRATO")
	 private LocalDate dataFimContrato;

//   @ManyToOne(fetch = FetchType.LAZY)
//   @JoinColumn(name = "ARRE_CD", nullable = false)
//   private Arrecadador arrecadador;

   @ManyToOne
   @JoinColumn(name = "ARRE_CD", referencedColumnName = "ARRE_CD")
   private Arrecadador arrecadador;

   public ArrecadadorContrato(Long id) {
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
      return MapperImpl.parseObject(this, ArrecadadorContratoDTO.class);
   }
}