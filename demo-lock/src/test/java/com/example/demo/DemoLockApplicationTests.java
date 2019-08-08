package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoLockApplicationTests {

    @Test
    public void contextLoads() {


        System.out.println(gcd(12,15));

    }

    int gcd(int a,int b)
    {
        return a%b==0?b:gcd(b,a%b);
    }

}
