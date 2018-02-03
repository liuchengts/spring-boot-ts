package com.export.xls.starter.common;

import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 导出工具类
 *
 * @author liucheng
 * @create 2018-01-30 16:01
 **/
@Component
public class ExportUtils {

    @SuppressWarnings("unchecked")
    public void exportExcel(HttpServletResponse response, ExportEntity exportEntity) throws Exception {
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        for (String sheetStr : exportEntity.sheets()) {
            // 生成一个表格
            HSSFSheet sheet = workbook.createSheet(sheetStr);
            // 产生表格标题行
            HSSFRow row = sheet.createRow(0);
            header(row, exportEntity.headers());
            int r = 1;
            for (Object obj : exportEntity.data()) {
                reflectDataXls(obj, sheet.createRow(r));
                r++;
            }
        }
        workbook.write(response.getOutputStream());
    }

    /**
     * 生成标题行
     */
    private void header(HSSFRow row, String[] headers) {
        int i = 0;
        for (String head : headers) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(new HSSFRichTextString(head));
            i++;
        }
    }

    /**
     * 反射填充数据
     */
    private void reflectDataXls(Object obj, HSSFRow row) throws Exception {
        Class tclass = obj.getClass();
        int i = 0;
        for (Field field : tclass.getDeclaredFields()) {
            HSSFCell cell = row.createCell(i);
            String fieldName = field.getName();
            if ("headers".equals(fieldName)) {
                continue;
            }
            String getMethodName = ("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1));
            Method method = tclass.getMethod(getMethodName);
            Object value = method.invoke(obj, (Object[]) method.getParameters());
            if (value != null) {
                cell.setCellValue(new HSSFRichTextString(value.toString()));
            }
            i++;
        }
    }
}
