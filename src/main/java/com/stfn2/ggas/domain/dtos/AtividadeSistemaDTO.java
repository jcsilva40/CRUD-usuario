package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.AtividadeSistema;
import com.stfn2.ggas.domain.ModuloSistema;
import com.stfn2.ggas.domain.OperacaoSistema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AtividadeSistemaDTO extends BaseDTO {
   private Long id;
   private AtividadeSistema atividadeSistema;
   private OperacaoSistema operacaoSistemaPrecedente;
   private ModuloSistema moduloSistema;
   private String descricao;
   private String emailRemetente;
   private String email;
   private Integer horaInicialProcesso;
   private Integer horaFinalProcesso;
   private Boolean obrigatoriedade;
   private Boolean externa;
   private Boolean cronograma;
   private Boolean detalhaRota;
   private Boolean agendamento;
   private Boolean enviaEmail;
   private Integer sequencia;
   private Integer diasIntervalo;
   private Integer diasDuracao;

   public AtividadeSistemaDTO(AtividadeSistema entity) {
      super();
   }

   @Override
   public Long getId() {
      return this.id;
   }
}