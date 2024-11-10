package com.testingacademy.ex_21092024_testngexamples;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Test007 {
    @Test
    public void serverStartedOk() {
        System.out.println("I will run first");
        Assert.assertTrue(false);
    }
    @Test(priority = 0)
    public void serverStartedOk1() {
        System.out.println("I will run first p0");
        Assert.assertTrue(true);
    }

    @Test(dependsOnMethods = {"serverStartedOk1","serverStartedOk"})
    public void method1() {
        System.out.println("method1");
    }
}
