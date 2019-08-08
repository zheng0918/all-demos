package com.zx.redis;

import redis.clients.jedis.Jedis;

import java.util.Set;

public class JedisFirst {

    public static void main(String[] args) {

        //连接redis数据库
        Jedis jedis = new Jedis("localhost",6379);
        jedis.auth("123");//验证密码
        jedis.getDB();//返回当前的db
        System.out.println(jedis.getDB());
        System.out.println(jedis.keys("*"));

        jedis.select(10);//切换到db10

        jedis.flushDB();//清空当前库
        jedis.flushAll();//清空所有

        jedis.set("a","Jim");//插入数据，如果已经存在key为'a'的数据，则修改
        jedis.set("b","Tom");
        jedis.set("c","Rose");

        Set<String> keys = jedis.keys("*");//获取所有的key
        for (String key : keys) {
            System.out.println(key+":"+jedis.get(key));
        }

        System.out.println(jedis.dbSize());//输出当前库中的记录条数

        jedis.move("c",11);//将'c'移动至db11

        jedis.select(11);

        System.out.println(jedis.dbSize());//输出当前库中的记录条数


        jedis.expire("c",5);//设置数据的生命周期为5S
        jedis.setex("d",5,"White");//设置的时候就设置生命周期
        try {
            System.out.println("等待6s");
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(jedis.dbSize());

        jedis.close();


    }


}
