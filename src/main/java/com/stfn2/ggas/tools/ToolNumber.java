package com.stfn2.ggas.tools;

import com.stfn2.ggas.constante.Constantes;
import com.stfn2.ggas.core.utils.Log;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class ToolNumber {

   private static final int INDICE_QUATRO = 4;
   private static final int INDICE_SEIS = 6;
   private static final int INDICE_DOIS = 2;
   private static final int INDICE_TRES = 3;
   private static final int INDICE_SETE = 7;

   public static Boolean isPositive(Number number) {
      if (number == null) return false;

      Double valueDouble = number.doubleValue();
      return valueDouble > 0;
   }

   public static boolean menorQueZero(BigDecimal valor) {
      if (valor != null) {
         return valor.compareTo(BigDecimal.ZERO) < 0;
      }
      throw new IllegalArgumentException("OPERANDO_NULO");
   }

   public static BigDecimal toBigDecimal(Number value) {
      if (value == null)
         return BigDecimal.ZERO;

      Double valueDouble = value.doubleValue();
      return BigDecimal.valueOf(valueDouble);
   }

   public static Boolean isMenor(BigDecimal n1, BigDecimal n2) {
      return n1.compareTo(n2) < 0;
   }

   public static Boolean isMaior(BigDecimal n1, BigDecimal n2) {
      return n1.compareTo(n2) > 0;
   }

   public static Boolean isMaiorOuIgual(BigDecimal n1, BigDecimal n2) {
      return n1.compareTo(n2) > 0 || n1.equals(n2);
   }

   public static Boolean isMenorOuIgual(BigDecimal n1, BigDecimal n2) {
      return n1.compareTo(n2) < 0 || n1.equals(n2);
   }

   public static String converterCampoValorDecimalParaString(String rotulo, BigDecimal valor, Locale locale,
                                                             int numeroDecimais) {
      if(valor == null){
         return BigDecimal.ZERO.toString();
      }
      try {
         DecimalFormat decf = obterDecimalFormatLocalizado(locale, numeroDecimais);
         return decf.format(valor);
      } catch (Exception e) {
         return valor.toString();
      }
   }

   private static DecimalFormat obterDecimalFormatLocalizado(Locale locale, Integer numeroDecimais) {

      DecimalFormat decimalFormat = new DecimalFormat(Constantes.FORMATO_VALOR_NUMERO,
              new DecimalFormatSymbols(locale));
      decimalFormat.setParseBigDecimal(true);
      decimalFormat.setDecimalSeparatorAlwaysShown(true);
      if (numeroDecimais == null) {
         decimalFormat.setMinimumFractionDigits(Constantes.QUANTIDADE_CASAS_VALOR_DECIMAL);
      } else {
         decimalFormat.setMinimumFractionDigits(numeroDecimais);
         decimalFormat.setMaximumFractionDigits(numeroDecimais);
      }

      return decimalFormat;
   }

   public static String converterCampoCurrencyParaString(BigDecimal valor, Locale locale) {

      NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);

      if (valor.compareTo(BigDecimal.ZERO) >= 0) {

         return numberFormat.format(valor).substring(INDICE_TRES);

      } else {
         String numero = numberFormat.format(valor).substring(INDICE_TRES).trim();
         return "-" + numero.trim();
      }
   }

   public static BigDecimal converterCampoStringParaValorBigDecimal(String rotulo, String strValor, String formato,
                                                                    Locale locale) {

      BigDecimal valor = null;
      DecimalFormat decimalFormat = new DecimalFormat(formato, new DecimalFormatSymbols(locale));
      decimalFormat.setParseBigDecimal(true);

      String novoValor = strValor.replaceAll("\u00A0", "");


      try {
         valor = new BigDecimal(novoValor);
         return valor;
      } catch (Exception ex) {
         try {
            valor = (BigDecimal) decimalFormat.parse(novoValor);
         } catch (ParseException e) {
            Log.erroStatic(ToolNumber.class, Constantes.ERRO_DADOS_INVALIDOS, e.getCause().getMessage());
         }
         return valor;
      }
   }
   
   private static final int[][] QUASI = { { 0, 3, 1, 7, 5, 9, 8, 6, 4, 2 }, { 7, 0, 9, 2, 1, 5, 4, 8, 6, 3 },
			{ 4, 2, 0, 6, 8, 7, 1, 3, 5, 9 }, { 1, 7, 5, 0, 9, 8, 3, 4, 2, 6 }, { 6, 1, 2, 3, 0, 4, 5, 9, 7, 8 },
			{ 3, 6, 7, 4, 2, 0, 9, 5, 8, 1 }, { 5, 8, 6, 9, 7, 2, 0, 1, 3, 4 }, { 8, 9, 4, 5, 3, 6, 2, 0, 1, 7 },
			{ 9, 4, 3, 8, 6, 1, 7, 2, 0, 5 }, { 2, 5, 8, 1, 4, 3, 6, 7, 9, 0 } };
   
    /**
    * 
    * @param number
    * @return
    */
    public static Integer checkSum(String number) {
        number = number.trim();
        int rowIndex = 0;
        int colIndex = 0;
        for (int i = 0; i < number.length(); i++) {
                colIndex = Integer.parseInt(String.valueOf(number.charAt(i)));
                rowIndex = QUASI[rowIndex][colIndex];
        }
        return rowIndex;
    }
}
