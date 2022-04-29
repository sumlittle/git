//package com.atguigu.springboot01.config;
//
//import java.util.function.Consumer;
//
//public class Task implements Runnable {
//        private Consumer<Job> jobConsumer;
//        private Job job;
//
//        public Task(Consumer<Job> jobConsumer, Job job) {
//            this.jobConsumer = jobConsumer;
//            this.job = job;
//        }
//
//        public Job getJob() {
//            return this.job;
//        }
//
//        @Override
//        public void run() {
//            this.jobConsumer.accept(job);
//        }
//    }
