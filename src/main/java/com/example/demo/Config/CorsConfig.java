package com.example.demo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web页面跨域请求配置
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowCredentials(true)//带上cookie信息
                        .allowedMethods("*")//允许任何方法（post，get请求）
                        .allowedOrigins("*")//允许任何域名
                        .allowedHeaders("*");//允许任何请求头
            }
        };
    }
}
