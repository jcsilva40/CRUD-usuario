package com.stfn2.ggas.services.componentes.relatorio;

public enum FormatoImpressao {
   PDF, XLS, XLSX, CSV, RTF;

   public static final String FORMATO_IMPRESSAO = "formatoImpressao";

   /**
    * Obtém o formato de impressão.
    *
    * @param tipoExportacao - {@link String}
    * @return formato de impressão - {@link FormatoImpressao}
    */
   public static FormatoImpressao getFormatoImpressao(String tipoExportacao) {

      return FormatoImpressao.valueOf(tipoExportacao);
   }

   /**
    * Obter formato relatorio.
    *
    * @param formatoImpressao the formato impressao
    * @return the string
    */
   public static String obterFormatoRelatorio(FormatoImpressao formatoImpressao) {

      String retorno = ".pdf";

      if(formatoImpressao.equals(FormatoImpressao.PDF)) {
         retorno = ".pdf";
      } else if(formatoImpressao.equals(FormatoImpressao.CSV)) {
         retorno = ".csv";
      } else if(formatoImpressao.equals(FormatoImpressao.XLS)) {
         retorno = ".xls";
      } else if(formatoImpressao.equals(FormatoImpressao.XLSX)) {
         retorno = ".xlsx";
      } else if(formatoImpressao.equals(FormatoImpressao.RTF)) {
         retorno = ".rtf";
      }

      return retorno;
   }

}
