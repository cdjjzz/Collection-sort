package com.utils;

import java.util.ArrayList;
import java.util.List;

import javax.jws.Oneway;

/**
 * 斐波那契数列
 * @author pet-lsf
 *
 */
public class Finb {
	static int find[];
	public static void main(String[] args) {
		Finb finb=new Finb();
		int n=45;
		find=new int[n+1];
		System.out.println(finb.findMax(n));
		System.out.println(finb.findDg(n));
		System.out.println(finb.findDy(n));
	}
	/**
	 * 0  1    1  better
	 * 使用数组保存已经计算过 的数据 -》 递归 合法效益：不重复进行计算  程序应该具备记录功能
	 * @param n
	 * @return
	 */
	int findMax(int n){
		if( n<=1 ) return n;
		if(find[n]>0)return find[n];
		find[n]=findMax(n-1)+findMax(n-2);
		return find[n];
	}
	/**
	 * 0  1  1 bad inst
	 * @param n
	 * @return
	 */
	int findDg(int n){
		if(n<=1)return n;
		return findDg(n-1)+findDg(n-2);
	}
	/**
	 * 动态规划实现
	 * 递归-分治： 新问题依赖子问题  将大任务拆分成小任务   计算小任务结果并返回相加=大任务结果
	 * 
	 * @param n
	 * @return
	 */
	int findDy(int n){
		if( n<=1 ) return n;
		/**
		 * f[i](parent)=f[i-2](left)+f[i-1](right)
		 */
		int  left=0;
		int  right=1;
		int  parent = 0;
		for(int i=2;i<=n;i++){
			parent=left+right;
			left=right;
			right=parent;
		}
		return parent;
	}
	
	/**
	 * 
	 * 
	 * 数列通项公式   (1/√5)*{[(1+√5)/2]^n - [(1-√5)/2]^n}
	 * n=1  r =1
	 * n=2  r=1
	 * n=3  r=2
	 * 
	 * 
	 * 等差数列通项公式 An=A1+(n-1).d 等差中项     An-1   An   An+1  An为等差中项   An=An-1+An+1=2An/2;
	 * 
	 * 一般的，若数列\left\{ a_{n} \right\} 满足a_{n}=a_{n-1}+f(n),n\geq 2，且\sum_{i=1}^{n}{f(i)} 方便计算，则可用叠加法求数列\left\{ a_{n} \right\} 的通项公式，a_{n}=a_{1}+\sum_{i=2}^{n}{f(i)} 
	 * 
	 */
	
	/**
	 * 等比数列 An=a1.q^(n-1)  证明是数列 An/A(n-1)=A(n-1)/A(n-2); q 被称为公比
	 * 
	 * 
	 * 
	 */
	
	/**
	 * copy 使用不同的地址
	 * @param list
	 * @param startIndex
	 * @param endIndex
	 * @return
	 * @throws Exception
	 */
   List<Object>	subList(List<Object> list,int startIndex,int endIndex) throws Exception{
	   if(startIndex<0){
		   startIndex=0;
	   }
	   if(endIndex>list.size()){
		   endIndex=list.size();
	   }
	   if(startIndex>endIndex){
		   throw new Exception();
	   }
	   List<Object> objects=new ArrayList<Object>();
	   for (int i = startIndex; i <endIndex; i++) {
		   objects.add(list.get(i));
	  }
	   return objects;
   }
   /**
    *子集 使用同一块地址 
    * @param list
    * @param startIndex
    * @param endIndex
    * @return
    * @throws Exception
    */
   List<Object> sub_List(List<Object> list,int startIndex,int endIndex) throws Exception{
	   if(startIndex<0){
		   startIndex=0;
	   }
	   if(endIndex>list.size()){
		   endIndex=list.size();
	   }
	   if(startIndex>=endIndex){
		   throw new Exception();
	   }
	   return list.subList(startIndex, endIndex);
   }
   
   
   List<Object> sub_ist(List<Object> list,int startIndex,int endIndex) throws Exception{
	   if(startIndex<0){
		   startIndex=0;
	   }
	   if(endIndex>list.size()){
		   endIndex=list.size();
	   }
	   if(startIndex>=endIndex){
		   throw new Exception();
	   }
	   return list.subList(startIndex, endIndex);
   }
   
   void checkElement(int startIndex,int endIndex) throws Exception{
	   if(startIndex<0)
		   throw new Exception();
	   if(startIndex>=endIndex)
		   throw new Exception();
   }
   
   void checkElement_index(int startIndex,int endIndex) throws Exception{
	   if(startIndex<0)
		   throw new Exception();
	   if(startIndex>=endIndex)
		   throw new Exception();
   }
	
	
}
