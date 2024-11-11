package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.DocumentoLayoutCampoDTO;
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
@Table(name = "DOCUMENTO_LAYOUT_CAMPO")
public class DocumentoLayoutCampo extends BaseEntity {
   @Serial
   private static final long serialVersionUID = 1L;

   @Id
   @Column(name = "DOLC_CD")
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_DOLC")
   @SequenceGenerator(name = "SQ_DOLC", sequenceName = "SQ_DOLC_CD", allocationSize = 1)
   private Long id;

   @Column(name = "DOLC_IN_USO")
   private Boolean habilitado = true;

   @Column(name = "DOLC_NR_VERSAO")
   private Integer versao = 0;

   @Column(name = "DOLC_TM_ULTIMA_ALTERACAO")
   private LocalDateTime ultimaAlteracao = LocalDateTime.now();

   @Column(name = "DOLC_DS", nullable = false)
   private String descricao;

   @Column(name = "DOLC_DS_VALOR_PADRAO")
   private String valorPadrao;

   @Column(name = "DOLC_DS_FORMATO")
   private String formato;

   @Column(name = "DOLC_NR_POSICAO_INICIAL", nullable = false)
   private Integer posicaoInicial;

   @Column(name = "DOLC_NR_POSICAO_FINAL", nullable = false)
   private Integer posicaoFinal;

   @Column(name = "DOLC_IN_REMESSA", nullable = false)
   private Boolean remessa;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "ENCO_CD_DOLC_DS_CAMPO", nullable = false)
   private EntidadeConteudo campo;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "DOLR_CD", nullable = false)
   private DocumentoLayoutRegistro registros;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "ENCO_CD_TIPO_CAMPO", nullable = false)
   private EntidadeConteudo tipo;

   public DocumentoLayoutCampo(Long id) {
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
      return MapperImpl.parseObject(this, DocumentoLayoutCampoDTO.class);
   }
}