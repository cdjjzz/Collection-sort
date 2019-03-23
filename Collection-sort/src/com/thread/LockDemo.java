package com.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {
    private static ReentrantLock lock = new ReentrantLock();
    static  Condition condition=lock.newCondition();
    public static void main(String[] args) {
    	final In in=new  In();
        for (int i = 0; i < 5; i++) {
		13123123232424
            Thread thread = new Thread(() -> {
                lock.lock();
                int j=in.getI();
                in.setI(++j);
                try {
                	System.out.println(in.getI());
                	if(in.getI()==0){
                		condition.await();
                	}else{
                		condition.signal();
                		System.out.println(Thread.currentThread().getName());
                	}
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            });
            thread.start();
        }
    }
    static class In{
    	volatile int i;

		public int getI() {
			return i;
		}

		public void setI(int i) {
			this.i = i;
		}
    	
    	
    }
}
