package com.thread;
public class BadSuspend {

  static Object u = new Object();
  static ChangeObjectThread t1 = new ChangeObjectThread("t1");
  static ChangeObjectThread t2 = new ChangeObjectThread("t2");


  static class ChangeObjectThread extends Thread {

    public ChangeObjectThread(String name) {
      super.setName(name);
    }

    public void run() {
      synchronized (u) {
        System.out.println("in " + getName());
        // ��ͣ
        Thread.currentThread().suspend();
      }
    }
  }

  public static void main(String[] args) throws InterruptedException {
    t1.start();
    Thread.sleep(100);
    // ��ʱ t1 �Ѿ���ͣ
    t2.start();
    // t1 �ָ�
    t1.resume();
    // t2 ��ʱ�ָ������� t2�ڻָ�֮���������ͣ������������
    // ����ʹ�� sleep �� t2 ����ͣ�Ϳ��ԡ�
//    Thread.sleep(100);
    t2.resume();
    t1.join();
    t2.join();

  }

}
