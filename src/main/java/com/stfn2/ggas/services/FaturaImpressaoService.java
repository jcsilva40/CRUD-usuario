package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.FaturaImpressao;
import com.stfn2.ggas.domain.dtos.FaturaImpressaoDTO;
import com.stfn2.ggas.domain.dtos.filter.FaturaImpressaoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.FaturaImpressaoBasicDTO;
import com.stfn2.ggas.repositories.FaturaImpressaoRepository;

@Service
public class FaturaImpressaoService extends BaseService<FaturaImpressao, FaturaImpressaoDTO, FaturaImpressaoBasicDTO, FaturaImpressaoFilterDTO, FaturaImpressaoRepository> {

   public FaturaImpressao obterDataGeracaoFaturaImpressaoPorFatura(Long faturaId) {
      FaturaImpressao retorno = null;

      if (faturaId != null) {
         retorno = repository.obterDataGeracaoFaturaImpressaoPorFatura(faturaId);
      }

      return retorno;
   }
}