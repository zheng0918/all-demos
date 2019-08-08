package com.zx.pipeline;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

public class JedisPipeline {

    public static void main(String[] args) {

        Jedis jedis = new Jedis("localhost",6379);
        jedis.auth("123");
        jedis.flushAll();

        int count = 10000;
        long start = System.currentTimeMillis();//开始时间
        Pipeline pl = jedis.pipelined();
        for(int i = 0; i < count ;i++){
//            jedis.set("key"+i,"tps");
            pl.set("key"+i,"tps");//pipeline插入数据
        }
        pl.sync();//管道同步

        long end = System.currentTimeMillis();//结束时间

        System.out.println(count +"条数据，耗时"+(end-start)+"毫秒");

        jedis.close();

    }
}
