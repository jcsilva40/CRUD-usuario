package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.DocumentoLayoutDTO;
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
@Table(name = "DOCUMENTO_LAYOUT")
public class DocumentoLayout extends BaseEntity {
   @Serial
   private static final long serialVersionUID = 1L;

   @Id
   @Column(name = "DOLA_CD")
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_DOLA")
   @SequenceGenerator(name = "SQ_DOLA", sequenceName = "SQ_DOLA_CD", allocationSize = 1)
   private Long id;

   @Column(name = "DOLA_IN_USO")
   private Boolean habilitado = true;

   @Column(name = "DOLA_NR_VERSAO")
   private Integer versao = 0;

   @Column(name = "DOLA_TM_ULTIMA_ALTERACAO")
   private LocalDateTime ultimaAlteracao = LocalDateTime.now();

   @Column(name = "DOLA_DS", nullable = false)
   private String descricao;

   @Column(name = "DOLA_NR_TAMANHO_REGISTRO")
   private int tamanhoRegistro;

   @Column(name = "DOLA_CLASSE_CONVERSOR_CAMPO", nullable = false)
   private String classeConversorCampo;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "ENCO_CD_TIPO_CONVENIO", nullable = false, insertable = false, updatable = false)
   private EntidadeConteudo formaArrecadacao;

   @OneToMany(fetch = FetchType.LAZY, mappedBy = "leiaute", cascade = CascadeType.ALL, orphanRemoval = true)
   @OrderBy("DOLR_DS_IDENT_REGISTRO ASC")
   private List<DocumentoLayoutRegistro> registros;

   @OneToMany(fetch = FetchType.LAZY, mappedBy = "leiaute", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<ArrecadadorContratoConvenio> convenios;

   public DocumentoLayout(Long id) {
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
      return MapperImpl.parseObject(this, DocumentoLayoutDTO.class);
   }
}