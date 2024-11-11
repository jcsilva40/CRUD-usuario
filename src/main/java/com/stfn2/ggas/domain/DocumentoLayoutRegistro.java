package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.DocumentoLayoutRegistroDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DOCUMENTO_LAYOUT_REGISTRO")
public class DocumentoLayoutRegistro extends BaseEntity {
   @Serial
   private static final long serialVersionUID = 1L;

   @Id
   @Column(name = "DOLR_CD")
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_DOLR")
   @SequenceGenerator(name = "SQ_DOLR", sequenceName = "SQ_DOLR_CD", allocationSize = 1)
   private Long id;

   @Column(name = "DOLR_IN_USO")
   private Boolean habilitado = true;

   @Column(name = "DOLR_NR_VERSAO")
   private Integer versao = 0;

   @Column(name = "DOLR_TM_ULTIMA_ALTERACAO")
   private LocalDateTime ultimaAlteracao = LocalDateTime.now();

   @Column(name = "DOLR_DS", nullable = false)
   private String descricao;

   @Column(name = "DOLR_DS_IDENT_REGISTRO", nullable = false)
   private String identificadorRegistro;

   @Column(name = "DOLR_METODO_PROCESSAMENTO")
   private String metodoProcessamento;

   @OneToMany(fetch = FetchType.EAGER, mappedBy = "registros", cascade = CascadeType.ALL, orphanRemoval = true)
   @OrderBy("DOLC_NR_POSICAO_INICIAL ASC")
   private List<DocumentoLayoutCampo> campos;

   @OneToMany(fetch = FetchType.LAZY, mappedBy = "registros", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<DocumentoLayoutRetornoOcorrencia> retornoOcorrencias;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "DOLA_CD")
   private DocumentoLayout leiaute;

   @OneToMany(fetch = FetchType.LAZY, mappedBy = "registros", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<DocumentoLayoutEnvioOcorrencia> envioOcorrencias;

   @OneToMany(fetch = FetchType.LAZY, mappedBy = "registros", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<DocumentoLayoutErroOcorrencia> erroOcorrencias;

   public DocumentoLayoutRegistro(Long id) {
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
      return MapperImpl.parseObject(this, DocumentoLayoutRegistroDTO.class);
   }
}