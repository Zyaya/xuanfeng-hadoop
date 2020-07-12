package com.xuanfeng.bigdata.junit;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {

    @Test
    public void testAdd() {

        Calculator calculator = new Calculator();
        int result = calculator.add(2, 5);
        assertEquals(7, result);
    }

    @Test
    public void testDivide(){
        Calculator calculator = new Calculator();
        int result = calculator.divide(9, 3);
        assertEquals(3, result);
    }

    @Test(expected = ArithmeticException.class) //  异常捕获，可以通过
   // @Ignore //  忽略它
    public void testDivide2(){
        Calculator calculator = new Calculator();
        int result = calculator.divide(9, 0);
        assertEquals(4, result);
    }
}
