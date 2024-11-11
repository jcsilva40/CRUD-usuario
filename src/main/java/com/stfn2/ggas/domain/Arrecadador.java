package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.ArrecadadorDTO;
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
@Table(name = "ARRECADADOR")
public class Arrecadador extends BaseEntity {
   @Serial
   private static final long serialVersionUID = 1L;

   @Id
   @Column(name = "ARRE_CD")
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ARRE")
   @SequenceGenerator(name = "SQ_ARRE", sequenceName = "SQ_ARRE_CD", allocationSize = 1)
   private Long id;

   @OneToMany(mappedBy = "arrecadador", cascade = CascadeType.MERGE, orphanRemoval = true)
   List<ArrecadadorContrato> arrecadadorContrato = new ArrayList<>();

   @OneToMany(mappedBy = "arrecadador", cascade = CascadeType.MERGE, orphanRemoval = true)
   List<ArrecadadorCarteiraCobranca> arrecadadorCarteiraCobranca = new ArrayList<>();

   @OneToMany(mappedBy = "arrecadador", cascade = CascadeType.ALL, orphanRemoval = true)
   List<AvisoBancario> avisoBancario = new ArrayList<>();

   @Column(name = "ARRE_IN_USO")
   private Boolean habilitado = true;

   @Column(name = "ARRE_NR_VERSAO")
   private Integer versao = 0;

   @Column(name = "ARRE_TM_ULTIMA_ALTERACAO")
   private LocalDateTime ultimaAlteracao = LocalDateTime.now();

   @Column(name = "ARRE_CD_AGENTE", nullable = false)
   private String codigoAgente;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "BANC_CD")
   private Banco banco;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "CLIE_CD")
   private Cliente cliente;

   public Arrecadador(Long id) {
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
      return MapperImpl.parseObject(this, ArrecadadorDTO.class);
   }
}