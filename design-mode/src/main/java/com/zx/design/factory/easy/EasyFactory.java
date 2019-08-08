package com.zx.design.factory.easy;

/**
 * @annotation:
 * @author: Zhengx
 * @create: 2019-08-02 11:12
 */
public class EasyFactory {

    /**简单工厂，根据字符串创建相应的对象**/
    public static Operation builderOperation(String name){
        Operation operation = null;
        switch (name){
            case "+":
                operation = new Add();
                break;
            case "-":
                operation = new Sub();
                break;
            case "*":
                operation = new Mul();
                break;
            case "/":
                operation = new Div();
                break;
        }
        return operation;
    }

}
