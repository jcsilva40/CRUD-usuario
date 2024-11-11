package com.stfn2.ggas.services.componentes.relatorio.relatorioFaturamento;

import com.stfn2.ggas.services.componentes.relatorio.relatorioFaturamento.dto.RelatorioAnaliseFaturamentoDTO;
import com.stfn2.ggas.services.componentes.relatorio.relatorioFaturamento.dto.RubricaDebitoCreditoDTO;
import com.stfn2.ggas.services.componentes.relatorio.relatorioFaturamento.dto.SubGasNaturalDTO;
import com.stfn2.ggas.services.componentes.relatorio.relatorioFaturamento.dto.ValoresRubricasDTO;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class RelatorioFaturamento {

   private static final String SHEET_NAME = "RelatorioAnaliseDeFaturamento";
   private static final String TITULO_GAS_NATURAL = "GÁS NATURAL";
   private static final String TITULO_IMPOSTO = "IMPOSTO";
   private static final String TITULO_JUROS_MULTA = "JUROS e MULTA";
   private static final String TITULO_DEBITO_CREDITO = "OUTROS DÉBITOS E CRÉDITOS";
   private static final String TITULO_TOTAL = "TOTAIS";
   private static final String SUBTITULO_IMPOSTO_BASE_CALCULO = "Base de Cálculo ICMS";
   private static final String SUBTITULO_IMPOSTO_ICMS = "ICMS";
   private static final String SUBTITULO_IMPOSTO_BASE_CALCULO_SUBS = "Base de Cálculo ICMS Subs";
   private static final String SUBTITULO_IMPOSTO_ICMS_SUBS = "ICMS Subs";
   private static final String SUBTITULO_JUROS = "Juros de mora";
   private static final String SUBTITULO_MULTA = "Multa atraso";
   private static final String SUBTITULO_DEBITO = "Outros Débitos";
   private static final String SUBTITULO_CREDITO = "Outros Créditos";
   private static final String SUBTITULO_TOTAL = "Total Fatura";
   private static final String NUMERO = "Número";
   private static final String PONTO_CONSUMO = "Ponto de Consumo";
   private static final String CLIENTE = "Razão Social";
   private static final String CIL = "CIL";
   private static final String PERIODO = "Período";
   private static final String DATA_VENCIMENTO = "Data Vencimento";
   private static final String QUANTIDADE = "Quantidade";
   private static final String VALOR_MEDIO = "Valor Medio";
   private static final String VALOR_UNITARIO = "Valor Unitário";
   private static final String VALOR_TOTAL = "Valor Total";
   private static final String DESCONTO = "Desconto";
   private static final String DIFERIMENTO = "DIFERIMENTO";
   private static final String VALOR_DIFERIMENTO = "VALOR DIFERIMENTO";

   SelectRelatorioAnaliseFaturamento selectRelatorioAnaliseFaturamento;

   RelatorioFaturamento(SelectRelatorioAnaliseFaturamento selectRelatorioAnaliseFaturamento){
      this.selectRelatorioAnaliseFaturamento =  selectRelatorioAnaliseFaturamento;
   }

   public List<RelatorioAnaliseFaturamentoDTO> getListaAnaliseFaturamento(Map<String, Object> parametros){

      List<Long> listaRubricasIgnorar = new ArrayList<>();
      Long rubricaGas = 1L;
      Long rubricaMulta = 8L;
      Long rubricaJuros = 25L;
      Long rubricaIcmsRetido = 7L;
      Long rubricaTermoFixoMensalIss = 30L;
      Long rubricaTermoFixoMensalIcms = 31L;
      Long rubricaGasUltapassagemPGU = 33L;
      Long rubricaGasUltapassagemPGU2 = 34L;
      Long rubricaParcelaMargem = 35L;

      listaRubricasIgnorar.add(rubricaGas);
      listaRubricasIgnorar.add(rubricaMulta);
      listaRubricasIgnorar.add(rubricaJuros);
      listaRubricasIgnorar.add(rubricaIcmsRetido);
      listaRubricasIgnorar.add(rubricaTermoFixoMensalIss);
      listaRubricasIgnorar.add(rubricaTermoFixoMensalIcms);
      listaRubricasIgnorar.add(rubricaGasUltapassagemPGU);
      listaRubricasIgnorar.add(rubricaGasUltapassagemPGU2);
      listaRubricasIgnorar.add(rubricaParcelaMargem);


      List<Long> listaRubricaCredito = Arrays.asList(0L); //CpFormater.obterListaLongDeConstante(Constantes.C_LISTA_RUBRICA_CREDITO);

      List<RelatorioAnaliseFaturamentoDTO> listaFaturamento = selectRelatorioAnaliseFaturamento.getDadosRelatorioAnaliseFaturamento(parametros, rubricaGas);

      /*
       * Responsável por iterar na lista de RelatorioAnaliseFaturamentoDTO e adicionar
       * por meio de consultas no banco os juros, multas e lista de consumo de gás.
       * Para calcular do valor total de juros e multas e feito através da soma de
       * todos os valores totais de cada posição da lista.
       */
      for (RelatorioAnaliseFaturamentoDTO relatorioAnaliseFaturamentoDTO : listaFaturamento) {
         relatorioAnaliseFaturamentoDTO.setListaJuros(
                 selectRelatorioAnaliseFaturamento.getDadosJuros(
                         relatorioAnaliseFaturamentoDTO.getIdFatura(), rubricaJuros));
         relatorioAnaliseFaturamentoDTO.setListaMulta(
                 selectRelatorioAnaliseFaturamento.getDadosMulta(
                         relatorioAnaliseFaturamentoDTO.getIdFatura(), rubricaMulta));
         relatorioAnaliseFaturamentoDTO.setListaGasNatural(
                 selectRelatorioAnaliseFaturamento.getDadosFaturaItemImpressao(
                         relatorioAnaliseFaturamentoDTO.getIdFaturaItem()));
         relatorioAnaliseFaturamentoDTO.sortListaGasNatural();
         //Lista de Rubricas já utilizadas na planilha para não se repetir

         List<Long> listaRubrica = selectRelatorioAnaliseFaturamento.getRubricas(relatorioAnaliseFaturamentoDTO.getIdFatura(), listaRubricasIgnorar);
         relatorioAnaliseFaturamentoDTO.setListaRubricaCredito(listaRubricaCredito);
         List<RubricaDebitoCreditoDTO> listaRubricaDebitoCredito = new ArrayList<>();
         if(listaRubrica != null && !listaRubrica.isEmpty()){
            for(Long rubrica : listaRubrica){
               RubricaDebitoCreditoDTO rubricaDebitoCredito = new RubricaDebitoCreditoDTO();
               List<ValoresRubricasDTO> listaValoresRubricas = selectRelatorioAnaliseFaturamento.getDadosRubrica(relatorioAnaliseFaturamentoDTO.getIdFatura(), rubrica);
               rubricaDebitoCredito.setIdRubrica(rubrica);
               rubricaDebitoCredito.setListaValoresRubricas(listaValoresRubricas);
               listaRubricaDebitoCredito.add(rubricaDebitoCredito);
            }
         }
         relatorioAnaliseFaturamentoDTO.setListaRubricaDebitoCredito(listaRubricaDebitoCredito);
      }

      return listaFaturamento;
   }


   public Workbook criarPlanilhaRelatorioAnaliseFaturamento(List<RelatorioAnaliseFaturamentoDTO> listaFaturamento) {

      // Cria um novo workbook
      Workbook workbook = new XSSFWorkbook();

      // Cria uma nova planilha
      Sheet sheet = workbook.createSheet(SHEET_NAME);

      int numeroLinha = 0;
      // Criando a primeira linha da planilha que é a de número 0
      Row rowLinha1 = sheet.createRow(numeroLinha);

      int comecoColuna = 0;
      int quantidadePrimeiraColuna = 6;
      int finalPrimeiraColuna = comecoColuna + (quantidadePrimeiraColuna - 1);

      int comecoGasNatural = finalPrimeiraColuna + 1;
      int maiorTamanhoGasNatural = listaFaturamento.stream()
              .mapToInt(faturamento -> faturamento.getListaGasNatural().size())
              .max()
              .orElse(0);

      boolean existeDebito = listaFaturamento.stream()
              .anyMatch(faturamento -> faturamento.getDebitoQuantidadeTotal().compareTo(BigDecimal.ZERO) > 0);

      boolean existeCredito = listaFaturamento.stream()
              .anyMatch(faturamento -> faturamento.getCreditoQuantidadeTotal().compareTo(BigDecimal.ZERO) > 0);

      boolean existeDiferimento = listaFaturamento.stream()
              .anyMatch(faturamento -> faturamento.getDiferimentoTributario().compareTo(BigDecimal.ZERO) > 0);

      int quantidadeCampoGasNatural = 4;
      int finalGasNatural = comecoGasNatural + (maiorTamanhoGasNatural * quantidadeCampoGasNatural) - 1;

      int comecoImposto = finalGasNatural + 1;
      int finalImposto = comecoImposto + 11;

      int comecoJurosMulta = finalImposto + 1;
      int finalJurosMulta = comecoJurosMulta + 5;

      int comecoDebito = 0;
      int finalDebito = 0;

      int comecoCredito = 0;
      int finalCredito = 0;

      int comecoTotal = 0;
      int finalTotal = 0;

      int comecoDiferimento = 0;
      int finalDiferimento = 0;

      int finalPlanilha = 0;

      if(existeDebito){
         comecoDebito = finalJurosMulta + 1;
         finalDebito = comecoDebito + 1;
         comecoTotal = finalDebito + 1;
      }
      if(existeDebito && existeCredito){
         comecoCredito = finalDebito + 1;
         finalCredito = comecoCredito + 1;
         comecoTotal = finalCredito + 1;
      }
      if(!existeDebito && existeCredito){
         comecoCredito = finalJurosMulta + 1;
         finalCredito = comecoCredito + 1;
         comecoTotal = finalCredito + 1;
      }
      if(!existeDebito && !existeCredito){
         comecoTotal = finalJurosMulta + 1;
      }

      finalTotal = comecoTotal + 2;
      finalPlanilha = finalTotal;

      if(existeDiferimento){
         comecoDiferimento = finalTotal + 1;
         finalDiferimento = comecoDiferimento;
         finalPlanilha = finalDiferimento;
      }


      sheet.addMergedRegion(setMergeTitulo(workbook, numeroLinha, numeroLinha, comecoColuna, finalPrimeiraColuna));
      setCellTitulo(workbook, rowLinha1, comecoColuna, "");

      //Criando as colunas de Gás Natural com Merge
      sheet.addMergedRegion(setMergeTitulo(workbook, numeroLinha, numeroLinha, comecoGasNatural, finalGasNatural));
      setCellTitulo(workbook, rowLinha1, comecoGasNatural, TITULO_GAS_NATURAL);

      //Criando as colunas de Imposto com Merge
      sheet.addMergedRegion(setMergeTitulo(workbook, numeroLinha, numeroLinha, comecoImposto, finalImposto));
      setCellTitulo(workbook, rowLinha1, comecoImposto, TITULO_IMPOSTO);

      //Criando as colunas de Juros e Multa com Merge
      sheet.addMergedRegion(setMergeTitulo(workbook, numeroLinha, numeroLinha, comecoJurosMulta, finalJurosMulta));
      setCellTitulo(workbook, rowLinha1, comecoJurosMulta, TITULO_JUROS_MULTA);

      if(existeDebito && existeCredito){
         sheet.addMergedRegion(setMergeTitulo(workbook, numeroLinha, numeroLinha, comecoDebito, finalCredito));
         setCellTitulo(workbook, rowLinha1, comecoDebito, TITULO_DEBITO_CREDITO);
      }
      if(existeDebito && !existeCredito){
         sheet.addMergedRegion(setMergeTitulo(workbook, numeroLinha, numeroLinha, comecoDebito, finalDebito));
         setCellTitulo(workbook, rowLinha1, comecoDebito, TITULO_DEBITO_CREDITO);
      }
      if(!existeDebito && existeCredito){
         sheet.addMergedRegion(setMergeTitulo(workbook, numeroLinha, numeroLinha, comecoCredito, finalCredito));
         setCellTitulo(workbook, rowLinha1, comecoCredito, TITULO_DEBITO_CREDITO);
      }
      //Criando as colunas de Total com Merge
      sheet.addMergedRegion(setMergeTitulo(workbook, numeroLinha, numeroLinha, comecoTotal, finalTotal));
      setCellTitulo(workbook, rowLinha1, comecoTotal, TITULO_TOTAL);

      if(existeDiferimento){
         if(finalDiferimento > comecoDiferimento){
            sheet.addMergedRegion(setMergeTitulo(workbook, numeroLinha, numeroLinha, comecoDiferimento, finalDiferimento));
         }
         setCellTitulo(workbook, rowLinha1, comecoDiferimento, DIFERIMENTO);
      }

      numeroLinha++;
      //Criando a segunda linha que é de número 1
      Row rowLinha2 = sheet.createRow(numeroLinha);

      sheet.addMergedRegion(setMergeTitulo(workbook, numeroLinha, numeroLinha, comecoColuna, finalPrimeiraColuna));
      setCellTitulo(workbook, rowLinha2, comecoColuna, "");

      List<String> listaGasNaturalString = listaFaturamento.stream()
              .map(faturamento -> faturamento.getListaGasNatural())
              .max(Comparator.comparing(List::size))
              .orElseThrow(() -> new IllegalArgumentException("Lista vazia"))
              .stream()
              .map(gasNatural -> gasNatural.getDescricao())
              .collect(Collectors.toList());

      int gasNaturalDescricao = comecoGasNatural;
      for (String descricaoGasNatural : listaGasNaturalString){

         sheet.addMergedRegion(setMergeTitulo(workbook, numeroLinha, numeroLinha, gasNaturalDescricao, gasNaturalDescricao + (quantidadeCampoGasNatural - 1)));
         setCellTitulo(workbook, rowLinha2, gasNaturalDescricao,descricaoGasNatural);
         gasNaturalDescricao += quantidadeCampoGasNatural;
      }

      int comecoImpostoBaseCalculo = comecoImposto;
      int finalImpostoBaseCalculo = comecoImpostoBaseCalculo + 2;

      int comecoImpostoICMS = finalImpostoBaseCalculo + 1;
      int finalImpostoICMS = comecoImpostoICMS + 2;

      int comecoImpostoBaseCalculoSubs = finalImpostoICMS + 1;
      int finalImpostoBaseCalculoSubs = comecoImpostoBaseCalculoSubs + 2;

      int comecoImpostoICMSSubs = finalImpostoBaseCalculoSubs + 1;
      int finalImpostoICMSSubs = comecoImpostoICMSSubs + 2;


      //Subtiulo Base de Calculo
      sheet.addMergedRegion(setMergeTitulo(workbook, numeroLinha, numeroLinha, comecoImpostoBaseCalculo, finalImpostoBaseCalculo));
      setCellTitulo(workbook, rowLinha2, comecoImpostoBaseCalculo, SUBTITULO_IMPOSTO_BASE_CALCULO);

      //Subtiulo ICMS
      sheet.addMergedRegion(setMergeTitulo(workbook, numeroLinha, numeroLinha, comecoImpostoICMS, finalImpostoICMS));
      setCellTitulo(workbook, rowLinha2, comecoImpostoICMS, SUBTITULO_IMPOSTO_ICMS);

      //Subtiulo Base de Calculo Substituido
      sheet.addMergedRegion(setMergeTitulo(workbook, numeroLinha, numeroLinha, comecoImpostoBaseCalculoSubs, finalImpostoBaseCalculoSubs));
      setCellTitulo(workbook, rowLinha2, comecoImpostoBaseCalculoSubs, SUBTITULO_IMPOSTO_BASE_CALCULO_SUBS);

      //Subtitulo ICMS Substituido
      sheet.addMergedRegion(setMergeTitulo(workbook, numeroLinha, numeroLinha, comecoImpostoICMSSubs, finalImpostoICMSSubs));
      setCellTitulo(workbook, rowLinha2, comecoImpostoICMSSubs, SUBTITULO_IMPOSTO_ICMS_SUBS);


      int comecoJuros = comecoJurosMulta;
      int finalJuros = comecoJuros +2;

      int comecoMulta = finalJuros + 1;
      int finalMulta = comecoMulta +2;

      sheet.addMergedRegion(setMergeTitulo(workbook, numeroLinha, numeroLinha, comecoJuros, finalJuros));
      setCellTitulo(workbook, rowLinha2, comecoJuros, SUBTITULO_JUROS);

      sheet.addMergedRegion(setMergeTitulo(workbook, numeroLinha, numeroLinha, comecoMulta, finalMulta));
      setCellTitulo(workbook, rowLinha2, comecoMulta, SUBTITULO_MULTA);

      if(existeDebito){
         sheet.addMergedRegion(setMergeTitulo(workbook, numeroLinha, numeroLinha, comecoDebito, finalDebito));
         setCellTitulo(workbook, rowLinha2, comecoDebito, SUBTITULO_DEBITO);
      }

      if(existeCredito){
         sheet.addMergedRegion(setMergeTitulo(workbook, numeroLinha, numeroLinha, comecoCredito, finalCredito));
         setCellTitulo(workbook, rowLinha2, comecoCredito, SUBTITULO_CREDITO);
      }

      sheet.addMergedRegion(setMergeTitulo(workbook, numeroLinha, numeroLinha, comecoTotal, finalTotal));
      setCellTitulo(workbook, rowLinha2, comecoTotal, SUBTITULO_TOTAL);

      if(existeDiferimento){
         if(finalDiferimento > comecoDiferimento){
            sheet.addMergedRegion(setMergeTitulo(workbook, numeroLinha, numeroLinha, comecoDiferimento, finalDiferimento));
         }
         setCellTitulo(workbook, rowLinha2, comecoDiferimento, VALOR_DIFERIMENTO);
      }

      numeroLinha++;
      //Criando a terceira linha que é de número 2
      Row rowLinha3 = sheet.createRow(numeroLinha);
      int comecoAtual = comecoColuna;
      setCellTitulo(workbook, rowLinha3, comecoAtual++, NUMERO);
      setCellTitulo(workbook, rowLinha3, comecoAtual++, CLIENTE);
      setCellTitulo(workbook, rowLinha3, comecoAtual++, CIL);
      setCellTitulo(workbook, rowLinha3, comecoAtual++, PONTO_CONSUMO);
      setCellTitulo(workbook, rowLinha3, comecoAtual++, PERIODO);
      setCellTitulo(workbook, rowLinha3, comecoAtual++, DATA_VENCIMENTO);
      int contador = 0;
      for(int i = comecoGasNatural; i <= finalImposto; i++){
         if (contador >= quantidadeCampoGasNatural){
            contador = 0;
         }
         switch (contador) {
            case 0:
               setCellTitulo(workbook, rowLinha3, i, QUANTIDADE);
               break;
            case 1:
               setCellTitulo(workbook, rowLinha3, i, VALOR_UNITARIO);
               break;
            case 2:
               setCellTitulo(workbook, rowLinha3, i, VALOR_TOTAL);
               break;
            case 3:
               setCellTitulo(workbook, rowLinha3, i, DESCONTO);
               break;
            default:
               break;
         }
         contador++;
      }

      //Subtitulo Juros
      setCellTitulo(workbook, rowLinha3, comecoJuros, QUANTIDADE);
      setCellTitulo(workbook, rowLinha3, comecoJuros + 1, VALOR_MEDIO);
      setCellTitulo(workbook, rowLinha3, comecoJuros + 2, VALOR_TOTAL);

      //Subtitulo Multa
      setCellTitulo(workbook, rowLinha3, comecoMulta, QUANTIDADE);
      setCellTitulo(workbook, rowLinha3, comecoMulta + 1, VALOR_MEDIO);
      setCellTitulo(workbook, rowLinha3, comecoMulta + 2, VALOR_TOTAL);

      if(existeDebito){
         //Subtitulo Debito
         setCellTitulo(workbook, rowLinha3, comecoDebito, QUANTIDADE);
         setCellTitulo(workbook, rowLinha3, finalDebito, VALOR_TOTAL);
      }
      if(existeCredito){
         //Subtitulo Credito
         setCellTitulo(workbook, rowLinha3, comecoCredito, QUANTIDADE);
         setCellTitulo(workbook, rowLinha3, finalCredito, VALOR_TOTAL);
      }

      if(existeDiferimento){
         setCellTitulo(workbook, rowLinha3, comecoDiferimento, VALOR_TOTAL);
      }

      //Subtitulo Total
      setCellTitulo(workbook, rowLinha3, comecoTotal, QUANTIDADE);
      setCellTitulo(workbook, rowLinha3, comecoTotal + 1, VALOR_UNITARIO);
      setCellTitulo(workbook, rowLinha3, comecoTotal + 2, VALOR_TOTAL);

      numeroLinha++;
      //Criando as linhas de dados segunda linha que é de número 1
      for(RelatorioAnaliseFaturamentoDTO faturamento : listaFaturamento){
         Row row = sheet.createRow(numeroLinha);
         comecoAtual = comecoColuna;
         setCellLong(workbook, row, comecoAtual++, faturamento.getCdPontoConsumo());
         setCell(workbook, row, comecoAtual++, faturamento.getCliente());
         setCell(workbook, row, comecoAtual++, faturamento.getCil());
         setCell(workbook, row, comecoAtual++, faturamento.getNomePontoConsumo());
         String periodo = faturamento.getDataInicial() + "-" + faturamento.getDataFinal();
         setCell(workbook, row, comecoAtual++, periodo);
         String dataVencimento = faturamento.getDataVencimento();
         setCell(workbook, row, comecoAtual++, dataVencimento);
         int j = 0;
         contador = 0;
         for(int i=comecoGasNatural; i <= finalGasNatural; i++){
            if(contador >= quantidadeCampoGasNatural){
               contador = 0;
            }
            List<SubGasNaturalDTO> listaGasNatural = faturamento.getListaGasNatural();

            switch (contador) {
               case 0:
                  if(listaGasNatural != null && j < listaGasNatural.size() && listaGasNatural.get(j) != null && listaGasNatural.get(j).getQuantidade() != null){
                     setCellDecimal(workbook, row, i, listaGasNatural.get(j).getQuantidade());
                     break;
                  }
                  setCellDecimal(workbook, row, i, BigDecimal.ZERO);
                  break;
               case 1:
                  if(listaGasNatural != null && j < listaGasNatural.size() && listaGasNatural.get(j) != null && listaGasNatural.get(j).getValorUnitario() != null){
                     setCellDecimal(workbook, row, i, listaGasNatural.get(j).getValorUnitario());
                     break;
                  }
                  setCellDecimal(workbook, row, i, BigDecimal.ZERO);
                  break;
               case 2:
                  if(listaGasNatural != null && j < listaGasNatural.size() && listaGasNatural.get(j) != null && listaGasNatural.get(j).getValorTotal() != null){
                     setCellMonetario(workbook, row, i, listaGasNatural.get(j).getValorTotal());
                     break;
                  }
                  setCellDecimal(workbook, row, i, BigDecimal.ZERO);
                  break;
               case 3:
                  if(listaGasNatural != null && j < listaGasNatural.size() && listaGasNatural.get(j) != null && listaGasNatural.get(j).getDesconto() != null){
                     setCellDecimal(workbook, row, i, listaGasNatural.get(j).getDesconto());
                     j++;
                     break;
                  }
                  setCellDecimal(workbook, row, i, BigDecimal.ZERO);
                  j++;
                  break;
               default:
                  break;
            }
            contador++;
         }

         //Base Calculo ICMS
         int comecoImpostoBaseCalculoAtual = comecoImpostoBaseCalculo;
         setCellDecimal(workbook, row, comecoImpostoBaseCalculoAtual++, BigDecimal.ONE);
         setCellMonetario(workbook, row, comecoImpostoBaseCalculoAtual++, faturamento.getBaseICMS());
         setCellDecimal(workbook, row, comecoImpostoBaseCalculoAtual++, faturamento.getBaseICMS());

         //ICMS
         int comecoImpostoICMSAtual = comecoImpostoICMS;
         setCellDecimal(workbook, row, comecoImpostoICMSAtual++, BigDecimal.ONE);
         setCellMonetario(workbook, row, comecoImpostoICMSAtual++, faturamento.getIcms());
         setCellDecimal(workbook, row, comecoImpostoICMSAtual++, faturamento.getIcms());

         //Base Calculo ICMS Subs
         int comecoImpostoBaseCalculoSubsAtual = comecoImpostoBaseCalculoSubs;
         setCellDecimal(workbook, row, comecoImpostoBaseCalculoSubsAtual++, BigDecimal.ONE);
         setCellMonetario(workbook, row, comecoImpostoBaseCalculoSubsAtual++, faturamento.getBaseICMSSubs());
         setCellDecimal(workbook, row, comecoImpostoBaseCalculoSubsAtual++, faturamento.getBaseICMSSubs());

         //ICMS Subs
         int comecoImpostoICMSSubsAtual = comecoImpostoICMSSubs;
         setCellDecimal(workbook, row, comecoImpostoICMSSubsAtual++, BigDecimal.ONE);
         setCellMonetario(workbook, row, comecoImpostoICMSSubsAtual++, faturamento.getIcmsSubstituto());
         setCellDecimal(workbook, row, comecoImpostoICMSSubsAtual++, faturamento.getIcmsSubstituto());

         //Juros
         int comecoJurosAtual = comecoJuros;
         setCellDecimal(workbook, row, comecoJurosAtual++, faturamento.getJurosQuantidadeTotal());
         setCellDecimal(workbook, row, comecoJurosAtual++, faturamento.getJurosValorMedio());
         setCellMonetario(workbook, row, comecoJurosAtual++, faturamento.getJurosTotal());

         //Multa
         int comecoMultaAtual = comecoMulta;
         setCellDecimal(workbook, row, comecoMultaAtual++, faturamento.getMultaQuantidadeTotal());
         setCellDecimal(workbook, row, comecoMultaAtual++, faturamento.getMultaValorMedio());
         setCellMonetario(workbook, row, comecoMultaAtual++, faturamento.getMultaTotal());

         if(existeDebito){
            //Debito
            setCellDecimal(workbook, row, comecoDebito, faturamento.getDebitoQuantidadeTotal());
            setCellMonetario(workbook, row, finalDebito, faturamento.getDebitoValorTotal());
         }

         if(existeCredito){
            //Credito
            setCellDecimal(workbook, row, comecoCredito, faturamento.getCreditoQuantidadeTotal());
            setCellMonetario(workbook, row, finalCredito, faturamento.getCreditoValorTotal());
         }

         if(existeDiferimento){
            setCellMonetario(workbook, row, comecoDiferimento, faturamento.getDiferimentoTributario());
         }

         //Total
         int comecoTotalAtual = comecoTotal;
         setCellDecimal(workbook, row, comecoTotalAtual++, BigDecimal.ONE);
         setCellDecimal(workbook, row, comecoTotalAtual++, faturamento.getValorTotal());
         setCellMonetario(workbook, row, comecoTotalAtual++, faturamento.getValorTotal());

         numeroLinha++;
      }

      //Adicionar os filtros
      sheet.setAutoFilter(new CellRangeAddress(2, 2, comecoColuna, finalPlanilha));

      // Autossize de todas as colunas
      for(int i = comecoColuna; i <= finalPlanilha; i++) {
         sheet.autoSizeColumn(i);
      }

      return workbook;
   }

   private CellRangeAddress setMergeTitulo(Workbook workbook, int linhaInicial, int linhaFinal, int colunaInicial, int colunaFinal){
      Sheet sheet = workbook.getSheet(SHEET_NAME);
      CellRangeAddress merge = new CellRangeAddress(linhaInicial, linhaFinal, colunaInicial, colunaFinal);

      // Aplica o estilo a cada célula na região
      for (int rowNum = merge.getFirstRow(); rowNum <= merge.getLastRow(); rowNum++) {
         Row row = sheet.getRow(rowNum);
         if (row == null) {
            row = sheet.createRow(rowNum);
         }
         for (int colNum = merge.getFirstColumn(); colNum <= merge.getLastColumn(); colNum++) {
            Cell currentCell = row.getCell(colNum);
            if (currentCell == null) {
               currentCell = row.createCell(colNum);
            }
            currentCell.setCellStyle(setTituloStyle(workbook));
         }
      }

      return merge;
   }

   private Cell setCellTitulo(Workbook workbook, Row linhaRow, int inicioColuna, String titulo){
      Cell cell = linhaRow.createCell(inicioColuna);
      cell.setCellValue(titulo);
      cell.setCellStyle(setTituloStyle(workbook));
      return cell;
   }

   private CellStyle setTituloStyle(Workbook workbook){
      CellStyle style = workbook.createCellStyle();

      // Definir a fonte para Arial 11 e Negrito
      Font font = workbook.createFont();
      font.setFontName("Arial");
      font.setFontHeightInPoints((short) 11);
      font.setBold(true);
      style.setFont(font);

      // Definir o alinhamento vertical para Botton e horizontal para Center
      style.setVerticalAlignment(VerticalAlignment.BOTTOM);
      style.setAlignment(HorizontalAlignment.CENTER);

      // Definir a borda
      style.setBorderTop(BorderStyle.THIN);
      style.setBorderBottom(BorderStyle.THIN);
      style.setBorderLeft(BorderStyle.THIN);
      style.setBorderRight(BorderStyle.THIN);

      // Definir o background para cinza
      style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
      style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

      return style;
   }

   private Cell setCell(Workbook workbook, Row linhaRow, int inicioColuna, String titulo){
      Cell cell = linhaRow.createCell(inicioColuna);
      cell.setCellValue(titulo);
      cell.setCellStyle(setStyle(workbook));
      return cell;
   }

   private Cell setCellLong(Workbook workbook, Row linhaRow, int inicioColuna, Long cellValue){
      Cell cell = linhaRow.createCell(inicioColuna);
      cell.setCellValue(cellValue);
      cell.setCellStyle(setStyle(workbook));
      return cell;
   }

   private Cell setCellDecimal(Workbook workbook, Row linhaRow, int inicioColuna, BigDecimal cellValue){
      Cell cell = linhaRow.createCell(inicioColuna);
      Double doubleValue = cellValue.doubleValue();
      cell.setCellValue(doubleValue);
      CellStyle style = setStyle(workbook);
      DataFormat format = workbook.createDataFormat();
      style.setDataFormat(format.getFormat("0.0000"));
      cell.setCellStyle(style);
      return cell;
   }

   private Cell setCellMonetario(Workbook workbook, Row linhaRow, int inicioColuna, BigDecimal cellValue){
      Cell cell = linhaRow.createCell(inicioColuna);
      Double doubleValue = cellValue.doubleValue();
      cell.setCellValue(doubleValue);
      CellStyle style = setStyle(workbook);
      //Para adicionar o formato de currency
      DataFormat format = workbook.createDataFormat();
      style.setDataFormat(format.getFormat("R$ #,##0.00"));
      cell.setCellStyle(style);
      return cell;
   }

   private CellStyle setStyle(Workbook workbook){
      CellStyle style = workbook.createCellStyle();

      // Definir a fonte para Arial 11 e Negrito
      Font font = workbook.createFont();
      font.setFontName("Arial");
      font.setFontHeightInPoints((short) 11);
      font.setBold(false);
      style.setFont(font);

      // Definir o alinhamento vertical para Botton e horizontal para Center
      style.setVerticalAlignment(VerticalAlignment.TOP);
      style.setAlignment(HorizontalAlignment.LEFT);

      // Definir a borda
      style.setBorderTop(BorderStyle.THIN);
      style.setBorderBottom(BorderStyle.THIN);
      style.setBorderLeft(BorderStyle.THIN);
      style.setBorderRight(BorderStyle.THIN);

      return style;
   }
}
