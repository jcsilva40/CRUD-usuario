package com.stfn2.ggas.services.componentes.faturamento;

import com.stfn2.ggas.config.exceptions.types.GGASException;
import com.stfn2.ggas.constante.Constantes;
import com.stfn2.ggas.core.models.DadosAuditoria;
import com.stfn2.ggas.domain.*;
import com.stfn2.ggas.domain.enumTypes.TipoOperacaoEnum;
import com.stfn2.ggas.services.*;
import com.stfn2.ggas.services.componentes.faturamento.uteis.ConstantesFatura;
import com.stfn2.ggas.services.componentes.faturamento.vo.NfeVO;
import com.stfn2.ggas.tools.ToolDate;
import com.stfn2.ggas.tools.ToolFile;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@Component
public class PopularParametrosRelatorioFatura {

   private final ClienteService clienteService;
   private final ContratoService contratoService;
   private final FaturaImpressaoService faturaImpressaoService;
   private ConstanteSistemaService constanteSistemaService;
   private DocumentoFiscalService documentoFiscalService;
   private DocumentoCobrancaService documentoCobrancaService;
   private EmpresaService empresaService;
   private RotaService rotaService;
   private FaturamentoGrupoService faturamentoGrupoService;
   private FeriadoService feriadoService;

   PopularParametrosRelatorioFatura(ConstanteSistemaService constanteSistemaService,
                                    DocumentoFiscalService documentoFiscalService,
                                    DocumentoCobrancaService documentoCobrancaService,
                                    EmpresaService empresaService,
                                    RotaService rotaService,
                                    FaturamentoGrupoService faturamentoGrupoService,
                                    ClienteService clienteService, ContratoService contratoService,
                                    FeriadoService feriadoService,
                                    FaturaImpressaoService faturaImpressaoService) {
      this.constanteSistemaService = constanteSistemaService;
      this.documentoFiscalService = documentoFiscalService;
      this.documentoCobrancaService = documentoCobrancaService;
      this.empresaService = empresaService;
      this.rotaService = rotaService;
      this.faturamentoGrupoService = faturamentoGrupoService;
      this.clienteService = clienteService;
      this.contratoService = contratoService;
      this.feriadoService = feriadoService;
      this.faturaImpressaoService = faturaImpressaoService;
   }

   private Map<String, Object> popularMapaParametrosVOPrincipal(Fatura fatura, DadosAuditoria dadosAuditoria,
                                                                Boolean ultimaFatura, DocumentoFiscal documentoFiscal) throws GGASException {

      Map<String, Object> retorno = new HashMap<>();

      DocumentoFiscal documentoFiscalOriginal = documentoFiscal;
      DocumentoFiscal documentoFiscalLocal = documentoFiscal;
      TipoOperacaoEnum tipoOperacaoSaida = TipoOperacaoEnum.SAIDA;

      if (fatura != null) {

         Contrato contratoAtual = fatura.getContratoAtual();

         List<DocumentoCobranca> listaDocumentoCobranca = documentoCobrancaService
                 .consultarDocumentoCobrancaPelaFatura(fatura.getId(), null);
         DocumentoCobranca documentoCobranca = null;
         Iterator<DocumentoCobranca> iterator = listaDocumentoCobranca.iterator();

         documentoCobranca = documentoCobrancaService.obterDocumentoCobrancaRecente(iterator);


         Empresa empresa = empresaService.obterEmpresaPrincipal();

         if (empresa != null) {
            Cliente cdl = empresa.getCliente();

            retorno.put("url", "");

            if (documentoFiscalLocal == null) {
               documentoFiscalLocal = documentoFiscalService.obterDocumentoFiscalPorFatura(fatura, tipoOperacaoSaida);
            }

            if (documentoFiscalLocal != null) {
               /*
                * if (documentoFiscalLocal.getNumero() != null) {
                * retorno.put("numeroNotaFiscal",
                * String.valueOf(documentoFiscalLocal.getNumero())); }
                *
                * if (!StringUtils.isEmpty(documentoFiscalLocal.
                * getDescricaoSerie())) { retorno.put("numeroSerie",
                * documentoFiscalLocal.getDescricaoSerie()); }
                *
                * if
                * (!StringUtils.isEmpty(documentoFiscalLocal.getChaveAcesso
                * ())) { retorno.put("chaveAcesso",
                * documentoFiscalLocal.getChaveAcesso()); } if
                * (documentoFiscalLocal.getNumeroProtocolo() != null &&
                * !StringUtils.isEmpty(documentoFiscalLocal.
                * getNumeroProtocolo())) { retorno.put("numeroProtocolo",
                * documentoFiscalLocal.getNumeroProtocolo()); } else {
                * retorno.put("numeroProtocolo", ""); }
                */

               if (documentoFiscalLocal.getArquivoXml() != null) {
                  try {
                     NfeVO nfe = ToolFile.lerXmlNfe(documentoFiscalLocal.getArquivoXml());
                     retorno.put("chaveAcesso", nfe.getChaveAcesso());
                     retorno.put("numeroSerie", nfe.getSerie().toString());
                     retorno.put("numeroProtocolo", nfe.getNumeroProtocoloEnvio());
                  } catch (ParserConfigurationException | SAXException | IOException e) {
                     System.out.print("Erro ao tentar ler arquivo XML.");
                  }
               }

               if (documentoFiscalLocal.getNaturezaOperacaoCFOP() != null && !StringUtils.isEmpty(
                       documentoFiscalLocal.getNaturezaOperacaoCFOP().getNaturezaOperacao().getDescricao())) {
                  retorno.put(ConstantesFatura.NATUREZA_OPERACAO_DESC,
                          documentoFiscalLocal.getNaturezaOperacaoCFOP().getNaturezaOperacao().getDescricao());
               } else {
                  retorno.put(ConstantesFatura.NATUREZA_OPERACAO_DESC, "");
               }

            }

            retorno.put(ConstantesFatura.IMAGEM, Constantes.URL_LOGOMARCA_GGAS);

            if (!StringUtils.isEmpty(fatura.getCicloReferenciaFormatado())) {
               retorno.put("referenciaCiclo", fatura.getCicloReferenciaFormatado());
            }

            if (!StringUtils.isEmpty(cdl.getNome())) {
               retorno.put("nomeCDL", cdl.getNome());
            }

            if (!StringUtils.isEmpty(cdl.getCnpjFormatado())) {
               retorno.put("cnpjCDL", cdl.getCnpjFormatado());
            }

            if (cdl.getEnderecoPrincipal() != null && cdl.getEnderecoPrincipal().getCep() != null) {
               ClienteEndereco enderecoCDL = cdl.getEnderecoPrincipal();

               if (enderecoCDL != null) {
                  retorno.put("enderecoCDL",
                          enderecoCDL.getEnderecoFormatadoRuaNumeroComplemento().toUpperCase());
               }

               if (enderecoCDL != null && !StringUtils.isEmpty(enderecoCDL.getEnderecoFormatado())) {
                  retorno.put("bairroCidadeEstadoCEPCDL",
                          enderecoCDL.getEnderecoFormatadoCepCidadeEstado().toUpperCase());
               }
            }

            if (!StringUtils.isEmpty(cdl.getInscricaoEstadual())) {
               retorno.put(ConstantesFatura.INSCRICAO_ESTADUAL_CDL, cdl.getInscricaoEstadual());
            }

            /*if (cdl.getFones() != null && !cdl.getFones().isEmpty()) {
               // segundo henrique cantarelli
               // deve pegar o primeiro da
               // lista
               ClienteFone fone = cdl.getFones().iterator().next();

               if (fone.getRamal() == null) {
                  retorno.put("telefone",
                          "(" + fone.getCodigoDDD().toString() + ") " + fone.getNumero().toString());
               } else {
                  retorno.put("telefone", "(" + fone.getCodigoDDD().toString() + ") "
                          + fone.getNumero().toString() + " R-" + fone.getRamal());
               }
            }*/

            if (!StringUtils.isEmpty(cdl.getInscricaoEstadual())) {
               retorno.put(ConstantesFatura.INSCRICAO_ESTADUAL_CDL, cdl.getInscricaoEstadual());
            }
         }

         if (fatura.getNaturezaOperacaoCfop() != null) {
            retorno.put(ConstantesFatura.NATUREZA_OPERACAO_DESC,
                    fatura.getNaturezaOperacaoCfop().getNaturezaOperacao().getDescricao());
         }

         if (documentoFiscalLocal != null) {
            retorno.put("dtEmissao", ToolDate.converterDataParaStringSemHora(documentoFiscalLocal.getDataEmissao(),
                    Constantes.FORMATO_DATA_BR));
         } else if (fatura.getDataEmissao() != null) {
            retorno.put("dtEmissao",
                    ToolDate.converterDataParaStringSemHora(fatura.getDataEmissao(), Constantes.FORMATO_DATA_BR));
         }

         if (fatura.getNumeroFatura() != null) {
            retorno.put("numeroNotaFiscal", String.valueOf(fatura.getNumeroFatura()));
         } else if (documentoFiscalLocal != null) {
            if (documentoFiscalLocal.getNumero() != null) {
               retorno.put("numeroNotaFiscal", String.valueOf(documentoFiscalLocal.getNumero()));
            }
         } else {
            retorno.put("numeroNotaFiscal", String.valueOf(fatura.getId()));
         }

       /*  if (fatura.getRota() != null) {

            Map<String, Integer> referenciaCicloPosterior = null;
            if (faturamentoGrupoService
                    .isPeriodicidadeGrupoMultiplosCiclos(fatura.getRota().getFaturamentoGrupo())) {
               referenciaCicloPosterior = ToolDate.gerarProximaReferenciaCiclo(
                       fatura.getRota().getFaturamentoGrupo().getAnoMesFaturamento(),
                       fatura.getRota().getFaturamentoGrupo().getNumeroCiclo(),
                       fatura.getRota().getFaturamentoGrupo().getPeriodicidade().getQuantidadeDias(),
                       fatura.getDataEmissao());
            } else {
               referenciaCicloPosterior = ToolDate.rolarReferenciaCiclo(
                       fatura.getRota().getFaturamentoGrupo().getAnoMesFaturamento(),
                       fatura.getRota().getFaturamentoGrupo().getNumeroCiclo(),
                       fatura.getRota().getFaturamentoGrupo().getPeriodicidade().getQuantidadeCiclos());
            }

            List<CronogramaRota> cronogramaRotaPosterior = rotaService.obterAtributosCronogramasRota(
                    fatura.getRota(), String.valueOf(referenciaCicloPosterior.get(ConstantesFatura.CICLO)),
                    String.valueOf(referenciaCicloPosterior.get(ConstantesFatura.REFERENCIA_FATURA)));
            for (CronogramaRota cronogramaRota : cronogramaRotaPosterior) {
               if (cronogramaRota.getCronogramaAtividadeFaturamento().getAtividadeSistema()
                       .getId() == AtividadeSistema.CODIGO_ATIVIDADE_REALIZAR_LEITURA) {
                  retorno.put("dtPrevProxLeitura", ToolDate.converterDataParaStringSemHora(
                          cronogramaRota.getDataPrevista(), Constantes.FORMATO_DATA_BR));
                  break;
               }
            }
         }*/

         if (fatura.getDataVencimento() != null) {
            if (documentoFiscalOriginal != null && documentoFiscalOriginal.getTipoOperacao()
                    .getId() != tipoOperacaoSaida.getId()) {
               retorno.put("dtVencimento", "");
            } else {
               retorno.put("dtVencimento", ToolDate.converterDataParaStringSemHora(fatura.getDataVencimento(),
                       Constantes.FORMATO_DATA_BR));
            }
         }

         if (fatura.getAnoMes() != null) {
            retorno.put("mesAno", ToolDate.formatarAnoMes(fatura.getAnoMes()));
         }

         if (fatura.getCliente() != null) {

            Cliente cliente = null;

            if (contratoAtual != null && contratoAtual.getClienteAssinatura() != null) {
               cliente = contratoAtual.getClienteAssinatura();
            } else {
               cliente = fatura.getCliente();
            }

            Map<String, Object> mapaValoresCEP = clienteService
                    .obterDadosRelatorioCodigoBarrasEndereco(cliente);
            if (mapaValoresCEP != null) {
               for (Entry<String, Object> entry : mapaValoresCEP.entrySet()) {
                  retorno.put(entry.getKey(), entry.getValue());
               }
            }

            if (!StringUtils.isEmpty(cliente.getNome())) {
               retorno.put(ConstantesFatura.NOME_CLIENTE, cliente.getNome());
            }

            String parametroNacionalidade = constanteSistemaService
                    .obterValorConstanteSistemaPorCodigo(Constantes.C_NACIONALIDADE_ESTRANGEIRA);
            Long codigoNacionalidadeEstrangeira = Long.valueOf(parametroNacionalidade);

            if ((cliente.getNacionalidade()) != null
                    && (cliente.getNacionalidade().getId() == codigoNacionalidadeEstrangeira)) {
               retorno.put(ConstantesFatura.DOC_IDENTIFICADOR, cliente.getCpf());
               retorno.put("nacionalidadeCliente", "Estrangeira");
               retorno.put(ConstantesFatura.PESSOA_FISICA_CONTROLE_FLUXO, Boolean.FALSE);
            } else {
               if (!StringUtils.isEmpty(cliente.getCpfFormatado())) {
                  retorno.put(ConstantesFatura.DOC_IDENTIFICADOR, cliente.getCpfFormatado());
                  retorno.put(ConstantesFatura.CPJ_CNPJ, cliente.getCpfFormatado());
                  retorno.put(ConstantesFatura.PESSOA_FISICA_CONTROLE_FLUXO, Boolean.TRUE);
               } else if (!StringUtils.isEmpty(cliente.getCnpjFormatado())) {
                  retorno.put(ConstantesFatura.DOC_IDENTIFICADOR, cliente.getCnpjFormatado());
                  retorno.put(ConstantesFatura.CPJ_CNPJ, cliente.getCnpjFormatado());
                  retorno.put(ConstantesFatura.PESSOA_FISICA_CONTROLE_FLUXO, Boolean.FALSE);
               }
               retorno.put("nacionalidadeCliente", "Brasileira");
            }

            // dados do cliente no boleto
            if (!StringUtils.isEmpty(cliente.getNome())) {
               Boolean pessoaFisicaControleFluxo = (Boolean) retorno.get(ConstantesFatura.PESSOA_FISICA_CONTROLE_FLUXO);
               String nomeComDocIdentificacao = cliente.getNome();
               String docIdentificador = (String) retorno.get(ConstantesFatura.DOC_IDENTIFICADOR);
               if (pessoaFisicaControleFluxo && docIdentificador != null) {
                  nomeComDocIdentificacao = nomeComDocIdentificacao + ", CPF: " + docIdentificador;
               } else {
                  nomeComDocIdentificacao = nomeComDocIdentificacao + ", CNPJ: " + docIdentificador;
               }
               retorno.put("nomeClienteSacado", nomeComDocIdentificacao.toUpperCase());
            }

          /*  if (fatura.getPontoConsumo() != null) {
               PontoConsumo pontoConsumo = Fachada.getInstancia()
                       .obterPontoConsumo(fatura.getPontoConsumo().getId(), "cep", ConstantesFatura.QUADRA_FACE);
               Cep cepPontoConsumo = pontoConsumo.getCepTabela();
               //QuadraFace quadraFace = pontoConsumo.getQuadraFace();

               List<Contrato> listaContrato = contratoService
                       .obterListaContratoPorCliente(fatura.getCliente().getId());

               ContratoPontoConsumo contratoPc = contratoService
                       .consultarContratoPontoConsumoPorPontoConsumo(fatura.getPontoConsumo().getId());

               int contratoCliente = 0; //Stefanini: correção para pontos de consumo sem contrato ativo.

               if(contratoPc != null){
                  contratoCliente = contratoPc.getContrato().getListaContratoCliente().size();
               }

               if (listaContrato.size() > 1 || contratoCliente > 1) {
                  retorno.put("contratoComMaisPontoConsumo", true);
               } else {
                  retorno.put("contratoComMaisPontoConsumo", false);
               }

               if (cepPontoConsumo != null) {
                  String bairroCidadeEstado = cepPontoConsumo.getBairro() + "-"
                          + cepPontoConsumo.getMunicipio().getDescricao() + "/" + cepPontoConsumo.getUf();
                  retorno.put("bairroCidadeEstadoSacado", bairroCidadeEstado);

                  StringBuilder ruaNumeroComplementoCep = new StringBuilder();
                  if (!StringUtils.isEmpty(cepPontoConsumo.getLogradouro())) {
                     if (!StringUtils.isEmpty(cepPontoConsumo.getTipoLogradouro())) {
                        ruaNumeroComplementoCep.append(cepPontoConsumo.getTipoLogradouro()).append(" ");
                     }
                     ruaNumeroComplementoCep.append(cepPontoConsumo.getLogradouro());
                  }

                  if (!StringUtils.isEmpty(pontoConsumo.getNumero())) {
                     if (ruaNumeroComplementoCep.length() > 0) {
                        ruaNumeroComplementoCep.append(ConstantesFatura.VIRGULA_ESPACO);
                        ruaNumeroComplementoCep.append(pontoConsumo.getNumero());
                     } else {
                        ruaNumeroComplementoCep.append(pontoConsumo.getNumero());
                     }
                  }

                  *//*if (!StringUtils.isEmpty(quadraFace.getEndereco().getComplemento())) {
                     if (ruaNumeroComplementoCep.length() > 0) {
                        ruaNumeroComplementoCep.append(ConstantesFatura.VIRGULA_ESPACO);
                        ruaNumeroComplementoCep.append(quadraFace.getEndereco().getComplemento());
                     } else {
                        ruaNumeroComplementoCep.append(quadraFace.getEndereco().getComplemento());

                     }
                  }*//*

                  if (ruaNumeroComplementoCep.length() > 0) {
                     ruaNumeroComplementoCep.append(" - CEP: ");
                     ruaNumeroComplementoCep.append(cepPontoConsumo.getNumeroCep());
                  } else {
                     ruaNumeroComplementoCep.append(" CEP: ");
                     ruaNumeroComplementoCep.append(cepPontoConsumo.getNumeroCep());
                  }

                  retorno.put("ruaNumeroComplementoCep", ruaNumeroComplementoCep.toString());
                  retorno.put("nomePontoConsumo", fatura.getPontoConsumo().getDescricao());
               }

               ContratoPontoConsumo contratoPontoConsumo = null;
               if (contratoAtual != null && fatura.getPontoConsumo() != null) {
                  contratoPontoConsumo = contratoService.obterContratoPontoConsumoPorContratoPontoConsumo(
                          contratoAtual.getId(), fatura.getPontoConsumo().getId());

               }

               if (contratoPontoConsumo != null && contratoPontoConsumo.getPontoConsumo() != null) {
                  if (contratoPontoConsumo.getPontoConsumo().getCil() != null) {
                     retorno.put("codigoUnico", contratoPontoConsumo.getPontoConsumo().getCil());
                  } else {
                     retorno.put("codigoUnico",
                             String.valueOf(contratoPontoConsumo.getPontoConsumo().getId()));
                  }
               }

               String endereco = null;
               Cep cep = null;

               endereco = fatura.getPontoConsumo().getLogradouroFormatado();
               cep = fatura.getPontoConsumo().getCepTabela();

               ClienteEndereco enderecoCliente = cliente.getEnderecoPrincipal();
               String logradouro = "";
               String numero = "";
               if (enderecoCliente == null) {
                  logradouro = endereco;
               } else {
                  logradouro = enderecoCliente.getEnderecoLogradouro() + ", " + enderecoCliente.getNumero();
               }

               if (cep.getLogradouro() == null || cep.getLogradouro().isEmpty()) {
                  if (pontoConsumo.getLogradouro() != null) {
                     logradouro = pontoConsumo.getLogradouro() + ", " + pontoConsumo.getNumero();
                  }
               }

               if (endereco != null) {
                  retorno.put("enderecoClienteReport", logradouro);
                  retorno.put("logradouroPontoConsumo", endereco);
               }

               if (cep != null) {
                  if (!StringUtils.isEmpty(cep.getBairro())) {
                     retorno.put("bairroClienteReport", cep.getBairro());
                  }
                  if (!StringUtils.isEmpty(cep.getMunicipio().getDescricao())) {
                     retorno.put("municipioClienteReport", cep.getMunicipio().getDescricao());
                  }
                  if (!StringUtils.isEmpty(cep.getNumeroCep())) {
                     retorno.put("cepClienteReport", cep.getNumeroCep());
                  }

                  if (!StringUtils.isEmpty(cep.getMunicipio().getDescricao())) {
                     retorno.put("municipioPontoConsumo", cep.getMunicipio().getDescricao());
                  }

                  if (!StringUtils.isEmpty(cep.getMunicipio().getDescricao())) {
                     retorno.put("ufPontoConsumo", cep.getUf());
                  }

                  if (!StringUtils.isEmpty(cep.getNumeroCep())) {
                     retorno.put("cepPontoConsumo", cep.getNumeroCep());
                  }

               }

               if (contratoPontoConsumo != null) {

                  EnderecoTemporarioFaturamentoVO enderecoTemporarioFaturamento = controladorEndereco
                          .obterPrinciapaisAtributosEnderecoTemporarioFaturamento(contratoPontoConsumo);

                  if (enderecoTemporarioFaturamento != null && enderecoTemporarioFaturamento.getCep() != null
                          && !enderecoTemporarioFaturamento.getCep().getNumeroCep().isEmpty()
                          && enderecoTemporarioFaturamento.getNumeroImovel() != null) {
                     if (enderecoTemporarioFaturamento.getComplemento() != null) {
                        endereco = enderecoTemporarioFaturamento.getNumeroImovel() + ""
                                + enderecoTemporarioFaturamento.getComplemento();
                     } else {
                        endereco = enderecoTemporarioFaturamento.getNumeroImovel();
                     }
                     cep = enderecoTemporarioFaturamento.getCep();
                  } else if (contratoPontoConsumo.getCep() != null
                          && contratoPontoConsumo.getComplementoEndereco() != null
                          && enderecoTemporarioFaturamento == null) {
                     endereco = contratoPontoConsumo.getEnderecoFormatadoRuaNumeroBairro();
                     cep = contratoPontoConsumo.getCep();
                  } else if (cliente.getEnderecoPrincipal() != null
                          && cliente.getEnderecoPrincipal().getCep() != null
                          && enderecoTemporarioFaturamento == null) {
                     enderecoCliente = cliente.getEnderecoPrincipal();
                     endereco = enderecoCliente.getEnderecoFormatadoRuaNumeroBairro();
                     cep = cliente.getEnderecoPrincipal().getCep();
                  }

               }

               if (enderecoCliente != null) {
                  if (!StringUtils.isEmpty(enderecoCliente.getCep().getNumeroCep())) {
                     retorno.put(ConstantesFatura.CEP_CLIENTE, enderecoCliente.getCep().getNumeroCep());
                  }

                  if (enderecoCliente.getComplemento() != null) {
                     retorno.put("complementoClienteEnd", enderecoCliente.getComplemento());
                  }

                  if (enderecoCliente.getCep().getBairro() != null
                          && !enderecoCliente.getCep().getBairro().equals("Não Informado")) {
                     retorno.put("bairroCliente", enderecoCliente.getCep().getBairro());
                  } else if (pontoConsumo.getComplemento() != null) {
                     retorno.put("bairroCliente", pontoConsumo.getComplemento());
                  }

                  retorno.put("enderecoClienteRuaBairro", enderecoCliente.getEnderecoFormatadoRuaNumeroBairro());

                  if (!StringUtils.isEmpty(cep.getMunicipio().getDescricao())) {
                     retorno.put("municipioCliente", enderecoCliente.getCep().getMunicipio().getDescricao());
                  }

                  if (!StringUtils.isEmpty(cep.getUf())) {
                     retorno.put(ConstantesFatura.UF_CLIENTE, enderecoCliente.getCep().getUf());
                  }

                  if (!StringUtils.isEmpty(enderecoCliente.getCep().getNumeroCep())) {
                     // retorno.put(CEP_CLIENTE, cep.getNumeroCep());
                     retorno.put(ConstantesFatura.CODIGO_BARRA_CEP,
                             ToolUtil.calculoCodigoBarrasEndereco(cep.getNumeroCep()));
                  }
               }
            }*/

            // Obter a data de postagem
            FaturaImpressao faturaImpressao = faturaImpressaoService
                    .obterDataGeracaoFaturaImpressaoPorFatura(fatura.getId());
            LocalDate dtPostagem = null;
            LocalDate dataPostagem = null;

            if (faturaImpressao != null && faturaImpressao.getDataGeracao() != null) {
               dtPostagem = faturaImpressao.getDataGeracao();
            } else {
               dtPostagem = LocalDate.now();
            }

            // Adicionar 2 dias à data.
            dtPostagem = dtPostagem.plusDays(2);

            // Verificar se a data de postagem
            // é um dia útil.

            if (feriadoService.isDiaUtil(dtPostagem, null)) {
               dataPostagem = dtPostagem;
            } else {
               dataPostagem = feriadoService.obterProximoDiaUtil(dtPostagem);
            }

            retorno.put("dataPostagem",
                    ToolDate.converterDataParaStringSemHora(dataPostagem, Constantes.FORMATO_DATA_BR));

            /*
             * codigoCliente
             */
            Map<String, Object> filtro = new HashMap<>();

            filtro.put(ConstantesFatura.CLIENTE, fatura.getCliente().getId());
            if (fatura.getContratoAtual() != null) {
               filtro.put(ConstantesFatura.CONTRATO, fatura.getContratoAtual().getId());
            }
            if (fatura.getPontoConsumo() != null && fatura.getPontoConsumo().getImovel() != null) {
               filtro.put(ConstantesFatura.IMOVEL, fatura.getPontoConsumo().getImovel().getId());
            }
           /* if (fatura.getPontoConsumo() != null && fatura.getPontoConsumo().getImovel() != null
                    && fatura.getPontoConsumo().getImovel().getQuadra() != null
                    && fatura.getPontoConsumo().getImovel().getQuadra().getSetorComercial() != null) {
               filtro.put(ConstantesFatura.SETOR_COMERCIAL,
                       fatura.getPontoConsumo().getImovel().getQuadra().getSetorComercial().getDescricao());
            }*/

          /*  ClienteDebitoAutomatico clienteDebitoAutomatico = null;
            if (fatura.getContratoAtual() != null) {
               clienteDebitoAutomatico = controladorArrecadacao.obterChaveClienteDebitoAutomatico(
                       fatura.getCliente().getId(), fatura.getContratoAtual().getId());
            }

            String identificadorClienteDebito = "";
            if (clienteDebitoAutomatico != null) {
               identificadorClienteDebito = String.valueOf(clienteDebitoAutomatico.getId());
            } else {
               identificadorClienteDebito = String
                       .valueOf(controladorArrecadacao.inserirClienteDebitoAutomatico(fatura, dadosAuditoria));
            }*/

            // DebitoAutomatico debitoAutomatico = controladorArrecadacao
            // .obterDebitoAutomaticoAtivo(clienteDebitoAutomatico.getId());
            //
            // if ("0".equals(identificadorClienteDebito) ||
            // debitoAutomatico != null) {
            // retorno.put(CODIGO_CLIENTE, "");
            // } else {
            // retorno.put(CODIGO_CLIENTE,
            // debitoAutomatico.getNumeroCliente());
            // }

          /*  String codigoCliente = identificadorClienteDebito
                    .concat(String.valueOf(DigitoAutoConferencia.modulo10(identificadorClienteDebito)));
            if ("0".equals(identificadorClienteDebito)) {
               retorno.put(ConstantesFatura.CODIGO_CLIENTE, "");
            } else {
               retorno.put(ConstantesFatura.CODIGO_CLIENTE, "");
            }

            if(!StringUtils.isEmpty(fatura.getClienteInscricaoEstadual())){
               retorno.put("inscricaoEstadualCliente", "IE " + fatura.getClienteInscricaoEstadual());
            }else if (!StringUtils.isEmpty(cliente.getInscricaoEstadual())) {
               retorno.put("inscricaoEstadualCliente", "IE " + cliente.getInscricaoEstadual());
            }else if (!StringUtils.isEmpty(cliente.getRg())) {
               retorno.put("inscricaoEstadualCliente", "RG " + cliente.getRg());
            }
         }

         if (fatura.getValorTotalFormatado() != null) {
            retorno.put(ConstantesFatura.VALOR_PAGAR,
                    Util.converterCampoCurrencyParaString(fatura.getValorTotal(), Constantes.LOCALE_PADRAO));
         }

         EntidadeConteudo formaCobranca = controladorEntidadeConteudo.obter(Long.valueOf(controladorConstanteSistema
                 .obterValorConstanteSistemaPorCodigo(Constantes.C_FORMA_COBRANCA_BOLETO_BANCARIO)));

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

            if (contratoAtual.getArrecadadorContratoConvenio().getTipoConvenio().getId() == formaCobranca
                    .getId()) {
               retorno.put(ConstantesFatura.INDICADOR_BOLETO, Boolean.TRUE);
               indicadorBoleto = Boolean.TRUE;
            } else {
               retorno.put(ConstantesFatura.INDICADOR_BOLETO, Boolean.FALSE);
               EntidadeConteudo debitoAutomatico = CpUtil
                       .obterEntidadePorConstante(Constantes.C_TIPO_CONVENIO_DEBITO_AUTOMATICO);
               if (contratoAtual.getArrecadadorContratoConvenio().getTipoConvenio()
                       .getId() == debitoAutomatico.getId()) {
                  retorno.put("indicadorDebitoAutomatico", Boolean.TRUE);
                  retorno.put("agenciaCodigo", contratoAtual.getAgencia());
               }
            }
         } else {
            if (controladorArrecadacao.obterArrecadadorContratoConvenioParaBoletoBancario().getTipoConvenio()
                    .getId() == formaCobranca.getId()) {
               retorno.put(ConstantesFatura.INDICADOR_BOLETO, Boolean.TRUE);
               indicadorBoleto = Boolean.TRUE;
            } else {
               retorno.put(ConstantesFatura.INDICADOR_BOLETO, Boolean.FALSE);
            }
         }
         documentoCobranca.setNossoNumero(fatura.getId());
         if (documentoFiscalOriginal != null && documentoFiscalOriginal.getTipoOperacao()
                 .getId() != tipoOperacaoSaida.getId()) {
            retorno.put(ConstantesFatura.INDICADOR_BOLETO, null);
         } else if (indicadorBoleto && documentoCobranca != null) {
            documentoCobranca.setNossoNumero(fatura.getId());
            BigDecimal valor = documentoCobranca.getValorTotal();
            documentoCobranca.setValorTotal(valor.divide(BigDecimal.ONE, 2, RoundingMode.HALF_UP));

            DocumentoCobranca docCobrancaTemp = (DocumentoCobranca) SerializationUtils.clone(documentoCobranca);
            BigDecimal ISSTOTAL = CpUtil.getValorTotalISS(fatura);
            if (ISSTOTAL.compareTo(BigDecimal.ZERO) > 0) {
               docCobrancaTemp.setValorTotal(docCobrancaTemp.getValorTotal().subtract(ISSTOTAL));
            }
            retorno.putAll(FabricaConstrutorBoleto.novoConstrutorBoleto(docCobrancaTemp)
                    .preencherNumeroCodigoBarras().preencherNumeroDocumentoPagamento().preencherNomeClienteBoleto()
                    .preencherEnderecoCliente().preencherDataDocumento().preencherValorDocumento()
                    .preencherLocalDePagamento().preencherImagemLogoBanco().preencherNumeroBanco()
                    .preencherCedente().preencherAgenciaCodigo().preencherNumeroDocumento()
                    .preencherEspecieDocumento().preencherDataProcessamento().preencherNossoNumero()
                    .preencherCarteira().preencherDescontosAbatimentos().preencherOutrasDeducoes()
                    .preencherMoraMulta().preencherOutrosAcrescimos().preencherValorTotalBoleto().mapaParametros());

            ToolsFaturamento.persitirCodigoBarras(retorno, documentoCobranca);

            retorno.put("dataVencimentoDocumentoCobranca", ToolDate.converterDataParaStringSemHora(
                    documentoCobranca.getDataVencimento(), Constantes.FORMATO_DATA_BR));
            Contrato contratoPai = fatura.getContratoPai();
            Fatura faturaComCliente = fatura; //(Fatura) this.obter(fatura.getId(), ConstantesFatura.CLIENTE);
            Cliente cliente = faturaComCliente.getCliente();
            if (contratoPai != null) {
               ClienteDebitoAutomatico clienteDebitoAutomatico = controladorArrecadacao
                       .obterClienteDebitoAutomatico(cliente.getId(), contratoPai.getId());
               if (clienteDebitoAutomatico != null) {
                  String identificadorClienteDebito = String.valueOf(clienteDebitoAutomatico.getId());
                  retorno.put("codigoDebitoAutomatico", identificadorClienteDebito
                          .concat(String.valueOf(DigitoAutoConferencia.modulo10(identificadorClienteDebito))));
               }
            }
         } else {
            retorno.put(ConstantesFatura.INDICADOR_BOLETO, null);
         }

         if (contratoAtual != null) {

            if (contratoAtual.getAgrupamentoCobranca() != null) {
               // Possivel Implementação Futura.
            }

            if ((contratoAtual.getAgrupamentoConta() != null) && (contratoAtual.getAgrupamentoConta())) {
               // Possivel Implementação Futura.
            }
         }

         if (ultimaFatura != null && !ultimaFatura) {
            retorno.put(ConstantesFatura.INDICADOR_BOLETO, null);
         }

         if (fatura.getPontoConsumo() != null) {
            retorno.put("unidadeConsumidora", fatura.getPontoConsumo().getDescricao());
         }

         if (contratoAtual != null && contratoAtual.getClienteAssinatura() != null) {
            retorno.put("razaoSocial", contratoAtual.getClienteAssinatura().getNome());
         }

      }*/

            return retorno;
         }
      }
      return retorno;
   }
}