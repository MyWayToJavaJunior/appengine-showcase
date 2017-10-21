package com.dataart.ittalk.backend;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

    public ServletInitializer() {
        super();
        // no proper response code with default configuration
        setRegisterErrorPageFilter(false);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Backend.class);
    }

}
