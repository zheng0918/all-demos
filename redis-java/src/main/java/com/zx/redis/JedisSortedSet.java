package com.zx.redis;

import redis.clients.jedis.Jedis;

import java.util.Set;

public class JedisSortedSet {


    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost",6379);
        jedis.flushAll();

        jedis.zadd("sset",100,"alice");
        jedis.zadd("sset",80,"peter");
        jedis.zadd("sset",60,"bob");
        jedis.zadd("sset",40,"jim");

        Double d = jedis.zscore("sset","jim");//得到jim的score
        System.out.println(d);

        Double dd = jedis.zincrby("sset",5,"jim");
        System.out.println(dd);

        Set<String> mm = jedis.zrangeByScore("sset",60,100);//查询60-100之间的数据
        for (String s : mm) {
            System.out.println(s);
        }

        jedis.close();

    }

}
