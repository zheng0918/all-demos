package com.zx.message.queen;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * redis用作MessageQueen
 *
 * 在Java中写publish代码，cmd订阅且接收
 * 在cmd中publish message ，在Java端接收，接收时需要定义一个JedisPubSub的子类且实现onMessage方法，此方法用作对接收到的消息做处理
 *
 */
public class JedisSubscribe {

    public static void main(String[] args) {

        Jedis jedis = new Jedis("localhost",6379);
        jedis.auth("123");
        MyListener myListener = new MyListener();
        jedis.subscribe(myListener,"cctv");//订阅一个频道
    }


    /**
     * 内部类，消息处理器
     */
    private static class MyListener extends JedisPubSub{

        @Override
        public void onMessage(String channel, String message) {
            System.out.println(channel+":"+message);//接收消息
//            super.onMessage(channel, message);
        }
    }


}
