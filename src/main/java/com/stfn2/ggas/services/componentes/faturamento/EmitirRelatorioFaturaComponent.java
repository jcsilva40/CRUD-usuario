package com.stfn2.ggas.services.componentes.faturamento;

import com.stfn2.ggas.config.exceptions.types.GGASException;
import com.stfn2.ggas.config.exceptions.types.NegocioException;
import com.stfn2.ggas.constante.Constantes;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.core.models.DadosAuditoria;
import com.stfn2.ggas.core.utils.Log;
import com.stfn2.ggas.domain.*;
import com.stfn2.ggas.domain.dtos.FaturaImpressaoDTO;
import com.stfn2.ggas.domain.enumTypes.FormaCobrancaEnum;
import com.stfn2.ggas.domain.enumTypes.TipoConvenioBancarioEnum;
import com.stfn2.ggas.domain.enumTypes.TipoOperacaoEnum;
import com.stfn2.ggas.services.*;
import com.stfn2.ggas.services.componentes.calculotributos.CalculosTributosComponent;
import com.stfn2.ggas.services.componentes.fabricaboleto.FabricaConstrutorBoleto;
import com.stfn2.ggas.services.componentes.fabricaboleto.construtoresboletos.uteis.DigitoAutoConferencia;
import com.stfn2.ggas.services.componentes.faturamento.uteis.ConstantesFatura;
import com.stfn2.ggas.services.componentes.faturamento.uteis.ToolsFaturamento;
import com.stfn2.ggas.services.componentes.faturamento.vo.*;
import com.stfn2.ggas.services.componentes.relatorio.RelatorioComponent;
import com.stfn2.ggas.tools.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class EmitirRelatorioFaturaComponent {

   private final DocumentoFiscalService documentoFiscalService;
   private final DocumentoImpressaoLayoutService documentoImpressaoLayoutService;
   private final FaturaService faturaService;
   private final FaturaImpressaoService faturaImpressaoService;
   private final FaturaItemImpressaoService faturaItemImpressaoService;
   private final FaturaHistoricoService faturaHistoricoService;
   private final FaturaDadosTecnicosService faturaDadosTecnicosService;
   private final FaturaMensagemService faturaMensagemService;
   private final ConstanteSistemaService constanteSistemaService;
   private final ContratoPontoConsumoService contratoPontoConsumoService;
   private final CreditoDebitoNegociadoService creditoDebitoNegociadoService;
   private final ClienteDebitoAutomaticoService clienteDebitoAutomaticoService;
   private final ParametroSistemaService parametroSistemaService;
   private final PontoConsumoService pontoConsumoService;
   private final TarifaService tarifaService;
   private final TributoService tributoService;
   private final RubricaService rubricaService;
   private final DocumentoCobrancaService documentoCobrancaService;
   private final FaturamentoGrupoService faturamentoGrupoService;
   private final CampanhaDescontoVincularService campanhaDescontoVincularService;
   private final BoletoService boletoService;
   private final ContratoService contratoService;
   private final RecebimentoService recebimentoService;
   private final CalculosTributosComponent calculosTributosComponent;
   private final ArrecadadorService arrecadadorService;

   private static final String NOME_CLIENTE = "nomeCliente";
   private static final String ENDERECO_CLIENTE = "enderecoCliente";
   private static final String UF_CLIENTE = "ufCliente";
   private static final String CEP_CLIENTE = "cepCliente";
   private static final String DATA_VENCIMENTO = "dataVencimento";
   private static final String VALOR_PAGAR = "valorAPagar";
   private static final String SEPARADOR_ARQUIVO = " - ";
   private static final int NUMERO_DECIMAIS = 2;
   private static final boolean IS_ICMS_ISENTO_DESTACADO = Boolean.FALSE;
   private static final String TEXTO_SUBSTITUICAO_TRIBUTARIA_NOTA = "TEXTO_SUBSTITUICAO_TRIBUTARIA_NOTA";

   private Log log = new Log(this.getClass());


   EmitirRelatorioFaturaComponent(DocumentoFiscalService documentoFiscalService,
                                  DocumentoImpressaoLayoutService documentoImpressaoLayoutService,
                                  FaturaHistoricoService faturaHistoricoService,
                                  FaturaDadosTecnicosService faturaDadosTecnicosService,
                                  FaturaMensagemService faturaMensagemService,
                                  ConstanteSistemaService constanteSistemaService,
                                  ContratoPontoConsumoService contratoPontoConsumoService,
                                  ClienteDebitoAutomaticoService clienteDebitoAutomaticoService,
                                  FaturaService faturaService,
                                  ParametroSistemaService parametroSistemaService, TarifaService tarifaService,
                                  RubricaService rubricaService, PontoConsumoService pontoConsumoService,
                                  TributoService tributoService, FaturaImpressaoService faturaImpressaoService,
                                  FaturaItemImpressaoService faturaItemImpressaoService,
                                  CreditoDebitoNegociadoService creditoDebitoNegociadoService,
                                  DocumentoCobrancaService documentoCobrancaService,
                                  FaturamentoGrupoService faturamentoGrupoService, FaturamentoGrupoService faturamentoGrupoService1,
                                  CampanhaDescontoVincularService campanhaDescontoVincularService,
                                  BoletoService boletoService, ArrecadadorService arrecadadorService,
                                  ContratoService contratoService,
                                  RecebimentoService recebimentoService,
                                  CalculosTributosComponent calculosTributosComponent
   ) {
      this.documentoFiscalService = documentoFiscalService;
      this.documentoImpressaoLayoutService = documentoImpressaoLayoutService;
      this.creditoDebitoNegociadoService = creditoDebitoNegociadoService;
      this.clienteDebitoAutomaticoService = clienteDebitoAutomaticoService;
      this.faturaService = faturaService;
      this.faturaImpressaoService = faturaImpressaoService;
      this.faturaItemImpressaoService = faturaItemImpressaoService;
      this.faturaHistoricoService = faturaHistoricoService;
      this.faturaMensagemService = faturaMensagemService;
      this.faturaDadosTecnicosService = faturaDadosTecnicosService;
      this.constanteSistemaService = constanteSistemaService;
      this.contratoPontoConsumoService = contratoPontoConsumoService;
      this.parametroSistemaService = parametroSistemaService;
      this.pontoConsumoService = pontoConsumoService;
      this.tarifaService = tarifaService;
      this.tributoService = tributoService;
      this.rubricaService = rubricaService;
      this.documentoCobrancaService = documentoCobrancaService;
      this.faturamentoGrupoService = faturamentoGrupoService1;
      this.campanhaDescontoVincularService = campanhaDescontoVincularService;
      this.boletoService = boletoService;
      this.arrecadadorService = arrecadadorService;
      this.contratoService = contratoService;
      this.recebimentoService = recebimentoService;
      this.calculosTributosComponent = calculosTributosComponent;
   }

   public byte[] gerarImpressaoFaturaSegundaVia(Long faturaId) {

      Fatura fatura = faturaService.getById(faturaId);
      DocumentoFiscal documentoFiscal = documentoFiscalService.obterDocumentoFiscalporIdFatura(faturaId);

      byte[] retorno = null;

      String caminhoDiretorioFatura = "/mnt/aplicativos";
      String caminhoCompletoFatura = "";

      try {
         if (documentoFiscal != null
                 && Objects.equals(documentoFiscal.getTipoOperacao().getId(), TipoOperacaoEnum.ENTRADA.getId())) {
            caminhoCompletoFatura = gerarNomeArquivoChavePrimaria(fatura, caminhoCompletoFatura);
         } else {
            caminhoCompletoFatura = gerarNomeArquivoDetalhado(fatura, caminhoCompletoFatura, documentoFiscal);
         }

         caminhoCompletoFatura = caminhoDiretorioFatura + caminhoCompletoFatura;

         File arquivoFatura = ToolFile.getFile(caminhoCompletoFatura);

         if (arquivoFatura.exists()) {
            retorno = ToolFile.converterFileParaByte(arquivoFatura);
         } else {
            retorno = gerarEmissaoFatura(fatura, null, null, null, fatura.getDadosAuditoria(), null,
                    BigDecimal.ZERO, documentoFiscal, true);

            String nomeFatura = getNomeFatura(fatura, documentoFiscal);

            log.info("Salvando o relatorio no diretorio");
            if (documentoFiscal != null && documentoFiscal.getTipoOperacao()
                    .getId() == TipoOperacaoEnum.ENTRADA.getId()) {
               salvarEmissaoFaturaNoDiretorio(retorno, nomeFatura + "-E", fatura.getDataEmissao(), null);
            } else {
               salvarEmissaoFaturaNoDiretorio(retorno, nomeFatura, fatura.getDataEmissao(), null);
            }

            // Obtem o FaturaImpressao da Fatura
            FaturaImpressao faturaImpressao = faturaService.obterFaturaImpressaoPorFatura(fatura.getId());

            // Caso exista, atualiza o
            // faturaImpressao para já
            // impresso.
            if (faturaImpressao != null) {
               faturaImpressao.setDataImpressao(LocalDate.now());
               faturaImpressao.setDadosAuditoria(fatura.getDadosAuditoria());
               faturaImpressaoService.createOrUpdate(MapperImpl.parseObject(faturaImpressao, FaturaImpressaoDTO.class));
            }
         }

      } catch (IOException | GGASException e) {
         throw new RuntimeException(e);
      }

      return retorno;
   }

   public byte[] gerarEmissaoFatura(Fatura faturaParam, Collection<Fatura> faturasFilhas, Integer sequencial,
                                    Integer lote, DadosAuditoria dadosAuditoria, Boolean ultimaFatura, BigDecimal acumuladoFaturas,
                                    DocumentoFiscal documentoFiscal, boolean segundaVia) throws GGASException {

      /*
       * Monta os VOs para enviá-los ao report
       */
      Fatura fatura = faturaService.getById(faturaParam.getId());
      // CpCalculosTributos.hasDiferimentoTributario(fatura);
      // CpCalculosTributos.ajustarBaseTributos(fatura);

      Map<String, Object> parametros = montarImpressaoFatura(fatura, documentoFiscal, ultimaFatura, acumuladoFaturas);

      List<RelatorioFaturaVO> colecaoStub = new ArrayList<>();
      colecaoStub.add(new RelatorioFaturaVO());

      if (segundaVia) {
         parametros.put("valorDocumento", parametros.get(VALOR_PAGAR));
      }

      parametros.put("valorNotaFiscalFatura", parametros.get(VALOR_PAGAR));

      String arquivoRelatorio = "";
      Contrato contratoAtivo =
              contratoPontoConsumoService.obterContratoAtivoPontoConsumo(fatura.getPontoConsumo().getId())
                      .getContrato();
      ArrecadadorContratoConvenio arrecadadorConvenioBoleto = contratoAtivo.getArrecadadorContratoConvenio();
      ArrecadadorContratoConvenio arrecadadorConvenioDebitoAutomatico = contratoAtivo
              .getArrecadadorContratoConvenioDebitoAutomatico();


      if (contratoAtivo.getIndicadorDebitoAutomatico() && !segundaVia) {
         arquivoRelatorio = arrecadadorConvenioDebitoAutomatico.getArquivoLayoutFatura();
      } else if (arrecadadorConvenioBoleto != null) {
         arquivoRelatorio = arrecadadorConvenioBoleto.getArrecadadorCarteiraCobranca().getArquivoLayoutFatura();
      }
      if (arquivoRelatorio == null || arquivoRelatorio.isEmpty()) {
         arquivoRelatorio = documentoImpressaoLayoutService
                 .obterDocumentoImpressaoLayoutPorConstante(Constantes.RELATORIO_NOTA_FISCAL_FATURA);
      }

      if (arquivoRelatorio == null || arquivoRelatorio.isEmpty()) {
         throw new NegocioException(Constantes.ERRO_DOCUMENTO_LAYOUT_NAO_CADASTRADO, Boolean.FALSE);
      }

      byte[] reportNotaFiscalFaturaFrente;

      reportNotaFiscalFaturaFrente = RelatorioComponent.gerarRelatorioPDF(colecaoStub, parametros, arquivoRelatorio);

      List<byte[]> pdf = new ArrayList<>();
      pdf.add(reportNotaFiscalFaturaFrente);

      return RelatorioComponent.unificarRelatoriosPdf(pdf);
   }

   public Map<String, Object> montarImpressaoFatura(Fatura fatura, DocumentoFiscal documentoFiscal, Boolean ultimaFatura, BigDecimal acumuladoFaturas) {
      Map<String, Object> retorno = new HashMap<>();

      BigDecimal somatorioTotalFornecimento = BigDecimal.ZERO;

      Integer qtdQuantidadeCasasDescimaisApresentacaoConsumoApurado = null;
      try {
         qtdQuantidadeCasasDescimaisApresentacaoConsumoApurado = Integer
                 .parseInt(parametroSistemaService
                         .obterValorDoParametroPorCodigo(
                                 Constantes.PARAMETRO_QUANTIDADE_CASAS_DECIMAIS_APRESENTACAO_CONSUMO_APURADO)
                         .toString());
      } catch (NegocioException e) {
         throw new RuntimeException(e);
      }

      PontoConsumo pontoConsumo = fatura.getPontoConsumo();
      Contrato contrato = fatura.getContratoAtual();
      ContratoPontoConsumo contratosPontoCosumo = contrato.getContratoPontoConsumo();

      Tarifa tarifa = contratosPontoCosumo.getListaContratoPontoConsumoItemFaturamento().iterator().next().getTarifa();

      /**
       * Bloco 1 de dados da Fatura
       * */
      log.info("Etapa: Verificando Bloco 1 de dados da Fatura");
      if (fatura.getNumeroFatura() != null) {
         retorno.put("numeroNotaFiscal", fatura.getNumeroFatura().toString());
      } else {
         retorno.put("numeroNotaFiscal", fatura.getId().toString());
      }
      retorno.put("dtEmissao", ToolDate.converterDataParaStringSemHora(fatura.getDataEmissao(),
              Constantes.FORMATO_DATA_BR));
      retorno.put("dtVencimento", ToolDate.converterDataParaStringSemHora(fatura.getDataVencimento(), Constantes.FORMATO_DATA_BR));
      retorno.put("valorAPagar", ToolNumber.converterCampoCurrencyParaString(fatura.getValorTotal(),
              Constantes.LOCALE_PADRAO));
      retorno.put("segmento", fatura.getSegmento().getDescricao());
      retorno.put("tarifa", tarifa.getDescricao());
      retorno.put("numeroContratoFmt",
              fatura.getPontoConsumo().getCil() + "-" + fatura.getPontoConsumo().getComplementoCil());

      /**
       * Dados da NFE
       * */
      log.info("Etapa: Verificando dados da NFE");
      if (documentoFiscal != null && documentoFiscal.getChaveAcesso() != null) {
         retorno.put("chaveAcesso", documentoFiscal.getChaveAcesso());
         if (documentoFiscal.getDescricaoSerie() != null) {
            retorno.put("numeroSerie", documentoFiscal.getDescricaoSerie());
         }
         if (documentoFiscal.getNumeroProtocolo() != null) {
            retorno.put("numeroProtocolo", documentoFiscal.getNumeroProtocolo());
         }
      } else if (documentoFiscal != null && documentoFiscal.getArquivoXml() != null) {
         try {
            NfeVO nfe = ToolFile.lerXmlNfe(documentoFiscal.getArquivoXml());
            retorno.put("chaveAcesso", nfe.getChaveAcesso());
            retorno.put("numeroSerie", nfe.getSerie().toString());
            retorno.put("numeroProtocolo", nfe.getNumeroProtocoloEnvio());
         } catch (ParserConfigurationException | SAXException | IOException e) {
            Log.erroStatic(EmitirRelatorioFaturaComponent.class,"Erro ao tentar ler arquivo XML.", e.getCause().getMessage());
         }
      }

      /**
       * Verifica se o Cliente tem mais de 1 Ponto de Consumo ativo.
       * */
      log.info("Etapa: Verificando se o Cliente tem mais de 1 Ponto de Consumo" +
              " " +
              "ativo");
      int contratoCliente = 0;
      try {
         contratoCliente = (contratoService.obterListaContratoPorCliente(fatura.getCliente().getId())).size();
      } catch (NegocioException e) {
         log.erro("Erro ao tentar obter Lista de Contratos de Cliente!", e.getMessage());
      }

      if (contratoCliente > 1) {
         retorno.put("contratoComMaisPontoConsumo", true);
      } else {
         retorno.put("contratoComMaisPontoConsumo", false);
      }

      /**
       * Dados de endereçamento do cliente
       * */
      log.info("Etapa: Verificando dados de endereçamento do cliente");
      retorno.put("nomeCliente", fatura.getCliente().getNome());
      retorno.put("enderecoClienteReport", pontoConsumo.getLogradouro());
      retorno.put("bairroCliente", pontoConsumo.getBairro());
      retorno.put("cepCliente", pontoConsumo.getCep());
      retorno.put("municipioCliente", pontoConsumo.getLocalidade());
      retorno.put("ufCliente", pontoConsumo.getUf());
      retorno.put("cpjCnpj", fatura.getClienteCpfOrCNPJ());
      retorno.put("inscricaoEstadualCliente", fatura.getClienteInscricaoEstadual());

      /**
       * Dados de enderecamento do Ponto de Consumo
       * */
      log.info("Etapa: Verificando dados de enderecamento do Ponto de Consumo");
      retorno.put("nomePontoConsumo", pontoConsumo.getDescricao());
      retorno.put("logradouroPontoConsumo", pontoConsumo.getLogradouro());
      retorno.put("municipioPontoConsumo", pontoConsumo.getLocalidade());
      retorno.put("cepPontoConsumo", pontoConsumo.getCep());
      retorno.put("ufPontoConsumo", pontoConsumo.getUf());

      /**
       * SubRelatorio: Dados de produtos faturados
       * */
      log.info("Etapa: Verificando SubRelatorio -> Dados de produtos faturados");
      List<SubRelatorioProdutosVO> colecaoProdutos = criarColecaoVOProduto(fatura, somatorioTotalFornecimento,
              qtdQuantidadeCasasDescimaisApresentacaoConsumoApurado);

      if (colecaoProdutos != null && !colecaoProdutos.isEmpty()) {
         String descricaoProduto = "";
         String descricaoTarifa = "";
         BigDecimal somatorioTotal = BigDecimal.ZERO;
         BigDecimal somatorioValorUnitario = BigDecimal.ZERO;
         BigDecimal desconto = BigDecimal.ZERO;
         try {
            for (SubRelatorioProdutosVO subRelatorioProdutosVO : colecaoProdutos) {
               if (subRelatorioProdutosVO.getValorTotal() != null
                       && !"".equals(subRelatorioProdutosVO.getValorTotal())) {
                  BigDecimal valor = ToolNumber.converterCampoStringParaValorBigDecimal("Valor Somatório",
                          subRelatorioProdutosVO.getValorTotal().trim().replaceAll("\\s+", ""), Constantes.FORMATO_VALOR_BR,
                          Constantes.LOCALE_PADRAO);
                  somatorioTotal = somatorioTotal.add(valor);

               }

               if (subRelatorioProdutosVO.getValorUnitario() != null
                       && !"".equals(subRelatorioProdutosVO.getValorUnitario())) {
                  BigDecimal valor = ToolNumber.converterCampoStringParaValorBigDecimal("Valor Unitario",
                          subRelatorioProdutosVO.getValorUnitario().trim(), Constantes.FORMATO_VALOR_BR,
                          Constantes.LOCALE_PADRAO);
                  somatorioValorUnitario = somatorioValorUnitario.add(valor);

               }

               if (subRelatorioProdutosVO.getDesconto() != null) {
                  BigDecimal valor =
                          ToolNumber.converterCampoStringParaValorBigDecimal(ConstantesFatura.DESCONTO_FATURA,
                                  subRelatorioProdutosVO.getDesconto().trim(), Constantes.FORMATO_VALOR_BR,
                                  Constantes.LOCALE_PADRAO);
                  desconto = desconto.add(valor);
               }

               if (subRelatorioProdutosVO.getDescricaoTarifa() != null
                       && !subRelatorioProdutosVO.getDescricaoTarifa().isEmpty()) {
                  descricaoTarifa = subRelatorioProdutosVO.getDescricaoTarifa();
               }

               descricaoProduto = descricaoProduto.concat(" ").concat(subRelatorioProdutosVO.getDescricao());
            }
         } catch (Exception e) {
            throw new RuntimeException(Constantes.ERRO_DADOS_INVALIDOS, e);
         }
         if (descricaoProduto != null) {
            retorno.put("descricaoProduto", descricaoProduto);
         }
         FaturaItem faturaItem = fatura.getListaFaturaItens().iterator().next();

         if (faturaItem.getTarifa() != null) {
            descricaoTarifa = faturaItem.getTarifa().getDescricao();
         }

         JRBeanCollectionDataSource listaProduto = new JRBeanCollectionDataSource(colecaoProdutos);
         retorno.put("tarifa", descricaoTarifa);
         retorno.put("colecaoProduto", listaProduto);
         if (desconto.compareTo(BigDecimal.ZERO) > 0) {
            retorno.put("totalProdutos",
                    ToolNumber.converterCampoCurrencyParaString(somatorioTotal, Constantes.LOCALE_PADRAO));

            String valorDesconto = "0,00";
            if (!(BigDecimal.ZERO.compareTo(desconto) == 0)) {
               valorDesconto = ToolNumber.converterCampoCurrencyParaString(desconto, Constantes.LOCALE_PADRAO);
            }

            retorno.put("totalDesconto", valorDesconto);
         }
         retorno.put("totalAPagar",
                 ToolNumber.converterCampoCurrencyParaString(somatorioTotal.subtract(desconto), Constantes.LOCALE_PADRAO));
         retorno.put("totalUnitario",
                 ToolNumber.converterCampoCurrencyParaString(somatorioValorUnitario, Constantes.LOCALE_PADRAO));

         if (ultimaFatura != null && ultimaFatura) {
            somatorioTotal = somatorioTotal.add(acumuladoFaturas);
         }
         //Stefanini: Ajuste informação valor a pagar Débito Automático
         retorno.put("totalAPagarCobranca",
                 ToolNumber.converterCampoCurrencyParaString(fatura.getValorTotal(), Constantes.LOCALE_PADRAO));
      }

      /**
       * SubRelatorio: Mensagens Grupo 1 (Campanha de Desconto e mensagem Ponto de Consumo)
       * */
      log.info("Etapa: Verificando SubRelatorio -> Mensagens Grupo 1 (Campanha de Desconto e mensagem Ponto de Consumo)");
      retorno.put("mensagemCampanha", this.adicionarMensagensPontoConsumo(fatura));

      /**
       * SubRelatorio: Dados de Mensagens Grupo 2 (ICMS substitutivo, ISS e Diferimento)
       * */
      log.info("Etapa: Verificando SubRelatorio -> Mensagens Grupo 2 (ICMS substitutivo, ISS e Diferimento)");
      List<SubRelatorioMensagemVO> colecaoMensagens = new ArrayList<>();
      colecaoMensagens = this.criarColecaoVOMensagem(fatura);
      if (!colecaoMensagens.isEmpty()) {
         JRBeanCollectionDataSource listaMensagens = new JRBeanCollectionDataSource(colecaoMensagens);
         retorno.put("colecaoMensagem", listaMensagens);
      }

      /**
       * SubRelatorio: Mensagens Grupo 3 (Dados de Faturas Vencidas)
       * */
      log.info("Etapa: Verificando SubRelatorio -> Mensagens Grupo 3 (Dados de Faturas Vencidas)");
      List<SubRelatorioFaturasVencidasVO> colecaoFaturasVencidas = criarColecaoVOFaturasVencidas(fatura);
      List<SubRelatorioFaturasVencidasVO> colecaoFaturasVencidasNovo = new CopyOnWriteArrayList<>();
      if (colecaoFaturasVencidas != null && !colecaoFaturasVencidas.isEmpty()) {
         colecaoFaturasVencidasNovo.add(colecaoFaturasVencidas.get(0));
         JRBeanCollectionDataSource listaFaturaVencida = new JRBeanCollectionDataSource(colecaoFaturasVencidasNovo);
         retorno.put("colecaoFaturaVencida", listaFaturaVencida);
      }

      /**
       * SubRelatorio: Dados Técnicos de Medição da Fatura
       * */
      log.info("Etapa: Verificando SubRelatorio -> Dados Técnicos de Medição da Fatura");
      retorno.put("periodoConsumo", fatura.getPeriodoCiclo());
      List<SubRelatorioFornecimentoVO> colecaoDadosTecnicos =
              faturaDadosTecnicosService.obterColecaoDadosTecnicos(fatura);
      if (colecaoDadosTecnicos != null && !colecaoDadosTecnicos.isEmpty()) {
         JRBeanCollectionDataSource listaDadosTecnicos = new JRBeanCollectionDataSource(colecaoDadosTecnicos);
         retorno.put("colecaoFornecimento", listaDadosTecnicos);
      }

      /**
       * SubRelatorio: Dados do Histórico de Faturas do Cliente
       * */
      log.info("Etapa: Verificando SubRelatorio -> Dados do Histórico de Faturas do Cliente");
      List<SubRelatorioHistoricoConsumoVO> colecaoHistoricosConsumo = faturaHistoricoService.obterColecaoHistoricoFatura(fatura);
      colecaoHistoricosConsumo = ToolsFaturamento.historicoLegadoFactory(fatura, colecaoHistoricosConsumo);
      if (colecaoHistoricosConsumo != null && !colecaoHistoricosConsumo.isEmpty()) {
         JRBeanCollectionDataSource listaHistoricoConsumo = new JRBeanCollectionDataSource(colecaoHistoricosConsumo);
         retorno.put("colecaoHistoricoConsumo", listaHistoricoConsumo);
      }

      /**
       * SubRelatorio: Dados de Tributos da Fatura
       * */
      log.info("Etapa: Verificando SubRelatorio -> Dados de Tributos da Fatura");
      List<SubRelatorioTributosIncluidosVO> colecaoTributos = null;
      colecaoTributos = criarColecaoVOTributo(fatura);
      if (!colecaoTributos.isEmpty()) {
         JRBeanCollectionDataSource listaTributo = new JRBeanCollectionDataSource(colecaoTributos);
         retorno.put("colecaoTributo", listaTributo);
      }

      /**
       * SubRelatorio: Dados do Boleto da Fatura
       * */
      log.info("Etapa: Verificando SubRelatorio -> Dados do Boleto da Fatura");
      DocumentoCobranca documentoCobranca = documentoCobrancaService.obtemDadosParaConsultarFaturas(fatura);
      retorno.putAll(this.verificarDadosBoleto(fatura, documentoCobranca));

      log.info("Finalizando Mapa de Dados Fatura");
      return retorno;
   }

   private List<SubRelatorioTributosIncluidosVO> criarColecaoVOTributo(Fatura fatura) {

      Boolean isProdesin = Boolean.FALSE;

      List<PontoConsumoTributoAliquota> listaPontoConsumoTributoAliquota =
              pontoConsumoService.obterListaPontoConsumoTributoAliquota(fatura.getPontoConsumo().getId());

      if (listaPontoConsumoTributoAliquota != null
              && !listaPontoConsumoTributoAliquota.isEmpty()) {
         for (PontoConsumoTributoAliquota pontoConsumoTributoAliquota : listaPontoConsumoTributoAliquota) {
            if (pontoConsumoTributoAliquota.getIndicadorCreditoIcms() != null
                    && pontoConsumoTributoAliquota.getIndicadorCreditoIcms()) {
               isProdesin = Boolean.TRUE;
               break;
            }
         }
      }

      List<SubRelatorioTributosIncluidosVO> retorno = new ArrayList<>();
      List<FaturaTributacao> colecaoFaturaTributacao = faturaService
              .consultarFaturaTributacaoPorFatura(fatura.getId());

      SubRelatorioTributosIncluidosVO subRelTribVO = null;
      /**
       * GGAS - Ajuste ICMS2023
       * */
      SubRelatorioTributosIncluidosVO subRelTrib2023 = new SubRelatorioTributosIncluidosVO();
      try {

         subRelTribVO = isIcmsIsentoDestacado(fatura, parametroSistemaService, subRelTribVO);

         for (FaturaTributacao faturaTributacao : colecaoFaturaTributacao) {

            if (faturaTributacao != null && faturaTributacao.getTributo() != null) {
               Tributo tributo = faturaTributacao.getTributo();

               String paramICMS = constanteSistemaService
                       .obterValorConstanteSistemaPorCodigo(Constantes.C_ICMS);
               String paramPIS = constanteSistemaService.obterValorConstanteSistemaPorCodigo(Constantes.C_PIS);
               String paramCOFINS = constanteSistemaService
                       .obterValorConstanteSistemaPorCodigo(Constantes.C_COFINS);
               String paramIPI = constanteSistemaService.obterValorConstanteSistemaPorCodigo(Constantes.C_IPI);

               if (!StringUtils.isEmpty(paramICMS) && tributo.getId() == Long.parseLong(paramICMS)
                       && !isProdesin) {

                  if (subRelTribVO == null) {
                     subRelTribVO = new SubRelatorioTributosIncluidosVO();
                  }

                  /*
                   * valorBaseCalculoICMS
                   */
                  if (faturaTributacao.getBaseCalculoTributo() != null) {
                     subRelTribVO.setValorBaseCalculoICMS(ToolNumber.converterCampoValorDecimalParaString(
                             "Base Cálculo ICMS", faturaTributacao.getBaseCalculoTributo(),
                             Constantes.LOCALE_PADRAO, NUMERO_DECIMAIS));
                  }

                  /*
                   * valorICMS
                   */
                  if (faturaTributacao.getValorTributo() != null) {
                     subRelTribVO.setValorICMS(ToolNumber.converterCampoValorDecimalParaString("VALOR_ICMS1",
                             faturaTributacao.getValorTributo(), Constantes.LOCALE_PADRAO, NUMERO_DECIMAIS));
                  }

                  /*
                   * baseCalculoFiscoICMS
                   */
                  if (faturaTributacao.getBaseCalculoTributoSubstitutivo() != null) {
                     subRelTribVO.setBaseCalculoFiscoICMS(ToolNumber.converterCampoValorDecimalParaString(
                             "Base Cálculo ICMS Substituição", faturaTributacao.getBaseCalculoTributoSubstitutivo(),
                             Constantes.LOCALE_PADRAO, NUMERO_DECIMAIS));
                  }

                  /*
                   * valorRetidoFiscoICMS
                   */
                  if (faturaTributacao.getValorTributoSubstitutivo() != null) {
                     subRelTribVO.setValorRetidoFiscoICMS(ToolNumber.converterCampoValorDecimalParaString(
                             "Valor ICMS Substituição", faturaTributacao.getValorTributoSubstitutivo(),
                             Constantes.LOCALE_PADRAO, NUMERO_DECIMAIS));
                  }

                  /*
                   * valorAliquotaICMS
                   */
                  if (faturaTributacao.getPercentualTributo() != null) {
                     subRelTribVO.setValorAliquotaICMS(ToolNumber.converterCampoValorDecimalParaString("Alíquota ICMS",
                             faturaTributacao.getPercentualTributo(), Constantes.LOCALE_PADRAO,
                             NUMERO_DECIMAIS));
                  }

                  /*
                   * informacoesComplementares
                   */
                  ParametroSistema parametro = parametroSistemaService
                          .obterParametroPorCodigo(TEXTO_SUBSTITUICAO_TRIBUTARIA_NOTA);
                  if (parametro != null) {
                     subRelTribVO.setInformacoesComplementares(parametro.getValor());
                  }

               } else if ((!StringUtils.isEmpty(paramPIS) && tributo != null
                       && tributo.getId() == Long.parseLong(paramPIS))
                       || (!StringUtils.isEmpty(paramCOFINS) && tributo != null
                       && tributo.getId() == Long.parseLong(paramCOFINS))) {

                  if (subRelTribVO == null) {
                     subRelTribVO = new SubRelatorioTributosIncluidosVO();
                  }

                  String nmTributo = "";
                  nmTributo = tributo.getDescricao();

                  /*
                   * valorBaseCalculoPIS
                   */
                  if (faturaTributacao.getBaseCalculoTributo() != null) {
                     BigDecimal baseCalculo = faturaTributacao.getBaseCalculoTributo();
                     subRelTribVO.setValorBaseCalculoPIS(
                             ToolNumber.converterCampoValorDecimalParaString("Base Cálculo " + nmTributo, baseCalculo,
                                     Constantes.LOCALE_PADRAO, NUMERO_DECIMAIS));
                  }

                  /*
                   * valorPIS
                   */
                  if (faturaTributacao.getValorTributo() != null) {
                     BigDecimal valorImposto = faturaTributacao.getValorTributo();
                     if (subRelTribVO.getValorPIS() != null) {
                        BigDecimal valorImpostoPIS = ToolNumber.converterCampoStringParaValorBigDecimal(
                                "Valor Imposto PIS", subRelTribVO.getValorPIS(), Constantes.FORMATO_VALOR_BR,
                                Constantes.LOCALE_PADRAO);
                        valorImposto = valorImposto.add(valorImpostoPIS);
                     }
                     subRelTribVO.setValorPIS(ToolNumber.converterCampoValorDecimalParaString("Valor " + nmTributo,
                             valorImposto, Constantes.LOCALE_PADRAO, NUMERO_DECIMAIS));
                  }

                  /*
                   * valorAliquotaPIS
                   */
                  if (faturaTributacao.getPercentualTributo() != null) {
                     if (subRelTribVO.getValorAliquotaPIS() != null) {
                        subRelTribVO.setValorAliquotaPIS(ToolNumber.converterCampoValorDecimalParaString(
                                "ALIQUOTA" + nmTributo, faturaTributacao.getPercentualTributo(),
                                Constantes.LOCALE_PADRAO, 4));
                     } else {
                        subRelTribVO.setValorAliquotaPIS(ToolNumber.converterCampoValorDecimalParaString(
                                "ALIQUOTA" + nmTributo, faturaTributacao.getPercentualTributo(),
                                Constantes.LOCALE_PADRAO, 4));
                     }
                  }

                  /*
                   * informacoesComplementares
                   */

                  if (!IS_ICMS_ISENTO_DESTACADO) {
                     ParametroSistema parametro = parametroSistemaService
                             .obterParametroPorCodigo(TEXTO_SUBSTITUICAO_TRIBUTARIA_NOTA);
                     if (parametro != null) {
                        subRelTribVO.setInformacoesComplementares(parametro.getDescricao());
                     }
                  }

               } else if (!StringUtils.isEmpty(paramIPI) && tributo != null
                       && tributo.getId() == Long.parseLong(paramIPI)) {

                  if (subRelTribVO == null) {
                     subRelTribVO = new SubRelatorioTributosIncluidosVO();
                  }

                  String nmTributo = "";
                  nmTributo = tributo.getDescricao();

                  /*
                   * valorBaseCalculoIPI
                   */
                  if (faturaTributacao.getBaseCalculoTributo() != null) {
                     BigDecimal baseCalculo = faturaTributacao.getBaseCalculoTributo();
                     subRelTribVO.setValorBaseCalculoIPI(
                             ToolNumber.converterCampoValorDecimalParaString("Base Cálculo " + nmTributo, baseCalculo,
                                     Constantes.LOCALE_PADRAO, 2));
                  }

                  /*
                   * valorIPI
                   */
                  if (faturaTributacao.getValorTributo() != null) {
                     BigDecimal valorImposto = faturaTributacao.getValorTributo();
                     if (subRelTribVO.getValorIPI() != null) {
                        BigDecimal valorImpostoIPI = ToolNumber.converterCampoStringParaValorBigDecimal(
                                "Valor Imposto PIS", subRelTribVO.getValorIPI(), Constantes.FORMATO_VALOR_BR,
                                Constantes.LOCALE_PADRAO);
                        valorImposto = valorImposto.add(valorImpostoIPI);
                     }
                     subRelTribVO.setValorIPI(ToolNumber.converterCampoValorDecimalParaString("Valor " + nmTributo,
                             valorImposto, Constantes.LOCALE_PADRAO, 2));
                  }

                  /*
                   * valorAliquotaIPI
                   */
                  if (faturaTributacao.getPercentualTributo() != null) {
                     if (subRelTribVO.getValorAliquotaPIS() != null) {
                        BigDecimal valorIPI = ToolNumber.converterCampoStringParaValorBigDecimal("VALOR_PIS",
                                subRelTribVO.getValorAliquotaIPI(), Constantes.FORMATO_VALOR_BR,
                                Constantes.LOCALE_PADRAO).add(faturaTributacao.getPercentualTributo());
                        subRelTribVO.setValorAliquotaIPI(
                                ToolNumber.converterCampoValorDecimalParaString("ALIQUOTA" + nmTributo, valorIPI,
                                        Constantes.LOCALE_PADRAO, 4));
                     } else {
                        subRelTribVO.setValorAliquotaIPI(ToolNumber.converterCampoValorDecimalParaString(
                                "ALIQUOTA" + nmTributo, faturaTributacao.getPercentualTributo(),
                                Constantes.LOCALE_PADRAO, 4));
                     }
                  }

                  /*
                   * informacoesComplementares
                   */

                  if (!IS_ICMS_ISENTO_DESTACADO) {
                     ParametroSistema parametro = parametroSistemaService
                             .obterParametroPorCodigo(TEXTO_SUBSTITUICAO_TRIBUTARIA_NOTA);
                     if (parametro != null) {
                        subRelTribVO.setInformacoesComplementares(parametro.getDescricao());
                     }
                  }

               }

            }
            /**
             * GGAS - Ajuste ICMS2023
             * */
            if (faturaTributacao.getTributo().getId() == 8L) {

               subRelTrib2023
                       .setValorBaseCalculoICMS(ToolNumber.converterCampoValorDecimalParaString("Base Cálculo ICMS2023",
                               faturaTributacao.getBaseCalculoTributo(), Constantes.LOCALE_PADRAO, NUMERO_DECIMAIS));

               subRelTrib2023.setValorICMS(ToolNumber.converterCampoValorDecimalParaString("VALOR_ICMS",
                       faturaTributacao.getValorTributo(), Constantes.LOCALE_PADRAO, NUMERO_DECIMAIS));

               subRelTrib2023.setValorAliquotaICMS(ToolNumber.converterCampoValorDecimalParaString("Alíquota ICMS",
                       faturaTributacao.getPercentualTributo(), Constantes.LOCALE_PADRAO, NUMERO_DECIMAIS));

               final BigDecimal valorCem = BigDecimal.valueOf(100);
               BigDecimal valorICMS2023 = ToolNumber
                       .converterCampoStringParaValorBigDecimal("VALOR_ICMS1", subRelTrib2023.getValorAliquotaICMS(),
                               Constantes.FORMATO_VALOR_BR, Constantes.LOCALE_PADRAO)
                       .multiply(valorCem);

               subRelTrib2023.setValorAliquotaICMS(ToolNumber.converterCampoValorDecimalParaString("Alíquota ICMS",
                       valorICMS2023, Constantes.LOCALE_PADRAO, 2).concat("%"));
               retorno.add(subRelTrib2023);
            }

         }

         if (subRelTribVO != null) {
            final BigDecimal valorCem = BigDecimal.valueOf(100);

            if (subRelTribVO.getValorAliquotaICMS() != null
                    && !StringUtils.isEmpty(subRelTribVO.getValorAliquotaICMS())) {
               BigDecimal valorICMS = ToolNumber.converterCampoStringParaValorBigDecimal("VALOR_ICMS1",
                               subRelTribVO.getValorAliquotaICMS(), Constantes.FORMATO_VALOR_BR, Constantes.LOCALE_PADRAO)
                       .multiply(valorCem);
               subRelTribVO.setValorAliquotaICMS(ToolNumber.converterCampoValorDecimalParaString("Alíquota ICMS",
                       valorICMS, Constantes.LOCALE_PADRAO, 2).concat("%"));
            }

            if (subRelTribVO.getValorAliquotaPIS() != null
                    && !StringUtils.isEmpty(subRelTribVO.getValorAliquotaPIS())) {
               BigDecimal valorPIS = ToolNumber.converterCampoStringParaValorBigDecimal("VALOR_PIS",
                               subRelTribVO.getValorAliquotaPIS(), Constantes.FORMATO_VALOR_BR, Constantes.LOCALE_PADRAO)
                       .multiply(valorCem);
               subRelTribVO.setValorAliquotaPIS(ToolNumber.converterCampoValorDecimalParaString("Alíquota PIS", valorPIS,
                       Constantes.LOCALE_PADRAO, 2).concat("%"));
            }

            if (subRelTribVO.getValorBaseCalculoICMS() == null) {
               subRelTribVO.setValorBaseCalculoICMS(ToolNumber.converterCampoValorDecimalParaString("Base Cálculo ICMS",
                       BigDecimal.ZERO, Constantes.LOCALE_PADRAO, NUMERO_DECIMAIS));
            }
            if (subRelTribVO.getValorICMS() == null) {
               subRelTribVO.setValorICMS(ToolNumber.converterCampoValorDecimalParaString("VALOR_ICMS1", BigDecimal.ZERO,
                       Constantes.LOCALE_PADRAO, NUMERO_DECIMAIS));
            }
            if (subRelTribVO.getValorAliquotaICMS() == null) {
               subRelTribVO.setValorAliquotaICMS(ToolNumber.converterCampoValorDecimalParaString("Alíquota ICMS",
                       BigDecimal.ZERO, Constantes.LOCALE_PADRAO, NUMERO_DECIMAIS));
            }

            retorno.add(subRelTribVO);
         }
      } catch (NegocioException e) {
         log.erro(Constantes.ERRO_DADOS_INVALIDOS, e.getMessage());
      }

      ToolsFaturamento.icmsItensAdicionais(fatura, retorno);
      return retorno;
   }

   private SubRelatorioTributosIncluidosVO isIcmsIsentoDestacado(Fatura fatura,
                                                                 ParametroSistemaService parametroSistemaService,
                                                                 SubRelatorioTributosIncluidosVO subRelatorioTribVO)
           throws NegocioException {

      SubRelatorioTributosIncluidosVO subRelTribVO = subRelatorioTribVO;
      if (IS_ICMS_ISENTO_DESTACADO) {
         subRelTribVO = new SubRelatorioTributosIncluidosVO();

         ParametroSistema param = parametroSistemaService
                 .obterParametroPorCodigo(Constantes.PARAMETRO_TEXTO_SUBSTITUICAO_TRIBUTARIA_NOTA_DESTACADO);
         if (param != null) {
            subRelTribVO.setInformacoesComplementares(param.getValor());
         }
      }
      return subRelTribVO;
   }

   public void atualizarCodigoBarras(Fatura fatura) {

   }

   private List<SubRelatorioMensagemVO> criarColecaoVOMensagem(Fatura fatura) {

      List<SubRelatorioMensagemVO> retorno = new ArrayList<>();

      // Verificar se a fatura emitida possui como substituidora a petrobras
      TarifaVigencia tarifaVigencia = null;
      LocalDate dataTarifaVigente = null;
      if (fatura != null && fatura.getConsumoHistorico() != null) {

         dataTarifaVigente = fatura.getConsumoHistorico().getHistoricoAtual().getDataLeituraFaturada();

         // Verfica se existem itens sobre os quais deve ser calculado ICMS
         // Substituto, para exibição correta da mensagem
         TributoAliquota icmsSubs = null;
         BigDecimal ICMS = BigDecimal.ONE;
         try {
            icmsSubs = tributoService.obterAliquotaVigente(
                    constanteSistemaService.obterConstantePorCodigo("C_ICMS_SUBS").getValorLong());

            ICMS = tributoService
                    .obterAliquotaVigente(constanteSistemaService.obterConstanteIcms().getValorLong())
                    .getValorAliquota();
         } catch (NegocioException e) {
            throw new RuntimeException(e);
         }
         BigDecimal valorBaseIcmsSubstituto = BigDecimal.ZERO;
         BigDecimal valorIcmsSubstituto = BigDecimal.ZERO;
         BigDecimal ISS = BigDecimal.ZERO;

         ConstanteSistema constanteRubrica = constanteSistemaService
                 .obterConstantePorCodigo(Constantes.C_RUBRICA_CUSTO_FIXO_MENSAL);

         Rubrica rubrica = rubricaService.getById(Long.parseLong(constanteRubrica.getValor()));

         for (FaturaItem item : fatura.getListaFaturaItens()) {
            if (item.getTarifa() == null && !item.getRubrica().equals(rubrica)) {

               List<FaturaItemTributacao> itensTributacao = faturaService.listarFaturaItemTributacao(item.getId());

               for (FaturaItemTributacao itemTributacao : itensTributacao) {
                  if (itemTributacao.getTributo().getDescricao().equals("ICMS")) {
                     if (itemTributacao.getBaseCalculoTributoSubstitutivo() != null) {
                        valorBaseIcmsSubstituto = (valorBaseIcmsSubstituto
                                .add(itemTributacao.getBaseCalculoTributoSubstitutivo()));
                     }
                     if (itemTributacao.getValorTributoSubstitutivo() != null) {
                        valorIcmsSubstituto = (valorIcmsSubstituto.add(itemTributacao.getValorTributoSubstitutivo()));
                     }
                  }
               }
            }
         }

         for (FaturaItem item : fatura.getListaFaturaItens()) {
            if (item.getTarifa() != null) {
               tarifaVigencia = tarifaService.obterTarifaVigencia(item.getTarifa().getId(),
                       dataTarifaVigente);

               if (tarifaVigencia != null && tarifaVigencia.getMsgICMSSubs() != null
                       && tarifaVigencia.getMsgICMSSubs().equals(Boolean.TRUE)) {
                  SubRelatorioMensagemVO subRelMsgVO = new SubRelatorioMensagemVO();
                  BigDecimal valorBaseCalculoMetroCubico = BigDecimal.ZERO;
                  BigDecimal valorIcmsMetroCubico = BigDecimal.ZERO;
                  BigDecimal volumeFaturado = BigDecimal.ZERO;
                  if (fatura.getConsumoHistorico().getConsumoApurado() != null) {
                     FaturaItemImpressao fatItemImpressaoTemp = faturaService
                             .consultarFaturaItemImpressaoPorFaturaItem(item.getId()).iterator().next();
                     if (fatItemImpressaoTemp.getConsumo() != null) {
                        volumeFaturado = fatItemImpressaoTemp.getValorTotal();
                     }
                  }

                  BigDecimal valorIcmsSubstitutoTotal = BigDecimal.ZERO;

                  // Correção para exibição correta da mensagem sobre de
                  // ICMS Substituto quando ocorre quebra de vigencia
                  // tarifária.
                  List<FaturaTributacao> colecaoFaturaTributacao = faturaService.consultarFaturaTributacaoPorFatura(
                          fatura.getId());
                  for (FaturaTributacao ftributo : colecaoFaturaTributacao) {
                     if (ftributo.getTributo().getDescricao().equals("ICMS")) {
                        ICMS = ftributo.getPercentualTributo();
                        if (ftributo.getBaseCalculoTributoSubstitutivo() == null) {
                           valorBaseIcmsSubstituto = (valorBaseIcmsSubstituto.add(BigDecimal.ZERO));
                        } else {
                           valorBaseIcmsSubstituto = (valorBaseIcmsSubstituto
                                   .add(ftributo.getBaseCalculoTributoSubstitutivo()));
                        }

                        if (ftributo.getValorTributoSubstitutivo() == null) {
                           valorIcmsSubstituto = (valorIcmsSubstituto.add(BigDecimal.ZERO));
                        } else {
                           valorIcmsSubstituto = (valorIcmsSubstituto.add(ftributo.getValorTributoSubstitutivo()));
                        }
                     }
                     if (ftributo.getTributo().getDescricao().equals("ISS")) {
                        ISS = ftributo.getValorTributo();
                     }
                  }

                  BigDecimal icmsNormal = volumeFaturado.multiply(ICMS).setScale(2,
                          RoundingMode.HALF_UP);

                  if (tarifaVigencia.getValorBaseCalculoSubst() != null) {
                     valorBaseCalculoMetroCubico = volumeFaturado
                             .multiply(ToolUtil.coalescenciaNula(
                                     BigDecimal.ONE.add(tarifaVigencia.getValorBaseCalculoSubst()),
                                     BigDecimal.ZERO))
                             .setScale(2, RoundingMode.HALF_UP);
                  } else {
                     valorBaseCalculoMetroCubico = volumeFaturado
                             .multiply(
                                     ToolUtil.coalescenciaNula(BigDecimal.ONE.add(BigDecimal.ZERO), BigDecimal.ZERO))
                             .setScale(2, RoundingMode.HALF_UP);
                  }

                  valorIcmsSubstitutoTotal = valorBaseCalculoMetroCubico.multiply(ICMS)
                          .setScale(2, RoundingMode.HALF_UP);

                  valorIcmsMetroCubico = valorIcmsSubstitutoTotal.subtract(icmsNormal)
                          .setScale(2, RoundingMode.HALF_UP);

                  String valorBaseCalculoMetroCubicoString = "";
                  if (valorBaseCalculoMetroCubico != null) {
                     valorBaseCalculoMetroCubicoString = ToolNumber.converterCampoValorDecimalParaString(
                             "Base Calculo Metro Cúbico", valorBaseCalculoMetroCubico, Constantes.LOCALE_PADRAO,
                             NUMERO_DECIMAIS);
                  }

                  String valorIcmsMetroCubicoString = "";
                  if (valorIcmsMetroCubico != null) {
                     valorIcmsMetroCubicoString = ToolNumber.converterCampoValorDecimalParaString("Icms Metro Cúbico",
                             valorIcmsMetroCubico, Constantes.LOCALE_PADRAO, NUMERO_DECIMAIS);
                  }

                  String mensagemIcmsSubstituto = "";
                  String baseCalculo = "";
                  String valorIcms = "";

                  if (tarifaVigencia.getMensagemICMSSubs() != null) {
                     mensagemIcmsSubstituto = tarifaVigencia.getMensagemICMSSubs() + ". ";
                  }

                  if (valorBaseCalculoMetroCubico != null
                          && valorBaseCalculoMetroCubico.compareTo(BigDecimal.ZERO) > 0) {
                     baseCalculo = "Base de Cálculo: R$ " + valorBaseCalculoMetroCubicoString + ". ";
                  }

                  if (valorIcmsMetroCubico != null && valorIcmsMetroCubico.compareTo(BigDecimal.ZERO) > 0) {
                     valorIcms = "Valor do ICMS: R$ " + valorIcmsMetroCubicoString + ".";
                  }

                  if (valorBaseIcmsSubstituto.compareTo(BigDecimal.ZERO) > 0) {
                     subRelMsgVO.setMensagem("ICMS Subs:: Base de Cálculo:R$ "
                             + ToolNumber.converterCampoValorDecimalParaString("BaseIcmsSub", valorBaseIcmsSubstituto,
                             Constantes.LOCALE_PADRAO, NUMERO_DECIMAIS)
                             + "  Valor:R$ " + ToolNumber.converterCampoValorDecimalParaString("ValorIcmsSub",
                             valorIcmsSubstituto, Constantes.LOCALE_PADRAO, NUMERO_DECIMAIS));
                     retorno.add(subRelMsgVO);
                  }

                  ISS = ISS.add(ToolsFaturamento.getISSTermoFixo(fatura));
                  if (ISS.compareTo(BigDecimal.ZERO) > 0) {
                     SubRelatorioMensagemVO subRelMsgIss = new SubRelatorioMensagemVO();
                     subRelMsgIss.setMensagem("ISS Retido:R$ " + ToolNumber.converterCampoValorDecimalParaString("ISS",
                             ISS, Constantes.LOCALE_PADRAO, NUMERO_DECIMAIS));

                     retorno.add(subRelMsgIss);
                  }
                  Fatura faturaInterna = (Fatura) faturaService.getById(fatura.getId());
                  if (faturaInterna.getValorDiferimento() != null
                          && faturaInterna.getValorDiferimento().compareTo(BigDecimal.ZERO) > 0) {
                     SubRelatorioMensagemVO subRelMsgDiferimento = new SubRelatorioMensagemVO();

                     subRelMsgDiferimento
                             .setMensagem("DIFERIMENTO CONFORME DECRETO 6434/2017 - PARANA COMPETITIVO: R$ "
                                     + ToolNumber.converterCampoValorDecimalParaString("Diferimento",
                                     faturaInterna.getValorDiferimento(), Constantes.LOCALE_PADRAO,
                                     NUMERO_DECIMAIS));

                     retorno.add(subRelMsgDiferimento);
                  }
                  break;
               }
            }
         }
      }
      return retorno;
   }

   private List<SubRelatorioProdutosVO> criarColecaoVOProduto(Fatura fatura, BigDecimal somatorioTotalFornecimento,
                                                              Integer qtdQuantidadeCasasDescimaisApresentacaoConsumoApurado) {

      String valorDesconto = "0,00";
      List<SubRelatorioProdutosVO> retorno = new ArrayList<>();
      Boolean existeMaisDeUmConsumoGasCiclo = Boolean.FALSE;

      ParametroSistema parametroSomarVolumesFaturaMesmoCiclo = parametroSistemaService
              .obterParametroPorCodigo(Constantes.AGRUPAR_VOLUMES_IMPRESSAO_FATURA_MAIS_TARIFA_CICLO);

      if (parametroSomarVolumesFaturaMesmoCiclo != null
              && parametroSomarVolumesFaturaMesmoCiclo.getValor().equals("1")) {
         popularVOProdutoConsumoGas(fatura.getListaFaturaItens(), retorno);
         if (!retorno.isEmpty()) {
            existeMaisDeUmConsumoGasCiclo = Boolean.TRUE;
         }

      }

      if (fatura != null) {

         /*
          * Verifica se a FaturaItem possui FaturaItemImpressao
          */
         Map<Long, List<FaturaItemImpressao>> mapaFaturaItemImpressao = faturaItemImpressaoService
                 .consultarMapaFaturaItemImpressaoPorFaturaItem(fatura.getListaFaturaItens());

         List<FaturaItem> listaItens = new ArrayList<>(fatura.getListaFaturaItens());
         for (FaturaItem itemFatura : listaItens) {
            if (itemFatura.getCreditoDebitoARealizar() != null && this.verificarCreditoIcms(itemFatura)) {
               valorDesconto = ToolNumber.converterCampoCurrencyParaString(itemFatura.getValorTotal(),
                       Constantes.LOCALE_PADRAO);
               break;// listaItens.remove(itemFatura);
            }
         }

         for (FaturaItem itemFatura : listaItens) {

            if (itemFatura != null && itemFatura.getRubrica() != null
                    && itemFatura.getRubrica().getComposicaoNF()) {

               List<FaturaItemImpressao> colecaoFaturaItemImpressao = tratarMapaFaturaItemImpressao(
                       mapaFaturaItemImpressao, itemFatura);

               if (colecaoFaturaItemImpressao != null && !colecaoFaturaItemImpressao.isEmpty()) {
                  for (FaturaItemImpressao itemImpressao : colecaoFaturaItemImpressao) {
                     if (itemImpressao.getValorTotal().compareTo(BigDecimal.ZERO) > 0) {
                        if (itemImpressao.getDescricaoDesconto() != null && ConstantesFatura.DESCONTO_FATURA
                                .equals(itemImpressao.getDescricaoDesconto().substring(0, 8))) {
                           itemImpressao.setValorTotal(
                                   ToolsFaturamento.arredondamentoDescontoFatura(itemImpressao.getValorTotal()));
                        }
                        SubRelatorioProdutosVO sub = popularVOProdutoPorItemImpressao(itemFatura, itemImpressao,
                                qtdQuantidadeCasasDescimaisApresentacaoConsumoApurado,
                                existeMaisDeUmConsumoGasCiclo);
                        if (sub != null) {
                           retorno.add(sub);
                        }
                     }
                  }
               } else if (itemFatura.getCreditoDebitoARealizar() == null) {
                  SubRelatorioProdutosVO sub = popularVOProdutoPorItemFatura(itemFatura,
                          qtdQuantidadeCasasDescimaisApresentacaoConsumoApurado, existeMaisDeUmConsumoGasCiclo);
                  if (sub != null) {
                     if (sub.getDescricao().equals("ICMS Retido")
                             || sub.getDescricao().equals("Termo Fixo Mensal")
                             || sub.getDescricao().equals("Imposto Retido - IRRF")) {
                        SubRelatorioProdutosVO subaux = sub;
                        if (sub.getDesconto() == null) {
                           sub.setDesconto("0,00");
                        }
                        sub.setVolumeFaturado("1,0000");
                        sub.setValorUnitario(subaux.getValorTotal());
                     }
                     retorno.add(sub);
                  }
                  // Apenas deve entrar aqui quando o Item de Fatura for
                  // Subst Tributaria
               }
            }
         }

         /*
          * Imprime os créditos e débitos
          */
         SubRelatorioProdutosVO subRelProd = null;
         for (FaturaItem itemFatura : fatura.getListaFaturaItens()) {

            if (itemFatura != null && itemFatura.getCreditoDebitoARealizar() != null) {

               subRelProd = new SubRelatorioProdutosVO();

               // Indicador de credito ou
               // débito
               String chaveTipoDebito = constanteSistemaService
                       .obterValorConstanteSistemaPorCodigo(Constantes.C_DEBITO);
               if (itemFatura.getRubrica() != null) {
                  if (itemFatura.getRubrica().getLancamentoItemContabil().getTipoDebitoCredito()
                          .getId() == Long.parseLong(chaveTipoDebito)) {
                     subRelProd.setIndicadorCredito(Boolean.FALSE);
                  } else {
                     subRelProd.setIndicadorCredito(Boolean.TRUE);
                  }
               } else {
                  if (itemFatura.getCreditoDebitoARealizar() != null && itemFatura.getCreditoDebitoARealizar()
                          .getCreditoDebitoNegociado().getCreditoOrigem() != null) {
                     subRelProd.setIndicadorCredito(Boolean.TRUE);
                  } else {
                     subRelProd.setIndicadorCredito(Boolean.FALSE);
                  }
               }

               // CFOP
               if (itemFatura.getNaturezaOperacaoCfop() != null
                       && itemFatura.getNaturezaOperacaoCfop().getCodigoFiscal() != null) {
                  subRelProd.setCfop(itemFatura.getNaturezaOperacaoCfop().getCodigoFiscal().toString());
               }

               // Quantidade, Unidade e
               // Descrição
               Long codigoRubricaGas = Long
                       .valueOf(constanteSistemaService.obterValorConstanteSistemaPorCodigo(Constantes.C_GAS));
               if (itemFatura.getRubrica().getItemFatura() != null && itemFatura.getRubrica().getItemFatura()
                       .getId() == codigoRubricaGas.longValue()) {

                  subRelProd.setUnidade("m³");

                  if (!StringUtils.isEmpty(itemFatura.getRubrica().getDescricaoImpressao())) {
                     subRelProd.setDescricao(itemFatura.getRubrica().getDescricaoImpressao());
                  }

               } else {

                  subRelProd.setUnidade("");

                  if (itemFatura.getRubrica().getDescricaoComplementar()) {

                     StringBuilder stringBuffer = new StringBuilder();

                     if (!StringUtils.isEmpty(itemFatura.getRubrica().getDescricaoImpressao())) {
                        stringBuffer.append(itemFatura.getRubrica().getDescricaoImpressao());
                        stringBuffer.append(" - ");
                     }

                     if (itemFatura.getCreditoDebitoARealizar() != null
                             && itemFatura.getCreditoDebitoARealizar().getCreditoDebitoNegociado() != null) {

                        CreditoDebitoNegociado creditoDebitoNegociado = itemFatura.getCreditoDebitoARealizar()
                                .getCreditoDebitoNegociado();

                        if (!StringUtils.isEmpty(creditoDebitoNegociado.getDescricao())) {
                           stringBuffer.append(creditoDebitoNegociado.getDescricao());
                        }
                        stringBuffer.append(" - ");

                        if (creditoDebitoNegociado.getQuantidadePrestacaoCobrada() != null
                                && creditoDebitoNegociado.getQuatidadePrestacoes() != null) {
                           stringBuffer.append(itemFatura.getCreditoDebitoARealizar().getNumeroPrestacao());
                           stringBuffer.append("/");
                           stringBuffer.append(creditoDebitoNegociado.getQuatidadePrestacoes());
                        }
                     }

                     subRelProd.setDescricao(stringBuffer.toString());
                  } else {
                     if (!StringUtils.isEmpty(itemFatura.getRubrica().getDescricaoImpressao())) {
                        subRelProd
                                .setDescricao(itemFatura.getRubrica().getDescricaoImpressao() + "("
                                        + itemFatura.getCreditoDebitoARealizar().getNumeroPrestacao()
                                        + "/" + itemFatura.getCreditoDebitoARealizar()
                                        .getCreditoDebitoNegociado().getQuatidadePrestacoes()
                                        + ")");
                     }
                  }

               }

               BigDecimal valorUsadoDoCredito = creditoDebitoNegociadoService
                       .obterValorCobradoCredito(itemFatura.getCreditoDebitoARealizar().getId(), null);

               StringBuilder descricao = new StringBuilder();
               descricao.append(subRelProd.getDescricao());
               if (valorUsadoDoCredito != null && itemFatura.getCreditoDebitoARealizar() != null) {
                  if (valorUsadoDoCredito.compareTo(itemFatura.getCreditoDebitoARealizar().getValor()) < 0) {
                     descricao.append(" (Parcial)");
                  } else {
                     Integer faturasItem = creditoDebitoNegociadoService.consultarQuantidadeFaturaItemPorCreditoDebito(
                             itemFatura.getCreditoDebitoARealizar());
                     if (faturasItem.intValue() > 1) {
                        descricao.append(" (Complemento)");
                     }
                  }
               }

               subRelProd.setDescricao(descricao.toString());

               // Valor Unitário
               if (itemFatura.getValorUnitario() != null) {
                  StringBuilder valorUnitario = new StringBuilder();
                  if (subRelProd.isIndicadorCredito()) {
                     subRelProd.setValorUnitario("");
                  } else {
                     valorUnitario.append(ToolNumber.converterCampoValorDecimalParaString(ConstantesFatura.VALOR_UNITARIO,
                             itemFatura.getValorUnitario(), Constantes.LOCALE_PADRAO, 4));
                     subRelProd.setValorUnitario(valorUnitario.toString());
                  }
               }

               // Valor Total
               if (itemFatura.getValorTotal() != null) {
                  StringBuilder valor = new StringBuilder();
                  if (subRelProd.isIndicadorCredito()) {
                     if (this.verificarCreditoIcms(itemFatura)) {
                        valor.append(ToolNumber.converterCampoCurrencyParaString(itemFatura.getValorTotal(),
                                Constantes.LOCALE_PADRAO));
                        valorDesconto = valor.toString();
                     } else {
                        valor.append(ToolNumber.converterCampoCurrencyParaString(itemFatura.getValorTotal(),
                                Constantes.LOCALE_PADRAO));
                        subRelProd.setDesconto(valor.toString());
                     }

                  } else {
                     valor.append(ToolNumber.converterCampoCurrencyParaString(itemFatura.getValorTotal(),
                             Constantes.LOCALE_PADRAO));
                     subRelProd.setValorTotal(valor.toString());
                  }

               }

               // Descricao da tarifa
               if (itemFatura.getTarifa() != null && itemFatura.getTarifa().getDescricao() != null) {

                  subRelProd.setDescricaoTarifa(itemFatura.getTarifa().getDescricao());
               } else {
                  subRelProd.setDescricaoTarifa("");
               }

               if (itemFatura.getQuantidadeItem() != null) {
                  BigDecimal quantidade = itemFatura.getQuantidadeItem().divide(BigDecimal.ONE, 4,
                          RoundingMode.HALF_UP);
                  subRelProd.setVolumeFaturado(quantidade.toString());
               }

               if (subRelProd != null) {
                  retorno.add(subRelProd);
               }
            }
         }

         /*
          * Insere as Faturas em Atraso
          */
         List<Fatura> faturasEmAtraso = consultarCiclosValoresFaturasACobrar(fatura);
         for (Fatura faturaAtrasada : faturasEmAtraso) {

            if (faturaAtrasada != null) {
               subRelProd = new SubRelatorioProdutosVO();

               if (!StringUtils.isEmpty(faturaAtrasada.getCicloReferenciaFormatado())) {
                  subRelProd.setDescricao(faturaAtrasada.getCicloReferenciaFormatado());
               }

               if (faturaAtrasada.getValorTotal() != null) {
                  subRelProd.setValorTotal(ToolNumber.converterCampoCurrencyParaString(faturaAtrasada.getValorTotal(),
                          Constantes.LOCALE_PADRAO));
               }

               retorno.add(subRelProd);
            }
         }
      }

      retorno.sort((item1, item2) -> {
         if (item1.getDescricao().contains("Gás Natural") && item2.getDescricao().contains("Gás Natural")) {
            if (item1.getDescricao().contains("Ultrap. (PGU") || item2.getDescricao().contains("Ultrap. (PGU")) {
               return item1.getDescricao().compareTo(item2.getDescricao());
            } else {
               try {
                  return (ToolNumber
                          .converterCampoStringParaValorBigDecimal("vrUnit1", item1.getValorUnitario(),
                                  Constantes.FORMATO_VALOR_BR, Constantes.LOCALE_PADRAO)
                          .compareTo(ToolNumber.converterCampoStringParaValorBigDecimal("vrUnit2",
                                  item2.getValorUnitario(), Constantes.FORMATO_VALOR_BR,
                                  Constantes.LOCALE_PADRAO)))
                          * -1;
               } catch (Exception e) {
                  log.erro("ERRO AO CONVERTER VALORES UNITÁRIOS", e.getMessage());
               }
            }
         }
         return item1.getDescricao().compareTo(item2.getDescricao());
      });

      for (SubRelatorioProdutosVO item : retorno) {
         if (item.getDescricao().equals("Consumo de gás")) {
            item.setDesconto(valorDesconto);
         }
      }

      return retorno;
   }

   private List<SubRelatorioFaturasVencidasVO> criarColecaoVOFaturasVencidas(Fatura fatura) {

      List<SubRelatorioFaturasVencidasVO> retorno = new ArrayList<>();

      Long chavePontoConsumo = null;
      if (fatura.getPontoConsumo() != null) {
         chavePontoConsumo = fatura.getPontoConsumo().getId();
      }
      Long chaveContrato = null;
      if (fatura.getContratoAtual() != null) {
         chaveContrato = fatura.getContratoAtual().getId();
      }
      Long chaveCliente = null;
      if (fatura.getCliente() != null) {
         chaveCliente = fatura.getCliente().getId();
      }
      Integer anoMesReferencia = null;
      if (fatura.getAnoMes() != null) {
         anoMesReferencia = fatura.getAnoMes();
      }
      Integer ciclo = null;
      if (fatura.getCiclo() != null) {
         ciclo = fatura.getCiclo();
      }

      List<Fatura> colecaoFaturasVencidas = faturaService.consultarFaturasVencidas(chavePontoConsumo, chaveContrato,
              chaveCliente, anoMesReferencia, ciclo);

      TipoOperacaoEnum tipoOperacaoSaida = TipoOperacaoEnum.SAIDA;

      Map<Long, List<DocumentoFiscal>> documentoPorFatura = documentoFiscalService
              .obterDocumentosFiscaisPorFatura(colecaoFaturasVencidas, tipoOperacaoSaida);

      for (Fatura faturaVencida : colecaoFaturasVencidas) {
         SubRelatorioFaturasVencidasVO subRelFatVencidaVO = new SubRelatorioFaturasVencidasVO();

         DocumentoFiscal documentoFiscalFaturaAtual = ToolUtil.getElemento(documentoPorFatura,
                 faturaVencida.getId());

         if (documentoFiscalFaturaAtual != null) {
            subRelFatVencidaVO.setNotaFiscalFatura(documentoFiscalFaturaAtual.getNumero() + "-"
                    + documentoFiscalFaturaAtual.getSerie().getNumero());
            subRelFatVencidaVO.setTipo(documentoFiscalFaturaAtual.getFatura().getDocumentoTipo().getDescricao());
         }

         if (faturaVencida.getCicloReferenciaFormatado() != null) {
            subRelFatVencidaVO.setMesAno(faturaVencida.getCicloReferenciaFormatado());
         }

         if (faturaVencida.getDataVencimento() != null) {
            subRelFatVencidaVO.setVencimento(ToolDate.converterDataParaStringSemHora(faturaVencida.getDataVencimento(),
                    Constantes.FORMATO_DATA_BR));
         }

         String codigoSituacaoPagamentoParcialmentePago = constanteSistemaService
                 .obterValorConstanteSistemaPorCodigo(Constantes.C_SITUACAO_RECEBIMENTO_PARCIALMENTE_PAGO);
         Long idFaturaParcialmentePaga = null;

         try {
            idFaturaParcialmentePaga = ToolStr.converterCampoStringParaValorLong(ConstantesFatura.SITUACAO_DE_PAGAMENTO,
                    codigoSituacaoPagamentoParcialmentePago);
         } catch (Exception e) {
            log.info(e.getMessage());
            log.erro(Constantes.ERRO_DADOS_INVALIDOS, ConstantesFatura.SITUACAO_DE_PAGAMENTO);
         }

         if (faturaVencida.getValorConciliado() != null) {

            BigDecimal valor = faturaVencida.getValorConciliado();
            if ((idFaturaParcialmentePaga != null) && (faturaVencida.getSituacaoPagamento() != null)
                    && (idFaturaParcialmentePaga == faturaVencida.getSituacaoPagamento().getId())) {

               BigDecimal valorRecebimento = recebimentoService
                       .obterValorRecebimentoPelaFatura(faturaVencida.getId());

               if (valorRecebimento != null) {
                  valor = faturaVencida.getValorConciliado().subtract(valorRecebimento);
               }
            }
            subRelFatVencidaVO.setValor(ToolNumber.converterCampoValorDecimalParaString("Valor", valor,
                    Constantes.LOCALE_PADRAO, ConstantesFatura.DUAS_CASAS_DECIMAIS));
         }

         if (faturaVencida.getDataRevisao() != null) {
            subRelFatVencidaVO.setRevisao(ToolDate.converterDataParaStringSemHora(faturaVencida.getDataRevisao(),
                    Constantes.FORMATO_DATA_BR));
         }

         retorno.add(subRelFatVencidaVO);

      }

      return retorno;
   }

   private void popularVOProdutoConsumoGas(List<FaturaItem> listaFaturaItem,
                                           List<SubRelatorioProdutosVO> retorno) {
      Long idRubricaConsumoGas = Long.valueOf(
              constanteSistemaService.obterValorConstanteSistemaPorCodigo(Constantes.C_RUBRICA_CONSUMO_GAS));

      Rubrica rubricaConsumoGas = (Rubrica) rubricaService.getById(idRubricaConsumoGas);
      List<FaturaItem> listaAux = new ArrayList<>();

      Integer count = 0;
      if (rubricaConsumoGas != null) {
         for (FaturaItem faturaItem : listaFaturaItem) {
            if (faturaItem.getRubrica().getId() == rubricaConsumoGas.getId()) {
               listaAux.add(faturaItem);
               count++;
            }
         }
         if (count >= 2) {
            BigDecimal volumeConsumido = BigDecimal.ZERO;
            BigDecimal valorTotal = BigDecimal.ZERO;
            BigDecimal valorUnitarioMedia = BigDecimal.ZERO;
            for (FaturaItem fat : listaAux) {
               volumeConsumido = volumeConsumido.add(fat.getConsumo());
               valorTotal = valorTotal.add(fat.getValorTotal());

            }
            valorUnitarioMedia = valorTotal.divide(volumeConsumido, 2, RoundingMode.HALF_UP);
            FaturaItem itemFatura = ToolUtil.primeiroElemento(listaAux);

            SubRelatorioProdutosVO subRelProd;
            subRelProd = new SubRelatorioProdutosVO();
            subRelProd.setCfop("");

            StringBuilder descricao = new StringBuilder();
            descricao.append(itemFatura.getRubrica().getDescricaoImpressao());
            subRelProd.setDescricao(descricao.toString());
            subRelProd.setIndicadorCredito(Boolean.FALSE);
            StringBuilder valorTotalString = new StringBuilder();
            StringBuilder valorUnitarioString = new StringBuilder();
            if (subRelProd.isIndicadorCredito()) {
               valorTotalString.append("-");
               valorUnitarioString.append("-");
            }

            valorTotalString.append(ToolNumber.converterCampoCurrencyParaString(valorTotal, Constantes.LOCALE_PADRAO));
            subRelProd.setValorTotal(valorTotalString.toString());

            valorUnitarioString.append(ToolNumber.converterCampoValorDecimalParaString(ConstantesFatura.VALOR_UNITARIO,
                    valorUnitarioMedia,
                    Constantes.LOCALE_PADRAO, 4));
            subRelProd.setValorUnitario(valorUnitarioString.toString());

            subRelProd.setUnidade(" m³ ");
            if (volumeConsumido != null) {
               subRelProd.setVolumeFaturado(ToolNumber.converterCampoValorDecimalParaString("Quantidade",
                       volumeConsumido, Constantes.LOCALE_PADRAO, ConstantesFatura.DUAS_CASAS_DECIMAIS));

            }
            retorno.add(subRelProd);
         }
      }

   }

   private String gerarNomeArquivoDetalhado(Fatura fatura, String caminhoDiretorioFatura,
                                            DocumentoFiscal documentoFiscal) {
      String path = caminhoDiretorioFatura + "\\"
              + ToolDate.converterDataParaStringSemHoraSemBarra(fatura.getDataEmissao(), "yyyyMMdd")
              + "\\" + getNomeFatura(fatura, documentoFiscal) + ".pdf";
      return path;
   }

   private String gerarNomeDoArquivoDeImpressao(Fatura fatura) {
      String retorno = "";
      if (fatura.getContratoAtual() != null && fatura.getCliente() != null && fatura.getPontoConsumo() != null) {
         retorno = ToolStr.removerTodosCaracteresEspeciais(fatura.getContratoAtual().getNumeroFormatado())
                 + SEPARADOR_ARQUIVO + ToolStr.removerTodosCaracteresEspeciais(fatura.getCliente().getNome())
                 + SEPARADOR_ARQUIVO + ToolStr.removerTodosCaracteresEspeciais(fatura.getPontoConsumo().getDescricao())
                 + SEPARADOR_ARQUIVO + ToolStr.removerTodosCaracteresEspeciais(fatura.getDataVencimentoFormatada());
      } else if (fatura.getPontoConsumo() != null && fatura.getCliente() != null) {
         retorno = ToolStr.removerTodosCaracteresEspeciais(fatura.getCliente().getNome()) + SEPARADOR_ARQUIVO
                 + ToolStr.removerTodosCaracteresEspeciais(fatura.getPontoConsumo().getDescricao()) + SEPARADOR_ARQUIVO
                 + ToolStr.removerTodosCaracteresEspeciais(fatura.getDataVencimentoFormatada());
      } else if (fatura.getDataVencimentoFormatada() != null) {
         retorno = ToolStr.removerTodosCaracteresEspeciais(fatura.getDataVencimentoFormatada());
      }
      retorno = retorno + "-" + fatura.getId();
      if (fatura.getNumeroFatura() != null) {
         retorno = retorno + "-" + fatura.getNumeroFatura();
      }
      return retorno;
   }

   private String gerarNomeArquivoChavePrimaria(Fatura fatura, String caminhoDiretorioFatura) {
      String path = caminhoDiretorioFatura + "\\"
              + ToolDate.converterDataParaStringSemHoraSemBarra(fatura.getDataEmissao(), "yyyyMMdd")
              + "\\" + fatura.getId() + "-E" + ".pdf";
      return path;
   }

   private String getNomeFatura(Fatura fatura, DocumentoFiscal documentoFiscal) {
      String nomeFatura = Optional.ofNullable(documentoFiscal).map(d -> gerarNomeDoArquivoDeImpressao(d.getFatura()))
              .orElse(String.valueOf(fatura.getId()));
      return nomeFatura;
   }

   private void salvarEmissaoFaturaNoDiretorio(byte[] bytes, String nomeFatura, LocalDate dataEmissao, Integer numeroLote) {

      if (bytes != null) {
         File pdf = null;
         FileOutputStream saida = null;
         String caminhoDiretorio = "";
         String diretorioDocumento = null;
         try {

            String caminhoDiretorioFatura = "/mnt/aplicativos";

            if (numeroLote != null) {
               caminhoDiretorio = caminhoDiretorioFatura + File.separator
                       + ToolDate.converterDataParaStringSemHoraSemBarra(dataEmissao, "yyyy/MM/dd")
                       + File.separator + numeroLote + File.separator;

               pdf = ToolFile.getFile(caminhoDiretorio);

               if (!pdf.exists()) {
                  pdf.mkdirs();
               }

               diretorioDocumento = caminhoDiretorio + nomeFatura + ".pdf";
               pdf = ToolFile.getFile(diretorioDocumento);

            } else {
               caminhoDiretorio = caminhoDiretorioFatura + File.separator
                       + ToolDate.converterDataParaStringSemHoraSemBarra(dataEmissao, "yyyy/MM/dd")
                       + File.separator;

               pdf = ToolFile.getFile(caminhoDiretorio);

               if (!pdf.exists()) {
                  pdf.mkdirs();
               }

               diretorioDocumento = caminhoDiretorio + nomeFatura + ".pdf";
               pdf = ToolFile.getFile(diretorioDocumento);
            }

            saida = new FileOutputStream(pdf);

           /* ProcessoDocumento processoDocumento = controladorProcessoDocumento
                    .criaEPopulaProcessoDocumento(processo, diretorioDocumento, nomeFatura);
            controladorProcessoDocumento.inserirProcessoDocumento(processoDocumento);*/

            saida.write(bytes);
            saida.flush();
            saida.close();
         } catch (FileNotFoundException e) {
            log.erro(e.getMessage(), e.getCause().getMessage());
         } catch (IOException e) {
            log.erro(e.getMessage(), e.getCause().getMessage());
         }
      }
   }

   private boolean verificarCreditoIcms(FaturaItem item) {
      boolean isCreditoIcms = item.getRubrica().getTributos().stream()
              .anyMatch(trib -> trib.getTributo().getDescricao().equals("ICMS"));
      return isCreditoIcms;
   }

   private List<FaturaItemImpressao> tratarMapaFaturaItemImpressao(
           Map<Long, List<FaturaItemImpressao>> mapaFaturaItemImpressao, FaturaItem itemFatura) {
      List<FaturaItemImpressao> colecaoFaturaItemImpressao = null;
      if (mapaFaturaItemImpressao != null) {
         colecaoFaturaItemImpressao = mapaFaturaItemImpressao.getOrDefault((itemFatura.getId()),
                 new ArrayList<FaturaItemImpressao>());
      }
      return colecaoFaturaItemImpressao;
   }

   private SubRelatorioProdutosVO popularVOProdutoPorItemImpressao(FaturaItem itemFatura,
                                                                   FaturaItemImpressao itemImpressao, Integer qtdQuantidadeCasasDescimaisApresentacaoConsumoApurado,
                                                                   Boolean existeMaisDeUmConsumoGasCiclo) {

      Long idRubricaConsumoGas = Long.valueOf(
              constanteSistemaService.obterValorConstanteSistemaPorCodigo(Constantes.C_RUBRICA_CONSUMO_GAS));

      Rubrica rubricaConsumoGas = (Rubrica) rubricaService.getById(idRubricaConsumoGas);

      SubRelatorioProdutosVO subRelProd = null;

      if (!existeMaisDeUmConsumoGasCiclo || (existeMaisDeUmConsumoGasCiclo
              && itemFatura.getRubrica().getId() != rubricaConsumoGas.getId())) {
         subRelProd = new SubRelatorioProdutosVO();
         subRelProd.setCfop("");
         if (itemImpressao.getDescricaoDesconto() != null) {
            StringBuilder descricao = new StringBuilder();
            descricao.append(itemImpressao.getDescricaoDesconto());

            subRelProd.setDescricao(descricao.toString());

            // O campo 'DescricaoDesconto' passou
            // a ser utilizado também para exibir
            // a informação de
            // 'consumo mínimo' na fatura. Sim, é
            // estranho.
            // Desta forma, nem sempre que existir
            // informação neste campo, significa
            // que existe um
            // desconto, é preciso verificar o
            // campo indicadorDesconto pra ter
            // certeza.
            if (itemImpressao.getDesconto()) {
               subRelProd.setIndicadorCredito(Boolean.TRUE);
            } else {
               subRelProd.setIndicadorCredito(Boolean.FALSE);
            }

         } else if (itemImpressao.getDesconto()) {
            subRelProd.setIndicadorCredito(Boolean.TRUE);
         } else {
            StringBuilder descricao = new StringBuilder();
            if (itemImpressao.getDescricaoItem() != null) {
               descricao.append(itemImpressao.getDescricaoItem());
            } else {
               descricao.append(itemFatura.getRubrica().getDescricaoImpressao());
            }
            subRelProd.setDescricao(descricao.toString());
            subRelProd.setIndicadorCredito(Boolean.FALSE);
         }
         if (itemImpressao.getTarifa() != null && itemImpressao.getTarifa().getDescricao() != null) {
            subRelProd.setDescricaoTarifa(itemImpressao.getTarifa().getDescricao());
         } else {
            subRelProd.setDescricaoTarifa("");
         }

         if (itemImpressao.getDataInicial() != null) {
            subRelProd.setDataInicio(ToolDate.converterDataParaStringSemHora(itemImpressao.getDataInicial(),
                    Constantes.FORMATO_DATA_BR));
         }
         if (itemImpressao.getDataFinal() != null) {
            subRelProd.setDataFinal(
                    ToolDate.converterDataParaStringSemHora(itemImpressao.getDataFinal(), Constantes.FORMATO_DATA_BR));
         }
         if (itemImpressao.getDataInicial() != null && itemImpressao.getDataFinal() != null) {
            int intervaloDatas = Integer.parseInt((ToolDate.diasEntre(itemImpressao.getDataInicial(),
                    itemImpressao.getDataFinal())).toString());
            if (intervaloDatas == 0) {
               subRelProd.setQuantidadeDias("1");
            } else {
               subRelProd.setQuantidadeDias(String.valueOf(intervaloDatas + 1));
            }
         }

         if (itemImpressao.getDesconto()) {
            subRelProd.setDesconto(
                    ToolNumber.converterCampoCurrencyParaString(itemImpressao.getValorTotal(),
                            Constantes.LOCALE_PADRAO));
         }

         if (!itemImpressao.getDesconto()) {
            subRelProd.setDesconto("0,00");
         }

         if (itemImpressao.getValorDesconto() != null
                 && itemImpressao.getValorDesconto().compareTo(BigDecimal.ZERO) > 0) {
            subRelProd.setDesconto(ToolNumber.converterCampoCurrencyParaString(itemImpressao.getValorDesconto(),
                    Constantes.LOCALE_PADRAO));
         }

         if (!itemImpressao.getDesconto() && itemImpressao.getDescricaoDesconto() == null) {
            subRelProd.setUnidade(" m³ ");
            String volumeFaturaTexto = ToolNumber.converterCampoValorDecimalParaString("Quantidade",
                    itemImpressao.getConsumo(), Constantes.LOCALE_PADRAO, ConstantesFatura.QUATRO_CASAS_DECIMAIS);
            subRelProd.setVolumeFaturado(volumeFaturaTexto);

            StringBuilder valorUnitario = new StringBuilder();

            if (subRelProd.isIndicadorCredito()) {
               valorUnitario.append("-");
            }

            valorUnitario.append(ToolNumber.converterCampoValorDecimalParaString(ConstantesFatura.VALOR_UNITARIO,
                    itemImpressao.getValorUnitario(), Constantes.LOCALE_PADRAO,
                    ConstantesFatura.QUATRO_CASAS_DECIMAIS));
            subRelProd.setValorUnitario(valorUnitario.toString());

         }

         StringBuilder valorTotal = new StringBuilder();

         if (subRelProd.isIndicadorCredito()) {
            valorTotal.append("-");
         }

         valorTotal.append(
                 ToolNumber.converterCampoCurrencyParaString(itemImpressao.getValorTotal(), Constantes.LOCALE_PADRAO));
         subRelProd.setValorTotal(valorTotal.toString());
      }

      return subRelProd;
   }

   private SubRelatorioProdutosVO popularVOProdutoPorItemFatura(FaturaItem itemFatura,
                                                                Integer qtdQuantidadeCasasDescimaisApresentacaoConsumoApurado,
                                                                Boolean existeMaisDeUmConsumoGasCiclo) {

      Long idRubricaConsumoGas = Long.valueOf(
              constanteSistemaService.obterValorConstanteSistemaPorCodigo(Constantes.C_RUBRICA_CONSUMO_GAS));

      Rubrica rubricaConsumoGas = (Rubrica) rubricaService.getById(idRubricaConsumoGas);

      SubRelatorioProdutosVO subRelProd = null;
      if (itemFatura.getRubrica().getId() != rubricaConsumoGas.getId()) {
         subRelProd = new SubRelatorioProdutosVO();
         subRelProd.setCfop("");

         StringBuilder descricao = new StringBuilder();
         descricao.append(itemFatura.getRubrica().getDescricaoImpressao());
         subRelProd.setDescricao(descricao.toString());
         subRelProd.setIndicadorCredito(Boolean.FALSE);

         StringBuilder valorTotal = new StringBuilder();
         StringBuilder valorUnitario = new StringBuilder();
         if (subRelProd.isIndicadorCredito()) {
            valorTotal.append("-");
            valorUnitario.append("-");
         }

         valorTotal.append(
                 ToolNumber.converterCampoCurrencyParaString(itemFatura.getValorTotal(), Constantes.LOCALE_PADRAO));
         subRelProd.setValorTotal(valorTotal.toString());

         valorUnitario.append(ToolNumber.converterCampoValorDecimalParaString(ConstantesFatura.VALOR_UNITARIO,
                 itemFatura.getValorUnitario(), Constantes.LOCALE_PADRAO, ConstantesFatura.QUATRO_CASAS_DECIMAIS));
         subRelProd.setValorUnitario(valorUnitario.toString());
         subRelProd.setDesconto("0,00");
         subRelProd.setUnidade(" m³ ");
         if (itemFatura.getConsumo() != null) {
            subRelProd.setVolumeFaturado(ToolNumber.converterCampoValorDecimalParaString(
                    "Quantidade", itemFatura.getConsumo()
                            .setScale(qtdQuantidadeCasasDescimaisApresentacaoConsumoApurado, RoundingMode.HALF_UP),
                    Constantes.LOCALE_PADRAO, NUMERO_DECIMAIS));
         }
      }
      return subRelProd;
   }

   private List<Fatura> consultarCiclosValoresFaturasACobrar(Fatura fatura) {

      List<Fatura> retorno = null;

      if (fatura.getCobrada()) {
         DocumentoCobranca documentoCobranca = documentoCobrancaService.obtemDadosParaConsultarFaturas(fatura);

         if (documentoCobranca != null) {
            retorno = documentoCobrancaService.consultarDadosFaturasPorDocumentoCobranca(
                    fatura.getId(), documentoCobranca.getId());
         } else {
            retorno = new ArrayList<>();
         }
      } else {
         retorno = new ArrayList<>();
      }

      return retorno;
   }

   public String adicionarMensagensPontoConsumo(Fatura fatura) {
      StringBuilder mensagemAtual = new StringBuilder();
      String mensagemDesconto = this.verificarMensagemDescontoAtivo(fatura.getPontoConsumo());

      if (mensagemDesconto != null) {
         mensagemAtual.append(mensagemDesconto);
      }

      if (fatura.getPontoConsumo().getMensagemFatura() != null
              && !fatura.getPontoConsumo().getMensagemFatura().isEmpty()) {
         mensagemAtual.append("\r\n" + fatura.getPontoConsumo().getMensagemFatura());
      }

      return mensagemAtual.toString();
   }

   public String verificarMensagemDescontoAtivo(PontoConsumo pontoConsumo) {

      String mensagem = null;
      CampanhaDescontoVincular CDV = obterDescontoAtivoPorPontoConsumo(pontoConsumo);

      if (CDV != null) {
         mensagem = CDV.getCampanhaDesconto().getMensagemFatura();
      }

      return mensagem;
   }

   public CampanhaDescontoVincular obterDescontoAtivoPorPontoConsumo(PontoConsumo pontoConsumo) {

      FaturamentoGrupo faturamentoGrupo = new FaturamentoGrupo();
      CampanhaDescontoVincular CDV = new CampanhaDescontoVincular();
      Integer periodo = 0;
      Integer ciclo = 0;

      try {
         faturamentoGrupo = faturamentoGrupoService.getById(pontoConsumo.getRota().getFaturamentoGrupo().getId());
         periodo = faturamentoGrupo.getAnoMesFaturamento();
         ciclo = faturamentoGrupo.getNumeroCiclo();
      } catch (Exception e) {
         e.printStackTrace();
      }

      CDV = campanhaDescontoVincularService.obterDescontoAtivoPorPontoConsumo(pontoConsumo, periodo, ciclo);

      return CDV;
   }

   private Map<String, Object> verificarDadosBoleto(Fatura fatura, DocumentoCobranca documentoCobranca) {
      Map<String, Object> retorno = new HashMap<>();
      FormaCobrancaEnum formaCobranca = FormaCobrancaEnum.BOLETO_BANCARIO;
      Contrato contratoAtual = contratoService.getById(fatura.getContratoAtual().getId());


      Boolean indicadorBoleto = Boolean.FALSE;
      if (contratoAtual != null && contratoAtual.getNumero() != null
              && contratoAtual.getFormaCobranca() != null) {
         retorno.put(ConstantesFatura.CONTRATO, contratoAtual.getNumero().toString());

         if (contratoAtual.getArrecadadorContratoConvenio().getArrecadadorCarteiraCobranca().getArrecadador()
                 .getBanco() != null) {
            retorno.put("nomeBanco", contratoAtual.getArrecadadorContratoConvenio()
                    .getArrecadadorCarteiraCobranca().getArrecadador().getBanco().getNome());
         }

         if (fatura.getPontoConsumo() != null) {
            retorno.put("numeroContratoFmt", fatura.getPontoConsumo().getCil() + "-"
                    + fatura.getPontoConsumo().getComplementoCil());
         }

         if (contratoAtual.getNumeroContratoCompagas() != null) {
            retorno.put("numeroContratoCompleto", contratoAtual.getNumeroContratoCompagas());
         }

         if (contratoAtual.getArrecadadorContratoConvenio().getTipoConvenio().getId().longValue() == formaCobranca.getId().longValue()) {
            retorno.put(ConstantesFatura.INDICADOR_BOLETO, Boolean.TRUE);
            indicadorBoleto = Boolean.TRUE;
         } else {
            retorno.put(ConstantesFatura.INDICADOR_BOLETO, Boolean.FALSE);

            TipoConvenioBancarioEnum debitoAutomatico = TipoConvenioBancarioEnum.DEBITO_AUTOMATICO;
            if (contratoAtual.getArrecadadorContratoConvenio().getTipoConvenio().equals(debitoAutomatico)) {
               retorno.put("indicadorDebitoAutomatico", Boolean.TRUE);
               retorno.put("agenciaCodigo", contratoAtual.getAgencia());
            }
         }
      } else {
         if (arrecadadorService.obterArrecadadorContratoConvenioParaBoletoBancario().getTipoConvenio().getId().longValue() == formaCobranca.getId().longValue()) {
            retorno.put(ConstantesFatura.INDICADOR_BOLETO, Boolean.TRUE);
            indicadorBoleto = Boolean.TRUE;
         } else {
            retorno.put(ConstantesFatura.INDICADOR_BOLETO, Boolean.FALSE);
         }
      }
      if (indicadorBoleto) {
         if (fatura.getBoleto() != null) {
            Boleto boleto = boletoService.getById(fatura.getBoleto());
            if (boleto != null) {
               retorno.putAll(boleto.toMap());
            }
         } else {

            retorno.putAll(this.atualizarDadosBoleto(fatura, documentoCobranca));
            /*
            documentoCobranca.setNossoNumero(fatura.getId());
            BigDecimal valor = documentoCobranca.getValorTotal();
            documentoCobranca.setValorTotal(valor.divide(BigDecimal.ONE, 2, RoundingMode.HALF_UP));

            DocumentoCobranca docCobrancaTemp = (DocumentoCobranca) SerializationUtils.clone(documentoCobranca);
            BigDecimal ISSTOTAL = calculosTributosComponent.getValorTotalISS(fatura);
            if (ISSTOTAL.compareTo(BigDecimal.ZERO) > 0) {
               docCobrancaTemp.setValorTotal(docCobrancaTemp.getValorTotal().subtract(ISSTOTAL));
            }

            try {
               retorno.putAll(FabricaConstrutorBoleto.novoConstrutorBoleto(documentoCobranca)
                       .preencherNumeroCodigoBarras().preencherNumeroDocumentoPagamento().preencherNomeClienteBoleto()
                       .preencherEnderecoCliente().preencherDataDocumento().preencherValorDocumento()
                       .preencherLocalDePagamento().preencherImagemLogoBanco().preencherNumeroBanco()
                       .preencherCedente().preencherAgenciaCodigo().preencherNumeroDocumento()
                       .preencherEspecieDocumento().preencherDataProcessamento().preencherNossoNumero()
                       .preencherCarteira().preencherDescontosAbatimentos().preencherOutrasDeducoes()
                       .preencherMoraMulta().preencherOutrosAcrescimos().preencherValorTotalBoleto().mapaParametros());
            } catch (NegocioException e) {
               throw new RuntimeException(e);
            }

            ToolsFaturamento.persitirCodigoBarras(retorno, documentoCobranca);

            retorno.put("dataVencimentoDocumentoCobranca", ToolDate.converterDataParaStringSemHora(
                    documentoCobranca.getDataVencimento(), Constantes.FORMATO_DATA_BR));
            Contrato contratoPai = fatura.getContratoPai();
            Fatura faturaComCliente = faturaService.getById(fatura.getId());
            Cliente cliente = faturaComCliente.getCliente();
            if (contratoPai != null) {
               ClienteDebitoAutomatico clienteDebitoAutomatico = clienteDebitoAutomaticoService
                       .obterClienteDebitoAutomatico(cliente.getId(), contratoPai.getId());
               if (clienteDebitoAutomatico != null) {
                  String identificadorClienteDebito = String.valueOf(clienteDebitoAutomatico.getId());
                  retorno.put("codigoDebitoAutomatico", identificadorClienteDebito
                          .concat(String.valueOf(DigitoAutoConferencia.modulo10(identificadorClienteDebito))));
               }
            }
            Boleto newBoleto = new Boleto();
            retorno.put("dataVencimento", ToolDate.converterDataParaString(fatura.getDataVencimento(), false));
            retorno.put("codigoCliente", fatura.getContratoAtual().getNumeroClienteDebito());
            retorno.put("cpfCnpj", fatura.getCliente().getNumeroCpfCnpj());
            newBoleto.updateBoleto(retorno);
            newBoleto = boletoService.save(newBoleto);
            fatura.setBoleto( newBoleto.getId());
            faturaService.save(fatura);

             */
         }

      }
      return retorno;
   }

   public Map<String, Object> atualizarDadosBoleto(Fatura fatura, DocumentoCobranca documentoCobranca){
      Map<String, Object> retornoBoleto = new HashMap<>();

      documentoCobranca.setNossoNumero(fatura.getId());
      BigDecimal valor = documentoCobranca.getValorTotal();
      documentoCobranca.setValorTotal(valor.divide(BigDecimal.ONE, 2, RoundingMode.HALF_UP));

      DocumentoCobranca docCobrancaTemp = (DocumentoCobranca) SerializationUtils.clone(documentoCobranca);
      BigDecimal ISSTOTAL = calculosTributosComponent.getValorTotalISS(fatura);
      if (ISSTOTAL.compareTo(BigDecimal.ZERO) > 0) {
         docCobrancaTemp.setValorTotal(docCobrancaTemp.getValorTotal().subtract(ISSTOTAL));
      }

      try {
         retornoBoleto.putAll(FabricaConstrutorBoleto.novoConstrutorBoleto(documentoCobranca)
                 .preencherNumeroCodigoBarras().preencherNumeroDocumentoPagamento().preencherNomeClienteBoleto()
                 .preencherEnderecoCliente().preencherDataDocumento().preencherValorDocumento()
                 .preencherLocalDePagamento().preencherImagemLogoBanco().preencherNumeroBanco()
                 .preencherCedente().preencherAgenciaCodigo().preencherNumeroDocumento()
                 .preencherEspecieDocumento().preencherDataProcessamento().preencherNossoNumero()
                 .preencherCarteira().preencherDescontosAbatimentos().preencherOutrasDeducoes()
                 .preencherMoraMulta().preencherOutrosAcrescimos().preencherValorTotalBoleto().mapaParametros());

         ToolsFaturamento.persitirCodigoBarras(retornoBoleto, documentoCobranca);

      } catch (NegocioException e) {
         throw new RuntimeException(e);
      }


      retornoBoleto.put("dataVencimentoDocumentoCobranca", ToolDate.converterDataParaStringSemHora(
              documentoCobranca.getDataVencimento(), Constantes.FORMATO_DATA_BR));
      Contrato contratoPai = fatura.getContratoPai();
      Fatura faturaComCliente = faturaService.getById(fatura.getId());
      Cliente cliente = faturaComCliente.getCliente();
      if (contratoPai != null) {
         ClienteDebitoAutomatico clienteDebitoAutomatico = clienteDebitoAutomaticoService
                 .obterClienteDebitoAutomatico(cliente.getId(), contratoPai.getId());
         if (clienteDebitoAutomatico != null) {
            String identificadorClienteDebito = String.valueOf(clienteDebitoAutomatico.getId());
            retornoBoleto.put("codigoDebitoAutomatico", identificadorClienteDebito
                    .concat(String.valueOf(DigitoAutoConferencia.modulo10(identificadorClienteDebito))));
         }
      }
      retornoBoleto.put("dataVencimento", ToolDate.converterDataParaString(fatura.getDataVencimento(), false));
      retornoBoleto.put("codigoCliente", fatura.getContratoAtual().getNumeroClienteDebito());
      retornoBoleto.put("cpfCnpj", fatura.getCliente().getNumeroCpfCnpj());
      retornoBoleto.put("nomeBanco", fatura.getContratoAtual().getArrecadadorContratoConvenio()
              .getArrecadadorCarteiraCobranca().getArrecadador().getBanco().getNome());

      Boleto newBoleto = new Boleto();
      newBoleto.updateBoleto(retornoBoleto);

      if(fatura.getBoleto() != null){
         newBoleto.setId(fatura.getBoleto());
      }

      newBoleto = boletoService.save(newBoleto);
      fatura.setBoleto( newBoleto.getId());
      faturaService.save(fatura);

      return retornoBoleto;
   }
}
