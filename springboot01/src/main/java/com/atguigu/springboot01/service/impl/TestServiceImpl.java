package com.atguigu.springboot01.service.impl;

import com.atguigu.springboot01.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TestServiceImpl implements TestService {
    @Override
    public void demo() {



    }

    @Override
    @Async("taskExecutor")
    public void doTask() {

        for (int j = 0; j < 100000; j++) {
            log.info(Thread.currentThread().getName()+" do add:");
        }

    }
}
