package com.sort;

public class BubbleSort {
	/**
	 * ð������ λ��i ��λ��j �Ƚϴ�С  λ��i��ֵ����λ��j��ֵ������i �� j ��ֵ
	 * ѭ��n*n ����λ��n*n
	 * @param src
	 * @return
	 */
	public static int[] bubbleSort(int src[]){
		for(int i=0;i<src.length-1;i++){
			for(int j=i+1;j<src.length;j++){
				int temp=src[i];
				if(temp>src[j]){
					src[i]=src[j];
					src[j]=temp;
				}
			}
		}
		return src;
	}
	public static void main(String[] args) {
		int src[]=new int[]{1,26,8,10,20,3,100,23};
		BubbleSort.print(src);
		BubbleSort.bubbleSort(src);
		BubbleSort.print(src);
	}
	public static void print(int src[]){
		for(int i=0;i<src.length;i++){
			System.out.print(src[i]+"\t");
		}
		System.out.println();
		int initialCapacity=10;
         initialCapacity |= (initialCapacity >>>  1);
         initialCapacity |= (initialCapacity >>>  2);
         initialCapacity |= (initialCapacity >>>  4);
         initialCapacity |= (initialCapacity >>>  8);
         initialCapacity |= (initialCapacity >>> 16);
         System.out.println(16|5);
	}

}
