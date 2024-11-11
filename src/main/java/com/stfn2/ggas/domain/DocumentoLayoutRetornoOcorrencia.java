package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.DocumentoLayoutRetornoOcorrenciaDTO;
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
@Table(name = "DOCUMENTO_LAYOUT_RETORNO_OCO")
public class DocumentoLayoutRetornoOcorrencia extends BaseEntity {
   @Serial
   private static final long serialVersionUID = 1L;

   @Id
   @Column(name = "DORO_CD")
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_DORO")
   @SequenceGenerator(name = "SQ_DORO", sequenceName = "SQ_DORO_CD", allocationSize = 1)
   private Long id;

   @Column(name = "DORO_IN_USO")
   private Boolean habilitado = true;

   @Column(name = "DORO_NR_VERSAO")
   private Integer versao = 0;

   @Column(name = "DORO_TM_ULTIMA_ALTERACAO")
   private LocalDateTime ultimaAlteracao = LocalDateTime.now();

   @Column(name = "DORO_NR_CODIGO", nullable = false)
   private String codigo;

   @Column(name = "DORO_DS", nullable = false)
   private String descricao;

   @Column(name = "DORO_IN_PROCESSA_RECEBIMENTO", nullable = false)
   private Boolean processaRecebimento;

   @Column(name = "DORO_IN_PROCESSA_CADASTRO", nullable = false)
   private Boolean processaCadastro;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "DOLR_CD", nullable = false)
   private DocumentoLayoutRegistro registros;

   public DocumentoLayoutRetornoOcorrencia(Long id) {
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
      return MapperImpl.parseObject(this, DocumentoLayoutRetornoOcorrenciaDTO.class);
   }
}