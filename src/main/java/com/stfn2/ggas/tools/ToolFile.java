package com.stfn2.ggas.tools;

import com.stfn2.ggas.config.exceptions.types.ReportException;
import com.stfn2.ggas.core.utils.XmlNfeHandler;
import com.stfn2.ggas.services.componentes.faturamento.vo.NfeVO;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ToolFile {

   public static List<String> readCSV(MultipartFile file) throws ReportException {
      try {
         String todoArquivo = new String(file.getBytes());
         if (todoArquivo.isEmpty()) {
            throw new ReportException("Arquivo em branco!");
         }
         String[] array = todoArquivo.split("\n");
         List<String> linhas = Arrays.asList(array);
         return linhas;
      } catch (IOException e1) {
         String msgErro = ToolUtil.getMsgExcecao(e1);
         throw new ReportException(msgErro);
      }
   }

   public static File getFileTmp(MultipartFile file, String extensao) throws ReportException {
      File fileTmp = null;
      extensao = extensao.replace(".", "");
      try {
         String nameFile = "tmp-" + System.currentTimeMillis() + "." + extensao;
         fileTmp = new File(nameFile);
         System.out.print(fileTmp.getAbsolutePath());
         FileOutputStream in = new FileOutputStream(fileTmp);
         byte[] bFile = file.getBytes();
         in.write(bFile);
         in.close();
      } catch (IOException e1) {
         throw new ReportException("Erro em Leitura/Gravação do arquivo.");
      }
      return fileTmp;
   }

   public static List<String> lerArquivo(File file) throws IOException {
      BufferedReader buffRead = new BufferedReader(new FileReader(file));
      String linha = null;
      List<String> linhas = new ArrayList<String>();
      while (true) {
         linha = buffRead.readLine();
         if (linha != null) {
            linhas.add(linha);
         } else
            break;

      }
      buffRead.close();
      return linhas;
   }

   public static void criarPastaSeNaoExistir(String path) {
      if (!pastaExiste(path)) {
         File diretorio = new File(path);
         diretorio.mkdirs(); // mkdir() cria somente um diretório, mkdirs()
         // cria diretórios e subdiretórios.
      }
   }

   public static boolean pastaExiste(String path) {
      File diretorio = new File(path);
      return diretorio.exists();
   }

   public static void moveArquivoDePasta(File file, String destino) throws IOException {
      String nomeFile = file.getName();
      File desFile = new File(destino + "\\" + nomeFile);
      FileUtils.moveFile(file, desFile);
   }

   public static byte[] lerArquivo(String path) throws IOException {
      File arquivo = new File(path);

      // Verifica se o arquivo existe
      if (!arquivo.exists()) {
         throw new ReportException("Arquivo não existe!");
      }

      // Cria um FileInputStream para ler o arquivo
      FileInputStream inputStream = new FileInputStream(arquivo);

      // Cria um ByteArrayOutputStream para armazenar os bytes lidos
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

      int byteLido;
      // Lê os bytes do arquivo e os armazena no ByteArrayOutputStream
      while ((byteLido = inputStream.read()) != -1) {
         outputStream.write(byteLido);
      }

      // Obtém os bytes lidos
      byte[] bytesLidos = outputStream.toByteArray();

      // Fecha os streams
      inputStream.close();
      outputStream.close();

      return bytesLidos;
   }

   public static boolean removerArquivo(String path) throws IOException {
      File arquivo = new File(path);

      // Verifica se o arquivo existe
      if (!arquivo.exists()) {
         throw new ReportException("Arquivo não existe!");
      }
      return arquivo.delete();
   }

   public static void imprimirPDFRelatorio(String nomeArquivo, byte[] relatorio, HttpServletResponse response) {

      if (relatorio != null) {
         try {
            response.setContentType("application/pdf");
            response.setContentLength(relatorio.length);
            response.addHeader("Content-Disposition", "attachment; filename=" + nomeArquivo + ".pdf");
            ServletOutputStream servletOutputStream = response.getOutputStream();
            servletOutputStream.write(relatorio, 0, relatorio.length);
            servletOutputStream.flush();
            servletOutputStream.close();
         } catch (IOException e) {
            throw new ReportException(e.getMessage());
         }
      }
   }

   public static byte[] obterPdfFatura(String caminhoCompletoFatura) {
      File arquivoFatura = getFile(caminhoCompletoFatura);
      byte[] retorno;
      try {
         retorno = converterFileParaByte(arquivoFatura);
         return retorno;
      } catch (IOException e) {
         throw new ReportException("Arquivo não encontrado: " + arquivoFatura);
      }

   }

   public static void salvarArquivoByte(String caminhoComNome, byte[] data) throws IOException {
      try (FileOutputStream fos = new FileOutputStream(caminhoComNome)) {
         fos.write(data);
      }
   }

   public static void zipFiles(String[] srcFiles, String zipFile) throws IOException {
      try (FileOutputStream fos = new FileOutputStream(zipFile); ZipOutputStream zos = new ZipOutputStream(fos)) {

         for (String srcFile : srcFiles) {
            File fileToZip = new File(srcFile);
            try (FileInputStream fis = new FileInputStream(fileToZip)) {

               ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
               zos.putNextEntry(zipEntry);

               byte[] buffer = new byte[1024];
               int length;
               while ((length = fis.read(buffer)) >= 0) {
                  zos.write(buffer, 0, length);
               }
            }
         }
      }
   }

   public static File getFile(String caminhoArquivo) {
      return new File(caminhoArquivo); // NOSONAR
   }

   public static byte[] converterFileParaByte(File arquivo) throws IOException {

      InputStream stream = null;
      try {
         stream = new FileInputStream(arquivo);

         // obtém o tamanho do arquivo
         long length = arquivo.length();

         if (length > Integer.MAX_VALUE) {
            // arquivo é muito grande
         }

         // cria um array de byte para suportar os
         // dados
         byte[] bytes = new byte[(int) length];

         // lê os bytes
         int offset = 0;
         int numRead = 0;
         while (offset < bytes.length && (numRead = stream.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
         }

         // valida se todos os bytes foram lidos
         if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + arquivo.getName());
         }

         stream.close();
         return bytes;

      } finally {
         if (stream != null) {
            stream.close();
         }
      }
   }

   public static NfeVO lerXmlNfe(byte[] xml) throws ParserConfigurationException, SAXException, IOException {
      SAXParserFactory factory = SAXParserFactory.newInstance();
      SAXParser saxParser = factory.newSAXParser();
      XmlNfeHandler xmlNfeHandler = new XmlNfeHandler();

      InputStream is = new ByteArrayInputStream(xml);
      saxParser.parse(is, xmlNfeHandler);

      return xmlNfeHandler.getNfe();
   }


}
