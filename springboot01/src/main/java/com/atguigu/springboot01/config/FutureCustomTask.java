//package com.atguigu.springboot01.config;
//
//import java.util.concurrent.FutureTask;
//
//public class FutureCustomTask extends FutureTask<FutureCustomTask> implements Comparable<FutureCustomTask> {
//        private Task task;
//
//        public FutureCustomTask(Task task) {
//            super(task, null);
//            this.task = task;
//        }
//
//        @Override
//        public int compareTo(FutureCustomTask o) {
//            return task.getJob().getPriority().compareTo(o.task.getJob().getPriority());
//        }
//    }