package com.sort;

public class SelectSort {
	/**
	 * ѡ�����򣬼���λ��i��ֵ����С����λ��i����min(��ʶ��Сֵ��λ��)��ѭ���Ƚ�λ��j��ֵ�Ƿ�С��λ��min��ֵ������ǽ���min����j��ֵ
	 * ���Ƚ�i ��min�Ƿ���ȣ�����Ǳ�ʾ��Сֵ����λ��i��ֵ������Ҫ����
	 * ѭ��n*n ����n��
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
