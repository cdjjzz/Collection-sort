package com.thread;

/**
 * 演示线程死锁
 * @author lsf53_000
 *
 */
public class DeadThreadDemo {
	//类变量
	private static String resource_a = "A";
    private static String resource_b = "B";

    public static void main(String[] args) {
        deadLock();
    }

    public static void deadLock() {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resource_a) {
                    System.out.println("get resource a");
                    try {
                        Thread.sleep(3000);
                        synchronized (resource_b) {
                            System.out.println("get resource b");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resource_b) {
                    System.out.println("get resource b");
                    synchronized (resource_a) {
                        System.out.println("get resource a");
                        //final 允许 引用对象不允许地址，但能修改里面的非final属性的值
                        //final 允许重载，不允许重写
                    }
                }
            }
        });
        threadA.start();
        threadB.start();

    }
}
