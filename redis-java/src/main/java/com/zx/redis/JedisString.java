package com.zx.redis;

import redis.clients.jedis.Jedis;

public class JedisString {

    public static void main(String[] args) {

        Jedis jedis = new Jedis("localhost",6379);
        jedis.select(10);
        jedis.flushDB();
        jedis.set("country","China");

        System.out.println(jedis.exists("country"));
        System.out.println(jedis.strlen("country"));//获取value的长度

        jedis.append("country","is great!");//在字符串值后追加
        System.out.println(jedis.get("country"));

        jedis.setrange("country",9,"amazing");//从索引为9的位置开始替换，将great替换为amazing

        jedis.getrange("country",6,7);//获取value的一部分，获取索引6-7

        jedis.set("age","100");
//        jedis.incr("age");//每次增加1
//        jedis.incrBy("age",10);//每次增加10
//        jedis.decr("age");//每次减少1
        jedis.decrBy("age",10);//每次减少10
        System.out.println(jedis.get("age"));

        jedis.flushDB();

        jedis.mset("name","zhangsan","age","40","sex","male");//批量增加
        System.out.println(jedis.dbSize());


        jedis.close();


    }


}
