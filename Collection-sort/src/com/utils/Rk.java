package com.utils;

public class Rk {
	public static void main(String[] args) {
		Rk rk=new Rk();
		int result=rk.rk("abcdefg", "bc");
		System.out.println(result);
	}
	
	public int rk(String parent,String child){
		char p[]=parent.toCharArray();
		char c[]=child.toCharArray();
		int n=p.length;
		int m=c.length;
		if(n==0||m==0||m>n)return -1;
		int pv=String.valueOf(p[0]).hashCode();
		int sv=String.valueOf(c[0]).hashCode();
		int i=0;
		int j=0;
		int d=1;
		for (i = 1; i < m; i++) {
		       sv =(sv*d+String.valueOf(c[i]).hashCode());
		       pv =(pv*d+String.valueOf(p[i]).hashCode());
		}
		 i = m - 1;
		 do{
			 //hash 一样 比较结果
			 if(sv==pv){
				 for (j = 0; j < m && p[i - m + 1 + j] == c[j]; j++);
					 if(j==m){
						 return i-m+1;
					 }
			 }
			 i++;
			 if(i>=n)break;
			 //回溯
			 pv = (pv-(String.valueOf(p[i - m]).hashCode()* d))*d+String.valueOf(p[i]).hashCode();
		 }while(i<n);
		 return -1;
	}
}
