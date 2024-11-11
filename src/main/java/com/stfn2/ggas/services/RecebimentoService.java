package com.stfn2.ggas.services;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.Recebimento;
import com.stfn2.ggas.domain.dtos.RecebimentoDTO;
import com.stfn2.ggas.domain.dtos.basic.RecebimentoBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.RecebimentoFilterDTO;
import com.stfn2.ggas.domain.enumTypes.RecebimentoSituacaoEnum;
import com.stfn2.ggas.repositories.RecebimentoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class RecebimentoService extends BaseService<Recebimento, RecebimentoDTO, RecebimentoBasicDTO, RecebimentoFilterDTO, RecebimentoRepository> {

   private ConstanteSistemaService constanteSistemaService;

   public RecebimentoService (ConstanteSistemaService constanteSistemaService){
      this.constanteSistemaService = constanteSistemaService;
   }
   public BigDecimal obterValorRecebimentoPelaFatura(Long idFatura) {

      BigDecimal valorRecebido = BigDecimal.ZERO;
      valorRecebido = repository.obterValorRecebimentoPelaFatura(idFatura, RecebimentoSituacaoEnum.ESTORNADO);

      if(valorRecebido == null){
         valorRecebido = BigDecimal.ZERO;
      }

      return valorRecebido;
   }

   public List<Recebimento> obterRecebimentoClassificadoPelaFatura(Long faturaId) {
      List<Recebimento> recebimentos = new ArrayList<>();
      List<RecebimentoSituacaoEnum> recebimentoSituacao = new ArrayList<>();
      recebimentoSituacao.addAll(Arrays.asList(RecebimentoSituacaoEnum.CLASSIFICADO,
                    RecebimentoSituacaoEnum.BAIXA_POR_DACAO, RecebimentoSituacaoEnum.VALOR_NAO_CONFERE));

      recebimentos = repository.obterRecebimentoClassificadoPelaFatura(faturaId, recebimentoSituacao);

      return recebimentos;
   }

   public List<Recebimento> obterRecebimentoPorFaturaGeral(Long id) {

      List<Recebimento> recebimentos = new ArrayList<>();
      recebimentos = repository.obterRecebimentoPorFaturaGeral(id);

      return recebimentos;
   }
}
