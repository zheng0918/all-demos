package com.zx.redis;

import redis.clients.jedis.Jedis;

import java.util.LinkedList;
import java.util.List;

public class JedisList {

    public static void main(String[] args) {

        Jedis jedis = new Jedis("localhost",6379);
        jedis.flushAll();

        List<String> strings = new LinkedList<String>();

        jedis.lpush("list-1","aa","bb","cc");//从左往右插入 cc bb aa

        jedis.rpush("list-1","dd","ee","ff");//cc bb aa dd ee ff

        jedis.llen("list-1");//查询一个列表类型的数据有几个元素

        jedis.lpop("list-1");//从左边取一个数据 bb aa dd ee ff ,有返回值为'cc'
//        jedis.rpop("list-1"); 从右边取一个数据

        jedis.lindex("list-1",2);//查询列表某个位置的元素，从左往右索引为2的数据 dd

        //取得部分或者全部元素
        List<String> lv = jedis.lrange("list-1",2,4);//去除2-4的数据 ,2到-1就是从2开始直到结束


        jedis.close();


    }


}
