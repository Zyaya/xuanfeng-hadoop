package com.xuanfeng.bigdata.junit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTest03 {

    static Calculator calculator;  //加上静态后整个类就执行（初始化）了一次



    @BeforeClass
    public static void setUp(){
        calculator = new Calculator();
        System.out.println("-----setUp----");
    }

    @AfterClass
    public static void tearDown(){
        calculator = null;
        System.out.println("-----tearDown-----");
    }

    @Test
    public void testAdd() {
        int result = calculator.add(2, 5);
        assertEquals(7, result);
        System.out.println("-----testAdd-----");
    }

    @Test
    public void testDivide(){

        int result = calculator.divide(9, 3);
        assertEquals(3, result);
        System.out.println("-----testDivide-----");
    }

    @Test(expected = ArithmeticException.class) //  异常捕获，可以通过
    @Ignore
    public void testDivide2(){

        int result = calculator.divide(9, 0);
        assertEquals(4, result);
        System.out.println("-----testDivide2-----");
    }
}
