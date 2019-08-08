package com.zx.message.queen;

import redis.clients.jedis.Jedis;

public class JedisMessageQueen {

    public static void main(String[] args) {

        Jedis jedis = new Jedis("localhost",6379);
        jedis.auth("123");
        for (int i=0;i<5;i++){
            jedis.publish("cctv","message"+i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
