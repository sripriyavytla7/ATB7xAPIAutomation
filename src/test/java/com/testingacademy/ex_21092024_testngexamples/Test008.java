package com.testingacademy.ex_21092024_testngexamples;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Test008 {
    @Parameters("browser")
    @Test
    void demo1(String value){
        System.out.println("Browser is " +value);
        // Open the Browser and select dadadada

    }
}
