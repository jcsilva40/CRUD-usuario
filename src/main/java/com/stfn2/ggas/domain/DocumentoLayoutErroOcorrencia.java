package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.DocumentoLayoutErroOcorrenciaDTO;
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
@Table(name = "DOCUMENTO_LAYOUT_ERRO_OCO")
public class DocumentoLayoutErroOcorrencia extends BaseEntity {
   @Serial
   private static final long serialVersionUID = 1L;

   @Id
   @Column(name = "DERO_CD")
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_DERO")
   @SequenceGenerator(name = "SQ_DERO", sequenceName = "SQ_DERO_CD", allocationSize = 1)
   private Long id;

   @Column(name = "DERO_IN_USO")
   private Boolean habilitado = true;

   @Column(name = "DERO_NR_VERSAO")
   private Integer versao = 0;

   @Column(name = "DERO_TM_ULTIMA_ALTERACAO")
   private LocalDateTime ultimaAlteracao = LocalDateTime.now();

   @Column(name = "DERO_NR_CODIGO", nullable = false)
   private String codigo;

   @Column(name = "DERO_DS", nullable = false)
   private String descricao;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "DOLR_CD", nullable = false)
   private DocumentoLayoutRegistro registros;

   public DocumentoLayoutErroOcorrencia(Long id) {
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
      return MapperImpl.parseObject(this, DocumentoLayoutErroOcorrenciaDTO.class);
   }
}