package com.atguigu.springboot01.controller;

import com.atguigu.springboot01.entity.InterfaceLog;
import com.atguigu.springboot01.entity.Person;

import com.atguigu.springboot01.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/demo")
    public void test() {
        try {
            testService.demo();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @GetMapping("/demo01")
    public String test01() {


        LocalDateTime localDateTime = LocalDateTime.now();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String date = dateTimeFormatter.format(localDateTime);

        System.out.println(date);
        return date;

    }

    @PostMapping("/convert")
    public Integer convertXml(@RequestBody List<InterfaceLog> interfaceLogs) {


        System.out.println(interfaceLogs);
        return 1;


    }

    @PostMapping("/timePrint")
    public Integer timePrint() {


        StopWatch sw = new StopWatch(UUID.randomUUID().toString());

        sw.start("方法1");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sw.stop();
        sw.start("方法2");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sw.stop();

        sw.start("方法3");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sw.stop();

        log.info(sw.prettyPrint());

        return 1;


    }


    @PostMapping("/doTask")
    public Integer doTask() {


        testService.doTask();
        return 1;


    }
}
