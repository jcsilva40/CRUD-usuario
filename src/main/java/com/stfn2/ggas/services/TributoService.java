package com.stfn2.ggas.services;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.Tributo;
import com.stfn2.ggas.domain.TributoAliquota;
import com.stfn2.ggas.domain.dtos.TributoDTO;
import com.stfn2.ggas.domain.dtos.basic.TributoBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.TributoFilterDTO;
import com.stfn2.ggas.repositories.TributoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TributoService extends BaseService<Tributo, TributoDTO, TributoBasicDTO, TributoFilterDTO, TributoRepository> {

   public TributoAliquota obterAliquotaVigente(Long tributoId) {
      TributoAliquota tributoAliquota = repository.obterAliquotaVigente(tributoId, LocalDate.now());
      return tributoAliquota;
   }
}

