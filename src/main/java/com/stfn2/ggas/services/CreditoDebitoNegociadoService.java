package com.stfn2.ggas.services;

import com.stfn2.ggas.constante.Constantes;
import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.CreditoDebitoARealizar;
import com.stfn2.ggas.domain.CreditoDebitoNegociado;
import com.stfn2.ggas.domain.dtos.CreditoDebitoNegociadoDTO;
import com.stfn2.ggas.domain.dtos.basic.CreditoDebitoNegociadoBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.CreditoDebitoNegociadoFilterDTO;
import com.stfn2.ggas.repositories.CreditoDebitoNegociadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CreditoDebitoNegociadoService extends BaseService<CreditoDebitoNegociado, CreditoDebitoNegociadoDTO, CreditoDebitoNegociadoBasicDTO, CreditoDebitoNegociadoFilterDTO, CreditoDebitoNegociadoRepository> {

   @Autowired
   private ConstanteSistemaService constanteSistemaService;

   @Autowired
   private ParametroSistemaService parametroSistemaService;

   public BigDecimal obterValorCobradoCredito(Long idCredito, Long idFatura) {

      BigDecimal valor = BigDecimal.ZERO;

      Long idSituacaoCancelada = Long.valueOf(constanteSistemaService
              .obterValorConstanteSistemaPorCodigo(Constantes.C_CREDITO_DEBITO_CANCELADO));

      valor = repository.obterValorCobradoCredito(idCredito, idFatura, idSituacaoCancelada);

      return valor;
   }

   public Integer consultarQuantidadeFaturaItemPorCreditoDebito(CreditoDebitoARealizar creditoDebitoARealizar) {

      Integer retorno = 0;
      Long idSituacaoCancelada = Long.valueOf(constanteSistemaService
              .obterValorConstanteSistemaPorCodigo(Constantes.C_CREDITO_DEBITO_CANCELADO));

      retorno = repository.consultarQuantidadeFaturaItemPorCreditoDebito(creditoDebitoARealizar.getId(),
              idSituacaoCancelada);

      return retorno;
   }
}