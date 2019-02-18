package com.sort;

public class InsertSort {
	/**
	 * ֱ�Ӳ�������
	 * ����0-j �Ѿ��ź���Ѱ��j+1λ�õ�ֵ�Ƿ������ź���֮�У��������˫����ֵ
	 * ѭ��n*n/2 ����
	 * @param src
	 * @return
	 */
	public static int[] sort(int src[]){
		int j;
		for(int i=1;i<src.length;i++){
			int temp=src[i];
			j=i;
			//�ҵ���С��ֵ����Ҫ�ƶ�������   �ƶ��Ƚϸ���  ��ϣ�������н��
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
