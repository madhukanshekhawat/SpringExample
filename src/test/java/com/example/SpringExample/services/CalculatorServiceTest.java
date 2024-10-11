package com.example.SpringExample.services;

import com.example.SpringExample.service.CalculatorService;
import org.junit.jupiter.api.*;

public class CalculatorServiceTest {

    @BeforeAll
    public static void init(){
        System.out.println("This is single time login");
    }

    @BeforeEach
    public void eachTestCase(){
        System.out.println("Before Each");
    }

    @AfterEach
    public void afterTestCase(){
        System.out.println("After Each");
    }

    @AfterAll
    public static void cleanup(){
        System.out.println("executed after all methods");
    }

    @Test
    @DisplayName("This is custom name")
    public void addTwoNumbersTest(){
        System.out.println("First test case");
        int result = CalculatorService.addTwoNumbers(12,45);
        int expected = 57;
        Assertions.assertEquals(expected, result,"Test failed !");
    }

    @Test
    @Disabled
    public void addAnyNumbersTest(){
        System.out.println("Second test case");
        int result = CalculatorService.sumAnyNumbers(2,7,5,8);
        int expectedResult = 22;
        Assertions.assertEquals(expectedResult, result);
    }

}

