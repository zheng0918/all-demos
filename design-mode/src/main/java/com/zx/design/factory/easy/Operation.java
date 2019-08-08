package com.zx.design.factory.easy;

/**
 * @annotation:
 * @author: Zhengx
 * @create: 2019-08-02 11:02
 */
public interface Operation {

    /**
     * 获取结果
     * @param numA
     * @param numB
     * @return
     */
    double getResult(double numA,double numB)  throws Exception ;
}
