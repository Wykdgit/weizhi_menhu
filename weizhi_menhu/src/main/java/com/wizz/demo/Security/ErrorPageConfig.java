package com.wizz.demo.Security;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
@Configuration
public class ErrorPageConfig {
    @Bean

    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer(){

        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {

            @Override

            public void customize(ConfigurableWebServerFactory factory) {

                //    factory.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST,"/400.html"));
                //   factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND,"/404.html"));
                factory.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN,"/403.html"));
                // factory.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,"/500.html"));

            }

        };

    }
}
