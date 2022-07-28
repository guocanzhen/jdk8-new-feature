package com.guocz.function;

/**
 * @author guocz
 * @date 2022/7/27 11:17
 */
public class Demo {

    public static void main(String[] args) {
       test();
    }

    static void test() {
        MyInterface myInterface = () -> {
            System.out.println("ghjkde");
        };
        myInterface.method();
    }
}
