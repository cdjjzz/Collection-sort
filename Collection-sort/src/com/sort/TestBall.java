package com.sort;

import java.util.Arrays;

public class TestBall {
	static int n=16;//���Ĳ���
	static int k=1234567;//С��ĸ���
	static int arr[];//���С��·����·��
	public static void main(String[] args) throws Exception{
		int dex=(int) Math.pow(2, n);
		arr=new int[dex];
		//Ϊarr�����ʼ��
		Arrays.fill(arr, -1);
		int index=1;
		for(int i=0;i<k;i++){
			index = 1;
			while(index*2<dex){//�ж��Ƿ��䵽�ײ�			
				arr[index] = -arr[index];//��ת����
				if(arr[index]==1){//���ݿ���״̬�ж�����
					index = 2*index;
				}else{
					index = 2*index+1;
				}
			}			
		}
		System.out.println("����"+index);
		
		//����ģ��·�� k=2K
		int k = 1;//��¼�ڵ�
		int m=1234567;
		for(int i=0;i<n-1;i++){//һ����Ҫ����n-1��(��Ϊ���ڵ㲻��)
			if(m%2==1){//�����ǰΪ������˵��
				k = 2*k;//����ڵ�
				m = (m+1)/2;//�������ߵ�(m+1)/2��
			}else{
				k = 2*k+1;//���ҽڵ�
				m = m/2;//�������ߵ�m/2��	
			}
		}
		System.out.println(k);
	}
}
