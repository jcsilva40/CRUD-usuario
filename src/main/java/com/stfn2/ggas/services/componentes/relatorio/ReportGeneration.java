package com.stfn2.ggas.services.componentes.relatorio;

import net.sf.jasperreports.engine.JasperPrint;

import java.util.List;
import java.util.Map;

public interface ReportGeneration {
   public JasperPrint execute(List<?> objects, String relatorio, Map<String, Object> parametros);
}
