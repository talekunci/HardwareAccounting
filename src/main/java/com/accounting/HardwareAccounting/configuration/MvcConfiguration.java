package com.accounting.HardwareAccounting.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        final String classPathStatic = "classpath:/static/";

        registry
                .addResourceHandler("/js/**")
                .addResourceLocations(classPathStatic + "js/");
        registry
                .addResourceHandler("/css/**")
                .addResourceLocations(classPathStatic + "css/");
        registry
                .addResourceHandler("/images/**")
                .addResourceLocations(classPathStatic + "images/");

    }

}
