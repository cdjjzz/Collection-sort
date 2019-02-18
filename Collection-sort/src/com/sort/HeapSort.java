package com.sort;

import java.util.Arrays;

public class HeapSort {
	/**
	 * ��ʼ����
	 * left =2*i+1
	 * right=2*i+2;
	 * parent=(i-1)/2;
	 * max parent>left �Ҵ��� parent right
	 * i Ϊ���� index��
	 * @param a
	 * @param i
	 * @param j
	 */
	public static void init(int a[],int parent,int length){
		int temp=a[parent];//���ڵ�ֵ
		int child=2*parent+1;// ��ȡ���ӽڵ��index
		while(child<length){
			//�������ӽڵ�
			if(child+1<length&&a[child]<a[child+1]){
				child++;
			}
			//���ӽڵ��Ѵ���
			if(temp>a[child])
				break;
			a[parent]=a[child];
			//����
			parent = child;
	        child = 2 * child + 1;
		}
		a[parent]=temp;
	}
	public static void heapSort(int a[]){
		//ѭ����ʼ���� (i-1)/2
		for (int i = a.length / 2; i >= 0; i--) {
			init(a, i, a.length);
	    }
		//R[0] ��R[n]����λ�� һֱ�� n=1 ֹͣ
	    for (int i = a.length - 1; i > 0; i--) {
	        // ���һ��Ԫ�غ͵�һԪ�ؽ��н���
	        int temp = a[i];
	        a[i] = a[0];
	        a[0] = temp;
	 
	        // ɸѡ R[0] ��㣬�õ�i-1�����Ķ�
	        init(a, 0, i);
	    }
	}
	public static void main(String[] args) {
		int[] array = {4,2,8,9,5,7,6,1,3,10};
		HeapSort.heapSort(array);
		System.out.println(Arrays.toString(array));
	}
}
