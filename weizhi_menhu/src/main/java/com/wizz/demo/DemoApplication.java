package com.wizz.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@MapperScan("com.wizz.demo.dao")//扫描mapper的路径
@EnableWebSecurity
@EnableCaching
@ComponentScan(basePackages = {"com.wizz.demo"})//因为Controller层有的为注解@Controller，需要这个注解来注入
public class DemoApplication {

    public static void main(String[] args) {SpringApplication.run(DemoApplication.class, args);}
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //单个文件最大
        factory.setMaxFileSize("102400KB"); //KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize("1024000KB");
        return factory.createMultipartConfig();
    }

}
