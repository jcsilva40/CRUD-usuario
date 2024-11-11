package com.stfn2.ggas.services;

import com.stfn2.ggas.config.exceptions.types.NegocioException;
import com.stfn2.ggas.config.exceptions.types.ValidationErrorException;
import com.stfn2.ggas.config.exceptions.utils.FieldMessage;
import com.stfn2.ggas.constante.Constantes;
import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.core.utils.Pair;
import com.stfn2.ggas.domain.*;
import com.stfn2.ggas.domain.dtos.FaturaDTO;
import com.stfn2.ggas.domain.dtos.basic.FaturaBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.FaturaFilterDTO;
import com.stfn2.ggas.domain.enumTypes.SituacaoPagamentoEnum;
import com.stfn2.ggas.domain.enumTypes.StatusFaturaEnum;
import com.stfn2.ggas.repositories.FaturaRepository;
import com.stfn2.ggas.services.componentes.calculojurosmulta.CalcularJurosMultasComponent;
import com.stfn2.ggas.services.componentes.faturamento.uteis.ConstantesFatura;
import com.stfn2.ggas.services.componentes.faturamento.vo.RelatorioResumoFaturaRubricaVO;
import com.stfn2.ggas.services.componentes.faturamento.vo.SubRelatorioGrupoFaturamentoVO;
import com.stfn2.ggas.services.componentes.relatorio.RelatorioComponent;
import com.stfn2.ggas.tools.ToolDate;
import com.stfn2.ggas.tools.ToolNumber;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;

@Service
public class FaturaService extends BaseService<Fatura, FaturaDTO, FaturaBasicDTO, FaturaFilterDTO, FaturaRepository> {

   private final RecebimentoService recebimentoService;
   private final ConstanteSistemaService constanteSistemaService;
   private final CalcularJurosMultasComponent calcularJurosMultasComponent;
   private final ContratoService contratoService;
   private List<FieldMessage> erros;

   @Override
   public Page<FaturaBasicDTO> findAll(FaturaFilterDTO filter, Pageable pageable) {
      Integer anoMes = filter.getAnoMes();
      Integer ciclo = filter.getCiclo();
      Integer numeroFatura = filter.getNumeroFatura();
      BigDecimal valorTotal = null;
      String periodoCiclo = null;
      String pontoConsumoDescricao = filter.getPontoConsumoDescricao();
      String segmentoDescricao = filter.getSegmentoDescricao();
      LocalDate dataEmissao = null;
      LocalDate dataVencimento = null;
      Long statusFatura = filter.getStatusFatura();
      StatusFaturaEnum statusFaturaEnum = null;

      if(!Objects.isNull(statusFatura)){
         statusFaturaEnum = StatusFaturaEnum.toEnum(statusFatura);
      }

      Long situacaoPagamento = filter.getSituacaoPagamento();
      SituacaoPagamentoEnum situacaoPagamentoEnum = null;

      if(!Objects.isNull(situacaoPagamento)){
         situacaoPagamentoEnum = SituacaoPagamentoEnum.toEnum(situacaoPagamento);
      }

      Boolean habilitado = filter.getHabilitado();
      Page<FaturaBasicDTO> result = this.repository.findAll(anoMes, ciclo, numeroFatura, valorTotal, periodoCiclo, pontoConsumoDescricao, segmentoDescricao, dataEmissao, dataVencimento, statusFaturaEnum, situacaoPagamentoEnum, habilitado, pageable).map(projection -> MapperImpl.parseObject(projection, FaturaBasicDTO.class));
      return result;
   }

   @Override
   public FaturaDTO createOrUpdate(FaturaDTO dto) {

      Fatura fatura = getById(dto.getId());
      fatura.setDataEmissao(dto.getDataEmissao());
      fatura.setDataVencimento(dto.getDataVencimento());
      fatura.setPeriodoCiclo(dto.getPeriodoCiclo());

      return entityToDTO(fatura);
   }

   @Override
   public void delete(Long id) {
      erros = new ArrayList<>();
      Fatura fatura = getById(id);
      validarDelete(id);
      if (!erros.isEmpty()) {
         throw new ValidationErrorException(erros);
      }
      repository.deleteById(id);

   }

   public void validarDelete(Long id) {
      Fatura fatura = getById(id);

      if(fatura.getStatusFatura().getId() >= 917L){
         addErro("Alerta", "Não é possível excluir uma fatura que já foi liberada ao Cliente");
         return;
      }
      List<Recebimento> recebimentos = recebimentoService.obterRecebimentoPorFaturaGeral(fatura.getFaturaGeral().getId());
      if(!recebimentos.isEmpty()){
         addErro("Alerta", "Não é possível excluir uma fatura que possua recebimentos");
      }

   }

   public void addErro(String fild, String msg) {

      this.erros.add(new FieldMessage(fild, msg));
   }

   public FaturaService(RecebimentoService recebimentoService,
                        ConstanteSistemaService constanteSistemaService,
                        CalcularJurosMultasComponent calcularJurosMultasComponent,
                        ContratoService contratoService) {
      this.contratoService =  contratoService;
      this.constanteSistemaService = constanteSistemaService;
      this.calcularJurosMultasComponent = calcularJurosMultasComponent;
      this.recebimentoService = recebimentoService;
   }

   public List<Fatura> consultarDadosFaturasQuitadasVersoFatura(Long clienteId, Long contratoId) {
      List<Fatura> faturas = new ArrayList<>();

      Long tipoDocumentoId =
              Long.parseLong(constanteSistemaService.obterValorConstanteSistemaPorCodigo(Constantes.C_TIPO_DOCUMENTO_FATURA));

      faturas = repository.consultarDadosFaturasQuitadasVersoFatura(clienteId, contratoId, tipoDocumentoId,
              SituacaoPagamentoEnum.QUITADO);

      if (faturas == null) {
         return new ArrayList<>();
      }

      return faturas;
   }

   public List<FaturaTributacao> consultarFaturaTributacaoPorFatura(long faturaId) {

      List<FaturaTributacao> faturaTributacoes = new ArrayList<>();
      faturaTributacoes = repository.consultarFaturaTributacaoPorFatura(faturaId);

      if (faturaTributacoes == null) {
         return new ArrayList<>();
      }

      return faturaTributacoes;
   }

   public List<FaturaItem> listarFaturaItemPorFatura(Long faturaId) {

      List<FaturaItem> retorno = new ArrayList<>();

      if (faturaId != null) {
         retorno = repository.listarFaturaItemPorFatura(faturaId);
      }
      return retorno;
   }

   public List<FaturaItemTributacao> listarFaturaItemTributacao(Long faturaItemId) {

      List<FaturaItemTributacao> listaFaturaItemTributacao = new ArrayList<>();
      listaFaturaItemTributacao = repository.listarFaturaItemTributacao(faturaItemId);

      if (listaFaturaItemTributacao == null) {
         return new ArrayList<>();
      }

      return listaFaturaItemTributacao;
   }

   public List<FaturaItemImpressao> consultarFaturaItemImpressaoPorFaturaItem(Long faturaItemId) {

      List<FaturaItemImpressao> faturaItemImpressoes = new ArrayList<>();
      faturaItemImpressoes = repository.listarFaturaItemImpressaoPorFaturaItem(faturaItemId);

      if (faturaItemImpressoes == null) {
         return new ArrayList<>();
      }

      return faturaItemImpressoes;
   }

   public FaturaImpressao obterFaturaImpressaoPorFatura(Long faturaId) {
      FaturaImpressao faturaImpressao = new FaturaImpressao();
      faturaImpressao = repository.obterFaturaImpressaoPorFatura(faturaId);
      return faturaImpressao;
   }

   public Pair<BigDecimal, BigDecimal> calcularSaldoFatura(boolean operacaoGgas, Fatura fatura, boolean corrigirSaldo,
                                                           boolean adicionarImpontualidade) throws NegocioException {
      return calcularSaldoFatura(operacaoGgas, fatura, LocalDate.now(), corrigirSaldo, adicionarImpontualidade);
   }

   public Pair<BigDecimal, BigDecimal> calcularSaldoFatura(boolean operacaoGgas, Fatura fatura, LocalDate dataFinalCalculo,
                                                           boolean corrigirSaldo, boolean adicionarImpontualidade) throws NegocioException {

      BigDecimal saldo = corrigirValorFatura(operacaoGgas, fatura, corrigirSaldo);
      BigDecimal saldoAnteriorVencimento = saldo;

      LocalDate dataVencimento = fatura.getDataVencimento();
      Contrato contrato = contratoService
              .consultarContratoPontoConsumoPorPontoConsumoAtivoOuMaisRecente(fatura);
      List<Recebimento> listaRecebimento = recebimentoService
              .obterRecebimentoClassificadoPelaFatura(fatura.getId());

      LocalDate dataRecebimentoAnterior = dataVencimento;
      if (CollectionUtils.isNotEmpty(listaRecebimento)) {
         for (Recebimento recebimento : listaRecebimento) {

            LocalDate dataRecebimentoSemHora = recebimento.getDataRecebimento();

            if (ToolDate.menorOuIgualQue(dataRecebimentoSemHora, dataVencimento)) {

               saldo = saldo.subtract(recebimento.getValorRecebimento());
               saldoAnteriorVencimento = saldo;
            } else {

               if (corrigirSaldo && contrato != null && contrato.getIndiceFinanceiro() != null) {

                  BigDecimal fatorCorrecaoIndiceFinanceiro = contratoService
                          .fatorCorrecaoIndiceFinanceiro(contrato.getIndiceFinanceiro(), dataRecebimentoAnterior,
                                  dataRecebimentoSemHora);

                  saldo = saldo.multiply(fatorCorrecaoIndiceFinanceiro);
               }

               saldo = saldo.subtract(recebimento.getValorRecebimento());
               dataRecebimentoAnterior = dataRecebimentoSemHora;
            }
         }
      }

      BigDecimal saldoCorrigido = saldo;
      if (fatura.isVencida() && contrato != null) {

         BigDecimal acrescimoJuros = BigDecimal.ZERO;
         BigDecimal acrescimoMulta = BigDecimal.ZERO;

         if (adicionarImpontualidade) {
            EntidadeConteudo tipoCalculo = contrato.getTipoCalculoCorrecaoMonetaria();
            if (tipoCalculo != null) {

               Boolean mesAnterior = tipoCalculo.getId() == constanteSistemaService
                       .obterConstantePorCodigo("C_IGPM_VENCIMENTO").getValorLong();
               Integer dias = ToolDate.diferencaDiasEntreDatas(dataRecebimentoAnterior, dataFinalCalculo);
               List<BigDecimal> indices = contratoService.fatorCorrecaoIndiceFinanceiro(
                       contrato.getIndiceFinanceiro(), dataRecebimentoAnterior, dataFinalCalculo, mesAnterior, tipoCalculo);
               BigDecimal juros = calcularJurosMultasComponent.calcularJurosAtualizados(indices, contrato.getPercentualJurosMora(),
                       saldo, dias, mesAnterior);
               BigDecimal multa = calcularJurosMultasComponent.calcularMulta(contrato.getPercentualMulta(), saldo, juros);

               acrescimoJuros = juros;
               acrescimoMulta = multa;
            } else {
               acrescimoJuros = calcularJurosMultasComponent.calculoJurosImpontualidade(saldo, contrato, dataRecebimentoAnterior,
                       dataFinalCalculo);
               acrescimoMulta = calcularJurosMultasComponent.calculoMultaImpontualidade(saldoAnteriorVencimento, contrato,
                       dataRecebimentoAnterior, dataVencimento, dataFinalCalculo);
            }
         }

         BigDecimal fatorCorrecaoIndiceFinanceiro = BigDecimal.ONE;
         if (contrato.getIndiceFinanceiro() != null) {
            fatorCorrecaoIndiceFinanceiro = contratoService.fatorCorrecaoIndiceFinanceiro(
                    contrato.getIndiceFinanceiro(), dataRecebimentoAnterior, dataFinalCalculo);
         }

         saldoCorrigido = saldo.multiply(fatorCorrecaoIndiceFinanceiro).add(acrescimoJuros).add(acrescimoMulta)
                 .setScale(ConstantesFatura.CASAS_VALOR_MONETARIO, RoundingMode.HALF_UP);
      }

      return new Pair<>(saldo, saldoCorrigido);
   }

   public BigDecimal corrigirValorFatura(boolean operacaoGgas, Fatura fatura, boolean corrigirSaldo)
           throws NegocioException {

      BigDecimal saldoCorrigido = fatura.getValorConciliado();

      if (corrigirSaldo) {
         //TODO: implementar classes de acordo com o V1, no momento não são utilizadas
         /*Parcelamento parcelamentoLazy = fatura.getParcelamento();
         if (parcelamentoLazy != null) {
            Parcelamento parcelamento = (Parcelamento) getControladorParcelamento().obter(
                    parcelamentoLazy.getChavePrimaria(), "parcelamentoPerfil",
                    "parcelamentoPerfil.indiceFinanceiro");
            IndiceFinanceiro indiceFinanceiro = parcelamento.getParcelamentoPerfil().getIndiceFinanceiro();
            if (indiceFinanceiro != null) {

               LocalDate dataFinalCalculo = null;
               if (operacaoGgas) {
                  dataFinalCalculo = fatura.getDataVencimento();
               } else {
                  dataFinalCalculo = LocalDate.now();
               }

               BigDecimal fatorCorrecaoIndiceFinanceiro = getControladorContrato()
                       .fatorCorrecaoIndiceFinanceiro(indiceFinanceiro, fatura.getDataEmissao(), dataFinalCalculo);

               saldoCorrigido = saldoCorrigido.multiply(fatorCorrecaoIndiceFinanceiro);
            }
         }*/
      }

      return saldoCorrigido;
   }

   public BigDecimal calcularSaldoFatura(Fatura fatura) throws NegocioException {
      BigDecimal valorRetorno = fatura.getValorConciliado();
      BigDecimal valorRecebimento = recebimentoService.obterValorRecebimentoPelaFatura(fatura.getId());

      if (valorRecebimento != null) {
         valorRetorno = valorRetorno.subtract(valorRecebimento);

         if (ToolNumber.menorQueZero(valorRetorno)) {
            return BigDecimal.ZERO;
         }
      }
      return valorRetorno;
   }

   public List<Fatura> consultarFaturasVencidas(Long pontoConsumoId, Long contratoId, Long clienteId,
                                                Integer anoMes, Integer Ciclo) {

      List<Fatura> faturasVencidas = new ArrayList<>();
      SituacaoPagamentoEnum situacaoPagamento = SituacaoPagamentoEnum.PENDENTE;

      faturasVencidas = repository.consultarFaturasVencidas(clienteId, contratoId, pontoConsumoId, LocalDate.now(),
              situacaoPagamento);

      return faturasVencidas;
   }

   public byte[] gerarRelatorioResumoFaturaRubrica(Integer parametroAnoMesFechamento, Integer numeroCiclo)
           throws NegocioException {

      byte[] relatorioRetorno;

      List<RelatorioResumoFaturaRubricaVO> listaRelatorioResumoFaturaRubricaVO =
              new ArrayList<RelatorioResumoFaturaRubricaVO>();

      List<Fatura> listaFatura = this.consultarFaturasPorAnoMesReferencia(parametroAnoMesFechamento,
              numeroCiclo);

      BigDecimal valorResidencial = BigDecimal.ZERO;
      BigDecimal valorComercial = BigDecimal.ZERO;
      BigDecimal valorIndustrial = BigDecimal.ZERO;
      BigDecimal valorOutros = BigDecimal.ZERO;

      RelatorioResumoFaturaRubricaVO relatorioResumoFaturaRubricaVO = new RelatorioResumoFaturaRubricaVO();
      relatorioResumoFaturaRubricaVO
              .setListaSubRelatorioGrupoFaturamentoVO(new ArrayList<SubRelatorioGrupoFaturamentoVO>());
      List<Rubrica> listaRubricas = new ArrayList<Rubrica>();

      for (Fatura fatura : listaFatura) {
         for (FaturaItem faturaItem : fatura.getListaFaturaItens()) {
            if (!listaRubricas.contains(faturaItem.getRubrica())) {
               listaRubricas.add(faturaItem.getRubrica());
            }
         }
      }

      for (Rubrica rubrica : listaRubricas) {
         SubRelatorioGrupoFaturamentoVO subRelatorioGrupoFaturamentoVO = new SubRelatorioGrupoFaturamentoVO();
         subRelatorioGrupoFaturamentoVO.setDescricaoRubrica(rubrica.getDescricao());

         for (Fatura fatura : listaFatura) {
            for (FaturaItem faturaItem : fatura.getListaFaturaItens()) {
               if (faturaItem.getRubrica().getId() == rubrica.getId()) {
                  Segmento segmento = faturaItem.getFatura().getSegmento();
                  if (segmento != null && segmento.getId() == 1) { // industrial
                     valorIndustrial = subRelatorioGrupoFaturamentoVO.getValorIndustrial();
                     valorIndustrial = valorIndustrial.add(faturaItem.getValorTotal());
                     subRelatorioGrupoFaturamentoVO.setValorIndustrial(valorIndustrial);

                  } else if (segmento != null && segmento.getId() == 2) { // comercial
                     valorComercial = subRelatorioGrupoFaturamentoVO.getValorComercial();
                     valorComercial = valorComercial.add(faturaItem.getValorTotal());
                     subRelatorioGrupoFaturamentoVO.setValorComercial(valorComercial);

                  } else if (segmento != null && segmento.getId() == 4) { // residencial
                     valorResidencial = subRelatorioGrupoFaturamentoVO.getValorResidencial();
                     valorResidencial = valorResidencial.add(faturaItem.getValorTotal());
                     subRelatorioGrupoFaturamentoVO.setValorResidencial(valorResidencial);

                  } else {
                     // outros - 356
                     valorOutros = subRelatorioGrupoFaturamentoVO.getValorOutros();
                     valorOutros = valorOutros.add(faturaItem.getValorTotal());
                     subRelatorioGrupoFaturamentoVO.setValorOutros(valorOutros);
                  }
               }
            }
         }
         relatorioResumoFaturaRubricaVO.getListaSubRelatorioGrupoFaturamentoVO().add(subRelatorioGrupoFaturamentoVO);
      }

      if (relatorioResumoFaturaRubricaVO.getListaSubRelatorioGrupoFaturamentoVO() != null
              && !relatorioResumoFaturaRubricaVO.getListaSubRelatorioGrupoFaturamentoVO().isEmpty()) {
         // adicionando na lista do jasper
         listaRelatorioResumoFaturaRubricaVO.add(relatorioResumoFaturaRubricaVO);
      }

      Map<String, Object> parametros = new HashMap<String, Object>();
      parametros.put("", "");
      if (listaRelatorioResumoFaturaRubricaVO != null && !listaRelatorioResumoFaturaRubricaVO.isEmpty()) {
         JRBeanCollectionDataSource listaParametro = new JRBeanCollectionDataSource(
                 listaRelatorioResumoFaturaRubricaVO);
         parametros.put("colecaoGruposFaturamento", listaParametro);
      }

      relatorioRetorno = RelatorioComponent.gerarRelatorioPDF(listaRelatorioResumoFaturaRubricaVO, parametros,
              ConstantesFatura.RELATORIO_RESUMO_FATURA_RUBRICA);
      return relatorioRetorno;
   }

   public List<Fatura> consultarFaturasPorAnoMesReferencia(Integer anoMesReferencia, Integer ciclo)
           throws NegocioException {

      List<Fatura> faturasPorAnoMesReferencia = new ArrayList<>();

      if (ciclo != null) {
         faturasPorAnoMesReferencia = repository.consultarFaturasPorAnoMesCiclo(anoMesReferencia, ciclo);
      } else {
         faturasPorAnoMesReferencia = repository.consultarFaturasPorAnoMes(anoMesReferencia);
      }


      return faturasPorAnoMesReferencia;
   }

   public List<Fatura> verificarFaturasPorStatus(FaturaFilterDTO faturaFilter) {
      List<Fatura> faturasPorStatus = new ArrayList<>();
      faturasPorStatus = repository.obterFaturasPorAnoMesCicloStatusRota(faturaFilter.getRotaId(),faturaFilter.getAnoMes(),
              faturaFilter.getCiclo(),
              faturaFilter.getStatusFaturaId());
      return faturasPorStatus;
   }
}

