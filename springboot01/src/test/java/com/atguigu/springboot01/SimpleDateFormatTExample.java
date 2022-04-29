package com.atguigu.springboot01;

import com.atguigu.springboot01.entity.Person;
import com.atguigu.springboot01.utils.StringUtil;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SimpleDateFormatTExample {


    private static Lock lockA = new ReentrantLock();
    private static Lock lockB = new ReentrantLock();


    public static void main(String args[]) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lockA.lock();
                    TimeUnit.SECONDS.sleep(2);
                    try {
                        lockB.lock();
                    } finally {
                        lockB.unlock();
                    }
                } catch (InterruptedException e) {

                } finally {
                    lockA.unlock();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lockB.lock();
                    TimeUnit.SECONDS.sleep(2);
                    try {
                        lockA.lock();
                    } finally {
                        lockA.unlock();
                    }
                } catch (InterruptedException e) {

                } finally {
                    lockB.unlock();
                }
            }
        }).start();
    }


}