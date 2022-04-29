package com.atguigu.springboot01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Springboot01Application {
	
	

    public static void main(String[] args) {
        SpringApplication.run(Springboot01Application.class, args);
    }

}
