package com.stfn2.ggas.services.componentes.relatorio.relatorioFaturamento;

import com.stfn2.ggas.domain.Rota;
import com.stfn2.ggas.repositories.FaturaItemImpressaoRepository;
import com.stfn2.ggas.repositories.FaturaItemRepository;
import com.stfn2.ggas.repositories.FaturaItemTributacaoRepository;
import com.stfn2.ggas.repositories.RotaRepository;
import com.stfn2.ggas.services.componentes.relatorio.relatorioFaturamento.dto.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class SelectRelatorioAnaliseFaturamento {

   private static final String ID_GRUPO_FATURAMENTO = "idGrupoFaturamento";
   private static final String ID_ROTA = "idRota";
   private static final String ANO_MES_FATURAMENTO = "anoMesFaturamento";
   private static final String CICLO = "ciclo";
   private static final String DESCRICAO_GAS_NULL = "Consumo de Gás";


   private FaturaItemRepository faturaItemRepository;
   private FaturaItemImpressaoRepository faturaItemImpressaoRepository;
   private FaturaItemTributacaoRepository faturaItemTributacaoRepository;
   private RotaRepository rotaRepository;

   public SelectRelatorioAnaliseFaturamento(FaturaItemRepository faturaItemRepository,
                                            FaturaItemImpressaoRepository faturaItemImpressaoRepository,
                                            FaturaItemTributacaoRepository faturaItemTributacaoRepository,
                                            RotaRepository rotaRepository){
      this.faturaItemRepository = faturaItemRepository;
      this.faturaItemImpressaoRepository = faturaItemImpressaoRepository;
      this.faturaItemTributacaoRepository = faturaItemTributacaoRepository;
      this.rotaRepository = rotaRepository;
   }

   private String caseTributoICMS(String campo){
      return "sum(CASE WHEN tributo.descricao = 'ICMS' THEN " + campo + " ELSE 0 END)";
   }

   /**
    * Método para pesquisar dados do relatório de Análise de Faturamento
    *
    * @param filtro
    * @return
    */
   public List<RelatorioAnaliseFaturamentoDTO> getDadosRelatorioAnaliseFaturamento(Map<String, Object> filtro, Long rubricaGas) {

      Long idGrupoFaturamento = null;
      Integer anoMes = null;
      Integer ciclo = null;
      Long idRota = null;

      idGrupoFaturamento = (Long) filtro.get(ID_GRUPO_FATURAMENTO);
      idRota = (Long) filtro.get(ID_ROTA);
      anoMes = (Integer) filtro.get(ANO_MES_FATURAMENTO);
      ciclo = (Integer) filtro.get(CICLO);

      List<RelatorioAnaliseFaturamentoDTO> relatorioAnaliseFaturamentoDTO = new ArrayList<>();

      List<Rota> rotas = rotaRepository.obterRotasPeloFaturamentoGrupo(idGrupoFaturamento);

      relatorioAnaliseFaturamentoDTO =
              faturaItemTributacaoRepository.obterRelatorioAnaliseFaturamento(idGrupoFaturamento,
                      rubricaGas, anoMes, ciclo);

      /*
      var teste = BigDecimal.ZERO;

      StringBuilder hql = new StringBuilder();

      hql.append(SELECT);
      hql.append(" fatura.chavePrimaria as idFatura,");
      hql.append(" faturaItem.chavePrimaria as idFaturaItem,");
      hql.append(" cliente.nome as cliente,");
      hql.append(" pontoConsumo.codigoPontoConsumoSupervisorio as cil,");
      hql.append(" pontoConsumo.chavePrimaria as cdPontoConsumo,");
      hql.append(" pontoConsumo.descricao as nomePontoConsumo,");
      hql.append(" rota.numeroRota as dsRota,");
      hql.append(" grupoFaturamento.descricao as dsGrupoFaturamento,");
      hql.append(" segmento.descricao as dsSegmento,");

      hql.append(" historicoMedicao.dataLeituraInformada as dataFinal,");
      hql.append(" fatura.dataVencimento as dataVencimento,");
      hql.append(" historicoConsumo.diasConsumo as diasConsumo,");
      hql.append(caseTributoICMS("faturaItem.medidaConsumo") + " as consumo,");
      hql.append(caseTributoICMS("faturaItem.valorTotal") + " as consumoGas,");
      hql.append(caseTributoICMS("faturaItemTributacao.valorBaseCalculo") + " as baseICMS,");
      hql.append(caseTributoICMS("faturaItemTributacao.valorImposto") + " as icms,");
      hql.append(caseTributoICMS("faturaItemTributacao.valorBaseSubstituicao") + " as baseICMSSubs,");
      hql.append(caseTributoICMS("faturaItemTributacao.valorSubstituicao") + " as icmsSubstituto ");

      hql.append(" fatura.valorDiferimento as diferimentoTributario,");
      hql.append("fatura.valorTotal as consumoGas,");

      hql.append(FROM);
      hql.append("FaturaItemTributacaoImpl");
      hql.append(" faturaItemTributacao inner join faturaItemTributacao.faturaItem faturaItem ");
      hql.append(" inner join faturaItem.fatura fatura ");
      hql.append(" inner join fatura.cliente cliente");
      hql.append(" inner join fatura.pontoConsumo pontoConsumo ");
      hql.append(" inner join pontoConsumo.rota rota ");
      hql.append(" inner join fatura.segmento segmento ");
      hql.append(" inner join fatura.historicoConsumo historicoConsumo ");
      hql.append(" inner join fatura.historicoMedicao historicoMedicao ");
      hql.append(" inner join rota.grupoFaturamento grupoFaturamento ");
      hql.append(" inner join faturaItemTributacao.tributo tributo ");

      hql.append(WHERE);
      hql.append("fatura.anoMesReferencia = :anoMesFaturamento ");
      hql.append(" and fatura.numeroCiclo = :ciclo ");
      hql.append(" and rota.grupoFaturamento = :idGrupoFaturamento ");
      hql.append(" and rota.chavePrimaria = :idRota ");
      hql.append(" and fatura.habilitado = true ");
      hql.append(" and faturaItem.rubrica = :rubricaGas ");

      hql.append(GROUP);
      hql.append(" pontoConsumo.chavePrimaria,");
      hql.append(" pontoConsumo.descricao,");
      hql.append(" rota.numeroRota,");
      hql.append(" cliente.nome,");
      hql.append(" pontoConsumo.codigoPontoConsumoSupervisorio,");
      hql.append(" fatura.chavePrimaria,");
      hql.append(" fatura.dataVencimento,");
      hql.append(" faturaItem.chavePrimaria,");
      hql.append(" fatura.valorDiferimento,");
      hql.append(" fatura.valorTotal,");
      hql.append(" segmento.descricao,");
      hql.append(" grupoFaturamento.descricao,");
      hql.append(" historicoConsumo.diasConsumo,");
      hql.append(" historicoMedicao.dataLeituraInformada");

      hql.append(ORDER);
      hql.append(" cliente.nome,");
      hql.append(" segmento.descricao,");
      hql.append(" pontoConsumo.chavePrimaria");

      Query query = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hql.toString())
              .setResultTransformer(new GGASTransformer(RelatorioAnaliseFaturamentoDTO.class,
                      sessionFactory.getAllClassMetadata()));

      if (idRota != null) {
         query.setLong(ID_ROTA, idRota);
      }
      query.setLong(ID_GRUPO_FATURAMENTO, idGrupoFaturamento);
      query.setInteger(ANO_MES_FATURAMENTO, anoMesFaturamento);
      query.setInteger(CICLO, ciclo);
      query.setLong("rubricaGas", rubricaGas);*/

      return relatorioAnaliseFaturamentoDTO;
   }

   /**
    * Método para pesquisar dados de Juros do relatório de Análise de
    * Faturamento
    *
    *
    * @return
    */
   public List<SubJurosDTO> getDadosJuros(Long id, Long rubricaJuros) {

      List<SubJurosDTO> dadosJuros = new ArrayList<>();
      dadosJuros = faturaItemRepository.obterDadosJuros(id, rubricaJuros);

      /*StringBuilder hql = new StringBuilder();
      hql.append(SELECT);
      hql.append(" faturaItem.quantidade as quantidade,");
      hql.append(" faturaItem.valorUnitario as valorUnitario,");
      hql.append(" faturaItem.valorTotal as valorTotal");
      hql.append(FROM);
      hql.append("FaturaItemImpl");
      hql.append(" faturaItem");
      hql.append(WHERE);
      hql.append(" faturaItem.fatura.chavePrimaria = :id");
      hql.append(" and faturaItem.rubrica = :rubricaJuros");*/

      return dadosJuros;
   }

   /**
    * Método para pesquisar dados de Multa do relatório de Análise de
    * Faturamento
    *
    *
    * @return
    */
   public List<SubMultaDTO> getDadosMulta(Long id, Long rubricaMulta) {

      List<SubMultaDTO> dadosMulta = new ArrayList<>();
      dadosMulta = faturaItemRepository.obterDadosMulta(id, rubricaMulta);

    /*  StringBuilder hql = new StringBuilder();
      hql.append(SELECT);
      hql.append(" faturaItem.quantidade as quantidade,");
      hql.append(" faturaItem.valorUnitario as valorUnitario,");
      hql.append(" faturaItem.valorTotal as valorTotal");
      hql.append(FROM);
      hql.append("FaturaItemImpl");
      hql.append(" faturaItem");
      hql.append(WHERE);
      hql.append("faturaItem.fatura.chavePrimaria = :id");
      hql.append(" and faturaItem.rubrica = :rubricaMulta");*/

      return dadosMulta;
   }

   /**
    * Método para pesquisar dados de Gás Natural do relatório de Análise de
    * Faturamento
    *
    * @param id
    * @return
    */
   public List<SubGasNaturalDTO> getDadosFaturaItemImpressao(Long id) {

      List<SubGasNaturalDTO> listSubGasNatura = new ArrayList<>();
      listSubGasNatura = faturaItemImpressaoRepository.obterSubGasNatural(id);

     /* StringBuilder hql = new StringBuilder();
      hql.append(SELECT);
      hql.append(" COALESCE(faturaItemImpressao.descricaoImpressao,'" + "Consumo de Gás" + "') as descricao,");
      hql.append(" faturaItemImpressao.consumo as quantidade, \n");
      hql.append(" faturaItemImpressao.valorUnitario as valorUnitario, \n");
      hql.append(" faturaItemImpressao.valorTotal as valorTotal, \n");
      hql.append(" COALESCE(faturaItemImpressao.valorDesconto, 0) AS desconto \n");
      hql.append(FROM);
      hql.append("FaturaItemImpressaoImpl");
      hql.append(" faturaItemImpressao");
      hql.append(WHERE);
      hql.append("faturaItemImpressao.faturaItem.chavePrimaria = :id ");
      hql.append(GROUP);
      hql.append(" faturaItemImpressao.descricaoImpressao,");
      hql.append(" faturaItemImpressao.consumo,");
      hql.append(" faturaItemImpressao.valorUnitario,");
      hql.append(" faturaItemImpressao.valorTotal,");
      hql.append(" faturaItemImpressao.valorDesconto");*/


      return listSubGasNatura;
   }

   public List<Long> getRubricas(Long idFatura, List<Long> listaRubricasIgnorar) {

      List<Long> rubricas = new ArrayList<>();
      rubricas = faturaItemRepository.obterRubricas(idFatura, listaRubricasIgnorar);


     /* StringBuilder hql = new StringBuilder();
      hql.append(SELECT);
      hql.append(" faturaItem.rubrica.chavePrimaria");
      hql.append(FROM);
      hql.append("FaturaItemImpl faturaItem");
      hql.append(WHERE);
      hql.append("faturaItem.fatura.chavePrimaria = :idFatura");
      hql.append(" and faturaItem.rubrica.chavePrimaria NOT IN :listaRubricasIgnorar ");*/

      return rubricas;
   }

   public List<ValoresRubricasDTO> getDadosRubrica(Long idFatura, Long idRubrica) {

      List<ValoresRubricasDTO> valoresRubricasDTOS = new ArrayList<>();
      valoresRubricasDTOS = faturaItemRepository.obterItensPorRubrica(idFatura, idRubrica);

     /* for(FaturaItem faturaItem : faturaItems) {
         ValoresRubricasDTO valoresRubricasDTO = new ValoresRubricasDTO();
         valoresRubricasDTO.setQuantidade(faturaItem.getQuantidadeItem());
         valoresRubricasDTO.setValorTotal(faturaItem.getValorTotal());
         valoresRubricasDTOS.add(valoresRubricasDTO);
      }*/

      /*StringBuilder hql = new StringBuilder();
      hql.append(SELECT);
      hql.append(" faturaItem.quantidade as quantidade,");
      hql.append(" faturaItem.valorTotal as valorTotal");
      hql.append(FROM);
      hql.append("FaturaItemImpl");
      hql.append(" faturaItem");
      hql.append(WHERE);
      hql.append("faturaItem.fatura.chavePrimaria = :idFatura");
      hql.append(" and faturaItem.rubrica.chavePrimaria = :rubrica");*/

      return valoresRubricasDTOS;
   }
}
