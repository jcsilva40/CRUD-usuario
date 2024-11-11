package com.stfn2.ggas.services.componentes.fabricaboleto.construtoresboletos.uteis;

import com.stfn2.ggas.constante.Constantes;

import java.math.BigDecimal;

public class DigitoAutoConferencia {
   private static final int PARAMETRO = 2;
   private static final int MULTIPLICADOR = 2;
   private static final int TAMANHO_MAX_PARAMETRO = 9;

   /**
    * Efetua o cálculo do dígito de auto conferência do código de barras. Este
    * cálculo só pode ser realizado após o preenchimento de todos os campos
    * envolvidos, o que varia de acordo com o seu propósito.
    *
    * @param numero
    *            a composiçao de todos os campos envolvidos, na ordem
    * @return o dígito resultante
    */
   public static final Integer modulo10(final String numero) {
      Integer dac = 0;
      Integer valorResultado = 0;
      Integer multiplicador = MULTIPLICADOR;

      int posicao = 1;
      for (int i = 0; i < numero.length(); i++) {
         Integer digito = Integer.valueOf(numero.substring(numero.length() - posicao, numero.length() - i));
         Integer resultado = digito * multiplicador;
         String resultadoStr = String.valueOf(resultado);
         for (int j = 0; j < resultadoStr.length(); j++) {
            valorResultado = valorResultado + Integer.parseInt(resultadoStr.substring(j, j + 1));
         }
         if (multiplicador == MULTIPLICADOR) {
            multiplicador = 1;
         } else {
            multiplicador = MULTIPLICADOR;
         }
         posicao++;
      }

      Integer restoDiv = valorResultado % BigDecimal.TEN.intValue();
      if (restoDiv != 0) {
         dac = BigDecimal.TEN.intValue() - restoDiv;
      }
      return dac;
   }

   /**
    * Efetua o cálculo do dígito de auto conferência do código de barras. Este
    * cálculo só pode ser realizado após o preenchimento de todos os campos
    * envolvidos, o que, para o caso do dígito de auto conferência do código de
    * barras, é o código de barras inteiro menos o próprio dígito.
    *
    * @param numero
    *            a composiçao de todos os campos do código de barras menos o
    *            próprio dígito, na ordem
    * @return o dígito resultante
    */
   public static final Integer modulo11(final String numero) {
      String aux = numero;
      int param = PARAMETRO;
      int soma = 0;

      for (int ind = aux.length() - 1; ind >= 0; ind--) {
         if (param > TAMANHO_MAX_PARAMETRO) {
            param = PARAMETRO;
         }
         soma = soma + (Integer.parseInt(aux.substring(ind, ind + 1)) * param);
         param = param + 1;
      }

      int resto = soma % Constantes.DIVISOR_MODULO11;
      int digito;

      if ((resto == 0) || (resto == 1) || (resto == 10) ) {
         digito = 1;
      } else {
         digito = Constantes.DIVISOR_MODULO11 - resto;
      }
      return digito;
   }

}
