package com.export.web.vo;

import com.export.xls.starter.common.ExportEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 工作表2
 *
 * @author liucheng
 * @create 2018-01-30 17:20
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SheetTwoVO extends ExportEntity {
    public static final String[] headers = {"A", "B"};
    public String a;
    public String b;

    @Override
    public String[] headers() {
        return headers;
    }
    
}
