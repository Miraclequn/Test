package com.yf.util;

import java.io.FileOutputStream;
import java.util.Properties;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import ch.qos.logback.classic.net.SyslogAppender;

@SuppressWarnings("deprecation")
@Configuration
public class UrlUitl extends WebMvcConfigurationSupport {
	String relativelyPath=System.getProperty("user.dir"); 
	String picurl=relativelyPath+"\\pic\\";	
	String userpic=relativelyPath+"\\userpic\\";
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/resources/")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/js/")
                .addResourceLocations("classpath:/static/htmlpic/")
                .addResourceLocations("classpath:/public/")
                .addResourceLocations("classpath:/templates/")
        		.addResourceLocations("file:"+picurl)
        		.addResourceLocations("file:"+userpic);
        super.addResourceHandlers(registry);
    }	
}

