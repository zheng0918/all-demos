package com.zx.transaction;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;

public class JedisExample {

    /**
     * 简单事务
     * @param args
     */
    public static void main(String[] args) {

        Jedis jedis = new Jedis("localhost",6379);
        jedis.auth("123");
        jedis.flushDB();


        jedis.set("credit","4000");//信用余额
        jedis.set("debt","1000");//债务余额

        int out = 1000;//将要消费的金额
        int credit = Integer.parseInt(jedis.get("credit"));
        if(credit<out){
            System.out.println("信用余额不足！");
            return;
        }
        jedis.watch("credit");
        jedis.watch("debt");
        jedis.set("credit","8000");
        Transaction transaction = jedis.multi();//开启事务，获取事务对象
        transaction.decrBy("credit",out);
        transaction.incrBy("debt",out);
        List<Object> result = transaction.exec();
        jedis.unwatch();
        if(!result.isEmpty())
            System.out.println("消费成功，当前信用余额为["+jedis.get("credit")+"]，债务余额为["+jedis.get("debt")+"].");
        else
            System.out.println("消费失败！");
    }


}
