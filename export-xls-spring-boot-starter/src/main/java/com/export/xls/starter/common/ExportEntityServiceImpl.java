package com.export.xls.starter.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

/**
 * 实现导出
 *
 * @author liucheng
 * @create 2018-01-30 16:53
 **/
@Component
public class ExportEntityServiceImpl implements ExportEntityService {
    @Autowired
    private ExportUtils exportUtils;

    @Override
    public void export(HttpServletResponse response, Object obj) throws Exception {
        exportUtils.exportExcel(response, (ExportEntity) obj);
    }
}
