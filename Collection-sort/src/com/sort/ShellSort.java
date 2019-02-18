package com.sort;

import java.util.Arrays;

public class ShellSort {
	
	//knuth间隔序列的希尔排序算法实现 knuth 间隔序列 3h+1
	public static void ShellKnuthSort(int a[]){
		int step=1;
		int len=a.length;
		//计算增量
		while(step<=len/3){
			step=step*3+1;
		}
		while(step>0){
			for (int i = step; i <len; i++) {
				int temp=a[i];
				int j=i;
				while(j>step-1&&temp<=a[j-step]){
					a[j]=a[j-step];
					j-=step;
				}
				a[j]=temp;
			}
			step=(step-1)/3;
		}
	}
	
	//间隔为2h的希尔排序
	public static void Shell2HSort(int a[]){
		int step=1;
		int len=a.length;
		for(step=len/2;step>0;step/=2){
			for (int i = step; i < a.length; i++) {
				int temp=a[i];
				int j=i;
				while(j-step>=0&&temp<=a[j-step]){
					a[j]=a[j-step];
					j-=step;
				}
				a[j]=temp;
			}
		}
	}
	
	public static void main(String[] args) {
		int[] array = {4,2,8,9,5,7,6,1,3,10};
		ShellSort.Shell2HSort(array);
		System.out.println(Arrays.toString(array));
	}
}
