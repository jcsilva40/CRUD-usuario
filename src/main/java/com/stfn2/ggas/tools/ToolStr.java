package com.stfn2.ggas.tools;

import com.google.gson.*;
import com.stfn2.ggas.constante.Constantes;
import com.stfn2.ggas.core.utils.Log;
import org.apache.commons.lang3.StringUtils;

import javax.swing.text.MaskFormatter;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ToolStr {


    public static Boolean hasData(String str) {
        if (str == null) {
            return false;
        }
        return !str.isEmpty();
    }

    public static String aplicacarMascaraCep(String cep) {
        // Remove caracteres não numéricos do CEP
        String cleanedCep = cep.replaceAll("[^0-9]", "");

        // Verifica se o CEP tem pelo menos 8 dígitos
        if (cleanedCep.length() >= 8) {
            // Adiciona a máscara ao CEP
            StringBuilder formattedCep = new StringBuilder();
            formattedCep.append(cleanedCep, 0, 5);
            formattedCep.append("-");
            formattedCep.append(cleanedCep, 5, 8);

            return formattedCep.toString();
        } else {
            // Retorna o CEP sem máscara se não tiver pelo menos 8 dígitos
            return cleanedCep;
        }
    }

    public static String toJson(Object obj) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        String json = gson.toJson(obj);
        return json;
    }


    public static <Destino> Destino jsonToObj(Class<Destino> destino, String obj) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        Destino newObj = gson.fromJson(obj, destino);
        return newObj;
    }

    public static String converterCampoValorDecimalParaString(String rotulo, BigDecimal valor, Locale locale,
            int numeroDecimais) {
        if (valor == null) {
            return BigDecimal.ZERO.toString();
        }
        try {
            DecimalFormat decf = obterDecimalFormatLocalizado(locale, numeroDecimais);
            return decf.format(valor);
        } catch (Exception e) {
            return valor.toString();
        }
    }

    private static DecimalFormat obterDecimalFormatLocalizado(Locale locale, Integer numeroDecimais) {

        DecimalFormat decimalFormat = new DecimalFormat(Constantes.FORMATO_VALOR_NUMERO,
                new DecimalFormatSymbols(locale));
        decimalFormat.setParseBigDecimal(true);
        decimalFormat.setDecimalSeparatorAlwaysShown(true);
        if (numeroDecimais == null) {
            decimalFormat.setMinimumFractionDigits(Constantes.QUANTIDADE_CASAS_VALOR_DECIMAL);
        } else {
            decimalFormat.setMinimumFractionDigits(numeroDecimais);
            decimalFormat.setMaximumFractionDigits(numeroDecimais);
        }

        return decimalFormat;
    }

    public static String aplicarMascaraCNPJ(String cnpj){
        String cnpjFormatado = "";
        try {
            MaskFormatter mask = new MaskFormatter("##.###.###/####-##");
            mask.setValueContainsLiteralCharacters(false);
            cnpjFormatado = mask.valueToString(cnpj);
        } catch (ParseException ex) {
            Log.erroStatic(ToolStr.class,"Erro ao tentar aplicar máscada", ex.getMessage());
        }
        return cnpjFormatado;
    }

    public static String aplicarMascaraCPF(String cpf){
        String cpfFormatado = "";
        try {
            MaskFormatter mask = new MaskFormatter("###.###.###-##");
            mask.setValueContainsLiteralCharacters(false);
            cpfFormatado = mask.valueToString(cpf);
        } catch (ParseException ex) {
            Log.erroStatic(ToolStr.class,"Erro ao tentar aplicar máscada", ex.getMessage());
        }
        return cpfFormatado;
    }

    public static String substituirArgumentosDoTexto(String texo, int numeroDeArgumentos, Object valorDoArgumento) {

        StringBuilder stringBuilder = new StringBuilder(texo.length() + 10);
        String chave = "{" + numeroDeArgumentos + "}";
        int i = texo.indexOf(chave);
        if (i >= 0) {
            stringBuilder.append(texo.substring(0, i));
            stringBuilder.append(valorDoArgumento);
            stringBuilder.append(texo.substring(i + chave.length()));
        } else {
            stringBuilder.append(texo);
        }
        return stringBuilder.toString();
    }

    public static String formatarAnoMesComMascara(Integer anoMes) {

        String anoMesTemp = null;
        if (anoMes != null) {
            anoMesTemp = anoMes.toString();
            if (anoMesTemp.length() == 6) {
                anoMesTemp = anoMesTemp.substring(0, 4) + "/"
                        + anoMesTemp.substring(4, 6);
            }
        }  
        return anoMesTemp;
    }
    
    public static class LocalDateAdapter implements JsonSerializer<LocalDate> {
        @Override
        public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
            return context.serialize(src.format(DateTimeFormatter.ISO_LOCAL_DATE));
        }
    }
    
    public static String formatNumber6Digits(Integer number) {
        return String.format("%06d", number);
    }    

    public static String removerTodosCaracteresEspeciais(String texto) {
        return texto.replaceAll("[^A-Za-z0-9\\s]", "").trim();
    }

    public static String formatarValor(Object valor, String mascara) {
        String retorno = null;
        MaskFormatter maskFormatter;
        try {
            maskFormatter = new MaskFormatter(mascara);
            maskFormatter.setValueContainsLiteralCharacters(false);
            retorno = maskFormatter.valueToString(String.valueOf(valor));
        } catch (ParseException e) {
            Log.erroStatic(ToolStr.class,e.getMessage(), e.getCause().getMessage());
        }

        return retorno;
    }

    public static String adicionarZerosEsquerdaNumero(String numero, int tamanhoTotal) {
        return StringUtils.leftPad(numero, tamanhoTotal, "0");
    }

    public static Long converterCampoStringParaValorLong(String rotulo, String strValor) {
        Long valor = null;
        try {
            valor = Long.valueOf(strValor);
        } catch (NumberFormatException e) {
            Log.erroStatic(ToolStr.class,Constantes.ERRO_DADOS_INVALIDOS, rotulo);
        }

        return valor;
    }
}
