package com.testingacademy;

public class BuilderPatternDesignPattern {
    public BuilderPatternDesignPattern  step1()
    {
        System.out.println("step1 - given");
        return this;
    } public BuilderPatternDesignPattern step2()
    {
        System.out.println("step2 - when");
        return this;
    } public BuilderPatternDesignPattern step3()
    {
        System.out.println("step3-then");
        return this;
    }

    public static void main(String[] args) {
        BuilderPatternDesignPattern bp = new BuilderPatternDesignPattern();
        bp.step1().step2().step3();
        bp.step2().step1().step3();
    }

}
