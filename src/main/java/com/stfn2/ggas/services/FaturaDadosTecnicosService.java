package com.stfn2.ggas.services;

import com.stfn2.ggas.constante.Constantes;
import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.Fatura;
import com.stfn2.ggas.domain.FaturaDadosTecnicos;
import com.stfn2.ggas.domain.dtos.FaturaDadosTecnicosDTO;
import com.stfn2.ggas.domain.dtos.basic.FaturaDadosTecnicosBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.FaturaDadosTecnicosFilterDTO;
import com.stfn2.ggas.repositories.FaturaDadosTecnicosRepository;
import com.stfn2.ggas.services.componentes.faturamento.vo.SubRelatorioFornecimentoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FaturaDadosTecnicosService extends BaseService<FaturaDadosTecnicos, FaturaDadosTecnicosDTO, FaturaDadosTecnicosBasicDTO, FaturaDadosTecnicosFilterDTO, FaturaDadosTecnicosRepository> {

   @Autowired
   private ConstanteSistemaService constanteSistemaService;

   public List<SubRelatorioFornecimentoVO> obterColecaoDadosTecnicos(Fatura fatura) {
      List<SubRelatorioFornecimentoVO> colecaoDadosTecnicos = new ArrayList<>();

      List<FaturaDadosTecnicos> dadosTecnicosFatura = obterPorChaveFatura(fatura.getId());

      for (FaturaDadosTecnicos dadosTecnicos : dadosTecnicosFatura) {
         SubRelatorioFornecimentoVO subRelDadosTecnicos = new SubRelatorioFornecimentoVO();

         subRelDadosTecnicos.setMedidor(dadosTecnicos.getNumeroMedidor());
         subRelDadosTecnicos.setPressaoAtual(dadosTecnicos.getPressaoAtual()	+
                 constanteSistemaService.obterConstantePorCodigo(Constantes.C_UNIDADE_PRESSAO_FORNECIMENTO).getValor());

         subRelDadosTecnicos.setLeituraAnterior(dadosTecnicos.getNumeroLeituraAnterior().toString());
         subRelDadosTecnicos.setLeituraAtual(dadosTecnicos.getNumeroLeituraAtual().toString());

         subRelDadosTecnicos.setConsumoNaoCorrigido(dadosTecnicos.getConsumoMedido().toString());
         subRelDadosTecnicos.setFatorCorrecao(dadosTecnicos.getFatorCorrecao().toString());
         subRelDadosTecnicos.setConsumoCorrigido(dadosTecnicos.getConsumoFaturado().toString());

         colecaoDadosTecnicos.add(subRelDadosTecnicos);
      }

      return colecaoDadosTecnicos;
   }

   public List<FaturaDadosTecnicos> obterPorChaveFatura(Long faturaId) {
      List<FaturaDadosTecnicos> listaDadosTecnicos = repository.obterPorChaveFatura(faturaId);
      return listaDadosTecnicos;
   }


}