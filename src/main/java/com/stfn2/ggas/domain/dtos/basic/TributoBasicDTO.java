package com.stfn2.ggas.domain.dtos.basic;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.Tributo;
import com.stfn2.ggas.domain.TributoAliquota;
import com.stfn2.ggas.domain.enumTypes.EsferaPoderEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TributoBasicDTO extends BaseDTO {
   private Long id;
   private String descricao;
   private String abreviacao;
   private List<TributoAliquota> tributoAliquota;
   private EsferaPoderEnum esferaPoder;
   private Boolean padrao;
   private Boolean servico;
   private Boolean produto;
   private Boolean habilitado;

//   public List<TributoAliquotaDTO> getTributoAliquota() {
//      return MapperImpl.parseListObject(this.tributoAliquota, TributoAliquotaDTO.class);
//   }

   public TributoBasicDTO(Tributo entity) {
      super();
   }

   @Override
   public Long getId() {
      return this.id;
   }
}