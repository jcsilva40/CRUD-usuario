package com.stfn2.ggas.services.componentes.faturamento.vo;

import com.stfn2.ggas.domain.Cep;
import com.stfn2.ggas.domain.ContratoPontoConsumo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoTemporarioFaturamentoVO implements Serializable {
   private static final long serialVersionUID = -5269131620541787438L;

   private ContratoPontoConsumo contratoPontoConsumo;
   private Cep cep;
   private String numeroImovel;
   private String complemento;
   private String email;
}
