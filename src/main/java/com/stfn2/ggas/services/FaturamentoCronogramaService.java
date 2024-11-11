package com.stfn2.ggas.services;

import com.stfn2.ggas.config.exceptions.types.GGASException;
import com.stfn2.ggas.config.exceptions.types.NegocioException;
import com.stfn2.ggas.constante.Constantes;
import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.core.models.DadosAuditoria;
import com.stfn2.ggas.core.utils.Log;
import com.stfn2.ggas.core.utils.QueryHQLExecutor;
import com.stfn2.ggas.domain.*;
import com.stfn2.ggas.domain.dtos.FaturamentoCronogramaDTO;
import com.stfn2.ggas.domain.dtos.basic.FaturamentoCronogramaBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.FaturamentoCronogramaFilterDTO;
import com.stfn2.ggas.repositories.FaturamentoCronogramaRepository;
import com.stfn2.ggas.services.componentes.faturamento.vo.CronogramaAtividadeFaturamentoVO;
import com.stfn2.ggas.services.componentes.faturamento.vo.CronogramaAtividadeSistemaVO;
import com.stfn2.ggas.tools.ToolDate;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FaturamentoCronogramaService extends BaseService<FaturamentoCronograma, FaturamentoCronogramaDTO, FaturamentoCronogramaBasicDTO, FaturamentoCronogramaFilterDTO, FaturamentoCronogramaRepository> {

   private final ConstanteSistemaService constanteSistemaService;
   private final FaturamentoGrupoService faturamentoGrupoService;
   private final FeriadoService feriadoService;
   private final ParametroSistemaService parametroSistemaService;
   private final RotaService rotaService;
   private final FaturamentoAtividadeCronogrService faturamentoAtividadeCronogrService;
   private final RotaCronogramaService rotaCronogramaService;
   private Log log = new Log(this.getClass());

   public FaturamentoCronogramaService(ConstanteSistemaService constanteSistemaService,
                                       FaturamentoGrupoService faturamentoGrupoService,
                                       FeriadoService feriadoService,
                                       ParametroSistemaService parametroSistemaService, RotaService rotaService,
                                       FaturamentoAtividadeCronogrService faturamentoAtividadeCronogrService, RotaCronogramaService rotaCronogramaService){
      this.constanteSistemaService = constanteSistemaService;
      this.faturamentoGrupoService = faturamentoGrupoService;
      this.feriadoService = feriadoService;
      this.parametroSistemaService = parametroSistemaService;
      this.rotaService = rotaService;
      this.faturamentoAtividadeCronogrService = faturamentoAtividadeCronogrService;
      this.rotaCronogramaService = rotaCronogramaService;
   }
   public FaturamentoCronograma consultarCronogramaPorGrupoFaturamentoAnoMesCiclo(FaturamentoGrupo faturamentoGrupo) {

      FaturamentoCronograma faturamentoCronograma = new FaturamentoCronograma();
      faturamentoCronograma = repository.obterCronograma(faturamentoGrupo.getId(),
              faturamentoGrupo.getAnoMesFaturamento(), faturamentoGrupo.getNumeroCiclo());

      return faturamentoCronograma;
   }

   public FaturamentoCronograma obterUltimoCronogramaFaturamentoPorGrupoFaturamento(Long FaturamentoGrupoId) throws NegocioException {

      FaturamentoCronograma faturamentoCronograma = new FaturamentoCronograma();
      faturamentoCronograma = repository.obterUltimoCronogramaFaturamentoPorGrupoFaturamento(FaturamentoGrupoId);

      return faturamentoCronograma;
   }

   public List<FaturamentoAtividadeCronograma> consultarCronogramaAtivFaturamentoPorCronogramaFaturamento(
           Long idCronogramaFaturamento) {

      List<FaturamentoAtividadeCronograma> atividadeCronograma = new ArrayList<>();

     atividadeCronograma =
             repository.consultarCronogramaAtivFaturamentoPorCronogramaFaturamento(idCronogramaFaturamento);

      return atividadeCronograma;
   }

   public List<AtividadeSistema> listarAtividadeSistemaPorCronograma() throws NegocioException {

      List<AtividadeSistema> atividadeSistemas = new ArrayList<>();

      atividadeSistemas = repository.listarAtividadeSistemaPorCronograma();

      return atividadeSistemas;
   }

   public List<FaturamentoCronograma> consultarCronogramaFaturamento(Map<String, Object> filtro) throws NegocioException {

      new StringBuilder();

      List<String> filtrosWhere = new ArrayList<>();
      if (filtro != null) {
         Long codigoGrupoFaturamento = (Long) filtro.get("faturamentoGrupo");

         if (codigoGrupoFaturamento != null && codigoGrupoFaturamento > 0) {
            filtrosWhere.add("g.id = " + codigoGrupoFaturamento);
         }

         Long codigoPeriodicidade = (Long) filtro.get("periodicidade");
         if (codigoPeriodicidade != null && codigoPeriodicidade > 0) {
            filtrosWhere.add("p.id = " + codigoPeriodicidade);
         }

         String mesAnoFaturamentoInicial = (String) filtro.get("mesAnoFaturamentoInicial");
         if (!StringUtils.isEmpty(mesAnoFaturamentoInicial)) {
            filtrosWhere.add("c.anoMesFaturamento >= " + mesAnoFaturamentoInicial);
         }

         String mesAnoFaturamentoFinal = (String) filtro.get("mesAnoFaturamentoFinal");
         if (!StringUtils.isEmpty(mesAnoFaturamentoFinal)) {
            filtrosWhere.add("c.anoMesFaturamento <= " + mesAnoFaturamentoFinal);
         }

         Boolean habilitado = (Boolean) filtro.get("habilitado");
         if (habilitado != null) {
            filtrosWhere.add("c.habilitado = " + BooleanUtils.toStringTrueFalse(habilitado));
         }

         String situacao = (String) filtro.get("situacao");
         if (!StringUtils.isEmpty(situacao)) {
            if ("Agendado".equals(situacao)) {
               filtrosWhere.add("(c.anoMesFaturamento > g.anoMesReferencia or c.anoMesFaturamento = g.anoMesReferencia "
                       + "and c.numeroCiclo > g.numeroCiclo)");

            } else if ("Em Andamento".equals(situacao)) {
               filtrosWhere.add("(c.anoMesFaturamento = g.anoMesReferencia and c.numeroCiclo = g.numeroCiclo)");

            } else if ("Concluído".equals(situacao)) {
               filtrosWhere.add("(c.anoMesFaturamento < g.anoMesReferencia or c.anoMesFaturamento = g.anoMesReferencia "
                       + "and c.numeroCiclo > g.numeroCiclo)");
            }

         }

         final Long ultimaEtapa = (Long) filtro.get("ultimaEtapa");
         if (ultimaEtapa != null) {
            filtrosWhere.add(" exists ("
                    + "from FaturamentoAtividadeCronograma cf where cf.faturamentoCronograma.id = c.id "
                    + "and cf.atividadeSistema.id = " + ultimaEtapa
                    + "and cf.dataRealizacao in ("
                    + "select max(cf2.dataRealizacao) from FaturamentoAtividadeCronograma cf2 "
                    + "where cf2.faturamentoCronograma.id = c.id"
                    + ")"
                    + ")");
         }

      }

      if(filtrosWhere.size() < 1) {
         QueryHQLExecutor query = createQueryHQL("from FaturamentoCronograma c join fetch c" +
                 ".faturamentoGrupo g join fetch g.periodicidade p"
                 + " ORDER BY c.faturamentoGrupo, c.anoMesFaturamento desc, c.numeroCiclo desc"
         ); 
         return (List<FaturamentoCronograma>) query.list();
      } else{
         QueryHQLExecutor query = createQueryHQL("from FaturamentoCronograma c join fetch c.faturamentoGrupo g join " +
                 "fetch " +
                 "g.periodicidade p"
                 + filtrosWhere.stream().collect(Collectors.joining(" AND ", " WHERE ", ""))
                 + " ORDER BY c.faturamentoGrupo, c.anoMesFaturamento desc, c.numeroCiclo desc"
         );
         return (List<FaturamentoCronograma>) query.list();
      }
   }

   public Collection<CronogramaAtividadeSistemaVO> gerarListaCronogramaAtividade(Long[] chavesPrimarias,
                                                                                 Integer[] duracoes, String[] datasPrevistasTmp, Long[] chavesPrimariasMarcadas, String mesAnoPartida,
                                                                                 String cicloPartida, Integer qtdCronogramas, Long idGrupoFaturamento, String[] listaDatasAtividadesIniciais,
                                                                                 String[] listaDatasAtividadesFinais, Boolean isPrimeiroCronogramaTmp) throws NegocioException {
      String[] datasPrevistas = datasPrevistasTmp;

      Boolean isPrimeiroCronograma = isPrimeiroCronogramaTmp;
      Collection<CronogramaAtividadeSistemaVO> listaCronogramaAtividadeSistemaVO = new ArrayList<>();
      CronogramaAtividadeSistemaVO cronogramaAtividadeSistemaVO = null;

      Integer anoMes = null;
      Integer ciclo = null;

      if (!StringUtils.isEmpty(mesAnoPartida) && !StringUtils.isEmpty(cicloPartida)) {
         anoMes = Integer.parseInt(ToolDate.converterMesAnoEmAnoMes(mesAnoPartida));
         ciclo = Integer.parseInt(cicloPartida);
      }

      FaturamentoGrupo grupoFaturamento =
              faturamentoGrupoService.getById(idGrupoFaturamento);

      List<Rota> listaRotas = rotaService
              .listarRotasPorGrupoFaturamento(grupoFaturamento.getId());

      String[] dataPrevistaAnterior = null;
      String[] dataPrevistaTemporaria = Arrays.copyOf(datasPrevistas, datasPrevistas.length);
      LocalDate dataPeriodoLeituraAnterior = null;
      Boolean isCronogramaComplementar = Boolean.FALSE;

      ArrayList<AtividadeSistema> atividadeSistemaAux = new ArrayList<AtividadeSistema>(
              this.listarAtividadeSistemaPorCronograma());
      int indexPeriodoLeitura = 0;

      for (int i = 0; i < atividadeSistemaAux.size(); i++) {
         if ("REGISTRAR LEITURA".equals(atividadeSistemaAux.get(i).getDescricao())) {
            indexPeriodoLeitura = i;
            break;
         }
      }

      if (!isPrimeiroCronograma) {
         dataPrevistaAnterior = Arrays.copyOf(datasPrevistas, datasPrevistas.length);
      }

      for (int i = 0; i < qtdCronogramas; i++) {

         cronogramaAtividadeSistemaVO = new CronogramaAtividadeSistemaVO();
         Map<String, Integer> referenciaCiclo = null;
         dataPeriodoLeituraAnterior =ToolDate.converterParaData(datasPrevistas[indexPeriodoLeitura]);

         Collection<CronogramaAtividadeFaturamentoVO> listaCronogramaAtividadeFatVO = new ArrayList<>();
         String[] datasPrevistasParaProximoCiclo = null;

         if (!isPrimeiroCronograma
                 && faturamentoGrupoService.isPeriodicidadeGrupoMultiplosCiclos(grupoFaturamento)
                 && dataPeriodoLeituraAnterior != null && verificaViradaMesPeriodoLeituraComPeriodicade(
                 dataPeriodoLeituraAnterior, grupoFaturamento.getPeriodicidade().getQuantidadeDias())) {
            isCronogramaComplementar = Boolean.TRUE;
            Integer diferencaDias = ToolDate.diferencaDiasEntreDatas(dataPeriodoLeituraAnterior,
                    ToolDate.obterUltimoDiaDoMesParaData(dataPeriodoLeituraAnterior));
            if (dataPrevistaAnterior != null) {
               String[] dataPrevistaAnteriorAux = Arrays.copyOf(dataPrevistaAnterior, dataPrevistaAnterior.length);
               datasPrevistas = retornaDatasPrevistasCronogramaComplementar(dataPrevistaAnteriorAux,
                       diferencaDias);
            }

            datasPrevistasParaProximoCiclo = this.popularListaAtividadeSistemaVO(duracoes, datasPrevistas,
                    chavesPrimariasMarcadas, listaRotas, anoMes, ciclo, grupoFaturamento.getPeriodicidade(),
                    isPrimeiroCronograma, listaCronogramaAtividadeFatVO, listaDatasAtividadesIniciais,
                    listaDatasAtividadesFinais, grupoFaturamento.getLeituraTipo(), isCronogramaComplementar);

            dataPeriodoLeituraAnterior = ToolDate.converterParaData(datasPrevistas[indexPeriodoLeitura]);

         } else {
            datasPrevistasParaProximoCiclo = this.popularListaAtividadeSistemaVO(duracoes, datasPrevistas,
                    chavesPrimariasMarcadas, listaRotas, anoMes, ciclo, grupoFaturamento.getPeriodicidade(),
                    isPrimeiroCronograma, listaCronogramaAtividadeFatVO, listaDatasAtividadesIniciais,
                    listaDatasAtividadesFinais, grupoFaturamento.getLeituraTipo(), isCronogramaComplementar);
         }

         if (!isPrimeiroCronograma && dataPrevistaAnterior != null) {
            dataPrevistaTemporaria = Arrays.copyOf(dataPrevistaAnterior, dataPrevistaAnterior.length);
         }

         cronogramaAtividadeSistemaVO.setAnoMes(anoMes);
         cronogramaAtividadeSistemaVO.setCiclo(ciclo);

         cronogramaAtividadeSistemaVO.setListaCronogramaAtividadeFaturamentoVO(listaCronogramaAtividadeFatVO);
         listaCronogramaAtividadeSistemaVO.add(cronogramaAtividadeSistemaVO);

         isPrimeiroCronograma = Boolean.FALSE;

         datasPrevistas = datasPrevistasParaProximoCiclo;
         dataPrevistaAnterior = Arrays.copyOf(datasPrevistas, datasPrevistas.length);

         if (isCronogramaComplementar) {
            isCronogramaComplementar = Boolean.FALSE;
            referenciaCiclo = ToolDate.gerarProximaReferenciaCiclo(anoMes, ciclo,
                    grupoFaturamento.getPeriodicidade().getQuantidadeDias(),
                    dataPeriodoLeituraAnterior.minusDays(1));

            ciclo = referenciaCiclo.get("ciclo");
            anoMes = referenciaCiclo.get("referencia");

            cronogramaAtividadeSistemaVO = new CronogramaAtividadeSistemaVO();

            listaCronogramaAtividadeFatVO = new ArrayList<>();
            datasPrevistasParaProximoCiclo = this.popularListaAtividadeSistemaVO(duracoes, dataPrevistaTemporaria,
                    chavesPrimariasMarcadas, listaRotas, anoMes, ciclo, grupoFaturamento.getPeriodicidade(),
                    isPrimeiroCronograma, listaCronogramaAtividadeFatVO, listaDatasAtividadesIniciais,
                    listaDatasAtividadesFinais, grupoFaturamento.getLeituraTipo(), isCronogramaComplementar);

            cronogramaAtividadeSistemaVO.setAnoMes(anoMes);
            cronogramaAtividadeSistemaVO.setCiclo(ciclo);

            cronogramaAtividadeSistemaVO.setListaCronogramaAtividadeFaturamentoVO(listaCronogramaAtividadeFatVO);
            listaCronogramaAtividadeSistemaVO.add(cronogramaAtividadeSistemaVO);

            datasPrevistas = datasPrevistasParaProximoCiclo;

            dataPeriodoLeituraAnterior = ToolDate.converterParaData(datasPrevistas[indexPeriodoLeitura]);
         }

         if (faturamentoGrupoService.isPeriodicidadeGrupoMultiplosCiclos(grupoFaturamento)) {
            referenciaCiclo = ToolDate.gerarProximaReferenciaCiclo(anoMes, ciclo,
                    grupoFaturamento.getPeriodicidade().getQuantidadeDias(),
                    dataPeriodoLeituraAnterior.minusDays(1));
         } else {
            referenciaCiclo = ToolDate.rolarReferenciaCiclo(anoMes, ciclo,
                    grupoFaturamento.getPeriodicidade().getQuantidadeCiclos());
         }

         ciclo = referenciaCiclo.get("ciclo");
         anoMes = referenciaCiclo.get("referencia");
      }

      return listaCronogramaAtividadeSistemaVO;
   }

   private String[] popularListaAtividadeSistemaVO(Integer[] duracoes, String[] datasPrevistas, Long[] chavesPrimariasMarcadas,
                                                   Collection<Rota> listaRotas, Integer anoMes, Integer ciclo, Periodicidade periodicidade, Boolean isPrimeiroCronograma,
                                                   Collection<CronogramaAtividadeFaturamentoVO> listaCronogramaAtividadeFatVO, String[] listaDatasAtividadesIniciais,
                                                   String[] listaDatasAtividadesFinais, LeituraTipo tipoLeitura,
                                                   Boolean isCronogramaComplementar) throws NegocioException {

      Collection<AtividadeSistema> listaAtividadeSistemas = this.listarAtividadeSistemaPorCronograma();
      CronogramaAtividadeFaturamentoVO cronogramaAtividadeFaturamentoVO = null;

      int j = 0;
      int i = 0;
      String[] datasPrevistasParaProximoCiclo = new String[listaAtividadeSistemas.size()];
      LocalDate dataPrevistaAtividade = null;
      String dataInicialAtividade = null;
      String dataFinalAtividade = null;

      for (AtividadeSistema atividadeSistema : listaAtividadeSistemas) {
         for (; i < chavesPrimariasMarcadas.length;) {
            if (atividadeSistema.getId() == Long.parseLong(chavesPrimariasMarcadas[i].toString())) {

               cronogramaAtividadeFaturamentoVO = new CronogramaAtividadeFaturamentoVO();
               try {
                  if (isPrimeiroCronograma != null && isPrimeiroCronograma.booleanValue()) {

                     dataPrevistaAtividade =
                             ToolDate.converterCampoStringParaData("Prevista", datasPrevistas[j],
                                     Constantes.FORMATO_DATA_BR);
                     dataInicialAtividade = listaDatasAtividadesIniciais[j];
                     dataFinalAtividade = listaDatasAtividadesFinais[j];
                  } else {

                     LocalDate dataPrevista = ToolDate.converterCampoStringParaData("Prevista", datasPrevistas[j],
                                     Constantes.FORMATO_DATA_BR);

                     Map<String, Object> mapaDatas = null;
                     if (parametroSistemaService.obterValorParametroUtilizacaoMultiplosCiclos() && periodicidade
                             .getQuantidadeDias() < Constantes.QUANTIDADE_MINIMA_DE_DIAS_PARA_SER_CONSIDERADO_MENSAL) {
                        mapaDatas = this.obterDatasSugestaoCronograma(dataPrevista, periodicidade, isCronogramaComplementar);
                     } else {
                        mapaDatas = this.obterDatasSugestaoCronograma(dataPrevista, periodicidade, null, null, null, Boolean.TRUE,
                                tipoLeitura, ciclo, null);
                     }

                     dataPrevistaAtividade = (LocalDate)(mapaDatas.get("dataPrevista"));
                     dataInicialAtividade =
                             ToolDate.converterDataParaStringSemHora((LocalDate)mapaDatas.get("dataInicial"),
                                     Constantes.FORMATO_DATA_BR);
                     dataFinalAtividade =
                             ToolDate.converterDataParaStringSemHora((LocalDate)mapaDatas.get("dataFinal"),
                                     Constantes.FORMATO_DATA_BR);
                  }

                  cronogramaAtividadeFaturamentoVO.setDataPrevista(dataPrevistaAtividade);
                  cronogramaAtividadeFaturamentoVO.setDataInicialAtividade(dataInicialAtividade);
                  cronogramaAtividadeFaturamentoVO.setDataFinalAtividade(dataFinalAtividade);

                  datasPrevistasParaProximoCiclo[j] =
                          (ToolDate.converterDataParaStringSemHora(cronogramaAtividadeFaturamentoVO.getDataPrevista(),
                                  Constantes.FORMATO_DATA_BR));

               } catch (GGASException e) {
                  log.erro(e.getMessage(), e.getCause().getMessage());
                  throw new NegocioException(Constantes.ERRO_DADOS_INVALIDOS, true);
               }

               Integer duracao = this.getDuracao(j, duracoes);

               cronogramaAtividadeFaturamentoVO.setDuracao(duracao);

               cronogramaAtividadeFaturamentoVO.setAtividadeSistema(atividadeSistema);

               List<RotaCronograma> listaCronogramaRota = new ArrayList<>();
               for (Rota rota : listaRotas) {

                  RotaCronograma cronogramaRota = new RotaCronograma();
                  this.popularCronogramaRota(cronogramaAtividadeFaturamentoVO, cronogramaRota, rota, anoMes, ciclo, tipoLeitura);
                  listaCronogramaRota.add(cronogramaRota);
               }
               cronogramaAtividadeFaturamentoVO.setListaCronogramaRota(listaCronogramaRota);

               listaCronogramaAtividadeFatVO.add(cronogramaAtividadeFaturamentoVO);
               i++;
               break;
            } else {
               datasPrevistasParaProximoCiclo[j] = ",";
               break;
            }

         }
         j++;

      }
      return datasPrevistasParaProximoCiclo;
   }

   private Map<String, Object> obterDatasSugestaoCronograma(LocalDate dataPrevistaCicloAnterior,
                                                            Periodicidade periodicidade, Boolean isCronogramaComplementar) {
      Map<String, Object> mapaDatas = new LinkedHashMap<>();
      LocalDate dataInicialCiclo = dataPrevistaCicloAnterior.plusDays(1);
      LocalDate dataPrevista = dataInicialCiclo;
      LocalDate dataInicial = null;
      LocalDate dataFinal = null;

      int quantidadeDiasPeriodicidade = periodicidade.getQuantidadeDias();
      if(isCronogramaComplementar) {
         dataPrevista = dataPrevistaCicloAnterior;
      }else{
         dataPrevista = dataInicialCiclo.plusDays(quantidadeDiasPeriodicidade).minusDays(1);
      }
      mapaDatas.put("dataPrevista", dataPrevista);

      Integer numeroMinimoDiasCiclo = periodicidade.getMinimoDiasCiclo();
      if (numeroMinimoDiasCiclo != null) {
         int minimoDias = quantidadeDiasPeriodicidade - numeroMinimoDiasCiclo;
         dataInicial = dataPrevista.minusDays(minimoDias);
      } else {
         dataInicial = dataPrevista;
      }
      mapaDatas.put("dataInicial", dataInicial);

      Integer numeroMaxiDiasCiclo = periodicidade.getMaximoDiasCiclo();
      if (numeroMaxiDiasCiclo != null) {
         int maximoDias = numeroMaxiDiasCiclo - quantidadeDiasPeriodicidade;
         dataFinal = dataPrevista.plusDays(maximoDias);
      } else {
         dataFinal = dataPrevista;
      }
      mapaDatas.put("dataFinal", dataFinal);
      return mapaDatas;
   }

   public Map<String, Object> obterDatasSugestaoCronograma(LocalDate dataPrevistaCicloAnterior,
                                                           Periodicidade periodicidade,
                                                           StringBuilder listaDatasPrevistas, StringBuilder listaDatasIniciais, StringBuilder listaDatasFinais,
                                                           Boolean verificarDataPrevista, LeituraTipo tipoLeitura, Integer cicloTmp, Rota rota) throws GGASException {

      int ultimoDiaMesAnterior = dataPrevistaCicloAnterior.getMonth().maxLength();
      Map<String, Object> mapaDatas = new LinkedHashMap<>();
      LocalDate dataInicialCiclo = dataPrevistaCicloAnterior.plusDays(1);
      LocalDate dataPrevista = dataInicialCiclo;
      LocalDate dataInicial = null;
      LocalDate dataFinal = null;
      Integer ciclo = cicloTmp;


      ConstanteSistema constanteSistemaTipoLeituraEletroCorretor = constanteSistemaService
              .obterConstantePorCodigo(Constantes.C_TIPO_LEITURA_ELETROCORRETOR);

      int anoMesInicial = ToolDate.obterAnoMes(dataInicialCiclo);

      dataPrevista = dataInicialCiclo.plusDays(periodicidade.getQuantidadeDias());
      int ultimoDiaMes = dataInicialCiclo.getMonth().maxLength();

      if (verificarDataPrevista != null && verificarDataPrevista) {

         if (tipoLeitura.getId() == Integer.parseInt(constanteSistemaTipoLeituraEletroCorretor.getValor())) {
            if (periodicidade.getMesCivil() != null && periodicidade.getMesCivil()) {

               int anoMesFinal = ToolDate.obterAnoMes(dataPrevista);

               // TODO: ajuste para o mês/ano-ciclo não quebrar quando a periodicidade tiver 14 dias e 3 ciclos
               if (periodicidade.getQuantidadeDias() == Constantes.QUANTIDADE_DIAS_PERIODICIDADE
                       && periodicidade.getQuantidadeCiclos() == Constantes.QUANTIDADE_CICLOS_PERIODICIDADE
                       && ultimoDiaMesAnterior == Constantes.ULTIMO_DIA_FEVEREIRO
                       && ultimoDiaMes != Constantes.ULTIMO_DIA_FEVEREIRO
                       || periodicidade.getQuantidadeDias() == Constantes.QUANTIDADE_DIAS_PERIODICIDADE_SEMANAL
                       && periodicidade.getQuantidadeCiclos() == Constantes.QUANTIDADE_CICLOS_PERIODICIDADE_SEMANAL
                       && ultimoDiaMesAnterior != Constantes.ULTIMO_DIA_FEVEREIRO
                       && ultimoDiaMes == Constantes.ULTIMO_DIA_FEVEREIRO
                       || periodicidade.getQuantidadeDias() == Constantes.QUANTIDADE_DIAS_PERIODICIDADE_SEMANAL
                       && periodicidade.getQuantidadeCiclos() == Constantes.QUANTIDADE_CICLOS_PERIODICIDADE_SEMANAL
                       && ultimoDiaMesAnterior == Constantes.ULTIMO_DIA_FEVEREIRO
                       && ultimoDiaMes != Constantes.ULTIMO_DIA_FEVEREIRO) {

                  ciclo = 1;
               }

               if (anoMesFinal > anoMesInicial || ciclo.equals(periodicidade.getQuantidadeCiclos())) {
                  String mes = String.valueOf(dataInicialCiclo.getMonthValue());

                  if (dataInicialCiclo.getMonthValue() < 10) {
                     mes = "0" + dataInicialCiclo.getMonthValue();
                  }

                  int ano = dataInicialCiclo.getYear();
                  String dataPrevistaAux = ultimoDiaMes + "/" + mes + "/" + ano;
                  dataPrevista = ToolDate.converterCampoStringParaData("", dataPrevistaAux,
                          Constantes.FORMATO_DATA_BR).plusDays(1);
               }

            }

            if (listaDatasPrevistas != null) {

               listaDatasPrevistas.append(ToolDate.converterDataParaStringSemHora(dataPrevista,
                       Constantes.FORMATO_DATA_BR));
            }
         } else {

            dataPrevista = this.verificarDiaUtil(dataPrevista, listaDatasPrevistas);

            if (rota != null && rota.getSetorComercial() != null) {

               Long idMunicipio = rota.getSetorComercial().getMunicipio().getId();

               if (!feriadoService.isDiaUtil(dataPrevista, idMunicipio)) {
                  dataPrevista = feriadoService.obterProximoDiaUtil(dataPrevista);
               }
            }
         }
         mapaDatas.put("dataPrevista", dataPrevista);
      }

      if (periodicidade.getMinimoDiasCiclo() != null) {

         int minimoDias = periodicidade.getQuantidadeDias() - periodicidade.getMinimoDiasCiclo();
         dataInicial = dataPrevista.minusDays(minimoDias);

      } else {
         dataInicial = dataPrevista;
      }
      dataInicial = this.verificarDiaUtil(dataInicial, listaDatasIniciais);
      mapaDatas.put("dataInicial", dataInicial);

      if (periodicidade.getMaximoDiasCiclo() != null) {

         int maximoDias = periodicidade.getMaximoDiasCiclo() - periodicidade.getQuantidadeDias();
         dataFinal = dataPrevista.plusDays(maximoDias);

      } else {
         dataFinal = dataPrevista;
      }
      dataFinal = this.verificarDiaUtil(dataFinal, listaDatasFinais);
      mapaDatas.put("dataFinal", dataFinal);

      mapaDatas.put("anoMesInicial", anoMesInicial);
      mapaDatas.put("ciclo", ciclo);

      return mapaDatas;
   }

   private Integer getDuracao(Integer index, Integer[] duracoes) {
      Integer duracao = 0;
      if(duracoes != null) {
         duracao = duracoes[index];
      }
      return duracao;
   }

   private void popularCronogramaRota(CronogramaAtividadeFaturamentoVO cronogramaAtividadeFaturamentoVO, RotaCronograma cronogramaRota,
                                      Rota rota, Integer anoMes, Integer ciclo, LeituraTipo tipoLeitura) throws NegocioException {

      ConstanteSistema constanteSistemaTipoLeituraEletroCorretor = constanteSistemaService
              .obterConstantePorCodigo(Constantes.C_TIPO_LEITURA_ELETROCORRETOR);

      LocalDate dataPrevista = cronogramaAtividadeFaturamentoVO.getDataPrevista();

      if (tipoLeitura.getId() != Integer.parseInt(constanteSistemaTipoLeituraEletroCorretor.getValor()) && rota != null
              && rota.getSetorComercial() != null) {

         Long idMunicipio = rota.getSetorComercial().getMunicipio().getId();

         if (!feriadoService.isDiaUtil(dataPrevista, idMunicipio)) {
            dataPrevista = feriadoService.obterProximoDiaUtil(dataPrevista);
         }
      }

      cronogramaRota.setDataPrevista(dataPrevista);
      cronogramaRota.setRota(rota);
      cronogramaRota.setId(0L);
      cronogramaRota.setHabilitado(true);
      cronogramaRota.setAnoMesReferencia(anoMes);
      cronogramaRota.setNumeroCiclo(ciclo);
   }

   public LocalDate verificarDiaUtil(LocalDate data, StringBuilder listaDatas) throws NegocioException {
      if (!feriadoService.isDiaUtil(data, null)) {
         data = feriadoService.obterProximoDiaUtil(data);
      }
      if (listaDatas != null) {
         listaDatas.append(ToolDate.converterDataParaStringSemHora(data, Constantes.FORMATO_DATA_BR));
      }
      return data;
   }

   public String[] retornaDatasPrevistasCronogramaComplementar(String[] datasPrevistas, Integer diferencaDias) {
      for (int i = 0; i < datasPrevistas.length; i++) {
         if (!",".equals(datasPrevistas[i]) && datasPrevistas[i]!= null) {
            LocalDate dataAux = ToolDate.converterParaData(datasPrevistas[i]);
            LocalDate dataAux1 = dataAux.plusDays(diferencaDias);
            datasPrevistas[i] = ToolDate.converterDataParaStringSemHora(
                    dataAux1, Constantes.FORMATO_DATA_BR);
         }
      }
      return datasPrevistas;
   }

   public Boolean verificaViradaMesPeriodoLeituraComPeriodicade(LocalDate dataPeriodoDeLeitura, Integer periodicidade) {
      LocalDate dataComPeriodicidade = dataPeriodoDeLeitura.plusDays(periodicidade);
      LocalDate ultimoDiaMes = ToolDate.obterUltimoDiaDoMesParaData(dataPeriodoDeLeitura);
      Integer primeiroDia = dataComPeriodicidade.getDayOfMonth();
      if(dataComPeriodicidade.compareTo(ultimoDiaMes) > 0 && primeiroDia != 1) {
         return Boolean.TRUE;
      }

      return Boolean.FALSE;
   }

   public void inserirCronogramaFaturamento(Long idGrupoFaturamento,
                                            Collection<CronogramaAtividadeSistemaVO> listaCronogramaAtividadeSistemaVO, DadosAuditoria dadosAuditoria)
           throws NegocioException {

      FaturamentoGrupo grupoFaturamento =
              faturamentoGrupoService.getById(idGrupoFaturamento);

      Boolean primeiroCronograma = Boolean.FALSE;
      Boolean atualizarGrupoFaturamento = Boolean.FALSE;

      List<FaturamentoCronograma> listaCronogramaFaturamento =
              this.listarCronogramaFaturamentosPorGrupo(grupoFaturamento.getId());
      if(listaCronogramaFaturamento != null && listaCronogramaFaturamento.isEmpty()) {
         primeiroCronograma = Boolean.TRUE;
         atualizarGrupoFaturamento = Boolean.TRUE;
      }

      for (CronogramaAtividadeSistemaVO cronogramaAtividadeSistemaVO : listaCronogramaAtividadeSistemaVO) {

         FaturamentoCronograma cronogramaFaturamento = new FaturamentoCronograma();
         cronogramaFaturamento.setFaturamentoGrupo(grupoFaturamento);
         cronogramaFaturamento.setAnoMesFaturamento(cronogramaAtividadeSistemaVO.getAnoMes());
         cronogramaFaturamento.setCiclo(cronogramaAtividadeSistemaVO.getCiclo());
         cronogramaFaturamento.setDadosAuditoria(dadosAuditoria);
         this.save(cronogramaFaturamento);

         if (primeiroCronograma && atualizarGrupoFaturamento) {
            grupoFaturamento.setAnoMesFaturamento(cronogramaFaturamento.getAnoMesFaturamento());
            grupoFaturamento.setNumeroCiclo(cronogramaFaturamento.getCiclo());
            faturamentoGrupoService.save(grupoFaturamento);
            atualizarGrupoFaturamento = Boolean.FALSE;
         }

         Collection<CronogramaAtividadeFaturamentoVO> listaCronogramaAtividadeFaturamentoVO = cronogramaAtividadeSistemaVO
                 .getListaCronogramaAtividadeFaturamentoVO();
         for (CronogramaAtividadeFaturamentoVO cronogramaAtividadeFaturamentoVO : listaCronogramaAtividadeFaturamentoVO) {
            FaturamentoAtividadeCronograma cronogramaAtividadeFaturamento = new FaturamentoAtividadeCronograma();

            cronogramaAtividadeFaturamento.setAtividadeSistema(cronogramaAtividadeFaturamentoVO.getAtividadeSistema());
            cronogramaAtividadeFaturamento.setFaturamentoCronograma(cronogramaFaturamento);
            cronogramaAtividadeFaturamento.setDadosAuditoria(dadosAuditoria);
            cronogramaAtividadeFaturamento.setDataFim(cronogramaAtividadeFaturamentoVO.getDataPrevista());

            LocalDate dataPrevistaAux = cronogramaAtividadeFaturamentoVO.getDataPrevista();
            Integer duracaoAux = cronogramaAtividadeFaturamentoVO.getDuracao() - 1;
            cronogramaAtividadeFaturamento.setDataInicio(dataPrevistaAux.minusDays(duracaoAux));
            faturamentoAtividadeCronogrService.save(cronogramaAtividadeFaturamento);

            Collection<RotaCronograma> listaCronogramaRota = cronogramaAtividadeFaturamentoVO.getListaCronogramaRota();
            for (RotaCronograma cronogramaRota : listaCronogramaRota) {

               cronogramaRota.setFaturamentoAtividadeCronograma(cronogramaAtividadeFaturamento);

               rotaCronogramaService.save(cronogramaRota);
            }

         }

      }

   }

   public List<FaturamentoCronograma> listarCronogramaFaturamentosPorGrupo(Long idGrupoFaturamento) {

      List<FaturamentoCronograma> cronogramas = new ArrayList<>();

     cronogramas = repository.listarCronogramaFaturamentosPorGrupo(idGrupoFaturamento);

      return cronogramas;
   }

   public Integer consultarMenorReferenciaCicloGrupoFaturamentoComCronograma() throws NegocioException {

      Integer menorReferencia = repository.consultarMenorReferenciaCicloGrupoFaturamentoComCronograma();

      return menorReferencia;
   }
}