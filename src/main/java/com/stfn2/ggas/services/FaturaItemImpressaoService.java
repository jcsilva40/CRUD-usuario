package com.stfn2.ggas.services;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.FaturaItem;
import com.stfn2.ggas.domain.FaturaItemImpressao;
import com.stfn2.ggas.domain.dtos.FaturaItemImpressaoDTO;
import com.stfn2.ggas.domain.dtos.basic.FaturaItemImpressaoBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.FaturaItemImpressaoFilterDTO;
import com.stfn2.ggas.repositories.FaturaItemImpressaoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FaturaItemImpressaoService extends BaseService<FaturaItemImpressao, FaturaItemImpressaoDTO, FaturaItemImpressaoBasicDTO, FaturaItemImpressaoFilterDTO, FaturaItemImpressaoRepository> {

   public Map<Long, List<FaturaItemImpressao>> consultarMapaFaturaItemImpressaoPorFaturaItem(
           List<FaturaItem> itensFatura) {

      Map<Long, List<FaturaItemImpressao>> mapaFaturaItens = new HashMap<>();
      List<FaturaItemImpressao> listaFaturaItemImpressao = new ArrayList<>();

      if (itensFatura != null && !itensFatura.isEmpty()) {
         for (FaturaItem faturaItem : itensFatura) {
            listaFaturaItemImpressao = repository.obterListaFaturaItemImpressaoPorIdFaturaItem(faturaItem.getId());
               mapaFaturaItens.put(faturaItem.getId(), listaFaturaItemImpressao);
         }
      }
      return mapaFaturaItens;
   }
}