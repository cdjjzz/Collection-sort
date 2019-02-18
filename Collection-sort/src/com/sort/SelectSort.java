package com.sort;

public class SelectSort {
	/**
	 * 选择排序，假设位置i的值是最小，将位置i赋予min(标识最小值的位置)，循环比较位置j的值是否小于位置min的值，如果是交换min赋予j的值
	 * 最后比较i 和min是否相等，如果是表示最小值就是位置i的值，不需要交换
	 * 循环n*n 交换n次
	 * @param src
	 * @return
	 */
	public static int[] sort(int src[]){
		for(int i=0;i<src.length-1;i++){
			int min=i;
			for(int j=i+1;j<src.length;j++){
				if(src[j]<src[min]){
					min=j;
				}
			}
			if(min!=i){
			  int temp=src[i];
			  src[i]=src[min];
			  src[min]=temp;
			}
		}
		return src;
	}
	public static void main(String[] args) {
		int src[]=new int[]{1,26,8,10,20,3,100,23};
		BubbleSort.print(src);
		SelectSort.sort(src);
		BubbleSort.print(src);
	}
}
