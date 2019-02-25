package com.thread;
public class FinalDemo {
    private int a;  //��ͨ��
    private final int b; //final��
    private static FinalDemo finalDemo;

    public FinalDemo() {
        a = 1; // 1. д��ͨ��
        b = 2; // 2. дfinal��
    }

    public static void writer() {
        finalDemo = new FinalDemo();
    }

    public static void reader() {
        FinalDemo demo = finalDemo; // 3.����������
        int a = demo.a;    //4.����ͨ��
        int b = demo.b;    //5.��final��
        int c = a;
    }
}