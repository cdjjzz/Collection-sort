package com.sort;

import java.util.Arrays;

/**
 * �鲢����
 * @author pet-lsf
 * �鲢:�ϲ� 
 * �ϲ������Ѿ���������飬���� ��ϳ��µ� ��������-������
 */
public class MergerSort {
	
	
	public static int[] sort(int a[],int low,int high){
		if(low<high){
			int mid=low+(high-low)/2;
			/**
			 * �����黮��С����
			 */
			sort(a, low, mid);
			sort(a, mid+1, high);
			
			/**
			 * ��С����ϲ��ɴ�����
			 */
			System.out.println("high:"+high+" mid:"+mid+" low:"+low);
			merageSort(a, low, mid, high);
		}
		return a;
	}
	
	public static void merageSort(int a[],int low,int mid,int high){
		//
		int temp[]=new int[high-low+1];
		//�Ƚ�a ����low-mid λ�� �� mid-high λ�ô�С
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
		 // �����ʣ������������� 
        while(i<=mid){
            temp[k++] = a[i++];
        }
        // ���ұ߱�ʣ�������������
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
