package com.example.SpringExample.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AssertExample {

    @Test
    public void test1(){
        System.out.println("Testing some assertion methods");
        int actualArray[] = {1,2,3,4,5};
        int expectedArray[] = {1,2,3,4,5};
        Assertions.assertArrayEquals(expectedArray, actualArray);

        String name = new String("Madhu");
        String expectedName = new String("Madhu");
        Assertions.assertSame(expectedName, name); // chek references point
        Assertions.assertEquals(expectedName, name); // compare the obj value
    }

}


//Assertion.assertNull()
//Assertion.assertNotNull()
//Assertion.assertTrue()
//Assertion.assertIterableEquals()
//Assertion.assertThrows
//assertation- validating actual result wih expected result