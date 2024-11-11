package com.stfn2.ggas.services;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.PontoConsumo;
import com.stfn2.ggas.domain.PontoConsumoTributoAliquota;
import com.stfn2.ggas.domain.dtos.filter.PontoConsumoTributoAliquotaFilterDTO;
import com.stfn2.ggas.domain.dtos.PontoConsumoTributoAliquotaDTO;
import com.stfn2.ggas.domain.dtos.basic.PontoConsumoTributoAliquotaBasicDTO;
import com.stfn2.ggas.repositories.PontoConsumoTributoAliquotaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PontoConsumoTributoAliquotaService extends BaseService<PontoConsumoTributoAliquota, PontoConsumoTributoAliquotaDTO, PontoConsumoTributoAliquotaBasicDTO, PontoConsumoTributoAliquotaFilterDTO, PontoConsumoTributoAliquotaRepository> {

   public List<PontoConsumoTributoAliquota> verificarDiferimentoPontoConsumo(PontoConsumo pontoConsumo) {
      List<PontoConsumoTributoAliquota> pctas = repository.findByPontoConsumo(pontoConsumo);

      if(pctas == null){
         pctas = new ArrayList<PontoConsumoTributoAliquota>();
      }

      return pctas;
   }
}

