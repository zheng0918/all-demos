package com.example.demo.lock;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;

/**
 * @annotation: 测试独占锁
 * @author: Zhengx
 * @create: 2019-05-30 15:43
 */
public class Sequence {

    private static int CORE_POOL_SIZE = 5;

    /**线程池最大线程数**/
    private static int MAX_POOL_SIZE = 20;

    /**额外线程空状态生存空间**/
    private static int KEEP_ALIVE_TIME = 30 * 1000;

    /**线程池**/
    private static ExecutorService THREAD_POOL;

    /**阻塞队列，当核心线程都被占用，且阻塞队列已满的情况下，才会开启额外线程**/
    private static BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(CORE_POOL_SIZE);

    private static ThreadFactory threadFactory = new ThreadFactory() {

        private final AtomicInteger integer = new AtomicInteger();

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r,"ThreadPool thread :" + integer.getAndIncrement());
        }
    };

    private static final ExecutorService THREAD_POOL_FIXED = Executors.newFixedThreadPool(10);

    private Lock lock = new SubLock();
    private int value;

    private Integer testValue;

    static{
        THREAD_POOL = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, workQueue, threadFactory);
    }

    public int getNext(){
        lock.lock();
        value++;
        lock.unlock();
        return value;
    }

    public static void main(String[] args) {
        Sequence sequence = new Sequence();

        for(int i = 0; i < 5; i++){
            new Thread(() -> {
                for(int i1 = 0; i1 <10; i1++){
                    System.out.println("当前线程"+Thread.currentThread().getName()+"获取value -- "+sequence.getNext());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }).start();
        }
//        CompletionService<List<Integer>> service = new ExecutorCompletionService<>(THREAD_POOL);
//        for (int i = 0; i < 5; i++) {
//            service.submit(() -> {
////                Thread.currentThread().setName("cxk-1");
//                List<Integer> list = new ArrayList<>();
//                for (int j = 0; j < 10; j++) {
//                    System.out.println("当前线程"+Thread.currentThread().getName()+"获取value -- "+sequence.getNext());
//                    Thread.sleep(300);
//                    list.add(sequence.getNext());
//                }
//                return list;
//            });
//        }

//
//        for (int i = 0; i < 5; i++) {
//            try {
//                Future<List<Integer>> future =service.take();
//                if(future != null){
//                    try {
//                        System.out.println("线程执行完毕返回结果:"+future.get());
//                    } catch (ExecutionException e) {
//                        e.printStackTrace();
//                    }
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

    }
}
