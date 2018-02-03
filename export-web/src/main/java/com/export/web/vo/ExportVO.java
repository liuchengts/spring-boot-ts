package com.export.web.vo;

import com.export.xls.starter.common.ExportEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 导出xls的vo类
 *
 * @author liucheng
 * @create 2018-01-30 17:10
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExportVO extends ExportEntity {

    public static final String[] sheets = {"工作表1", "工作表2"};

    public List<SheetOneVO> sheetOneVOs;

    public List<String> sheetTwoVOs;

    @Override
    public String[] sheets() {
        return sheets;
    }
}
