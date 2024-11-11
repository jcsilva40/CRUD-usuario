package com.stfn2.ggas.services.componentes.relatorio;

import net.sf.jasperreports.engine.JasperPrint;

import java.util.List;
import java.util.Map;

public class CSVReportGenerator implements ReportGeneration{
   @Override
   public JasperPrint execute(List<?> objects, String relatorio, Map<String, Object> parametros) {
      return null;
   }
}
