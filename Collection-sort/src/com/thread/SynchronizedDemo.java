package com.thread;
public class SynchronizedDemo {
    public static void main(String[] args) {
        synchronized (SynchronizedDemo.class) {
        	 int  a=0;
        }
        method();
    }

    private static void method() {
    }
}