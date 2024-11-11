package com.stfn2.ggas.tools;

import com.stfn2.ggas.constante.Constantes;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class ToolDate {

   static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
   private static final int NUMERO_PARA_ADICIONAR_ZERO = 2;
   private static final String ZERO = "0";
   private static final String FORMATO_DATA_BR = "dd/MM/yyyy";
   private static final int INDICE_QUATRO = 4;
   private static final int INDICE_SEIS = 6;
   private static final int INDICE_DOIS = 2;
   private static final int INDICE_TRES = 3;
   private static final int INDICE_SETE = 7;
   private static final int PRIMEIRO_CICLO = 1;
   private static final int AUXILIAR_DOIS = 2;
   private static final int AUXILIAR_CINCO = 5;
   private static final int AUXILIAR_SEIS = 6;
   private static final int AUXILIAR_OITO = 8;
   private static final int AUXILIAR_NOVE = 9;
   private static final int AUXILIAR_DEZ = 10;
   private static final int AUXILIAR_DOZE = 12;
   private static final int AUXILIAR_CEM = 100;
   private static final int AUXILIAR_CINQUENTA = 50;
   private static final int BASE_DEZ = 10;

   public static String dateToString(Date date) {
      return sdf.format(date);
   }

   public static LocalDate getDataAtualLocalDate(){
       ZoneId fusoHorario = ZoneId.of("America/Sao_Paulo");
       LocalDate dataAtual = LocalDate.now(fusoHorario);
       return dataAtual;
   }

   public static LocalDateTime getDataAtualLocalDateTime(){
       ZoneId fusoHorario = ZoneId.of("America/Sao_Paulo");
       ZonedDateTime dateTimeInSaoPaulo = ZonedDateTime.now(fusoHorario);
       LocalDateTime dataAtual = dateTimeInSaoPaulo.toLocalDateTime();
       return dataAtual;
   }

   public static String converterDataParaString(LocalDate data, boolean imprimirHora) {
      String formato = null;
      if (imprimirHora) {
         formato = Constantes.FORMATO_DATA_HORA_BR;
      } else {
         formato = Constantes.FORMATO_DATA_BR;
      }
      DateFormat df = new SimpleDateFormat(formato);
      return df.format(data);
   }

   public static String dateToString(Date date, String formato) {
      SimpleDateFormat sdfcustom = new SimpleDateFormat(formato);
      return sdfcustom.format(date);
   }

   public static LocalDateTime stringToLocalDateTime(String data) {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
      LocalDateTime localDateTime = LocalDateTime.parse(data, formatter);
      return localDateTime;
   }

   public static LocalDate converterCampoStringParaData(String rotulo, String strData, String formato) {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato);
      LocalDate localDate = LocalDate.parse(strData, formatter);

      Date data = null;
      SimpleDateFormat formatador = null;

      return localDate;
   }

   public static Integer obterAnoMes(LocalDate data) {

      String ano = String.valueOf(data.getYear());
      int mesData = data.getMonthValue();
      String mes = String.valueOf(mesData);
      if (mesData < AUXILIAR_DEZ) {
         mes = ZERO + mes;
      }
      return Integer.valueOf(ano + mes);
   }

   public static String getCaminhoAnoMesDia() {
      LocalDate localDate = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).toLocalDate();
      return localDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd/"));
   }

   public static String horarioTimestamp() {
      LocalDateTime localDateTime = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).toLocalDateTime();
      long tempoEmMillisegundos = localDateTime.atZone(ZoneId.of("America/Sao_Paulo")).toInstant().toEpochMilli();
      return String.valueOf(tempoEmMillisegundos);
   }

   public static boolean isDiaValido(Integer dia) {
      if (Objects.isNull(dia)) return false;
      return dia >= 1 && dia <= 31;
   }

   public static boolean isMesValido(Integer mes) {
      if (Objects.isNull(mes)) return false;
      return mes >= 1 && mes <= 12;
   }

   public static boolean isAnoValido(Integer ano) {
      if (Objects.isNull(ano)) return false;
      return ano >= 1950 && ano <= 2100;
   }

   public static LocalDate intToDate(Integer year, Integer month, Integer day) {
      return LocalDate.of(year, month, day);
   }

   public static LocalDate getDateFromStringYYYYHifenMM(String anoMes) {
      String ano = anoMes.substring(0, 4);
      String mes = anoMes.substring(5, 7);
      return intToDate(Integer.valueOf(ano), Integer.valueOf(mes), 1);
   }

   public static Integer getYearOfDate(LocalDate date) {
      return date.getYear();
   }

   public static Integer getMonthOfDate(LocalDate date) {
      return date.getMonthValue();
   }

   public static Integer getLastDayOfMonth(LocalDate date) {
      int month = getMonthOfDate(date);
      int year = getYearOfDate(date);
      if (year % 4 == 0 && month == 2 && (year % 400 == 0 || year % 100 != 0)) {
         return 29;
      } else if (month == 2) {
         return 28;
      } else if (month == 4 || month == 6 || month == 9 || month == 11) {
         return 30;
      } else {
         return 31;
      }
   }

   public static LocalDate lastDayOfMonth(LocalDate dt) {
      int ano = getYearOfDate(dt);
      int mes = getMonthOfDate(dt);
      int ultimoDia = getLastDayOfMonth(dt);
      return intToDate(ano, mes, ultimoDia);
   }

   public static LocalDate getDayInNextMonth(LocalDate date, int day) {
      int year = date.getYear();
      int month = date.getMonthValue() + 1;

      if (month == 13) {
         month = 1;
         year++;
      }

      if (day > 28 && month == 2) {
         if (isLeapYear(date)) {
            day = 29;
         } else {
            day = 28;
         }
      } else if (day == 31 && (month == 4 || month == 6 || month == 9 || month == 11)) {
         day = 30;
      }
      return LocalDate.of(year, month, day);
   }

   public static Boolean isLeapYear(LocalDate dt) {
      int year = dt.getYear();
      return year % 4 == 0 && (year % 400 == 0 || year % 100 != 0);
   }

   public static LocalDate getDateFromStringYYYYHifenMMHifenDD(String dateString) {
      String dia = dateString.substring(8, 10);
      String ano = dateString.substring(0, 4);
      String mes = dateString.substring(5, 7);
      return intToDate(Integer.valueOf(ano), Integer.valueOf(mes), Integer.valueOf(dia));
   }

   public static LocalDate addOneDay(LocalDate date) {
      return date.plusDays(1);
   }

   public static LocalDate getFirstDayOfMonth(LocalDate date) {
      return date.withDayOfMonth(1);
   }

   public static LocalDate getLastDayOfMonthAsDate(LocalDate date) {
      int lastDay = getLastDayOfMonth(date);
      return LocalDate.of(date.getYear(), date.getMonth(), lastDay);
   }

   public static Map<Integer, Integer> diasPorMesPeriodo(LocalDate dataInicial, LocalDate dataFinal) {
      Map<Integer, Integer> diasPorMes = new HashMap<>();

      LocalDate inicio = dataInicial.withDayOfMonth(1);
      LocalDate fim = dataFinal.withDayOfMonth(dataFinal.lengthOfMonth());

      while (inicio.isBefore(dataFinal) || inicio.isEqual(dataFinal)) {
         LocalDate finalDoMes = inicio.withDayOfMonth(inicio.lengthOfMonth());
         if (finalDoMes.isAfter(dataFinal)) {
            finalDoMes = dataFinal;
         }

         long dias = diasEntre(inicio, finalDoMes) + 1; //+1 pra add ultimo dia de calculo
         int mes = inicio.getMonthValue();
         diasPorMes.put(mes, (int) diasPorMes.getOrDefault(mes, 0) + (int) dias);

         inicio = finalDoMes.plusDays(1).withDayOfMonth(1);
      }

      return diasPorMes;
   }

   public static Long diasEntre(LocalDate dataInicial, LocalDate dataFinal) {
      return ChronoUnit.DAYS.between(dataInicial, dataFinal);
   }

   public static Integer diferencaDiasEntreDatas(LocalDate data1, LocalDate data2) {
      return Integer.valueOf(Long.toString(diasEntre(data1, data2)));
   }

   public static Integer getCurrentYear() {
      return Calendar.getInstance().get(Calendar.YEAR);
   }

   public static String converterDataParaStringSemCaracteresEspeciais(Date data) {

      StringBuilder retorno = new StringBuilder();
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(data);

      String dia = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
      String mes = String.valueOf(calendar.get(Calendar.MONTH) + 1);
      String ano = String.valueOf(calendar.get(Calendar.YEAR));
      String hora = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
      String minuto = String.valueOf(calendar.get(Calendar.MINUTE));
      String segundo = String.valueOf(calendar.get(Calendar.SECOND));

      retorno.append(adicionarZeros(dia));
      retorno.append(adicionarZeros(mes));
      retorno.append(adicionarZeros(ano));
      retorno.append(adicionarZeros(hora));
      retorno.append(adicionarZeros(minuto));
      retorno.append(adicionarZeros(segundo));

      return retorno.toString();
   }

   public static String adicionarZeros(String param) {
      if (param.length() < NUMERO_PARA_ADICIONAR_ZERO) {
         return ZERO + param;
      } else {
         return param;
      }
   }

   public static LocalDate formateToQuery(String value) {
      if (value == null || value.isEmpty()) {
         return null;
      }
      return parseDate(value);
   }

   private static LocalDate parseDate(String value) {
      DateTimeFormatter[] formats = {
              DateTimeFormatter.ofPattern("yyyy-MM-dd"),
              DateTimeFormatter.ofPattern("dd/MM/yyyy"),
              // adicione mais formatos conforme necessário
      };

      for (int i = 0; i < formats.length; i++) {
         try {
            LocalDate data = LocalDate.parse(value, formats[i]);
            return data;
         } catch (DateTimeParseException e) {
            if (i == formats.length - 1) {
               throw e;  // lançar a exceção para o último formato
            }
            // ignorar a exceção e tentar o próximo formato
         }
      }

      return null;  // este ponto nunca será alcançado
   }

   public static String converterDataParaStringSemHoraSemBarra(LocalDate data, String mascara) {

      String dia = String.valueOf(data.getDayOfMonth());
      String mes = String.valueOf(data.getMonthValue() + 1);
      String ano = String.valueOf(data.getYear());

      StringBuilder retorno = new StringBuilder();
      if (FORMATO_DATA_BR.equals(mascara) || FORMATO_DATA_BR.equals(mascara)) {
         retorno.append(adicionarZeros(dia));
         retorno.append(adicionarZeros(mes));
         retorno.append(adicionarZeros(ano));
      } else {
         retorno.append(adicionarZeros(ano));
         retorno.append(adicionarZeros(mes));
         retorno.append(adicionarZeros(dia));
      }

      return retorno.toString();
   }

   public static String converterDataParaStringSemHora(LocalDate data, String mascara) {
      StringBuilder retorno = new StringBuilder();
      if (data != null) {

         String dia = String.valueOf(data.getDayOfMonth());
         String mes = String.valueOf(data.getMonthValue());
         String ano = String.valueOf(data.getYear());

         if (Constantes.FORMATO_DATA_HORA_BR.equals(mascara) || Constantes.FORMATO_DATA_BR.equals(mascara)) {
            retorno.append(adicionarZeros(dia));
            retorno.append("/");
            retorno.append(adicionarZeros(mes));
            retorno.append("/");
            retorno.append(adicionarZeros(ano));
         } else {
            retorno.append(adicionarZeros(ano));
            retorno.append("/");
            retorno.append(adicionarZeros(mes));
            retorno.append("/");
            retorno.append(adicionarZeros(dia));
         }
      }

      return retorno.toString();
   }

   public static int compararDatas(LocalDate dataInicial, LocalDate dataFinal) {

      LocalDate dataI = dataInicial;
      LocalDate dataF = dataFinal;

      return dataI.compareTo(dataF);
   }

   public static Map<String, Integer> gerarProximaReferenciaCiclo(int referencia, int ciclo, int quantidadeDias,
                                                                  LocalDate data) {
      Map<String, Integer> referenciaCiclo = new HashMap<>();
      int quantidade = quantidadeDias;
      int referenciaFinal = referencia;

      // Se for mensal ou se for o ultimo dia do mes
      // deve-se mudar a referencia e reiniciar os ciclos
      if (verificarSeEhPeriodicidadeMensalPelaQuantidadeDeDias(quantidade)
              || obterUltimoDiaDoMesParaData(data).compareTo(data) == 0) {
         ciclo = PRIMEIRO_CICLO;
         referenciaFinal = adicionarMesEmAnoMes(referencia);
      } else {
         ciclo++;
      }

      referenciaCiclo.put(Constantes.CICLO, ciclo);
      referenciaCiclo.put(Constantes.REFERENCIA, referenciaFinal);
      return referenciaCiclo;
   }

   private static boolean verificarSeEhPeriodicidadeMensalPelaQuantidadeDeDias(int quantidadeDeDias) {
      return quantidadeDeDias > Constantes.QUANTIDADE_MINIMA_DE_DIAS_PARA_SER_CONSIDERADO_MENSAL;
   }

   public static LocalDate obterUltimoDiaDoMesParaData(LocalDate data) {
      return lastDayOfMonth(data);
   }

   public static Integer adicionarMesEmAnoMes(Integer anoMesLeitura) {

      int ano = Integer.parseInt(anoMesLeitura.toString().substring(0, INDICE_QUATRO));
      int mes = Integer.parseInt(anoMesLeitura.toString().substring(INDICE_QUATRO, INDICE_SEIS));
      LocalDate dateTime = LocalDate.of(ano, mes, 1);
      LocalDate anoMes = dateTime.plusMonths(1);

      int year = anoMes.getYear();
      int month = anoMes.getMonthValue();

      String retorno = String.valueOf(year) + adicionarZerosEsquerdaNumero(String.valueOf(month), AUXILIAR_DOIS);
      return Integer.valueOf(retorno);
   }

   public static String adicionarZerosEsquerdaNumero(String numero, int tamanhoTotal) {
      return StringUtils.leftPad(numero, tamanhoTotal, ZERO);
   }

   public static Map<String, Integer> rolarReferenciaCiclo(int referencia, int ciclo, int maximoCiclosReferencia) {

      // by gmatos on 15/10/09 10:22

      Map<String, Integer> referenciaCiclo = new HashMap<>();
      ++ciclo;
      int cicloIncrementado = ciclo;
      int referenciaIncrementada = referencia;
      if (cicloIncrementado > maximoCiclosReferencia) {
         referenciaIncrementada = adicionarMesEmAnoMes(referencia);
         cicloIncrementado = 1;
      }

      referenciaCiclo.put(Constantes.CICLO, cicloIncrementado);
      referenciaCiclo.put(Constantes.REFERENCIA, referenciaIncrementada);

      return referenciaCiclo;
   }

   public static String formatarAnoMes(Integer anoMes) {

      String anoMesTemp = null;
      if (anoMes != null) {
         anoMesTemp = anoMes.toString();
         if (anoMesTemp.length() == AUXILIAR_SEIS) {
            anoMesTemp = anoMesTemp.substring(INDICE_QUATRO, INDICE_SEIS) + "/"
                    + anoMesTemp.substring(0, INDICE_QUATRO);
         }
      }

      return anoMesTemp;
   }

   public static boolean menorOuIgualQue(LocalDate menor, LocalDate maior) {

      LocalDate menorComparacao = menor;
      LocalDate maiorComparacao = maior;

      if (menorComparacao != null && maiorComparacao != null) {
         if (menorComparacao.compareTo(maiorComparacao) <= 0) {
            return true;
         }
      } else {
         throw new IllegalArgumentException("OPERANDO_NULO");
      }
      return false;
   }

   public static boolean antesHoje(LocalDate data) {
      if (data != null) {
         LocalDate hoje = LocalDate.now();
         LocalDate dataSemHora = data;
         return dataSemHora.compareTo(hoje) < 0;
      }
      throw new IllegalArgumentException("OPERANDO_NULO");
   }

   public static LocalDate gerarDataSemHoraPrimeiroDiaMes(LocalDate data) {
      return data.withDayOfMonth(1);
   }

   public static LocalDate decrementarDiaMes(LocalDate data, int decrementoDia, int decrementoMes) {
      LocalDate dateTime = data;
      return dateTime.minusDays(decrementoDia).minusMonths(decrementoMes);
   }

   public static LocalDate incrementarDiaMes(LocalDate data, int incrementoDia, int incrementoMes) {
      LocalDate dateTime = data;
      return dateTime.plusDays(incrementoDia).plusMonths(incrementoMes);
   }

   public static BigDecimal gerarDiasPorMesDeItervaloDatas(LocalDate dataVencimento, LocalDate dataPagamento) {

      List<LocalDate> datas = gerarIntervaloDatas(dataVencimento, dataPagamento);
      List<Integer> meses = new ArrayList<>();
      List<Integer> diasPorMes = new ArrayList<>();
      List<Integer> totalDiasPorMes = new ArrayList<>();

      for (LocalDate date : datas) {
         if (!meses.isEmpty()) {
            boolean adicionaMes = true;
            for (int i = 0; i < meses.size(); i++) {
               if (meses.get(i) == date.getMonthValue()) {
                  adicionaMes = false;
                  int somaDia = diasPorMes.get(i) + 1;
                  diasPorMes.set(i, somaDia);
               }
            }
            if (adicionaMes) {
               meses.add(date.getMonthValue());
               totalDiasPorMes.add(date.getMonth().maxLength());
               diasPorMes.add(1);
            }
         } else {
            meses.add(date.getMonthValue());
            totalDiasPorMes.add(date.getMonth().maxLength());
            diasPorMes.add(1);
         }
      }

      BigDecimal expoente = BigDecimal.ZERO;

      for (int i = 0; i < diasPorMes.size(); i++) {
         expoente = expoente.add(BigDecimal.valueOf(diasPorMes.get(i))
                 .divide(BigDecimal.valueOf(totalDiasPorMes.get(i)), 6, RoundingMode.HALF_UP));
      }

      return expoente;
   }

   public static List<LocalDate> gerarIntervaloDatas(LocalDate dataLeituraInicial, LocalDate dataLeituraFinal) {

      List<LocalDate> datas = new ArrayList<>();

      Integer dias = diferencaDiasEntreDatas(dataLeituraInicial, dataLeituraFinal);
      for (int i = 0; i < dias; i++) {
         LocalDate data = dataLeituraInicial.plusDays(i + 1);
         datas.add(data);
      }
      return datas;
   }

   public static String converterAnoMesEmMesAno(String anoMes) {
      String mesAno = "";
      if (!StringUtils.isEmpty(anoMes)) {
         String mes;
         String ano;
         mesAno = anoMes.replaceAll("/", "");

         while (mesAno.length() < AUXILIAR_SEIS) {
            mesAno = ZERO + anoMes;
         }
         ano = anoMes.substring(0, INDICE_QUATRO);
         mes = anoMes.substring(INDICE_QUATRO, INDICE_SEIS);
         mesAno = mes + ano;
      }
      return mesAno;
   }

   public static String converterMesAnoEmAnoMes(String mesAno) {
      String anoMes = "";
      if (!StringUtils.isEmpty(mesAno)) {
         String mes;
         String ano;
         anoMes = mesAno.replaceAll("/", "");
         while (anoMes.length() < AUXILIAR_SEIS) {
            anoMes = ZERO + anoMes;
         }
         ano = anoMes.substring(INDICE_DOIS, INDICE_SEIS);
         mes = anoMes.substring(0, INDICE_DOIS);
         anoMes = ano + mes;
      }
      return anoMes;
   }

   public static LocalDate converterParaData(String strData){
      LocalDate data = LocalDate.parse(strData);
      //data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
      return data;
   }

   public static int anoMesCorrente() {
      LocalDateTime now = LocalDateTime.now();
      int ano = now.getYear();
      int mes = now.getMonthValue();

      return ano * 100 + mes;
   }
}
