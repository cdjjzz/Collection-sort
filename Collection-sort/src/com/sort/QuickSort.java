package com.sort;

import java.util.Arrays;

/**
 * 实现快排序
 * @author pet-lsf
 *
 */
public class QuickSort {

	//交换元素
	public static void swap(int a[],int i,int j){
		int temp=a[i];
		a[i]=a[j];
		a[j]=temp;
	}
	public static int mid(int a[],int left,int right){
		int i=left;
		int j=right+1;
		int base=a[left];
		for(;;){
			//寻找左子比base小的数据
			while(i<right&&a[++i]<base){};
			//寻找右子比base大的数据
			while(j>0&&a[--j]>base){};
			if(i>=j){
				break;
			}else{
				swap(a, i, j);
			}
		}
		swap(a, left, j);
	    return j;
	}
	public static void  QuSort(int a[],int left,int right){
		if(left>=right){
			return;
		}else{
			//分治
			int j=mid(a, left, right);
			//继续
			QuSort(a, left, j-1);
			QuSort(a, j+1, right);
		}
	}
	public static void Sort(int a[]){
		QuSort(a, 0,a.length-1);
	}
	public static void main(String[] args) {
		int[] array = {4,2,8,9,5,7,6,1,3,10};
		QuickSort.Sort(array);
		System.out.println(Arrays.toString(array));
	}
}
