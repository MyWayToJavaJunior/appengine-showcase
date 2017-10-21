package com.dataart.ittalk.backend.config;

import com.dataart.ittalk.common.dao.DatastoreCurrencyDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(httpRequestFactory());
    }

    @Bean
    public ClientHttpRequestFactory httpRequestFactory() {
        return new SimpleClientHttpRequestFactory();
    }

    @Bean
    public DatastoreCurrencyDao datastoreCurrencyDao() {
        return new DatastoreCurrencyDao();
    }

}
