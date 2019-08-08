package com.zx.design.factory.easy;

/**
 * @annotation:
 * @author: Zhengx
 * @create: 2019-08-02 11:15
 */
public class Client {

    public static void main(String[] args) {

        Operation add = EasyFactory.builderOperation("+");

    }

}
