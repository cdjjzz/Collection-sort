package com.utils;
/**
 * 最大子序列
 * @author pet-lsf
 *
 */
public class BigMaxSeq {
	
	public static void main(String[] args) {
		
	}
	/**
	 * O(N3)
	 * @param a
	 * @return
	 */
	public  int maxSubO3(int[] a){
		int maxSum=0;
		//控制数组长度
		for (int i = 0; i < a.length; i++) {
			 //控制子序列长度
			for (int j = i; j < a.length; j++) {
				int tempSum=0;
				//计算子序列的值
				for (int k = i; k<=j; k++) {
					tempSum+=a[k];
				}
				if(maxSum<tempSum){
					maxSum=tempSum;
				}
			}
		}
		return maxSum;
	}
	/**
	 * O(N3)
	 * @param a
	 * @return
	 */
	public  int maxSubO2(int[] a){
		int maxSum=0;
		//控制数组长度
		for (int i = 0; i < a.length; i++) {
			 //控制子序列长度
			int tempSum=0;
			for (int j = i; j < a.length; j++) {
				//计算子序列的值
				tempSum+=a[j];
				if(maxSum<tempSum){
					maxSum=tempSum;
				}
			}
		}
		return maxSum;
	}
	public int maxSubO(int[] a){
		int maxSum=0;
		int tempSum=0;
		for(int i=0;i<a.length;i++){
			tempSum+=a[i];
			if(tempSum>maxSum){
				maxSum=tempSum;
			}else{
				
			}
		}
		return maxSum;
	}


}
