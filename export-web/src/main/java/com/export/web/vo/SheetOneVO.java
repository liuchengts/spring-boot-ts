package com.export.web.vo;

import com.export.xls.starter.common.ExportEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 工作表1
 *
 * @author liucheng
 * @create 2018-01-30 17:20
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SheetOneVO extends ExportEntity {
    public static final String[] headers = {"用户名", "密码"};
    public String name;
    public String pwd;

    @Override
    public String[] headers() {
        return headers;
    }

}
