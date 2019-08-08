package com.zx.redis;

import redis.clients.jedis.Jedis;

import java.util.Set;

public class JedisSet {

    public static void main(String[] args) {

        Jedis jedis = new Jedis("localhost",6379);

        jedis.flushAll();

        jedis.sadd("set1","tom","jim","peter");//set1:tom、jim、peter
        System.out.println(jedis.scard("set1"));//查询集合中的元素

        jedis.srem("set1","jim");//移除元素

        jedis.sadd("set2","smith","rose","tom");

        Set<String> mm = jedis.smembers("set1");//返回集合数据所有的成员
        for (String s : mm) {
            System.out.println(s);
        }

        Set<String> sinter = jedis.sinter("set1","set2");//返回多个集合的交集

        jedis.sinterstore("set3","set1","set2");//直接将set1、set2的交集存入set3中

        jedis.close();


    }

}
