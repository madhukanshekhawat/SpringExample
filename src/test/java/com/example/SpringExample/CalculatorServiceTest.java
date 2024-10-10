package com.example.SpringExample;

import com.example.SpringExample.service.CalculatorService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class CalculatorServiceTest {

    @Test
    public void addTwoNumbersTest(){
        int result = CalculatorService.addTwoNumbers(12,45);
        int expected = 57;
        Assert.assertEquals(expected, result);
    }

    @Test
    public void addTwoNumbersTest_withWrongOutput(){
        int result = CalculatorService.addTwoNumbers(12,45);
        int unexpected = 52;
        Assert.assertNotEquals(unexpected, result);
    }

    @Test
    public void addAnyNumbersTest(){
        int result = CalculatorService.sumAnyNumbers(2,7,5,8);
        int expectedResult = 22;
        Assert.assertEquals(expectedResult, result);
    }

}
