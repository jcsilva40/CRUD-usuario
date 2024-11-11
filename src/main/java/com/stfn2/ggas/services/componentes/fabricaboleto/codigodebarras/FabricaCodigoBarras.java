package com.stfn2.ggas.services.componentes.fabricaboleto.codigodebarras;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.stfn2.ggas.config.exceptions.types.NegocioException;
import com.stfn2.ggas.constante.Constantes;
import com.stfn2.ggas.services.componentes.fabricaboleto.codigodebarras.dados.ChaveLeiauteCodigoBarras;
import com.stfn2.ggas.services.componentes.fabricaboleto.codigodebarras.dados.DadosCodigoBarras;

public final class FabricaCodigoBarras {
   private static final String BANCO_ARRECADACAO = "001";
   private static final String CAIXA = "104";
   private static final String ITAU = "341";
   private static final String BANCO_DO_BRASIL = "001";
   private static final String SANTANDER = "033";
   private static final String DAYCOVAL = "707";
   private static final String BANCO_NORDESTE = "004";
   private static final String BANCO_BRADESCO = "237";
   private static final String BANCO_SAFRA = "422";
   private static final String ARRECADACAO = "Arrecadação";
   private static final String BOLETO_BANCARIO = "Boleto Bancário";
   private static final String DEBITO_AUTOMATICO = "Débito Automático";

   public static final ImmutableMap<ChaveLeiauteCodigoBarras, LeiauteCodigoBarras> LEIAUTES = leiautesDisponiveis();

   private FabricaCodigoBarras() {

   }


   /**
    * @return
    */
   private static final ImmutableMap<ChaveLeiauteCodigoBarras, LeiauteCodigoBarras> leiautesDisponiveis() {

      return ImmutableMap.<ChaveLeiauteCodigoBarras, LeiauteCodigoBarras> builder()
              .put(new ChaveLeiauteCodigoBarras(BANCO_DO_BRASIL, BOLETO_BANCARIO), LeiauteCodigoBarras.BB7POSICOES)
              .put(new ChaveLeiauteCodigoBarras(BANCO_DO_BRASIL, DEBITO_AUTOMATICO), LeiauteCodigoBarras.BB7POSICOES)
              .put(new ChaveLeiauteCodigoBarras(ITAU, BOLETO_BANCARIO), LeiauteCodigoBarras.ITAU)
              .put(new ChaveLeiauteCodigoBarras(ITAU, DEBITO_AUTOMATICO), LeiauteCodigoBarras.ITAU)
              .put(new ChaveLeiauteCodigoBarras(DAYCOVAL, BOLETO_BANCARIO), LeiauteCodigoBarras.DAYCOVAL)
              .put(new ChaveLeiauteCodigoBarras(DAYCOVAL, DEBITO_AUTOMATICO), LeiauteCodigoBarras.DAYCOVAL)
              .put(new ChaveLeiauteCodigoBarras(CAIXA, BOLETO_BANCARIO), LeiauteCodigoBarras.CAIXA)
              .put(new ChaveLeiauteCodigoBarras(CAIXA, DEBITO_AUTOMATICO), LeiauteCodigoBarras.CAIXA)
              .put(new ChaveLeiauteCodigoBarras(BANCO_NORDESTE, BOLETO_BANCARIO), LeiauteCodigoBarras.BANCO_NORDESTE)
              .put(new ChaveLeiauteCodigoBarras(BANCO_NORDESTE, DEBITO_AUTOMATICO), LeiauteCodigoBarras.BANCO_NORDESTE)
              .put(new ChaveLeiauteCodigoBarras(BANCO_BRADESCO, DEBITO_AUTOMATICO), LeiauteCodigoBarras.BANCO_BRADESCO)
              .put(new ChaveLeiauteCodigoBarras(BANCO_BRADESCO, BOLETO_BANCARIO), LeiauteCodigoBarras.BANCO_BRADESCO)
              .put(new ChaveLeiauteCodigoBarras(SANTANDER, BOLETO_BANCARIO), LeiauteCodigoBarras.SANTANDER)
              .put(new ChaveLeiauteCodigoBarras(SANTANDER, DEBITO_AUTOMATICO), LeiauteCodigoBarras.SANTANDER)
              .put(new ChaveLeiauteCodigoBarras(BANCO_SAFRA, BOLETO_BANCARIO), LeiauteCodigoBarras.SAFRA)
              .put(new ChaveLeiauteCodigoBarras(BANCO_SAFRA, DEBITO_AUTOMATICO), LeiauteCodigoBarras.SAFRA)
              .put(new ChaveLeiauteCodigoBarras(BANCO_ARRECADACAO, ARRECADACAO), LeiauteCodigoBarras.ARRECADACAO).build();
   }

   /**
    * Gera um novo {@link CodigoBarras} já preenchido com os dados passados como
    * argumento.
    *
    * @param dados
    *            os dados usados no preenchimento do {@link CodigoBarras}
    * @return o código de barras preenchido
    * @throws NegocioException the negocio exception
    */
   public static final CodigoBarras novoCodigoBarras(DadosCodigoBarras dados) throws NegocioException {

      // validacao temporaria, essa validacao sera feita preventivamente
      if (!LEIAUTES.containsKey(dados.chaveLeiaute())) {
         throw new NegocioException(Constantes.ERRO_NEGOCIO_BANCO_CODIGO_BARRAS_NAO_IMPLEMENTADO, dados.chaveLeiaute());
      }


      return LEIAUTES.get(dados.chaveLeiaute()).codigoBarras(dados);
   }

   /**
    * Gera uma string separados com virgula com os bancos suportados pelo sistema.
    *
    * @return os codigos dos bancos suportados pelo sistema
    */
   public static final String obterCodigoBancosSuportados() {

      StringBuilder codigoBancosSuportados = new StringBuilder();
      ImmutableSet<ChaveLeiauteCodigoBarras> bancos = FabricaCodigoBarras.LEIAUTES.keySet();

      int cont = 0;
      for (ChaveLeiauteCodigoBarras banco : bancos) {
         cont++;
         if (banco.getCodigoBanco()!=null) {
            codigoBancosSuportados.append(banco.getCodigoBanco());
         }
         if (cont < bancos.size()) {
            codigoBancosSuportados.append(", ");
         }
      }

      return codigoBancosSuportados.toString();
   }
}
