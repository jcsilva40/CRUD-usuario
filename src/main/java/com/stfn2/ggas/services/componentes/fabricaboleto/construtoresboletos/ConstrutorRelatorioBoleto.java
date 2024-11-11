package com.stfn2.ggas.services.componentes.fabricaboleto.construtoresboletos;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.stfn2.ggas.config.exceptions.types.NegocioException;
import com.stfn2.ggas.constante.Constantes;
import com.stfn2.ggas.core.utils.ApplicationContextProvider;
import com.stfn2.ggas.domain.*;
import com.stfn2.ggas.services.ConstanteSistemaService;
import com.stfn2.ggas.services.EmpresaService;
import com.stfn2.ggas.services.FaturaService;
import com.stfn2.ggas.services.componentes.fabricaboleto.FabricaConstrutorBoleto;
import com.stfn2.ggas.services.componentes.fabricaboleto.codigodebarras.CodigoBarras;
import com.stfn2.ggas.services.componentes.fabricaboleto.codigodebarras.FabricaCodigoBarras;
import com.stfn2.ggas.services.componentes.fabricaboleto.codigodebarras.dados.DadosPadraoCodigoBarras;
import com.stfn2.ggas.services.componentes.fabricaboleto.construtoresboletos.uteis.DigitoAutoConferencia;
import com.stfn2.ggas.services.componentes.fabricaboleto.construtoresboletos.uteis.RepresentacaoNumerica;
import com.stfn2.ggas.services.componentes.relatorio.RelatorioComponent;
import com.stfn2.ggas.tools.ToolDate;
import com.stfn2.ggas.tools.ToolNumber;
import com.stfn2.ggas.tools.ToolStr;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public abstract class ConstrutorRelatorioBoleto {
   private static final int CONSTANTE_NUMERO_OITO = 8;
   private static final String NUMERO_DOC_PAGAMENTO = "numeroDocPagamento";
   private static final String NUMERO_CODIGO = "numeroCodigo";
   private static final String SEPARADOR_CEDENTE = ", CNPJ: ";
   private static final String ZERO_MONETARIO = "0,00";

   private static final String CODIGO_BARRA = "codigoBarra";
   private static final String VALOR_TOTAL_BOLETO = "valorTotalBoleto";
   private static final String CARTEIRA = "carteira";
   private static final String MORA_MULTA = "moraMulta";
   private static final String OUTROS_ACRESCIMOS = "outrosAcrescimos";
   private static final String DESCONTOS_ABATIMENTOS = "descontosAbatimentos";
   private static final String OUTRA_DEDUCOES = "outraDeducoes";
   private static final String DATA_PROCESSAMENTO = "dataProcessamento";
   private static final String DATA_VENCIMENTO = "dataVencimento";
   private static final String DATA_DOCUMENTO = "dataDocumento";
   private static final String ENDERECO_CLIENTE = "enderecoCliente";
   private static final String NOME_CLIENTE_BOLETO = "nomeClienteBoleto";
   private static final String NOME_BANCO = "nomeBanco";
   private static final String NUMERO_BANCO = "numeroBanco";
   private static final String IMAGEM_LOGO_BANCO = "imagemLogoBanco";
   private static final String CEDENTE = "cedente";
   private static final String FILTRO_PRINCIPAL = "principal";
   private static final String VALOR_DOCUMENTO = "valorDocumento";
   private static final String AGENCIA_CODIGO = "agenciaCodigo";
   private static final String NUMERO_DOCUMENTO = "numeroDocumento";
   private static final int DUAS_CASAS_DECIMAIS = 2;
   private static final int TRES_ZEROS_A_ESQUERDA = 3;
   private static final String ESPECIE_TITULO = "especieTitulo";
   private static final String CPF_CNPJ = "cpfCnpj";

   public static final String NOSSO_NUMERO = "nossoNumero";
   public static final String LOCAL_PAGAMENTO = "localPagamento";

   protected DocumentoCobranca documentoCobranca;
   private Map<String, Object> parametros;
   private ArrecadadorContratoConvenio arrecadador;
   private ContaBancaria conta;

   ApplicationContext context = ApplicationContextProvider.getApplicationContext();

   private final ConstanteSistemaService constanteSistemaService = context.getBean(ConstanteSistemaService.class);
   private EmpresaService empresaService = context.getBean(EmpresaService.class);
   private FaturaService faturaService = context.getBean(FaturaService.class);

   /**
    * Constrói uma instância desta classe obtendo a conta e o arrecadador relativos
    * ao convênio bancário. Os dados usados em vários campos que esta classe
    * preenche são extraídos do {@link DocumentoCobranca} passado como argumento no
    * construtor.
    *
    * @param documentoCobranca o documento de cobrança com dados para o relatório,
    *                          não pode ser nulo
    * @throws NegocioException Em caso de erro na consulta do convênio
    */
   public ConstrutorRelatorioBoleto(DocumentoCobranca documentoCobranca) {
      this.arrecadador = FabricaConstrutorBoleto.convenioParaBoleto(documentoCobranca);
      this.conta = this.arrecadador.getContaConvenio();
      this.documentoCobranca = documentoCobranca;
      this.parametros = Maps.newHashMap();
   }

   /**
    * Preenche o campo nosso número.
    *
    * @return esta instância
    */
   public ConstrutorRelatorioBoleto preencherNossoNumero() {
      Integer codigoCarteira = arrecadador.getArrecadadorCarteiraCobranca().getNumero();
      StringBuilder construtorNossoNumero = new StringBuilder();
      construtorNossoNumero.append(conta.getAgencia().getCodigo());
      construtorNossoNumero.append(conta.getNumeroConta());
      construtorNossoNumero.append(String.valueOf(codigoCarteira));
      construtorNossoNumero.append(String.valueOf(ToolStr
              .adicionarZerosEsquerdaNumero(documentoCobranca.getNossoNumero().toString(), CONSTANTE_NUMERO_OITO)));
      String dacNossoNumero = String.valueOf(DigitoAutoConferencia.modulo10(construtorNossoNumero.toString()));
      parametros.put(NOSSO_NUMERO, documentoCobranca.getNossoNumero().toString());

      return this;
   }

   /**
    * Preenche o campo local de pagamento
    *
    * @return esta instância
    */
   public abstract ConstrutorRelatorioBoleto preencherLocalDePagamento();

   /**
    * Preenche o campo código da agência e o número da conta com dígito de
    * autoconferência.
    *
    * @return esta instância
    */
   public ConstrutorRelatorioBoleto preencherAgenciaCodigo() {
      parametros.put(AGENCIA_CODIGO, conta.getAgencia().getCodigo() + Constantes.STRING_BARRA + conta.getNumeroConta()
              + Constantes.STRING_HIFEN + conta.getNumeroDigito());
      return this;
   }

   /**
    * Preenche o campo espécie do documento.
    *
    * @return esta instância
    */
   public ConstrutorRelatorioBoleto preencherEspecieDocumento() {

      FaturaGeral faturaGeral = this.documentoCobranca.getItens().iterator().next().getFaturaGeral();

      if (faturaGeral == null) {
         parametros.put(ESPECIE_TITULO, "DM");
      } else {
         parametros.put(ESPECIE_TITULO,
                 this.documentoCobranca.getItens().iterator().next().getFaturaGeral().getFaturaAtual()
                         .getListaFaturaItens().iterator().next().getRubrica().getFinanciamentoTipo()
                         .getEspecieTitulo());
      }

      return this;
   }

   /**
    * Preenche o campo número do documento.
    *
    * @return esta instância
    */
   public ConstrutorRelatorioBoleto preencherNumeroDocumento() {
      parametros.put(NUMERO_DOCUMENTO, String.valueOf(this.documentoCobranca.getId()));
      return this;
   }

   /**
    * Preenche o campo valor do documento.
    *
    * @return esta instância
    * @throws NegocioException em caso de erro no cálculo do valor total
    */
   public ConstrutorRelatorioBoleto preencherValorDocumento() throws NegocioException {
      parametros.put(VALOR_DOCUMENTO, calcularSomatorio());
      return this;
   }

   /**
    * Preenche o campo número código.
    *
    * @return esta instância
    * @throws NegocioException the negocio exception
    */
   public ConstrutorRelatorioBoleto preencherNumeroCodigoBarras() throws NegocioException {
      CodigoBarras codigoBarras = this.gerarCodigoBarras();
      parametros.put(CODIGO_BARRA, codigoBarras.toString());
      parametros.put(NUMERO_CODIGO, new RepresentacaoNumerica(codigoBarras).toString());
      return this;
   }

   /**
    * Preenche o campo cedente.
    *
    * @return esta instância
    * @throws NegocioException em caso de erro na consulta por empresa
    */
   public ConstrutorRelatorioBoleto preencherCedente() throws NegocioException {

      //Map<String, Object> filtro = new HashMap<>();
      //filtro.put(FILTRO_PRINCIPAL, Boolean.TRUE);
      //List<Empresa> listaEmpresas = empresaService.consultarDadosEmpresas(filtro);
      Empresa empresaPrincipal = empresaService.obterEmpresaPrincipal();
      parametros.put(CEDENTE, empresaPrincipal.getCliente().getNome() + SEPARADOR_CEDENTE
              + empresaPrincipal.getCliente().getCnpjFormatado());
      return this;
   }

   /**
    * Preenche o campo imagem de logomarca do banco.
    *
    * @return esta instância
    */
   public ConstrutorRelatorioBoleto preencherImagemLogoBanco() {
      parametros.put(IMAGEM_LOGO_BANCO, Constantes.URL_LOGOMARCA_BANCO
              + arrecadador.getArrecadadorContrato().getArrecadador().getBanco().getId());
      return this;
   }

   /**
    * Preenche o campo número do banco.
    *
    * @return esta instância
    */
   public ConstrutorRelatorioBoleto preencherNumeroBanco() {
      String codigoBanco = arrecadador.getArrecadadorContrato().getArrecadador().getBanco().getCodigoBanco();
      if ("707".equals(codigoBanco)) {
         codigoBanco = "341";
      }
      codigoBanco = ToolStr.adicionarZerosEsquerdaNumero(codigoBanco, TRES_ZEROS_A_ESQUERDA);
      String dacCodigoBanco = String.valueOf(DigitoAutoConferencia.modulo11(codigoBanco));
      parametros.put(NUMERO_BANCO, codigoBanco + Constantes.STRING_HIFEN + dacCodigoBanco);
      return this;
   }

   /**
    * Preenche o campo nome do banco.
    *
    * @return esta instância
    */

   public ConstrutorRelatorioBoleto preencherCnpjCpf() {
      parametros.put(CPF_CNPJ, documentoCobranca.getCliente().getNumeroCpfCnpj());
      return this;
   }

   /**
    * Preenche o campo nome do cliente.
    *
    * @return esta instância
    */
   public ConstrutorRelatorioBoleto preencherNomeClienteBoleto() {
      parametros.put(NOME_CLIENTE_BOLETO, documentoCobranca.getCliente().getNome());
      return this;
   }

   /**
    * Preenche o campo endereço do cliente.
    *
    * @return esta instância
    */
   public ConstrutorRelatorioBoleto preencherEnderecoCliente() {
      if (documentoCobranca.getCliente().getEnderecoPrincipal() != null) {
         parametros.put(ENDERECO_CLIENTE,
                 documentoCobranca.getCliente().getEnderecoPrincipal().getEnderecoFormatado());
      }
      return this;
   }

   /**
    * Preenche o campo código de barras.
    *
    * @return esta instância
    * @throws NegocioException
    */
   private CodigoBarras gerarCodigoBarras() throws NegocioException {
      return FabricaCodigoBarras.novoCodigoBarras(new DadosPadraoCodigoBarras(this.documentoCobranca));
   }

   /**
    * Preenche o campo data do documento.
    *
    * @return esta instância
    */
   public ConstrutorRelatorioBoleto preencherDataDocumento() {
      parametros.put(DATA_DOCUMENTO,
              ToolDate.converterDataParaStringSemHora(documentoCobranca.getDataEmissao(), Constantes.FORMATO_DATA_BR));
      return this;
   }

   /**
    * Preenche o campo data do vencimento.
    *
    * @return esta instância
    */
   public ConstrutorRelatorioBoleto preencherDataVencimento() {
      parametros.put(DATA_VENCIMENTO,
              ToolDate.converterDataParaStringSemHora(documentoCobranca.getDataVencimento(), Constantes.FORMATO_DATA_BR));
      return this;
   }

   /**
    * Preenche o campo instruções.
    *
    * @return esta instância
    */
   public ConstrutorRelatorioBoleto preencherInstrucoes() {
      return this;
   }

   /**
    * Preenche o campo data de processamento.
    *
    * @return esta instância
    */
   public ConstrutorRelatorioBoleto preencherDataProcessamento() {
      parametros.put(DATA_PROCESSAMENTO, ToolDate.converterDataParaStringSemHora(LocalDate.now(),
              Constantes.FORMATO_DATA_BR));
      return this;
   }

   /**
    * Preenche o campo outras deduções.
    *
    * @return esta instância
    */
   public ConstrutorRelatorioBoleto preencherOutrasDeducoes() {
      parametros.put(OUTRA_DEDUCOES, ZERO_MONETARIO);
      return this;
   }

   /**
    * Preenche o campo descontos/abatimentos.
    *
    * @return esta instância
    */
   public ConstrutorRelatorioBoleto preencherDescontosAbatimentos() {
      parametros.put(DESCONTOS_ABATIMENTOS, ZERO_MONETARIO);
      return this;
   }

   /**
    * Preenche o campo outros acréscimos.
    *
    * @return esta instância
    */
   public ConstrutorRelatorioBoleto preencherOutrosAcrescimos() {
      parametros.put(OUTROS_ACRESCIMOS, ZERO_MONETARIO);
      return this;
   }

   /**
    * Preenche o campo mora/multa.
    *
    * @return esta instância
    */
   public ConstrutorRelatorioBoleto preencherMoraMulta() {
      parametros.put(MORA_MULTA, ZERO_MONETARIO);
      return this;
   }

   /**
    * Preenche o campo carteira.
    *
    * @return esta instância
    */
   public ConstrutorRelatorioBoleto preencherCarteira() {
      parametros.put(CARTEIRA, arrecadador.getArrecadadorCarteiraCobranca().getNumero().toString());
      return this;
   }

   /**
    * Preenche o campo valor total.
    *
    * @return esta instância
    * @throws NegocioException em caso de erro no cálculo do valor total
    */
   public ConstrutorRelatorioBoleto preencherValorTotalBoleto() throws NegocioException {
      parametros.put(VALOR_TOTAL_BOLETO, ToolNumber.converterCampoValorDecimalParaString("", documentoCobranca.getValorTotal()
              , Constantes.LOCALE_PADRAO, DUAS_CASAS_DECIMAIS));
      return this;
   }

   /**
    * Preenche o campo numero do documento de pagamento
    *
    * @return esta instância
    */
   public ConstrutorRelatorioBoleto preencherNumeroDocumentoPagamento() {
      parametros.put(NUMERO_DOC_PAGAMENTO, String.valueOf(documentoCobranca.getId()));
      return this;
   }

   public ConstrutorRelatorioBoleto preencherNomeBanco() {
      parametros.put(NOME_BANCO, arrecadador.getArrecadadorContrato().getArrecadador().getBanco().getNome());
      return this;
   }

   /**
    * Gera um novo boleto bancário em PDF consolidando todos os campos preenchidos
    * usando este objeto.
    *
    * @return o relatório gerado
    * @throws NegocioException Em caso de erro na geração do arquivo, ou na
    *                          consulta pelo arquivo do relatório
    */
   public byte[] arrayBytes() throws NegocioException {
      return RelatorioComponent.gerarRelatorioPDF(ImmutableSet.of().asList(), parametros, obterNomeArquivoRelatorio());
   }

   /**
    * @return o mapa de parametros;
    */
   public Map<String, Object> mapaParametros() {
      return this.parametros;
   }

   /**
    * Obter nome arquivo relatorio.
    *
    * @return the string
    * @throws NegocioException the negocio exception
    */
   private String obterNomeArquivoRelatorio() {

      return constanteSistemaService
              .obterConstantePorCodigo(Constantes.RELATORIO_BOLETO).getValor();
   }

   /**
    * Calcular somatorio.
    *
    * @return the big decimal
    * @throws NegocioException the negocio exception
    */
   private String calcularSomatorio() throws NegocioException {
      BigDecimal somatorio = BigDecimal.ZERO;

      if (documentoCobranca != null && documentoCobranca.getId() > 0
              && documentoCobranca.getItens().iterator().next().getFaturaGeral() != null) {
         for (DocumentoCobrancaItem item : documentoCobranca.getItens()) {
            somatorio = somatorio.add(faturaService.calcularSaldoFatura(true, item.getFaturaGeral().getFaturaAtual(),
                    documentoCobranca.getDataVencimento(), true, true).getSecond());
         }
      }else{
         somatorio = documentoCobranca.getValorTotal();
      }

      return ToolNumber.converterCampoValorDecimalParaString("", somatorio, Constantes.LOCALE_PADRAO, DUAS_CASAS_DECIMAIS);
   }
}
