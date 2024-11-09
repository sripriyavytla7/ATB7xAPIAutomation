package com.testingacademy;

public class nobuilderpattern {
    public void step1(    )
    {
        System.out.println("step1");
    }; public void step2(    )
    {
        System.out.println("step2");
    }; public void step3(  String param  )
    {
        System.out.println("step3"+param);
    };

    public static void main(String[] args) {
        nobuilderpattern bp = new nobuilderpattern();
        bp.step1();
        bp.step2();
        bp.step3("priya");
    }

}
