package com.stfn2.ggas.services;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.Tributo;
import com.stfn2.ggas.domain.TributoAliquota;
import com.stfn2.ggas.domain.dtos.filter.TributoAliquotaFilterDTO;
import com.stfn2.ggas.domain.dtos.TributoAliquotaDTO;
import com.stfn2.ggas.domain.dtos.basic.TributoAliquotaBasicDTO;
import com.stfn2.ggas.domain.enumTypes.TipoAplicacaoEnum;
import com.stfn2.ggas.repositories.TributoAliquotaRepository;
import org.springframework.stereotype.Service;

import java.util.EnumSet;

@Service
public class TributoAliquotaService extends BaseService<TributoAliquota, TributoAliquotaDTO, TributoAliquotaBasicDTO, TributoAliquotaFilterDTO, TributoAliquotaRepository> {

   public TributoAliquota obterAliquotaVigente(Tributo tributo) {
      return repository.findByTributo(tributo);
   }

   public EnumSet<?> obterEnuns(){
      return EnumSet.allOf(TipoAplicacaoEnum.class);
   }
}

