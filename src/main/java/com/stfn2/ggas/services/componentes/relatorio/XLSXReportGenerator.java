package com.stfn2.ggas.services.componentes.relatorio;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.tools.ToolXLSX;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class XLSXReportGenerator {

   public Workbook mapBIToXlsx(List<HashMap<String, Object>> list) {
      Workbook workbook = new XSSFWorkbook();
      Sheet sheet = workbook.createSheet("Sheet1");

      /**
       * Se a lista estiver vazia, retorna a planilha vazia
       * */
      if (list.isEmpty()) {
         return workbook;
      }

      Set<String> allKeys = new HashSet<>();
      list.forEach(m -> {
         for (String key : m.keySet()) {
            Object ob = m.get(key);
            if (ob instanceof List) {
               continue;
            }
            allKeys.add(key);
         }
      });

      int rowNum = 0;
      Row headerRow = sheet.createRow(rowNum++);
      int colNum = 0;
      for (String key : allKeys) {
         ToolXLSX.setCellTitulo(workbook, headerRow, colNum++, key);
      }

      for (HashMap<String, Object> map : list) {
         Row row = sheet.createRow(rowNum++);
         colNum = 0;
         for (String key : allKeys) {
            Object ob = map.get(key);
            if (ob instanceof List) {
               continue;
            } else if (ob instanceof BaseEntity obj) {
               ToolXLSX.setCellLong(workbook, row, colNum++, obj.getId());
            } else if (ob instanceof Long bd) {
               ToolXLSX.setCellLong(workbook, row, colNum++, bd);
            } else if (ob instanceof Integer bd) {
               ToolXLSX.setCellInteger(workbook, row, colNum++, bd);
            } else if (ob instanceof BigDecimal bd) {
               ToolXLSX.setCellDecimal(workbook, row, colNum++, bd);
            } else if (ob instanceof Date bd) {
               ToolXLSX.setCellDate(workbook, row, colNum++, bd.toInstant());
            } else if (ob instanceof LocalDate bd) {
               ToolXLSX.setCellDate(workbook, row, colNum++, bd);
            } else if (ob instanceof LocalDateTime bd) {
               ToolXLSX.setCellDate(workbook, row, colNum++, bd);
            } else if (ob != null) {
               ToolXLSX.setCell(workbook, row, colNum++, ob.toString());
            } else {
               ToolXLSX.setCell(workbook, row, colNum++, "");
            }

         }
      }

      /**
       * Adiciona autofiltro
       * */

      sheet.setAutoFilter(new CellRangeAddress(0, rowNum - 1, 0, colNum - 1));

      /**
       * Ajusta automaticamente o tamanho de todas as colunas
       * */
      for (int i = 0; i < colNum; i++) {
         sheet.autoSizeColumn(i);
         sheet.setColumnWidth(i, sheet.getColumnWidth(i) + 1512);
      }

      return workbook;
   }

   public byte[] workBookToByte(Workbook workbook) {
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      try {
         workbook.write(bos);
         bos.close();
      } catch (IOException ex) {
      }
      return bos.toByteArray();
   }

}
