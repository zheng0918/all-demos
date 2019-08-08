package com.zx.redis;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class JedisHash {

    public static void main(String[] args) {

        Jedis jedis = new Jedis("localhost",6379);

        HashMap<String,String> map = new HashMap<String, String>();
        map.put("name","HP-3321");
        map.put("price","5000");

        jedis.hmset("hpComputer", map);//向redis中存入一个hash数据类型
        System.out.println(jedis.hlen("hpComputer"));//得到hash类型数据的元素个数

        jedis.hset("hpComputer","madeIn","China");//向hash类型的数据中添加元素
        System.out.println(jedis.hlen("hpComputer"));

        Map<String,String> all = jedis.hgetAll("hpComputer");//获取当前库中key为"hpComputer"的hash类型数据值中的所有数据
        for (String s : all.keySet()) {
            System.out.println(s+":"+all.get(s));
        }

        jedis.hget("hpComputer","price");//获取map中key为'peice'的值

        //jedis.hvals("hpComputer"); 获取key为'hpComputer'的hash数据的所有value值
        Set<String> keys =  jedis.hkeys("hpComputer");//获取一个hash类型的诗句的全部元素的key值
        for (String key : keys) {
            System.out.println(key);
        }

        jedis.close();








    }

}
