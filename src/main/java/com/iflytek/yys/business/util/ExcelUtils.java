package com.iflytek.yys.business.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;

/**
 * Description:
 *
 * @author createdBy huizhang43.
 * @date createdAt 2022/5/26 10:58
 **/
public class ExcelUtils {
    
    public static CellStyle defaultCellStyle(Workbook workbook) {
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setWrapText(true);
        // 水平居中
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        // 垂直居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        
        return cellStyle;
    }
    
    public static CellStyle titleCellStyle(Workbook workbook) {
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setWrapText(true);
        // 居中
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        // 边框
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        
        // 字体
        Font font = new XSSFFont();
        font.setBold(true);
        
        cellStyle.setFont(font);
        
        return cellStyle;
    }
}
