package com.stfn2.ggas.services.componentes.fabricaboleto;

import com.google.common.collect.ImmutableMap;
import com.stfn2.ggas.config.exceptions.types.NegocioException;
import com.stfn2.ggas.core.utils.Log;
import com.stfn2.ggas.domain.ArrecadadorContratoConvenio;
import com.stfn2.ggas.domain.Contrato;
import com.stfn2.ggas.domain.DocumentoCobranca;
import com.stfn2.ggas.services.ArrecadadorContratoConvenioService;
import com.stfn2.ggas.services.componentes.fabricaboleto.construtoresboletos.ConstrutorRelatorioBoleto;
import com.stfn2.ggas.tools.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public final class FabricaConstrutorBoleto {


   private static ArrecadadorContratoConvenioService arrecadadorContratoConvenioService;

   private static final ImmutableMap<String, LeiauteBoletoBancario> LEIAUTES = leiautesDisponiveis();

   @Autowired
   public FabricaConstrutorBoleto(ArrecadadorContratoConvenioService arrecadadorContratoConvenioService) {
      this.arrecadadorContratoConvenioService = arrecadadorContratoConvenioService;
   }

   private static final ImmutableMap<String, LeiauteBoletoBancario> leiautesDisponiveis() {
      return ImmutableMap.<String, LeiauteBoletoBancario>builder().put("001", LeiauteBoletoBancario.BB)
              .put("033", LeiauteBoletoBancario.SANTANDER).put("341", LeiauteBoletoBancario.ITAU)
              .put("104", LeiauteBoletoBancario.CAIXA).put("237", LeiauteBoletoBancario.BRADESCO)
              .put("004", LeiauteBoletoBancario.BN).put("707", LeiauteBoletoBancario.DAYCOVAL)
              .put("422", LeiauteBoletoBancario.SAFRA).build();
   }

   /**
    * Gera um novo {@link ConstrutorRelatorioBoleto} com os dados passados como
    * argumento.
    *
    * @param documentoCobranca o {@link DocumentoCobranca}
    * @return o código de barras preenchido
    * @throws NegocioException the negocio exception
    */
   public static final ConstrutorRelatorioBoleto novoConstrutorBoleto(DocumentoCobranca documentoCobranca) throws NegocioException {
      return LEIAUTES.get(convenioParaBoleto(documentoCobranca).getArrecadadorContrato().getArrecadador().getBanco()
              .getCodigoBanco()).construtorBoleto(documentoCobranca);
   }

   public static final ConstrutorRelatorioBoleto novoConstrutorBoleto(DocumentoCobranca documentoCobranca,
                                                               Contrato contrato) throws NegocioException {
      return LEIAUTES.get(convenioParaBoleto(documentoCobranca, contrato).getArrecadadorContrato().getArrecadador()
              .getBanco().getCodigoBanco()).construtorBoleto(documentoCobranca);
   }

   /**
    * Convenio para boleto.
    *
    * @param documentoCobranca the documento cobranca
    * @return the arrecadador contrato convenio
    */
   public  static ArrecadadorContratoConvenio convenioParaBoleto(DocumentoCobranca documentoCobranca) {
      ArrecadadorContratoConvenio arrecadadorConvenioPadrao = convenioPadrao();

      if (documentoCobranca != null && documentoCobranca.getId() > 0
              && documentoCobranca.getItens().iterator().next().getFaturaGeral() != null) {

         ArrecadadorContratoConvenio arrecadadorContratoConvenio = arrecadadorContratoConvenioService
                 .obterArrecadadorContratoConvenioPorDocumentoCobranca(documentoCobranca);

         if (arrecadadorContratoConvenio != null) {
            return ToolUtil.coalescenciaNula(arrecadadorContratoConvenio, arrecadadorConvenioPadrao);
         } else {
            return arrecadadorConvenioPadrao;
         }
      }
      return arrecadadorConvenioPadrao;
   }

   public static ArrecadadorContratoConvenio convenioParaBoleto(DocumentoCobranca documentoCobranca,
                                                         Contrato contrato) {
      if (documentoCobranca != null && documentoCobranca.getId() > 0
              && documentoCobranca.getItens().iterator().next().getFaturaGeral() != null) {
         return convenioParaBoleto(documentoCobranca);
      } else if (documentoCobranca != null) {
         return contrato.getArrecadadorContratoConvenio();
      }
      return convenioPadrao();
   }

   /**
    * Convenio padrao.
    *
    * @return the arrecadador contrato convenio
    */
   private static ArrecadadorContratoConvenio convenioPadrao() {
      return arrecadadorContratoConvenioService.obterArrecadadorContratoConvenioPadrao();
   }

   public static Map<String, Object> buildMap(DocumentoCobranca doc, Contrato contrato) {
      Map<String, Object> dadosBoletos = null;
      try {
         dadosBoletos = FabricaConstrutorBoleto.novoConstrutorBoleto(doc, contrato).preencherNumeroCodigoBarras()
                 .preencherNumeroDocumentoPagamento().preencherNomeClienteBoleto().preencherEnderecoCliente()
                 .preencherDataDocumento().preencherValorDocumento().preencherLocalDePagamento()
                 .preencherImagemLogoBanco().preencherNumeroBanco().preencherCedente().preencherAgenciaCodigo()
                 .preencherNumeroDocumento().preencherEspecieDocumento().preencherDataProcessamento()
                 .preencherNossoNumero().preencherCarteira().preencherDescontosAbatimentos()
                 .preencherOutrasDeducoes().preencherMoraMulta().preencherOutrosAcrescimos()
                 .preencherDataVencimento().preencherNomeBanco().preencherCnpjCpf()
                 .preencherValorTotalBoleto().mapaParametros();

         dadosBoletos.put("codigoCliente", contrato.getNumeroClienteDebito());
         return dadosBoletos;
      } catch (NegocioException e) {
         Log.erroStatic( FabricaConstrutorBoleto.class,"Não foi possível criar o boleto solicitado!", e.getMessage());
         return dadosBoletos;
      }
   }
}
