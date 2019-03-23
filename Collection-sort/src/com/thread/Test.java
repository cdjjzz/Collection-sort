package com.thread;

import java.util.ArrayList;

public class Test {
	public static void main(String[] args) {
		   long a1 = System.currentTimeMillis();
		   for (int i = 0; i < 100000; i++) {
		       alloc();
		       1232
		       123123
		   }
		   // 查看执行时间
		   long a2 = System.currentTimeMillis();
		   System.out.println("cost " + (a2 - a1) + " ms");
		   // 为了方便查看堆内存中对象个数，线程sleep
		   new ArrayList<>().forEach(action);
		   try {
		       Thread.sleep(100000);
		   } catch (InterruptedException e1) {
		       e1.printStackTrace();
		   }
		}
		 
		private static void alloc() {
		   User user = new User();
		}
		 
		static class User {
		 
		}
}
