package com.sort;

import java.util.Arrays;

public class HeapSort {
	/**
	 * 初始化堆
	 * left =2*i+1
	 * right=2*i+2;
	 * parent=(i-1)/2;
	 * max parent>left 且大于 parent right
	 * i 为数组 index；
	 * @param a
	 * @param i
	 * @param j
	 */
	public static void init(int a[],int parent,int length){
		int temp=a[parent];//父节点值
		int child=2*parent+1;// 获取左子节点的index
		while(child<length){
			//存在右子节点
			if(child+1<length&&a[child]<a[child+1]){
				child++;
			}
			//父子节点已大于
			if(temp>a[child])
				break;
			a[parent]=a[child];
			//继续
			parent = child;
	        child = 2 * child + 1;
		}
		a[parent]=temp;
	}
	public static void heapSort(int a[]){
		//循环初始化堆 (i-1)/2
		for (int i = a.length / 2; i >= 0; i--) {
			init(a, i, a.length);
	    }
		//R[0] 与R[n]交换位置 一直到 n=1 停止
	    for (int i = a.length - 1; i > 0; i--) {
	        // 最后一个元素和第一元素进行交换
	        int temp = a[i];
	        a[i] = a[0];
	        a[0] = temp;
	 
	        // 筛选 R[0] 结点，得到i-1个结点的堆
	        init(a, 0, i);
	    }
	}
	public static void main(String[] args) {
		int[] array = {4,2,8,9,5,7,6,1,3,10};
		HeapSort.heapSort(array);
		System.out.println(Arrays.toString(array));
	}
}
