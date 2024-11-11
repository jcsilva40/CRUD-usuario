package com.stfn2.ggas.domain.dtos.basic;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.CertificadoMedidor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CertificadoMedidorBasicDTO extends BaseDTO {
   private Long id;
   private String descricao;
   private LocalDate calibracao;

   public CertificadoMedidorBasicDTO(CertificadoMedidor entity) {
      super();
   }

   @Override
   public Long getId() {
      return this.id;
   }
}