package com.atguigu.springboot01.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

@Component
public class RestTemplateConfig {



    @Bean
    public RestTemplateBuilder restTemplateBuilder()
    {
        RestTemplateBuilder restTemplateBuilder=new RestTemplateBuilder();
        restTemplateBuilder.setConnectTimeout(Duration.ofMinutes(2));
        restTemplateBuilder.setReadTimeout(Duration.ofMinutes(1));
        return restTemplateBuilder;
    }

}
