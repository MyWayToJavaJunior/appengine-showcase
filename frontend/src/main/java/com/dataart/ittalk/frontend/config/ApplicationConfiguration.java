package com.dataart.ittalk.frontend.config;

import com.dataart.ittalk.common.dao.DatastoreCurrencyDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public DatastoreCurrencyDao datastoreCurrencyDao() {
        return new DatastoreCurrencyDao();
    }

}
