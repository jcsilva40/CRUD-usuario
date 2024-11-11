package com.stfn2.ggas.services.componentes.fabricaboleto.codigodebarras;

import com.stfn2.ggas.services.componentes.fabricaboleto.codigodebarras.dados.DadosCodigoBarras;

public enum LeiauteCodigoBarras {

   BB7POSICOES {
      @Override
      CodigoBarras codigoBarras(DadosCodigoBarras dados) {
         return new CodigoBarrasBB7Posicoes(dados);
      }
   },
   SANTANDER {
      @Override
      CodigoBarras codigoBarras(DadosCodigoBarras dados) {
         return new CodigoBarrasSantander(dados);
      }
   },
   BANCO_NORDESTE {
      @Override
      CodigoBarras codigoBarras(DadosCodigoBarras dados) {
         return new CodigoBarrasSantander(dados);
      }
   },
   BANCO_BRADESCO {
      @Override
      CodigoBarras codigoBarras(DadosCodigoBarras dados) {
         return new CodigoBarrasSantander(dados);
      }
   },
   CAIXA {
      @Override
      CodigoBarras codigoBarras(DadosCodigoBarras dados) {
         return new CodigoBarrasCaixa(dados);
      }
   },
   ITAU {
      @Override
      CodigoBarras codigoBarras(DadosCodigoBarras dados) {
         return new CodigoBarrasItau(dados);
      }
   },
   DAYCOVAL {
      @Override
      CodigoBarras codigoBarras(DadosCodigoBarras dados) {
         return new CodigoBarrasDaycoval(dados);
      }
   },
   SAFRA {
      @Override
      CodigoBarras codigoBarras(DadosCodigoBarras dados) {
         return new CodigoBarrasBancoSafra(dados);
      }
   },
   ARRECADACAO {
      @Override
      CodigoBarras codigoBarras(DadosCodigoBarras dados) {
         return new AdaptadorCodigoBarrasArrecadacao(dados);
      }
   };

   /**
    * Cria um {@link CodigoBarras} preenchido cujo leiaute está associado ao
    * tipo da enumeração.
    *
    * @param dados
    *            os dados usados no preenchimento do {@link CodigoBarras}
    * @return o {@link CodigoBarras} preenchido
    */
   abstract CodigoBarras codigoBarras(DadosCodigoBarras dados);
}
