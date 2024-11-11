package com.stfn2.ggas.services.componentes.faturamento;

import com.stfn2.ggas.config.exceptions.types.GGASException;
import com.stfn2.ggas.constante.Constantes;
import com.stfn2.ggas.core.models.DadosAuditoria;
import com.stfn2.ggas.core.utils.DadosAuditoriaUtil;
import com.stfn2.ggas.domain.*;
import com.stfn2.ggas.services.*;
import com.stfn2.ggas.services.componentes.faturamento.uteis.ConstantesFatura;
import com.stfn2.ggas.services.componentes.faturamento.vo.CronogramaAtividadeSistemaVO;
import com.stfn2.ggas.services.componentes.relatorio.RelatorioComponent;
import com.stfn2.ggas.tools.ToolDate;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EncerrarCicloComponent {

   private final AtividadeSistemaService atividadeSistemaService;
   private final ConstanteSistemaService constanteSistemaService;
   private final FaturaService faturaService;
   private final FaturamentoCronogramaService faturamentoCronogramaService;
   private final FaturamentoAtividadeCronogrService faturamentoAtividadeCronogrService;
   private final FaturamentoGrupoService faturamentoGrupoService;
   private final ParametroSistemaService parametroSistemaService;
   private final RotaCronogramaService  rotaCronogramaService;
  
   public EncerrarCicloComponent(
           AtividadeSistemaService atividadeSistemaService,
           ConstanteSistemaService constanteSistemaService,
           FaturaService faturaService,
           FaturamentoCronogramaService faturamentoCronogramaService,
           FaturamentoAtividadeCronogrService faturamentoAtividadeCronogrService,
           FaturamentoGrupoService faturamentoGrupoService,
           ParametroSistemaService parametroSistemaService,
           RotaCronogramaService  rotaCronogramaService
   ) {
      this.atividadeSistemaService = atividadeSistemaService;
      this.constanteSistemaService = constanteSistemaService;
      this.faturaService = faturaService;
      this.faturamentoCronogramaService  = faturamentoCronogramaService;
      this.faturamentoAtividadeCronogrService = faturamentoAtividadeCronogrService;
      this.faturamentoGrupoService = faturamentoGrupoService;
      this.parametroSistemaService =  parametroSistemaService;
      this.rotaCronogramaService = rotaCronogramaService;
   }

   public void executar(StringBuilder logProcessamento, FaturamentoGrupo grupoFaturamento) throws GGASException {

      DadosAuditoria dadosAuditoria = DadosAuditoriaUtil.getDadosAuditoria();
      
      ParametroSistema parametroAnoMesReferencia = parametroSistemaService
              .obterParametroPorCodigo(Constantes.PARAMETRO_REFERENCIA_FATURAMENTO);

      int parametroAnoMesFechamento = Integer.parseInt(parametroAnoMesReferencia.getValor());

      ConstanteSistema constanteSistemaAtividadeSistemaEncerrarCicloFaturamento = constanteSistemaService
              .obterConstantePorCodigo(Constantes.C_ATIVIDADE_SISTEMA_ENCERRAR_CICLO_FATURAMENTO);

      Long idAtividadeSistemaEncerrarCicloFaturamento = Long
              .parseLong(constanteSistemaAtividadeSistemaEncerrarCicloFaturamento.getValor());

      logProcessamento.append("Processando os Grupos de Faturamento...\r\n");

      boolean isGruposEncerrados = true;

      int anoMesreferecia = grupoFaturamento.getAnoMesFaturamento();
      int numeroCiclo = grupoFaturamento.getNumeroCiclo();

      FaturamentoCronograma cronogramaFaturamento = faturamentoCronogramaService 
              .consultarCronogramaPorGrupoFaturamentoAnoMesCiclo(grupoFaturamento);

      if (cronogramaFaturamento != null) {

         List<FaturamentoAtividadeCronograma> listaCronogramaAtividadeFaturamentoPorCronograma = (List<FaturamentoAtividadeCronograma>) faturamentoCronogramaService 
                 .consultarCronogramaAtivFaturamentoPorCronogramaFaturamento(
                         cronogramaFaturamento.getId());

         // verificação se os cronogramas atividades foram fechados
         boolean isCicloEncerrado = true;
         FaturamentoAtividadeCronograma cronogramaAtividadeFaturamentoEncerrarCiclo = null;

         for (FaturamentoAtividadeCronograma cronogramaAtividadeFaturamento : listaCronogramaAtividadeFaturamentoPorCronograma) {
            if (cronogramaAtividadeFaturamento.getDataRealizacao() == null
                    && cronogramaAtividadeFaturamento.getAtividadeSistema().getObrigatoriedade()
                    && cronogramaAtividadeFaturamento.getAtividadeSistema()
                    .getId() != idAtividadeSistemaEncerrarCicloFaturamento) {
               cronogramaAtividadeFaturamentoEncerrarCiclo = cronogramaAtividadeFaturamento;
               isCicloEncerrado = false;
               break;
            } else if (cronogramaAtividadeFaturamento.getAtividadeSistema()
                    .getId() == idAtividadeSistemaEncerrarCicloFaturamento) {
               cronogramaAtividadeFaturamentoEncerrarCiclo = cronogramaAtividadeFaturamento;
            }
         }

         if (isCicloEncerrado) {

            FaturamentoCronograma ultimoCronogramaFaturamento = faturamentoCronogramaService 
                    .obterUltimoCronogramaFaturamentoPorGrupoFaturamento(grupoFaturamento.getId());
            List<FaturamentoAtividadeCronograma> listaUltimoCronogramaAtividadeFaturamentoPorCronograma =
                    faturamentoCronogramaService
                    .consultarCronogramaAtivFaturamentoPorCronogramaFaturamento(
                            ultimoCronogramaFaturamento.getId());

            List<AtividadeSistema> listaAtividadeSistemas = faturamentoCronogramaService
                    .listarAtividadeSistemaPorCronograma();

            Long[] listaChavesCronogramaAtividadeFaturamento = new Long[listaUltimoCronogramaAtividadeFaturamentoPorCronograma
                    .size()];

            String[] listaDatasAtividadesIniciais = new String[listaAtividadeSistemas.size()];
            String[] listaDatasPrevistas = new String[listaAtividadeSistemas.size()];
            String[] listaDatasAtividadesFinais = new String[listaAtividadeSistemas.size()];
            Integer[] listaDuracoes = new Integer[listaAtividadeSistemas.size()];
            Long chaveRegistrar = null;
            int indCronogramaAtividade = 0;
            int indAtividade = 0;
            LocalDate primeiroDiaMes = null;
            FaturamentoAtividadeCronograma ultimoCronogramaAtividadeFaturamento = null;
            for (AtividadeSistema atividadeSistema : listaAtividadeSistemas) {
               if ("REGISTRAR LEITURA".equals(atividadeSistema.getDescricao()) && chaveRegistrar == null) {
                  chaveRegistrar = atividadeSistema.getId();
               }
               Boolean possuiAtividadeSistema = Boolean.FALSE;

               for (FaturamentoAtividadeCronograma cronogramaAtividadeFaturamento : listaUltimoCronogramaAtividadeFaturamentoPorCronograma) {
                  ultimoCronogramaAtividadeFaturamento = cronogramaAtividadeFaturamento;
                  if (atividadeSistema.getId() == cronogramaAtividadeFaturamento.getAtividadeSistema()
                          .getId()) {

                     if ("REGISTRAR LEITURA".equals(atividadeSistema.getDescricao()) && primeiroDiaMes == null) {
                        primeiroDiaMes = cronogramaAtividadeFaturamento.getDataFim();
                     }

                     listaChavesCronogramaAtividadeFaturamento[indAtividade] = cronogramaAtividadeFaturamento
                             .getAtividadeSistema().getId();

                     listaDatasPrevistas[indCronogramaAtividade] = "";
                     listaDatasAtividadesIniciais[indCronogramaAtividade] = "";
                     listaDuracoes[indCronogramaAtividade] = 1;
                     listaDatasAtividadesFinais[indCronogramaAtividade] = "";

                     if (cronogramaAtividadeFaturamento.getDataInicio() != null) {
                        listaDatasAtividadesIniciais[indCronogramaAtividade] = ToolDate
                                .converterDataParaString(cronogramaAtividadeFaturamento.getDataInicio(), false);
                     }

                     if (cronogramaAtividadeFaturamento.getDataFim() != null
                             && cronogramaAtividadeFaturamento.getDataInicio() != null) {
                        listaDuracoes[indCronogramaAtividade] = ToolDate.diferencaDiasEntreDatas(
                                cronogramaAtividadeFaturamento.getDataInicio(),
                                cronogramaAtividadeFaturamento.getDataFim()) + 1;
                     }

                     if (cronogramaAtividadeFaturamento.getDataFim() != null) {
                        listaDatasAtividadesFinais[indCronogramaAtividade] = ToolDate
                                .converterDataParaString(cronogramaAtividadeFaturamento.getDataFim(), false);
                        listaDatasPrevistas[indCronogramaAtividade] = ToolDate
                                .converterDataParaString(cronogramaAtividadeFaturamento.getDataFim(), false);
                     }
                     possuiAtividadeSistema = Boolean.TRUE;
                     indAtividade++;
                     break;
                  }
               }

               if (!possuiAtividadeSistema) {
                  listaDatasPrevistas[indCronogramaAtividade] = ",";
                  listaDuracoes[indCronogramaAtividade] = 0;
                  listaDatasAtividadesIniciais[indCronogramaAtividade] = ",";
                  listaDatasAtividadesFinais[indCronogramaAtividade] = ",";
               }
               indCronogramaAtividade++;
            }

            Map<String, Integer> referenciaNovoCronograma = null;

            Map<String, Object> filtroCronograma = new HashMap<String, Object>();
            filtroCronograma.put("grupoFaturamento", grupoFaturamento.getId());
            filtroCronograma.put("situacao", "Agendado");
            filtroCronograma.put("habilitado", Boolean.TRUE);
            int tamanhoCronogramaAgendado = faturamentoCronogramaService 
                    .consultarCronogramaFaturamento(filtroCronograma).size();

            if (parametroSistemaService.obterValorParametroUtilizacaoMultiplosCiclos()) {

               LocalDate dataFim = ultimoCronogramaAtividadeFaturamento.getDataFim();

               if (primeiroDiaMes.getDayOfMonth() == 1) {
                  dataFim = primeiroDiaMes.minusDays(1);
               }

               referenciaNovoCronograma = ToolDate
                       .gerarProximaReferenciaCiclo(ultimoCronogramaFaturamento.getAnoMesFaturamento(),
                               ultimoCronogramaFaturamento.getCiclo(), ultimoCronogramaFaturamento
                                       .getFaturamentoGrupo().getPeriodicidade().getMaximoDiasCiclo(),
                               dataFim);
            } else {
               referenciaNovoCronograma = ToolDate.rolarReferenciaCiclo(
                       ultimoCronogramaFaturamento.getAnoMesFaturamento(),
                       ultimoCronogramaFaturamento.getCiclo(),
                       ultimoCronogramaFaturamento.getFaturamentoGrupo().getPeriodicidade().getQuantidadeCiclos());
            }

            if (tamanhoCronogramaAgendado < 1) {
               Collection<CronogramaAtividadeSistemaVO> listaCronogramaAtividadeSisteomaVOs = faturamentoCronogramaService
                       .gerarListaCronogramaAtividade(listaChavesCronogramaAtividadeFaturamento, listaDuracoes,
                               listaDatasPrevistas, listaChavesCronogramaAtividadeFaturamento,
                               ToolDate.converterAnoMesEmMesAno(
                                       String.valueOf(referenciaNovoCronograma.get(ConstantesFatura.REFERENCIA_FATURA))),
                               String.valueOf(referenciaNovoCronograma.get(ConstantesFatura.CICLO)), 1,
                               grupoFaturamento.getId(), listaDatasAtividadesIniciais,
                               listaDatasAtividadesFinais, Boolean.FALSE);

               faturamentoCronogramaService.inserirCronogramaFaturamento(grupoFaturamento.getId(),
                       listaCronogramaAtividadeSisteomaVOs, dadosAuditoria);
            }

            LocalDate dataAtual = LocalDate.now();
            if (cronogramaAtividadeFaturamentoEncerrarCiclo != null) {
               cronogramaAtividadeFaturamentoEncerrarCiclo.setDataRealizacao(dataAtual);
               faturamentoAtividadeCronogrService.save(cronogramaAtividadeFaturamentoEncerrarCiclo);

               List<RotaCronograma> listaCronogramaRota = rotaCronogramaService
                       .consultarCronogramaRota(cronogramaAtividadeFaturamentoEncerrarCiclo);

               if (listaCronogramaRota != null && !listaCronogramaRota.isEmpty()) {
                  for (RotaCronograma cronogramaRota : listaCronogramaRota) {
                     cronogramaRota.setDataRealizada(dataAtual);
                     rotaCronogramaService.save(cronogramaRota);
                  }
               }
            }

            Map<String, Integer> referenciaCicloPosterior = null;
            if (faturamentoGrupoService
                    .isPeriodicidadeGrupoMultiplosCiclos(ultimoCronogramaFaturamento.getFaturamentoGrupo())) {
               FaturamentoAtividadeCronograma cronograma = faturamentoAtividadeCronogrService
                       .consultarCronogramaAtividadeFaturamento(
                               atividadeSistemaService.getById(chaveRegistrar),
                               grupoFaturamento);
               referenciaCicloPosterior = ToolDate.gerarProximaReferenciaCiclo(
                       cronogramaFaturamento.getAnoMesFaturamento(), cronogramaFaturamento.getCiclo(),
                       cronogramaFaturamento.getFaturamentoGrupo().getPeriodicidade().getMaximoDiasCiclo(),
                       cronograma.getDataFim().minusDays(1));
            } else {
               referenciaCicloPosterior = ToolDate.rolarReferenciaCiclo(cronogramaFaturamento.getAnoMesFaturamento(),
                       cronogramaFaturamento.getCiclo(),
                       cronogramaFaturamento.getFaturamentoGrupo().getPeriodicidade().getQuantidadeCiclos());
               referenciaNovoCronograma = ToolDate.rolarReferenciaCiclo(
                       ultimoCronogramaFaturamento.getAnoMesFaturamento(),
                       ultimoCronogramaFaturamento.getCiclo(),
                       ultimoCronogramaFaturamento.getFaturamentoGrupo().getPeriodicidade().getQuantidadeCiclos());
            }

            grupoFaturamento.setAnoMesFaturamento(referenciaCicloPosterior.get(ConstantesFatura.REFERENCIA_FATURA));
            grupoFaturamento.setNumeroCiclo((referenciaCicloPosterior.get(ConstantesFatura.CICLO)));
            faturamentoGrupoService.save(grupoFaturamento);
            logProcessamento.append("Grupos de Faturamento atualizados referencia ciclo: \r\n");
            logProcessamento.append("	").append(grupoFaturamento.getDescricao()).append(": ")
                    .append(grupoFaturamento.getAnoMesFaturamento()).append("-")
                    .append(grupoFaturamento.getNumeroCiclo()).append(" \r\n");

            logProcessamento.append("Emitindo Resumo Faturas por Ciclo...\r\n");
            byte[] relatorio = faturaService.gerarRelatorioResumoFaturaRubrica(anoMesreferecia, numeroCiclo);
            RelatorioComponent.salvarRelatorioEmDiretorio(relatorio, "RelatorioResumoCicloFaturamento");
         }

         if (anoMesreferecia == parametroAnoMesFechamento
                 && numeroCiclo != grupoFaturamento.getPeriodicidade().getQuantidadeCiclos()) {
            isGruposEncerrados = false;
         }
      }

      if (isGruposEncerrados) {

         logProcessamento.append("Grupos de Faturamento sem cronogramas atualizados referencia ciclo: \r\n");
         logProcessamento.append("	").append(grupoFaturamento.getDescricao()).append(": ")
                 .append(grupoFaturamento.getAnoMesFaturamento()).append("-")
                 .append(grupoFaturamento.getNumeroCiclo()).append(" \r\n");

         logProcessamento.append("Atualizando valores do parametro de anoMesReferencia ...\r\n");
         Integer menorReferencia = faturamentoCronogramaService 
                 .consultarMenorReferenciaCicloGrupoFaturamentoComCronograma();
         if (menorReferencia != null && menorReferencia != 0) {
            parametroAnoMesReferencia.setValor(menorReferencia.toString());
            parametroAnoMesReferencia.setDadosAuditoria(dadosAuditoria);

            parametroSistemaService.save(parametroAnoMesReferencia);
         }

         logProcessamento.append("Listando Faturas para Emissao...\r\n");
         logProcessamento.append("Emitindo Resumo Fechamento de faturamento ...\r\n");

         byte[] relatorio = faturaService.gerarRelatorioResumoFaturaRubrica(parametroAnoMesFechamento, null);
         RelatorioComponent.salvarRelatorioEmDiretorio(relatorio, "RelatorioResumoFaturamento");
         //Util.enviarEmailRotasSemCronogramas(processo);
      }

      logProcessamento.append("Finalizado!\r\n");

   }
}
