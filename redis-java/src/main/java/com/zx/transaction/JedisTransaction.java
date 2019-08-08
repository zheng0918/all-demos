package com.zx.transaction;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;

/**
 * Redis事务后处理机制实例
 */
public class JedisTransaction {


    public static void main(String[] args) {

        Jedis jedis = new Jedis("localhost",6379);
        jedis.auth("123");
        jedis.flushDB();
//        try {
//            Transaction t = jedis.multi();//获得事务 对象
//
////            jedis.set("a","peter"); 未进行事务处理得时候使用jedis插入对象  这时候下面会抛出异常，但是a始终会插入，因为未启用事务管理
////            int cc = 100/0;//模拟两个操作之间出错
////            jedis.set("b","tom");
//
//            t.set("b","peter");
//            int cc = 100/0;//模拟两个操作之间出错
//            t.set("c","tom");
//
//            t.exec();//提交事务
//        }catch (Exception e){
//            e.printStackTrace();
//        }


        jedis.set("money","100");
        jedis.watch("money");//开始监视
//        jedis.set("money","80");//在开始监视到事务提交之前，数据被本事务对象以外的对象修改，则会导致提交失败，r的返回值此时为[]
        Transaction t = jedis.multi();
        t.set("money","120");
        List<Object> r = t.exec();//提交事务
        jedis.unwatch();//解除监视
        System.out.println(r);// [OK]
        System.out.println("money="+jedis.get("money"));




    }

}
