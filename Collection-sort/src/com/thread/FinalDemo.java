package com.thread;
public class FinalDemo {
    private int a;  //∆’Õ®”Ú
    private final int b; //final”Ú
    private static FinalDemo finalDemo;

    public FinalDemo() {
        a = 1; // 1. –¥∆’Õ®”Ú
        b = 2; // 2. –¥final”Ú
    }

    public static void writer() {
        finalDemo = new FinalDemo();
    }

    public static void reader() {
        FinalDemo demo = finalDemo; // 3.∂¡∂‘œÛ“˝”√
        int a = demo.a;    //4.∂¡∆’Õ®”Ú
        int b = demo.b;    //5.∂¡final”Ú
        int c = a;
    }
}