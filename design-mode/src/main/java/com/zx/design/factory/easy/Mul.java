package com.zx.design.factory.easy;

/**
 * @annotation:
 * @author: Zhengx
 * @create: 2019-08-02 11:10
 */
public class Mul implements Operation {

    @Override
    public double getResult(double numA, double numB) {
        return numA*numB;
    }
}
