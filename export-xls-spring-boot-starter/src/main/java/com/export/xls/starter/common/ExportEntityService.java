package com.export.xls.starter.common;

import javax.servlet.http.HttpServletResponse;

/**
 * 导出xls的元数据定义抽象方法
 *
 * @author liucheng
 * @create 2018-01-30 16:18
 **/
public interface ExportEntityService {

    /**
     * 导出
     *
     * @param response 必须的参数
     */
    void export(HttpServletResponse response,Object obj) throws Exception;
}
