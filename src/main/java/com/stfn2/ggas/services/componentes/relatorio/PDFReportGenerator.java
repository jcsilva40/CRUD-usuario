package com.stfn2.ggas.services.componentes.relatorio;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.InputStream;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class PDFReportGenerator implements ReportGeneration{
   private final String path_relatorio = "/relatorios/";
   private final String ext_jasper = ".jasper";

   @Override
   public JasperPrint execute(List<?> objects, String relatorio, Map<String, Object> parametros) {
      parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
      JasperPrint jasperPrint = null;
      try {
      InputStream inputStream =
              this.getClass().getResourceAsStream(path_relatorio + relatorio + ext_jasper);
         jasperPrint = JasperFillManager.fillReport(inputStream, parametros, new JRBeanCollectionDataSource(objects));
      } catch (JRException e) {
         throw new RuntimeException(e);
      }
      return jasperPrint;
   }
}
