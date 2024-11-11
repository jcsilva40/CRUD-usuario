package com.stfn2.ggas.services;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.ContratoPontoConsumo;
import com.stfn2.ggas.domain.dtos.ContratoPontoConsumoDTO;
import com.stfn2.ggas.domain.dtos.basic.ContratoPontoConsumoBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.ContratoPontoConsumoFilterDTO;
import com.stfn2.ggas.repositories.ContratoPontoConsumoRepository;
import org.springframework.stereotype.Service;

@Service
public class ContratoPontoConsumoService extends BaseService<ContratoPontoConsumo, ContratoPontoConsumoDTO, ContratoPontoConsumoBasicDTO, ContratoPontoConsumoFilterDTO, ContratoPontoConsumoRepository> {

   public ContratoPontoConsumo obterContratoAtivoPontoConsumo(Long pontoConsumoId){
      ContratoPontoConsumo contratoPontoConsumo = new ContratoPontoConsumo();
      contratoPontoConsumo = repository.obterContratoAtivoPontoConsumo(pontoConsumoId);
      return contratoPontoConsumo;
   }
}