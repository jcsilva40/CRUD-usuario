package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.DocumentoTipo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentoTipoDTO extends BaseDTO {
   private Long id;
   private String descricao;
   private String abreviado;
   private Boolean pagavel;
   private Boolean codigoBarras;

   public DocumentoTipoDTO(DocumentoTipo entity) {
      super();
   }

   @Override
   public Long getId() {
      return this.id;
   }
}