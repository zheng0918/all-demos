package com.zx.sentinel;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

/**
 * Redis-哨兵池案例(SentinelPool)
 */
public class JedisSentinel {

    /**
     * 假设现在有三个哨兵参与监听master
     * @param args
     */
    public static void main(String[] args) {

        //创建哨兵集合
        Set<String> ips = new HashSet<String>();
        ips.add("127.0.0.1:26386");//哨兵的ip + 端口号
        ips.add("127.0.0.1:26387");
        ips.add("127.0.0.1:26388");

        JedisSentinelPool sPool = new JedisSentinelPool("redis001",ips);//第一个参数为master的名称，第二个为哨兵集合

        //从哨兵池得到数据库连接
        Jedis jedis =sPool.getResource();
        //哨兵池管理数据库要求主从数据库密码一致
        jedis.auth("123");
        jedis.flushAll();
        for(int i=0; i<1000; i++) {
            jedis.set("key" + i, "Redis is so cool!");
        }
        jedis.close();
        sPool.close();
    }

}
