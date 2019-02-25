package com.thread;

/**
 * @author lsf53_000
 *
 */
public class DeadThreadDemo {
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
                        System.out.println("1111232341231232341");
                        System.out.println("111");
                        System.out.println("113");
			System.out.println("114");
			System.out.println("14135");
			System.out.println("5555");

			System.out.println("555");
			System.out.println("55588");
			System.out.println("222333");

                    }
                }
            }
        });
        threadA.start();
        threadB.start();

    }
}
