package com.lost_found.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Value("${upload.picture.path}")
    private  String uploadPicturePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //其他静态资源
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/img/**").addResourceLocations("file:"+uploadPicturePath);
    }

}