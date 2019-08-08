package com.example.demo.lock;

import java.nio.channels.Selector;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @annotation:
 * @author: Zhengx
 * @create: 2019-06-17 16:30
 */
public class OrderService {

    /**使用static，这样每个线程拿到的是同一把锁，当然，spring中service默认就是单例**/
    private static ReentrantLock reentrantLock = new ReentrantLock(true);

    public void doSomething(String arg){



        // 比如我们同一时间，只允许一个线程创建订单
        reentrantLock.lock();
        // 通常，lock 之后紧跟着 try 语句
        try {
            // 这块代码同一时间只能有一个线程进来(获取到锁的线程)，
            // 其他的线程在lock()方法上阻塞，等待获取到锁，再进来
            // 执行代码...
            // 执行代码...
            // 执行代码...
        }catch (Exception e){
            // TODO
            e.printStackTrace();
        }finally {
            //释放锁,synchronize不需要释放锁
            reentrantLock.unlock();
        }
    }
}
