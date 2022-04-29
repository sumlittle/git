//package com.atguigu.springboot01.config;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//@Component
//    public class JobBusiness {
//
//        Logger logger = LoggerFactory.getLogger(getClass());
//
//        public void performSomethingOn(Job job) {
//            logger.info("Received Job with {} ID of priority {}", job.getId(), job.getPriority());
//            logger.info("Sleeping for 10 seconds");
//            try {
//                Thread.sleep(10000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            logger.info("Finished Job with {} ID of priority {}", job.getId(), job.getPriority());
//        }
//    }