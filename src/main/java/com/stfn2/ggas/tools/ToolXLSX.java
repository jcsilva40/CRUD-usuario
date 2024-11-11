package com.stfn2.ggas.tools;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;

public class ToolXLSX {
    
    public static Cell setCellTitulo(Workbook workbook, Row linhaRow, int inicioColuna, String titulo){
        Cell cell = linhaRow.createCell(inicioColuna);
        cell.setCellValue(titulo);
        cell.setCellStyle(setTituloStyle(workbook));
        return cell;
    }

    public static CellStyle setTituloStyle(Workbook workbook){
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

    public static Cell setCell(Workbook workbook, Row linhaRow, int inicioColuna, String titulo){
        Cell cell = linhaRow.createCell(inicioColuna);
        cell.setCellValue(titulo);
        cell.setCellStyle(setStyle(workbook));
        return cell;
    }

    public static Cell setCellLong(Workbook workbook, Row linhaRow, int inicioColuna, Long cellValue){
        Cell cell = linhaRow.createCell(inicioColuna);
        cell.setCellValue(cellValue);
        cell.setCellStyle(setStyle(workbook));
        return cell;
    }
    
    public static Cell setCellInteger(Workbook workbook, Row linhaRow, int inicioColuna, Integer cellValue){
        Cell cell = linhaRow.createCell(inicioColuna);
        cell.setCellValue(cellValue);
        cell.setCellStyle(setStyle(workbook));
        return cell;
    }

    public static Cell setCellDecimal(Workbook workbook, Row linhaRow, int inicioColuna, BigDecimal cellValue){
        Cell cell = linhaRow.createCell(inicioColuna);
        // Formatar BigDecimal e converter para String pois não consegue salvar diretamente BigDecimal
        String stringValue = cellValue.setScale(4, RoundingMode.HALF_EVEN).toString();
        cell.setCellValue(stringValue);
        CellStyle style = setStyle(workbook);
        DataFormat format = workbook.createDataFormat();
        style.setDataFormat(format.getFormat("0.0000"));
        cell.setCellStyle(style);
        return cell;
    }

    public static Cell setCellMonetario(Workbook workbook, Row linhaRow, int inicioColuna, BigDecimal cellValue){
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
    
    public static Cell setCellDate(Workbook workbook, Row linhaRow, int inicioColuna, TemporalAccessor cellValue){
        Cell cell = linhaRow.createCell(inicioColuna);
        // Converter TemporalAccessor para Date
        Date date = null;
        String pattern = "";
        if (cellValue instanceof LocalDateTime localDateTime) {
            date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
            pattern = "dd/MM/yyyy HH:mm:ss";
        } else if (cellValue instanceof LocalDate localDate) {
            date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            pattern = "dd/MM/yyyy";
        }        
        // Definir o valor da célula para a data
        cell.setCellValue(date);
        // Criar um estilo de data
        CellStyle style = setStyle(workbook);        
        short df = workbook.createDataFormat().getFormat(pattern);
        style.setDataFormat(df);
        // Definir o estilo da célula para o estilo de data
        cell.setCellStyle(style);
        return cell;
    }

    public static CellStyle setStyle(Workbook workbook){
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
