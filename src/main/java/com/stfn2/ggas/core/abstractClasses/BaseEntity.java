package com.stfn2.ggas.core.abstractClasses;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.interfaces.ValidarDados;
import com.stfn2.ggas.core.models.DadosAuditoria;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@SuppressWarnings("unused")
public abstract class BaseEntity implements Serializable, ValidarDados {
   private static final long serialVersionUID = 1L;
   private Long id;
   private Boolean habilitado = true;
   private Integer versao = 0;
   private LocalDateTime ultimaAlteracao = LocalDateTime.now();
   private DadosAuditoria dadosAuditoria;

   protected BaseEntity() {
   }

   protected BaseEntity(Long id) {
      this.id = id;
   }

   public abstract Long getId();

   protected abstract void setId(Long id);

   public abstract String getDescricao();

   public abstract void setDescricao(String descricao);

   protected abstract Integer getVersao();

   protected abstract LocalDateTime getUltimaAlteracao();

   protected abstract Boolean getHabilitado();

   public abstract BaseDTO convert();

   public void updateVersao() {
      this.versao = versao + 1;
      this.ultimaAlteracao = LocalDateTime.now();
   }

   public String hashString() {
      return toString();
   }

   public String getDescricaoField() {
      return "descricao";
   }

   public String getAbreviadoField() {
      return "abreviado";
   }

   public DadosAuditoria getDadosAuditoria() {
      return dadosAuditoria;
   }

   public void setDadosAuditoria(DadosAuditoria dadosAuditoria) {
      this.dadosAuditoria = dadosAuditoria;
   }

   @Override
   public int hashCode() {
      return Objects.hash(id);
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      BaseEntity other = (BaseEntity) obj;
      return Objects.equals(id, other.id);
   }
}
