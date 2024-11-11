package com.stfn2.ggas.services.componentes.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TipoParametroSistema {
   private static final long serialVersionUID = -7199542056328195918L;

   static final int TIPO_ESTATICO = 1;
   String DESCRICAO_TIPO_ESTATICO = "Estático";
   static final int TIPO_ENTIDADE_DINAMICA = 2;
   String DESCRICAO_TIPO_ENTIDADE_DINAMICA = "Entidade dinâmica";
   static final int TIPO_NEGOCIO = 3;
   String DESCRICAO_TIPO_NEGOCIO = "Negócio";

   private int codigo;
   private String descricao;

   public TipoParametroSistema(int codigo) {
      this.setCodigo(codigo);
   }

   public void setCodigo(int codigo) {
      setCodigo(codigo);
      if(codigo == TIPO_ENTIDADE_DINAMICA) {
         setDescricao(DESCRICAO_TIPO_ENTIDADE_DINAMICA);
      } else if(codigo == TIPO_ESTATICO) {
         setDescricao(DESCRICAO_TIPO_ESTATICO);
      } else if(codigo == TIPO_NEGOCIO) {
         setDescricao(DESCRICAO_TIPO_NEGOCIO);
      }
   }
}
