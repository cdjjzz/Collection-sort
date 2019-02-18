package com.utils;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class NBL {
   private Stack<String> czf=new Stack<String>();
   private ArrayList<String> sourc=new ArrayList<String>();
   //����沨�����ʽ
   private ArrayList<String> math=new ArrayList<String>();
   private static final int One = 1;      //
   private static final int Two = 3;     //
   private static final int Three = 5;   //�涨���ȼ�   Three ���
   //  ����һ������ջ
   private static Stack<String> ys_stack = new Stack<>();
   
   
   public NBL(String  bp) {
	   //ʹ��StringTokenizer ���string
	   StringTokenizer stringTokenizer=new StringTokenizer(bp, "+-*/()",true);
	   while(stringTokenizer.hasMoreTokens()){
		   sourc.add(stringTokenizer.nextToken());
	   }
   }
   //ת�����沨�����ʽ
   public void zbZh(){
	  for (int i = 0; i < sourc.size(); i++) {
		 String value=sourc.get(i);
		 if(isNum(value)){
			 math.add(value);
		 }else if(isCzf(value)){
			 //��������� ���ȼ�
			 stack_czf(value);
		 }
	  }
	  	while(!czf.isEmpty()){
		   math.add(czf.pop());
	    }
   }
   public void stack_czf(String value){
	   if(czf.isEmpty()){
		   czf.push(value);
		   return;
	   }
	   if("(".equals(value)){
		   czf.push(value);
		   return;
	   }
	   if(")".equals(value)){
		   String string="";
		   while(!"(".equals(string=czf.pop())){
			   math.add(string);
		   }
	   }
	   if("(".equals(czf.peek())){
		   czf.push(value);
		   return;
	   }
	   if(isYxj(value, czf.peek())){
		   czf.push(value);
		   return;
	   }
	   if(!isYxj(value, czf.peek())){
		   math.add(czf.pop());
		   czf.push(value);
	   }
	   
   }
   public int getYxj(String str){
       
       switch(str){
       case "(":return Three;
       case "*":
       case "/":return Two;
       case "+":
       case "-":return One;
       case ")":return 0;
       default : return -1;
       }
       
   }
   
   // �ж����ȼ� 
   public boolean isYxj(String str1,String str2){
       return getYxj(str1) > getYxj(str2);   
   }
   //�ж��Ƿ�������
   public boolean isNum(String value){
	   if(value.matches("[0-9]+")){
		   return true;
	   }else{
		   return false;
	   }
   }
   //�ж��Ƿ��ǲ�����
   public boolean isCzf(String value){
	   if(value.matches("[\\+\\-\\*\\/\\(\\)]")){
		   return true;
	   }else {
		   return false;
	   }
   }
   public void print(ArrayList<String> arrayList){
	  for (int i = 0; i < arrayList.size(); i++) {
		System.out.print(arrayList.get(i)+"\t");
	  }
	  System.err.println();
    }
   public int jsff(String s1,String s2,String s3){
       int a = Integer.parseInt(s2);
       int b = Integer.parseInt(s1);
       switch(s3){
       case "+":return a+b;
       case "-":return a-b;
       case "*":return a*b;
       case "/":return a/b;
       default : return 0;
       }
   }
   
   //  ���� �沨��ʽ
   public int js_nbl(){
       for(String str:math){
           if(isNum(str)){
               ys_stack.push(str);
           }else{
               ys_stack.push(String.valueOf(jsff(ys_stack.pop(), ys_stack.pop(), str)));
           }
       }
       return Integer.parseInt(ys_stack.pop());  //��� ջ��Ԫ�� ��Ϊ���
   }
    public static void main(String[] args) {
    	Scanner scanner=new Scanner(System.in);
    	System.out.println("���������ı��ʽ��");
    	String bp=scanner.next();
		NBL nbl=new NBL(bp);
		nbl.print(nbl.sourc);
		nbl.zbZh();
		nbl.print(nbl.math);
		System.out.println();
		System.out.println(nbl.js_nbl());
	}
    
}
