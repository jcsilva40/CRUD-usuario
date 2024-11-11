package com.stfn2.ggas.services.componentes.bi;

import com.stfn2.ggas.domain.BIQuery;
import com.stfn2.ggas.domain.BIStefanini;
import com.stfn2.ggas.tools.ToolFiles;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

@Component
public class JasperBIComponent {


    private static final String EXTENSAO_JASPER = ".jasper";

    private static final String EXTENSAO_JRXML = ".jrxml";


    private String getPathAndNameFile() {
        return "/mnt/aplicativos/jasper";
    }


    private String getPathRodape() {
        return "/mnt/aplicativos/jasper/padroes/RODAPE_BI.jasper";
    }

    private String getPathCabecalho() {
        return "/mnt/aplicativos/jasper/padroes/CABECALHO.jasper";
    }


    public byte[] buildJasper(List<HashMap<String, Object>> data, BIStefanini bi) {
        String filePathBase = getPathAndNameFile();

        ToolFiles.criarPastaSeNaoExistir(filePathBase);

        List<String> arquivos = gerarArquivos(filePathBase, bi.getQuery());

        String arquivoMain = filePathBase + "/" + bi.getQuery().getNomeField();

        byte[] report = runReport(bi, data, filePathBase, arquivoMain + EXTENSAO_JASPER);

        arquivos.forEach(arq -> {
            File jrxml = new File(arq + EXTENSAO_JRXML);
            jrxml.delete();
            File jasper = new File(arq + EXTENSAO_JASPER);
            jasper.delete();
        });

        return report;

    }


    public List<String> gerarArquivos(String filePathBase, BIQuery query) {
        List<String> referenciaArquivos = new ArrayList<>();
        try {
            BufferedWriter buffWritte;

            String filePath = filePathBase + "/" + query.getNomeField();

            referenciaArquivos.add(filePath);
            buffWritte = new BufferedWriter(new FileWriter(filePath + EXTENSAO_JRXML));

            buffWritte.append(query.getJasper().getConteudo());

            buffWritte.close();

            // Gera o jasper Design
            JasperDesign desenho = JRXmlLoader.load(filePath + EXTENSAO_JRXML);

            // confilar
            JasperCompileManager.compileReportToFile(desenho, filePath + EXTENSAO_JASPER);

            query.getChildren().forEach(e -> {
                List<String> referenciaArquivosFilhos = gerarArquivos(filePathBase, e);
                referenciaArquivos.addAll(referenciaArquivosFilhos);
            });

        } catch (IOException | JRException e) {
            e.printStackTrace();
            // throw new CpExceptionBaseRT(e.getMessage());
        }
        return referenciaArquivos;
    }

    public byte[] runReport(BIStefanini bi, List<HashMap<String, Object>> data, String caminhoSubJaspers, String filePath) {
        Collection<Map<String, Object>> dadosSubPadraoLista = new HashSet<>();

        Map<String, Object> dadosSubPadraoMap = new HashMap<String, Object>();
        dadosSubPadraoMap.put("F_TITLE", bi.getDescricao());
        dadosSubPadraoMap.put("F_DATA_GERACAO", new Date());

        dadosSubPadraoLista.add(dadosSubPadraoMap);

        Map<String, Object> parametros = new HashMap<String, Object>();

        String camingoCabecalho = getPathCabecalho();
        parametros.put("P_CABECALHO", camingoCabecalho);
        String camingoRodape = getPathRodape();
        parametros.put("P_RODAPE", camingoRodape);

        parametros.put("P_CAMINHO_SUB_JASPER", caminhoSubJaspers + "/");

        parametros.put("P_DADOS_GERACAO_BI", dadosSubPadraoLista);

        byte[] relatorio = gerarRelatorioPDF(data, parametros, filePath);
        return relatorio;
    }

    public static byte[] gerarRelatorioPDF(Collection<?> dados, Map<String, Object> parametros, String arquivoJasper) {
        try {
            InputStream inputStream = new FileInputStream(arquivoJasper);
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(inputStream);
            byte[] bytes = JasperRunManager.runReportToPdf(jasperReport, parametros,
                    new JRBeanCollectionDataSource(dados));
            inputStream.close();
            return bytes;
        } catch (JRException | IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


}
