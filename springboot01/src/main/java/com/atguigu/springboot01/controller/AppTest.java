package com.atguigu.springboot01.controller;

public class AppTest {



    public static void main(String[] args) throws InterruptedException {
       try {
           int i = 10 / 0;
           System.out.println(i);
       }catch (Exception e){

           Thread.sleep(10*1000*60*30);
           int i=0;
           System.out.println(i);
       }

    }

}
