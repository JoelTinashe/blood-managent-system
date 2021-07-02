package com.digitalkrapht.bloodbank.bloodbank.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public class WebMvcConfig implements WebMvcConfigurer {
    private final long MAX_AGE_SECS = 3600;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

        ReadOnlyMultipartFormDataEndpointConverter converter = new ReadOnlyMultipartFormDataEndpointConverter(
                objectMapper);
        List<MediaType> supportedMediaTypes = new CopyOnWriteArrayList<>();
        supportedMediaTypes.addAll(converter.getSupportedMediaTypes());
        supportedMediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
        converter.setSupportedMediaTypes(supportedMediaTypes);

        converters.add(converter);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*", "http://localhost:4200", "http://localhost:4201", "https://admin.cls.rapidconcepts.co.zw")
                .allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE")
                .allowedHeaders("*")
                .maxAge(MAX_AGE_SECS)
                .allowCredentials(true);
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/statics/")
                .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());

        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    {
    }
}
