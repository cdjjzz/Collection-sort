package com.sort;

public class InsertSort {
	/**
	 * 直接插入排序
	 * 假设0-j 已经排好序，寻找j+1位置的值是否在已排好序之中，如果交换双方的值
	 * 循环n*n/2 交换
	 * @param src
	 * @return
	 */
	public static int[] sort(int src[]){
		int j;
		for(int i=1;i<src.length;i++){
			int temp=src[i];
			j=i;
			//找到较小的值，需要移动比他大   移动比较复杂  在希尔排序中解决
			while(j>0&&temp<src[j-1]){
				src[j]=src[j-1];
				j--;
			}
			src[j]=temp;
		}
		return src;
	}
	public static void main(String[] args) {
		int src[]=new int[]{1,26,8,10,20,3,100,23};
		BubbleSort.print(src);
		InsertSort.sort(src);
		BubbleSort.print(src);
	}
}
