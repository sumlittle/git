package com.atguigu.springboot01.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@EnableAsync
@Configuration
public class TaskPoolConfig {
//    @Autowired
//    private TaskExecutor taskExecutor;
//    @Autowired
//    private JobBusiness jobBusiness;

    @Bean("taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new VisiableThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(80);
        executor.setQueueCapacity(200);
        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix("taskExecutor-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }

//    @Bean
//    public CommandLineRunner runner() {
//
//        return args -> {
//            Task initialFirst = new Task(jobBusiness::performSomethingOn, new Job(1L, 1L));
//            Task initialSecond = new Task(jobBusiness::performSomethingOn, new Job(2L, 2L));
//            // Core pool size is reached, start appending jobs to the queue
//            Task waitingInQueueFirst = new Task(jobBusiness::performSomethingOn, new Job(3L, 5L));
//            Task waitingInQueueSecond = new Task(jobBusiness::performSomethingOn, new Job(4L, 1L));
//            Task waitingInQueueThird = new Task(jobBusiness::performSomethingOn, new Job(5L, 10L));
//            taskExecutor.execute(new FutureCustomTask(initialFirst));
//            taskExecutor.execute(new FutureCustomTask(initialSecond));
//            /*
//             Once initial jobs are finished, `waitingInQueueSecond` Job will get the first freed thread
//             since it's priority is higher than priorities of `waitingInQueueFirst` and `waitingInQueueThird`
//            */
//            taskExecutor.execute(new FutureCustomTask(waitingInQueueFirst));
//            taskExecutor.execute(new FutureCustomTask(waitingInQueueSecond));
//            taskExecutor.execute(new FutureCustomTask(waitingInQueueThird));
//            /* Expected output:
//            Received Job with 1 ID of priority 1
//                Received Job with 2 ID of priority 2
//                    Sleeping for 10 seconds
//                    Sleeping for 10 seconds
//                Finished Job with 2 ID of priority 2
//                Finished Job with 1 ID of priority 1
//
//                Received Job with 4 ID of priority 1 (waitingInQueueSecond got the first freed thread)
//                Received Job with 3 ID of priority 5
//                    Sleeping for 10 seconds
//                    Sleeping for 10 seconds
//                Finished Job with 4 ID of priority 1
//                Finished Job with 3 ID of priority 5
//
//                Received Job with 5 ID of priority 10
//                    Sleeping for 10 seconds
//                Finished Job with 5 ID of priority 10
//             */
//        };
//    }
}
