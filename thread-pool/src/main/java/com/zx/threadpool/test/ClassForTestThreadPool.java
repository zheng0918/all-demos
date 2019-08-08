package com.zx.threadpool.test;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @annotation:
 * @author: Zhengx
 * @create: 2019-07-26 14:02
 */
public class ClassForTestThreadPool {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext a;

        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Future> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Future<String> future = executor.submit(() -> {
                Thread.sleep(2000);
                return Thread.currentThread().getName();
            });
            list.add(future);
        }

        list.forEach((future)-> {
            try {
                String s = (String)future.get();
                System.out.println(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });

    }



}
