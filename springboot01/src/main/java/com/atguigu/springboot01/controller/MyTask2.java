package com.atguigu.springboot01.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
@Slf4j
@Component
public class MyTask2 {


    @Scheduled( cron="0 */2 * * * ?")
    public void show() {
       log.info("date={}",new Date());
    }

}
