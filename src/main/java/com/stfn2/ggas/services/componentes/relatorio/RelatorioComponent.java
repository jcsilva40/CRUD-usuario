package com.stfn2.ggas.services.componentes.relatorio;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PRAcroForm;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.stfn2.ggas.config.exceptions.types.NegocioException;
import com.stfn2.ggas.config.exceptions.types.ReportException;
import com.stfn2.ggas.constante.Constantes;
import com.stfn2.ggas.core.utils.Log;
import com.stfn2.ggas.domain.dtos.DownloadDTO;
import com.stfn2.ggas.services.ConstanteSistemaService;
import com.stfn2.ggas.services.ParametroSistemaService;
import com.stfn2.ggas.tools.ToolDate;
import com.stfn2.ggas.tools.ToolFile;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Stream;

@Service
public class RelatorioComponent {

   private static ConstanteSistemaService constanteSistemaService;
   private static ParametroSistemaService parametroSistemaService;
   private static XLSXReportGenerator xlsxReportGenerator;

   public RelatorioComponent(ConstanteSistemaService constanteSistemaService,
                             ParametroSistemaService parametroSistemaService,
                             XLSXReportGenerator xlsxReportGenerator){
      this.constanteSistemaService = constanteSistemaService;
      this.parametroSistemaService = parametroSistemaService;
      this.xlsxReportGenerator = xlsxReportGenerator;
   }

   public byte[] executePdf(List<?> objects, String relatorio, Map<String, Object> parametros) {

      try {
         ReportGeneration generation = new PDFReportGenerator();
         JasperPrint jasperPrint = generation.execute(objects, relatorio, parametros);
         return JasperExportManager.exportReportToPdf(jasperPrint);
      } catch (JRException e) {
         throw new ReportException(e.getMessage());
      }
   }

   public byte[] executeCsv(List<?> objects, String relatorio, Map<String, Object> parametros) {

      try {
         ReportGeneration generation = new CSVReportGenerator();
         JasperPrint jasperPrint = generation.execute(objects, relatorio, parametros);
         return JasperExportManager.exportReportToPdf(jasperPrint);
      } catch (JRException e) {
         throw new ReportException(e.getMessage());
      }
   }

   public static byte[] gerarRelatorioPDF(List<?> dados, Map<String, Object> parametros, String arquivoJasper) {
      return gerarRelatorio(dados, parametros, arquivoJasper, FormatoImpressao.PDF);
   }

   public static byte[] gerarRelatorio(List<?> dados, Map<String, Object> parametros, String arquivoJasper,
                                       FormatoImpressao formatoImpressao) {

      //User usuario = controladorUsuario.buscar(Constantes.USUARIO_AUDITORIA);

      JasperReport jasperReport = null;
      InputStream inputStream = null;
      byte[] bytes = null;

      try {
         JRDataSource source = new JRBeanCollectionDataSource(dados);
         inputStream = RelatorioComponent.class.getClassLoader().getResourceAsStream(arquivoJasper);

         if (inputStream == null) {
            throw new NegocioException("Relatório não encontrado", true);
         }

         if(formatoImpressao != null) {

            if(formatoImpressao.equals(FormatoImpressao.PDF)) {

              // JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros,
              //         new JRBeanCollectionDataSource(dados));

              // return JasperExportManager.exportReportToPdf(jasperPrint);

               jasperReport = (JasperReport) JRLoader.loadObject(inputStream);
               bytes = JasperRunManager.runReportToPdf(jasperReport, parametros,
                       new JRBeanCollectionDataSource(dados));
               inputStream.close();

            } else {

               JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, source);
               ByteArrayOutputStream baos = new ByteArrayOutputStream();
               Exporter exporter = null;

               if(formatoImpressao.equals(FormatoImpressao.RTF)) {

                  exporter = new JRRtfExporter();
                  exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                  //exporter.setParameter(JRCsvExporterParameter.JASPER_PRINT, print);
                  exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
                  //exporter.setParameter(JRCsvExporterParameter.OUTPUT_STREAM, baos);

               } else if(formatoImpressao.equals(FormatoImpressao.CSV)) {

                  exporter = new JRCsvExporter();

                  exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                  //exporter.setParameter(JRCsvExporterParameter.JASPER_PRINT, print);
                  exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
                  //exporter.setParameter(JRCsvExporterParameter.OUTPUT_STREAM, baos);

                  SimpleCsvExporterConfiguration configuration = new SimpleCsvExporterConfiguration();
                  configuration.setFieldDelimiter("|");
                  //exporter.setParameter(JRCsvExporterParameter.FIELD_DELIMITER, "|");
                  configuration.setRecordDelimiter("\n");
                  //exporter.setParameter(JRCsvExporterParameter.RECORD_DELIMITER, "\n");

                  exporter.setConfiguration(configuration);


               } else if(formatoImpressao.equals(FormatoImpressao.XLS)) {

                  exporter = new JRXlsxExporter();

                  exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                  //exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, print);
                  exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
                  //exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, baos);

                  SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
                  configuration.setOnePagePerSheet(false);
                  //exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
                  configuration.setMaxRowsPerSheet(65000);
                  //exporter.setParameter(JRXlsExporterParameter.MAXIMUM_ROWS_PER_SHEET, 65000);
                  configuration.setDetectCellType(true);
                  //exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
                  configuration.setRemoveEmptySpaceBetweenColumns(false);
                  //exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.FALSE);
                  configuration.setRemoveEmptySpaceBetweenRows(false);
                  //exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.FALSE);
                  configuration.setIgnorePageMargins(true);
                  //exporter.setParameter(JRXlsExporterParameter.IGNORE_PAGE_MARGINS, Boolean.TRUE);

                  exporter.setConfiguration(configuration);

               }

               if(exporter != null) {
                  exporter.exportReport();
               }

               bytes = baos.toByteArray();

               baos.flush();
               baos.close();

            }
         }

      } catch(JRException e) {
         Log.erroStatic(RelatorioComponent.class,"Erro ao Gerar o Arquivo", e.getMessage());
         if(e.getMessage().contains("Image read failed.")) {
            if (parametros.containsKey("imagemLogoBanco")) {
               Log.erroStatic(RelatorioComponent.class,"Banco sem logo registrado", e.getMessage());
            }
            Log.erroStatic(RelatorioComponent.class,"CDL sem logo registrado", e.getMessage());
         }
      } catch(IOException |  NegocioException e) {
         Log.erroStatic(RelatorioComponent.class,e.getMessage(), e.getCause().getMessage());
      }

      return bytes;
   }

   public static byte[] unificarRelatoriosPdf(List<byte[]> relatoriosPdf) {

      byte[] pdfConcatenado = null;
      ByteArrayOutputStream outputStream = null;

      try {
         if(relatoriosPdf != null && !relatoriosPdf.isEmpty()) {
            byte[] relatorio = null;
            PdfCopy copiaPdf = null;
            Document documentoPdf = null;
            int qtdRelatorios = 0;

            Iterator<byte[]> iterator = relatoriosPdf.iterator();
            outputStream = new ByteArrayOutputStream();
            while(iterator.hasNext()) {
               relatorio = iterator.next();
               PdfReader reader = new PdfReader(relatorio);

               int qtdPaginas = reader.getNumberOfPages();
               if(qtdRelatorios == 0) {
                  documentoPdf = new Document(reader.getPageSizeWithRotation(1));
                  copiaPdf = new PdfCopy(documentoPdf, outputStream);
                  documentoPdf.open();
               }

               PdfImportedPage page;

               for (int i = 0; i < qtdPaginas;) {
                  ++i;
                  page = copiaPdf.getImportedPage(reader, i);
                  copiaPdf.addPage(page);
               }

               PRAcroForm form = reader.getAcroForm();

               if(form != null) {
                  copiaPdf.copyDocumentFields(reader);
               }

               qtdRelatorios++;
            }
            if(documentoPdf!=null){
               documentoPdf.close();
            }
         }
      } catch(IOException e) {
         Log.erroStatic(RelatorioComponent.class,e.getMessage(), e.getCause().getMessage());
      } catch(DocumentException e) {
         Log.erroStatic(RelatorioComponent.class,e.getMessage(), e.getCause().getMessage());
      }

      if(outputStream != null) {
         pdfConcatenado = outputStream.toByteArray();
      }

      return pdfConcatenado;
   }

   public static void salvarRelatorioEmDiretorio(byte[] relatorio, String nome) {

      final String nomeRelatorio;

      nomeRelatorio = nome + ToolDate.converterDataParaStringSemCaracteresEspeciais(Calendar.getInstance().getTime());

      if (relatorio != null) {
         ByteArrayInputStream byteArrayInputStream = null;
         File pdf = null;
         FileOutputStream saida = null;
         String caminhoDiretorio = "";
         try {
            byteArrayInputStream = new ByteArrayInputStream(relatorio);

            String caminhoDiretorioFatura = (String) parametroSistemaService
                    .obterValorDoParametroPorCodigo(Constantes.DIRETORIO_ARQUIVOS_FATURA);

            caminhoDiretorio = caminhoDiretorioFatura + "/"
                    // +
                    // Util.converterDataParaStringSemHoraSemBarra(dataEmissao,
                    // Constantes.FORMATO_DATA_US)
                    + nomeRelatorio + "/";
            pdf = ToolFile.getFile(caminhoDiretorio);
            if (!pdf.exists()) {
               pdf.mkdirs();
            }

            final String diretorioDocumento = caminhoDiretorio + nomeRelatorio + Constantes.EXTENSAO_ARQUIVO_PDF;

            pdf = ToolFile.getFile(diretorioDocumento);

            saida = new FileOutputStream(pdf);

           /* ProcessoDocumento processoDocumento = controladorProcessoDocumento
                    .criaEPopulaProcessoDocumento(processo, diretorioDocumento, nomeRelatorio);
            controladorProcessoDocumento.inserirProcessoDocumento(processoDocumento);*/

            int data;
            while ((data = byteArrayInputStream.read()) != -1) {
               char ch = (char) data;
               saida.write(ch);
            }
            saida.flush();
            saida.close();
         } catch (Exception e) {
            Log.erroStatic(RelatorioComponent.class,"InfraestruturaException", e.getMessage());
         }
      }

   }

   public static DownloadDTO gerarRelatorioXML(List<HashMap<String, Object>> list){
      byte[] bytesPlanilha = null;

      Workbook dadosPlanilha = xlsxReportGenerator.mapBIToXlsx(list);
      bytesPlanilha = xlsxReportGenerator.workBookToByte(dadosPlanilha);

      return new DownloadDTO("Planilha de Dados","xlsx", bytesPlanilha);
   }

   public static List<HashMap<String, Object>> convertListToListHashMap(List<?> list) throws InvocationTargetException,
           IllegalAccessException {

      List<HashMap<String, Object>> dadosPlanilha = new ArrayList<>();
      List<String> atributos = getAttributesNames(list.get(0));

      for (Object item : list) {
         HashMap<String, Object> map = new HashMap<>();

         for(String attribute : atributos) {
            map.put(attribute, getValue(attribute, item));
         }
         dadosPlanilha.add(map);
      }
      return dadosPlanilha;
   }

   private static Object getValue(String atributo, Object item) throws InvocationTargetException, IllegalAccessException {
      Method metodo  = getMethodByString(item, atributo);
      return metodo.invoke(item);
   }

   private static List<String> getAttributesNames(Object obj) {
      List<String> attributs = new ArrayList<>();
      Stream.of(obj.getClass().getDeclaredFields()).forEach(field -> attributs.add(field.getName()));
      return attributs;
   }

   public static Method getMethodByString(Object obj, String attribut) {
      Method method = null;
      try {
         Class<?> classe = Class.forName(obj.getClass().getName());
         method = classe.getDeclaredMethod("get" + StringUtils.capitalize(attribut));
      } catch (ClassNotFoundException | NoSuchMethodException | SecurityException e) {
         Log.erroStatic(RelatorioComponent.class,"ERRO AO TENTAR OBTER O MÉTODO: " + attribut, e.getMessage());
      }
      return method;
   }
}
