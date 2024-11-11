package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.DocumentoLayoutEnvioOcorrenciaDTO;
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
@Table(name = "DOCUMENTO_LAYOUT_ENVIO_OCO")
public class DocumentoLayoutEnvioOcorrencia extends BaseEntity {
   @Serial
   private static final long serialVersionUID = 1L;

   @Id
   @Column(name = "DLEO_CD")
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_DLEO")
   @SequenceGenerator(name = "SQ_DLEO", sequenceName = "SQ_DLEO_CD", allocationSize = 1)
   private Long id;

   @Column(name = "DLEO_IN_USO")
   private Boolean habilitado = true;

   @Column(name = "DLEO_NR_VERSAO")
   private Integer versao = 0;

   @Column(name = "DLEO_TM_ULTIMA_ALTERACAO")
   private LocalDateTime ultimaAlteracao = LocalDateTime.now();

   @Column(name = "DLEO_NR_CODIGO", nullable = false)
   private String codigo;

   @Column(name = "DLEO_DS", nullable = false)
   private String descricao;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "DOLR_CD", nullable = false)
   private DocumentoLayoutRegistro registros;

   public DocumentoLayoutEnvioOcorrencia(Long id) {
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
      return MapperImpl.parseObject(this, DocumentoLayoutEnvioOcorrenciaDTO.class);
   }
}