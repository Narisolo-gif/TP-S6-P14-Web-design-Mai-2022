package com.example.integration.configs;

import com.example.integration.models.Admin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@Configuration
public class Config {
    @Bean
    @Scope( value = WebApplicationContext.SCOPE_SESSION,proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Admin session(){
        return null;
    }
    @Bean
    public StandardServletMultipartResolver configMutltipart(){
        StandardServletMultipartResolver resolver=new StandardServletMultipartResolver();
        return resolver;
    }
}
