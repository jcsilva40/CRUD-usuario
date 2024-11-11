package com.stfn2.ggas.domain.dtos.basic;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.Imovel;
import com.stfn2.ggas.domain.ImovelContato;
import com.stfn2.ggas.domain.enumTypes.ContatoTipoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImovelContatoBasicDTO extends BaseDTO {
   private Long id;
   private Imovel imovel;
   private ContatoTipoEnum contatoTipo;
   private String descricao;
   private String cargo;
   private String ddd;
   private String telefone;
   private String ramal;
   private String email;
   private Boolean principal;
   private Boolean habilitado;

   public ImovelContatoBasicDTO(ImovelContato entity) {
      super();
   }

   @Override
   public Long getId() {
      return this.id;
   }
}