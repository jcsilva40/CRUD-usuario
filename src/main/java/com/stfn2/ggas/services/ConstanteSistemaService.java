package com.stfn2.ggas.services;

import com.stfn2.ggas.constante.Constantes;
import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.ConstanteSistema;
import com.stfn2.ggas.domain.dtos.ConstanteSistemaDTO;
import com.stfn2.ggas.domain.dtos.basic.ConstanteSistemaBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.ConstanteSistemaFilterDTO;
import com.stfn2.ggas.repositories.ConstanteSistemaRepository;
import org.springframework.stereotype.Service;

@Service
public class ConstanteSistemaService extends BaseService<ConstanteSistema, ConstanteSistemaDTO, ConstanteSistemaBasicDTO, ConstanteSistemaFilterDTO, ConstanteSistemaRepository> {

   public ConstanteSistema obterConstantePorCodigo(String codigo){
      return repository.obterConstantePorCodigo(codigo);
   }

   public String obterValorConstanteSistemaPorCodigo(String codigo) {
      ConstanteSistema constanteSistema = this.obterConstantePorCodigo(codigo);

      if(constanteSistema != null){
         return constanteSistema.getValor();
      }
      return null;
   }

   public ConstanteSistema obterConstanteIcms() {
      return obterConstantePorCodigo(Constantes.C_ICMS);
   }
   
    public Long obterValorLongConstanteSistemaPorCodigo(String codigo) {
      ConstanteSistema constanteSistema = this.obterConstantePorCodigo(codigo);

      if(constanteSistema != null){
         return Long.valueOf(constanteSistema.getValor());
      }
      return null;
   }
}