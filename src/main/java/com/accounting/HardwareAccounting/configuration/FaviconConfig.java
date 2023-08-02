package com.accounting.HardwareAccounting.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import java.util.Collections;
import java.util.List;

@Configuration
public class FaviconConfig {

    @Bean
    public SimpleUrlHandlerMapping customFaviconConfigHandler() {
        var handlerMapping = new SimpleUrlHandlerMapping();
        handlerMapping.setOrder(Integer.MIN_VALUE);
        handlerMapping.setUrlMap(Collections.singletonMap(
                "/favicon.ico", faviconHttpRequestHandler()
        ));

        return handlerMapping;
    }

    @Bean
    public ResourceHttpRequestHandler faviconHttpRequestHandler() {
        var requestHandler = new ResourceHttpRequestHandler();
        var classPathResource = new ClassPathResource("static/images/");
        List<Resource> resourceList = List.of(classPathResource);
        requestHandler.setLocations(resourceList);

        return requestHandler;
    }

}
