package com.example.demo.lock;

import java.util.concurrent.*;

/**
 * @annotation:
 * @author: Zhengx
 * @create: 2019-06-05 11:38
 */
public class SemaphoreExample {

    /**请求的数量**/
    private static final int THREAD_COUNT = 550;

    public static void main(String[] args) {
        // 创建一个具有固定线程数量的线程池对象（如果这里线程池的线程数量给太少的话你会发现执行的很慢）
        ExecutorService threadPool = new ThreadPoolExecutor(5,20,3000,TimeUnit.SECONDS,new LinkedBlockingQueue<>());
        // 一次只能允许执行的线程数量。
        final Semaphore semaphore = new Semaphore(20,false);

        for (int i = 0; i < THREAD_COUNT; i++) {
            final int threadNum = i;
            // Lambda 表达式的运用
            threadPool.execute(() -> {
                try {
                    semaphore.acquire();// 获取一个许可，所以可运行线程数量为20/1=20
                    test(threadNum);
                    semaphore.release();// 释放一个许可
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            });
        }
        threadPool.shutdown();
        System.out.println("finish");
    }

    private static void test(int threadNum) throws InterruptedException {
        // 模拟请求的耗时操作
        Thread.sleep(1000);
        System.out.println("threadNum:" + threadNum);
        // 模拟请求的耗时操作
        Thread.sleep(1000);
    }

}