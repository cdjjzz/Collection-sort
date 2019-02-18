package com.sort;

import java.util.Arrays;

/**
 * 归并排序
 * @author pet-lsf
 * 归并:合并 
 * 合并两个已经有序的数组，集合 组合成新的 有序数组-》集合
 */
public class MergerSort {
	
	
	public static int[] sort(int a[],int low,int high){
		if(low<high){
			int mid=low+(high-low)/2;
			/**
			 * 将数组划分小数组
			 */
			sort(a, low, mid);
			sort(a, mid+1, high);
			
			/**
			 * 将小数组合并成大数组
			 */
			System.out.println("high:"+high+" mid:"+mid+" low:"+low);
			merageSort(a, low, mid, high);
		}
		return a;
	}
	
	public static void merageSort(int a[],int low,int mid,int high){
		//
		int temp[]=new int[high-low+1];
		//比较a 数组low-mid 位置 与 mid-high 位置大小
		int i=low;
		int j=mid+1;
		int k=0;
		while(i<mid&&j<=high){
			if(a[i]<a[j]){
				 temp[k++] = a[i++];
			}else{
				 temp[k++] = a[j++];
			}
		}
		 // 把左边剩余的数移入数组 
        while(i<=mid){
            temp[k++] = a[i++];
        }
        // 把右边边剩余的数移入数组
        while(j<=high){
            temp[k++] = a[j++];
        }
        for(int x=0;x<temp.length;x++){
            a[x+low] = temp[x];
        }
	}
	public static void main(String[] args) {
		int[] c = {2,7,8,3,1,6,9,0,5,4};
		c = sort(c,0,c.length-1);
		System.out.println(Arrays.toString(c));
	}
}
