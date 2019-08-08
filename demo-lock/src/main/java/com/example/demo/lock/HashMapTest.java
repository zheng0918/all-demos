package com.example.demo.lock;

import java.util.HashMap;
import java.util.Map;

/**
 * @annotation:
 * @author: Zhengx
 * @create: 2019-06-19 13:52
 */
public class HashMapTest {


    private Integer a;

    public HashMapTest(int a) {
        this.a = a;
    }

    public static void main(String[] args) {



        Map<HashMapTest, Integer> map = new HashMap<HashMapTest, Integer>();
        HashMapTest instance = new HashMapTest(1);
        System.out.println("instanc" +
                "" + instance.hashCode());
        HashMapTest newInstance = new HashMapTest(1);
        System.out.println("newInstance.hashcode:" + newInstance.hashCode());
        System.out.println(instance == newInstance?"相等":"不相等");
        System.out.println(instance.equals(newInstance)?"相等":"不相等");
        map.put(instance, 1);
        map.put(newInstance, 2);
        Integer value = map.get(instance);
        System.out.println("instance value:"+value);
        Integer value1 = map.get(newInstance);
        System.out.println("newInstance value:"+value1);

    }

    @Override
    public boolean equals(Object o) {
        if(o == this) {
            return true;
        } else if(!(o instanceof HashMapTest)) {
            return false;
        } else {
            HashMapTest other = (HashMapTest)o;
            if(!other.canEqual(this)) {
                return false;
            } else {
                Integer this$data = this.getA();
                Integer other$data = other.getA();
                if(this$data == null) {
                    if(other$data != null) {
                        return false;
                    }
                } else if(!this$data.equals(other$data)) {
                    return false;
                }

                return true;
            }
        }
    }
    protected boolean canEqual(Object other) {
        return other instanceof HashMapTest;
    }

    public void setA(Integer a) {
        this.a = a;
    }

    public Integer getA() {
        return a;
    }

    @Override
    public int hashCode() {
        boolean PRIME = true;
        byte result = 1;
        Integer $data = this.getA();
        int result1 = result * 59 + ($data == null?43:$data.hashCode());
        return result1;
    }



}
