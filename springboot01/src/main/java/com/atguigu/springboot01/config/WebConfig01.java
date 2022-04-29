package com.atguigu.springboot01.config;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class WebConfig01 {
    /**
     * 启用 Jackson 为 HttpMessageConverter，转换对象为 JSON 字符串
     */
    @Bean
    public HttpMessageConverters jacksonHttpMessageConverters() {
        JacksonHttpMessageConverter jacksonHttpMessageConverter = new JacksonHttpMessageConverter();
        return new HttpMessageConverters(jacksonHttpMessageConverter);
    }
}