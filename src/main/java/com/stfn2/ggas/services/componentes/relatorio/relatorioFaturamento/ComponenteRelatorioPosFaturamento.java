package com.stfn2.ggas.services.componentes.relatorio.relatorioFaturamento;

import com.stfn2.ggas.constante.Constantes;
import com.stfn2.ggas.domain.dtos.filter.LiberacaoCronogramaFaturamentoFilterDTO;
import com.stfn2.ggas.services.ParametroSistemaService;
import com.stfn2.ggas.services.componentes.relatorio.relatorioFaturamento.dto.RelatorioAnaliseFaturamentoDTO;
import com.stfn2.ggas.tools.ToolDate;
import com.stfn2.ggas.tools.ToolFile;
import com.stfn2.ggas.tools.ToolStr;
import com.stfn2.ggas.tools.ToolUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Component
public class ComponenteRelatorioPosFaturamento {
   private static final String ID_GRUPO_FATURAMENTO = "idGrupoFaturamento";
   private static final String ANO_MES_FATURAMENTO = "anoMesFaturamento";
   private static final String ANO_MES_FATURAMENTO_FORMULARIO = "anoMesFaturamentoFormulario";
   private static final String CICLO = "ciclo";

   private final ParametroSistemaService parametroSistemaService;

   RelatorioFaturamento relatorioFaturamento;

   ComponenteRelatorioPosFaturamento(RelatorioFaturamento relatorioFaturamento, ParametroSistemaService parametroSistemaService){
      this.relatorioFaturamento = relatorioFaturamento;
      this.parametroSistemaService = parametroSistemaService;
   }

   public byte[] processar(LiberacaoCronogramaFaturamentoFilterDTO filter) {

      byte[] relatorio = "".getBytes();
      try {
         Map<String, Object> filtro = new HashMap<String, Object>();
         filtro.put(ID_GRUPO_FATURAMENTO, filter.getFaturamentoGrupoId());
         filtro.put(ANO_MES_FATURAMENTO, filter.getAnoMes());
         filtro.put(ANO_MES_FATURAMENTO_FORMULARIO,
                 ToolStr.formatarAnoMesComMascara(filter.getAnoMes()));
         filtro.put(CICLO, filter.getCiclo());

         List<RelatorioAnaliseFaturamentoDTO> listaFaturamento = relatorioFaturamento
                 .getListaAnaliseFaturamento(filtro);

         if(Objects.isNull(listaFaturamento) || listaFaturamento.isEmpty()){
            return null;
         }

         Workbook workbook = relatorioFaturamento
                 .criarPlanilhaRelatorioAnaliseFaturamento(listaFaturamento);

         String caminhoDiretorioSimulacao =
                 parametroSistemaService.obterParametroPorCodigo(Constantes.PARAMETRO_DIRETORIO_ARQUIVOS_SIMULACAO_FATURAMENTO).getValor();

         String diretorioDocumento = caminhoDiretorioSimulacao + "simulacao_faturamento_grupo"
                 + filter.getFaturamentoGrupoId() + "_"
                 + ToolDate.converterDataParaStringSemCaracteresEspeciais(Calendar.getInstance().getTime()) + ".xlsx";

         FileOutputStream fileOut = new FileOutputStream(diretorioDocumento);
         workbook.write(fileOut);
         fileOut.close();
         workbook.close();

         relatorio = ToolFile.lerArquivo(diretorioDocumento);
         ToolFile.removerArquivo(diretorioDocumento);
      } catch (IOException e) {
         ToolUtil.getMsgExcecao(e);
      }
      return relatorio;
   }
}
