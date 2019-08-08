package com.zx.design.factory.easy;

/**
 * @annotation:
 * @author: Zhengx
 * @create: 2019-08-02 11:09
 */
public class Sub implements Operation {

    @Override
    public double getResult(double numA, double numB) {
        return numA-numB;
    }
}
