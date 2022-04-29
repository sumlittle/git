package com.atguigu.springboot01;

import com.atguigu.springboot01.config.RestTemplateConfig;
import com.atguigu.springboot01.entity.MyPartitions;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@SpringBootTest
@Slf4j
class Springboot01ApplicationTests {


    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Test
    public void contextLoads() {
        log.info("........");
        RestTemplate restTemplate = restTemplateBuilder.build();
        SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        //超时时间设置为10秒
        clientHttpRequestFactory.setConnectTimeout(1000 * 10);
        clientHttpRequestFactory.setReadTimeout(1000 * 10);
        restTemplate.setRequestFactory(clientHttpRequestFactory);
        String url = "http://127.0.0.1:4457/rest";
        Integer i = restTemplate.getForObject(url, Integer.class);
        System.out.println(i);


    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Properties properties = new Properties();

        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.10.128:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, MyPartitions.class.getName());
        properties.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "first");
        //创建对象
        KafkaProducer<String, String> stringStringKafkaProducer = new KafkaProducer<String, String>(properties);
        //放数据
        stringStringKafkaProducer.initTransactions();
        stringStringKafkaProducer.beginTransaction();
        try{
            for (int i = 0; i < 10; i++) {
                stringStringKafkaProducer.send(new ProducerRecord<>("first", 0, "",
                        "1111" + i * 2), new Callback() {
                    @Override
                    public void onCompletion(RecordMetadata metadata, Exception exception) {

                        System.out.println("partition="+metadata.partition()+" topic="+metadata.topic());
                    }
                });
            }
        }catch (Exception e){
            stringStringKafkaProducer.abortTransaction();
        }finally {
            //关闭资源
            stringStringKafkaProducer.close();
        }

//            stringStringKafkaProducer.send(new ProducerRecord<>("first1",
//                    "atguigu" + i*2), new Callback() {
//                @Override
//                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
//                    if (e==null){
//                        //发送成功
//
//
//                        System.out.println("主题名："+recordMetadata.topic()+" ,分区："+recordMetadata.partition());
//                    }else {
//                        e.printStackTrace();
//                    }
//                }
//            });








    }


}
