package com.stfn2.ggas.services.componentes.faturamento.uteis;

import com.stfn2.ggas.config.exceptions.types.NegocioException;
import com.stfn2.ggas.constante.Constantes;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.core.utils.Log;
import com.stfn2.ggas.domain.DocumentoCobranca;
import com.stfn2.ggas.domain.Fatura;
import com.stfn2.ggas.domain.FaturaItem;
import com.stfn2.ggas.domain.FaturaItemTributacao;
import com.stfn2.ggas.domain.dtos.DocumentoCobrancaDTO;
import com.stfn2.ggas.services.DocumentoCobrancaService;
import com.stfn2.ggas.services.FaturaService;
import com.stfn2.ggas.services.HistoricoLegadoService;
import com.stfn2.ggas.services.ParametroSistemaService;
import com.stfn2.ggas.services.componentes.faturamento.vo.SubRelatorioHistoricoConsumoVO;
import com.stfn2.ggas.services.componentes.faturamento.vo.SubRelatorioTributosIncluidosVO;
import com.stfn2.ggas.tools.ToolNumber;
import com.stfn2.ggas.tools.ToolStr;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ToolsFaturamento {

   private static DocumentoCobrancaService documentoCobrancaService;
   private static HistoricoLegadoService historicoLegadoService;
   private static FaturaService faturaService;
   private static ParametroSistemaService parametroSistemaService;

   ToolsFaturamento(DocumentoCobrancaService documentoCobrancaService, HistoricoLegadoService historicoLegadoService,
                    FaturaService faturaService, ParametroSistemaService parametroSistemaService) {
      this.documentoCobrancaService = documentoCobrancaService;
      this.historicoLegadoService = historicoLegadoService;
      this.faturaService = faturaService;
      this.parametroSistemaService = parametroSistemaService;
   }

   public static void persitirCodigoBarras(Map<String, Object> dados, DocumentoCobranca doc) throws NegocioException {
      doc.setCodigoBarras(dados.get("codigoBarra").toString());
      doc.setLinhaDigitavel(dados.get("numeroCodigo").toString());

      try {
         documentoCobrancaService.createOrUpdate(MapperImpl.parseObject(doc, DocumentoCobrancaDTO.class));
         Log.updateStatic(ToolsFaturamento.class, doc);
      } catch (Exception e) {
         Log.erroStatic(ToolsFaturamento.class,"Não foi possível atualizar Documento de Cobrança", e.getMessage());
         throw new NegocioException("Não foi possível atualizar Documento de Cobrança");
      }
   }

   public static String calculoCodigoBarrasEndereco(String cep) {

      int tam;
      int soma;
      int numero;
      int dv;
      String tmp = cep;
      String aux2;
      String ud;
      String digito;

      tmp = tmp.replace("-", "");
      tmp = tmp.trim();

      numero = Integer.parseInt(tmp);
      tam = tmp.length();
      soma = 0;

      for (int j = 0; j < tam; j++) {
         aux2 = tmp.substring(j, j + 1);
         numero = Integer.parseInt(aux2);
         soma = soma + numero;
      }

      digito = String.valueOf(soma);
      tam = digito.length();
      ud = digito.substring(tam - 1, tam);
      numero = Integer.parseInt(ud);

      if ("0".equals(ud)) {
         dv = 0;
      } else {
         dv = 10 - numero;
      }

      return tmp + dv;
   }

   public static List<SubRelatorioHistoricoConsumoVO> historicoLegadoFactory(Fatura fatura,
                                                                             List<SubRelatorioHistoricoConsumoVO> historicosConsumo) {
      List<SubRelatorioHistoricoConsumoVO> historicos = new ArrayList<>();
      List<SubRelatorioHistoricoConsumoVO> historicosRetorno = new ArrayList<>();
      Long idPontoConsumo = fatura.getPontoConsumo().getId();

      for (SubRelatorioHistoricoConsumoVO hist : historicosConsumo) {

         historicosRetorno.add(hist);

      }

      try {
         historicos = historicoLegadoService.listarRelatorioHistoricoLegado(idPontoConsumo.toString());
         if (historicos.size() < 1) {
            return historicosRetorno;
         }
         int cont = 12 - historicosRetorno.size();
         int index = 11;
         for (int i = 0; i < cont; i++) {
            if (historicos.size() >= index + 1) {
               historicosRetorno.add(historicos.get(index));
            }
            index--;
         }

      } catch (Exception e) {
         Log.erroStatic(ToolsFaturamento.class,
                 "Erro ao tentar buscar relação de históricos legado do PC: " + idPontoConsumo,
                 e.getMessage());
      }
      /**
       * Ordenação dos históricos de consumo pelo número da Fatura
       * */
      historicosRetorno.sort((a, b) -> (Integer.parseInt(a.getNotaFiscalFatura()) > Integer.parseInt(b.getNotaFiscalFatura())) ? -1 : 1);

      return historicosRetorno;
   }

   public static BigDecimal getISSTermoFixo(Fatura fatura) {

      BigDecimal issTermoFixo = BigDecimal.ZERO;
      for (FaturaItem item : fatura.getListaFaturaItens()) {
         if (item.getRubrica().getDescricao().equals("TERMO FIXO MENSAL ISS")) {
            try {
               List<FaturaItemTributacao> itensTributacao = faturaService
                       .listarFaturaItemTributacao(item.getId()).stream().collect(Collectors.toList());
               for (FaturaItemTributacao itemTributacao : itensTributacao) {
                  if (itemTributacao.getTributo().getDescricao().equals("ISS")) {
                     issTermoFixo = itemTributacao.getValorTributo();
                  }
               }
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      }
      return issTermoFixo;
   }

   public static BigDecimal arredondamentoDescontoFatura(BigDecimal numero) {
      numero = numero.setScale(3, RoundingMode.HALF_UP);
      return numero.setScale(2, RoundingMode.HALF_UP);
   }

   public static void icmsItensAdicionais(Fatura fatura, List<SubRelatorioTributosIncluidosVO> colecaoTributos) {
      BigDecimal icmsItem = BigDecimal.ZERO;
      BigDecimal baseCalculoIcmsItem = BigDecimal.ZERO;

      BigDecimal icmsGas = BigDecimal.ZERO;
      BigDecimal baseCalculoIcmsGas = BigDecimal.ZERO;

      List<Long> listaItensAdicionaisICMS = parametroSistemaService
              .obterListaLongDeParametro(Constantes.C_RUBRICAS_COMPOEM_ICMS_FATURA);

      icmsGas = ToolNumber.converterCampoStringParaValorBigDecimal("ICMS_GAS", colecaoTributos.get(0).getValorICMS(),
                 Constantes.FORMATO_VALOR_NUMERO, Constantes.LOCALE_PADRAO);
      baseCalculoIcmsGas = ToolNumber.converterCampoStringParaValorBigDecimal("BASE_ICMS_GAS",
                 colecaoTributos.get(0).getValorBaseCalculoICMS(), Constantes.FORMATO_VALOR_NUMERO,
                 Constantes.LOCALE_PADRAO);

      for (FaturaItem item : fatura.getListaFaturaItens()) {
         if (listaItensAdicionaisICMS.contains(item.getRubrica().getId())) {
            try {
               List<FaturaItemTributacao> itensTributacao = faturaService
                       .listarFaturaItemTributacao(item.getId()).stream().collect(Collectors.toList());
               for (FaturaItemTributacao itemTributacao : itensTributacao) {
                  if (itemTributacao.getTributo().getDescricao().equals("ICMS")) {
                     icmsItem = icmsItem.add(itemTributacao.getValorTributo());
                     baseCalculoIcmsItem = baseCalculoIcmsItem.add(itemTributacao.getBaseCalculoTributo());

                     colecaoTributos.get(0)
                             .setValorBaseCalculoICMS("R$ " + ToolStr.converterCampoValorDecimalParaString(
                                     "Base Cálculo ICMS", (baseCalculoIcmsGas.add(baseCalculoIcmsItem)),
                                     Constantes.LOCALE_PADRAO, 2));

                     colecaoTributos.get(0)
                             .setValorICMS("R$ " + ToolStr.converterCampoValorDecimalParaString("Base Cálculo ICMS",
                                     (icmsGas.add(icmsItem)), Constantes.LOCALE_PADRAO, 2));
                  }
               }
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      }
   }
}
