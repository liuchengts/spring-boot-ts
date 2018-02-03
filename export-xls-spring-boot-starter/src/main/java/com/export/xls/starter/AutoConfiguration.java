package com.export.xls.starter;

import com.export.xls.starter.common.ExportEntityService;
import com.export.xls.starter.common.ExportEntityServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutoConfiguration {

    @Bean
    public ExportEntityService exportEntityService() {
        return new ExportEntityServiceImpl();
    }

}
