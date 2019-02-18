package com.sort;

import java.util.Arrays;

public class TestBall {
	static int n=16;//树的层数
	static int k=1234567;//小球的个数
	static int arr[];//存放小球路过的路径
	public static void main(String[] args) throws Exception{
		int dex=(int) Math.pow(2, n);
		arr=new int[dex];
		//为arr数组初始化
		Arrays.fill(arr, -1);
		int index=1;
		for(int i=0;i<k;i++){
			index = 1;
			while(index*2<dex){//判断是否落到底部			
				arr[index] = -arr[index];//翻转开关
				if(arr[index]==1){//根据开关状态判断走向
					index = 2*index;
				}else{
					index = 2*index+1;
				}
			}			
		}
		System.out.println("落在"+index);
		
		//根据模仿路径 k=2K
		int k = 1;//记录节点
		int m=1234567;
		for(int i=0;i<n-1;i++){//一个数要分流n-1次(因为根节点不算)
			if(m%2==1){//如果当前为奇数，说明
				k = 2*k;//到左节点
				m = (m+1)/2;//是往左走的(m+1)/2个
			}else{
				k = 2*k+1;//到右节点
				m = m/2;//是往右走的m/2个	
			}
		}
		System.out.println(k);
	}
}
