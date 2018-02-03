package com.export.xls.starter.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 导出的基类
 *
 * @author liucheng
 * @create 2018-01-30 16:59
 **/
public  class ExportEntity implements Serializable {
    /**
     * 工作表 sheets
     *
     * @return sheets
     */
    public String[] sheets() {
        return new String[0];
    }

    /**
     * 列名称 headers
     *
     * @return headers
     */
    public String[] headers() {
        return new String[0];
    }

    /**
     * 数据
     */
    public List<Object> data() {
        return new ArrayList<>();
    }
}
