package com.utils;
 
 import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;
 
 public class NBLL {
 
     //  ������ջ
     private Stack<String> czf_stack = new Stack<>();        // ��� �������ջ
     private  ArrayList<String> ysbds_list = new ArrayList<>();     //��� ԭʼ���ʽ�� arraylist
     private  ArrayList<String> nblbds_list = new ArrayList<>();      // ���ת����� �沨��ʽ
     private static final int One = 1;      //
     private static final int Two = 3;     //
     private static final int Three = 5;   //�涨���ȼ�   Three ���
     
     //  ����һ������ջ
     private static Stack<String> ys_stack = new Stack<>();
     
     // ��ʼ��                             ʹ��StringTokenizer�ָ� �ַ���
     public NBLL(String bdString) {
         // TODO Auto-generated constructor stub
         StringTokenizer stringTokenizer = new StringTokenizer(bdString, "+-*/()",true);
         while(stringTokenizer.hasMoreTokens()){
             ysbds_list.add(stringTokenizer.nextToken());
             //System.out.println(stringTokenizer.nextToken());
         }
     }
     
     
     // �ж� �Ƿ�������
     public boolean isNum(String str){
         if(str.matches("[0-9]+")){    //����ʹ��������ʽ ��֤�Ƿ�������
             //System.out.println("Y");
             return true;
         }else{
             //System.out.println("N");
             return false;
         }
     }
     
     // �ж� �Ƿ��ǲ�����
     public boolean isCzf(String str){
         if(str.matches("[\\+\\-\\*\\/\\(\\)]")){
             //System.out.println("Y");
             return true;
         }else{
             //System.out.println("N");
             return false;
         }
     }
     
     // ��ȡ ���ȼ�
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
     
     //   ********* �� ��ǰ����Ԫ��Ϊ ������ʱ**********    ������ ���Ĵ��룬 ���ڲ�����ջ���ж�
     public void stack_czf(String czf){
         
         //�жϵ�ǰջ���Ƿ�Ϊ��
         if(czf_stack.isEmpty()){
             czf_stack.push(czf);
             return;
         }
         
         //�ж��Ƿ�Ϊ (
         if("(".equals(czf)){
             czf_stack.push(czf);
             return;
         }
         
         //�ж��Ƿ�Ϊ )
         if(")".equals(czf)){
             String string = "";
             while(!"(".equals(string = czf_stack.pop())){
                 nblbds_list.add(string);
             }
             return;
         }
         
         //��� ��ǰջ��Ԫ����  (  ֱ����ջ
         if("(".equals(czf_stack.peek())){
             czf_stack.push(czf);
             return;
         }
         
         // �ж� �� ջ��Ԫ�ص����ȼ� , > Ϊtrue
         if(isYxj(czf, czf_stack.peek())){
             czf_stack.push(czf);
             return;
         }
         
         if(!isYxj(czf, czf_stack.peek())){
             nblbds_list.add(czf_stack.pop());
             stack_czf(czf);   //������ú��� �����������εĲ���������
         }
         
     }
     
     // ��׺ ��> ��׺
     public void zz_hz(){
         
         // ����ԭʼ���ʽlist
         for(String str:ysbds_list){
             
             //System.out.println("->  " + str);
             
             if(isNum(str)){
                 nblbds_list.add(str);
             }else if(isCzf(str)){
                 //TODO
                 stack_czf(str);
             }else{
                 System.out.println("�Ƿ�");
             }
             
         }
         
         // ������ԭʼ���ʽ��  ��������ջ��Ԫ�� ȫ������� �沨�����ʽlist
 
         while(!czf_stack.isEmpty()){
             //System.out.println("���� " + czf_stack.peek());
             nblbds_list.add(czf_stack.pop());
         }
         
     }
     
     // ������㷽��
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
     public void print(){
    	 for (int i = 0; i < nblbds_list.size(); i++) {
			System.out.print(nblbds_list.get(i)+"\t");
		}
    	 System.out.println();
     }
     
     //  ���� �沨��ʽ
     public int js_nbl(){
         for(String str:nblbds_list){
             if(isNum(str)){
                 ys_stack.push(str);
             }else{
                 ys_stack.push(String.valueOf(jsff(ys_stack.pop(), ys_stack.pop(), str)));
             }
         }
         return Integer.parseInt(ys_stack.pop());  //��� ջ��Ԫ�� ��Ϊ���
     }
     
 //    public void nbls_bc(){
 //        for(String string:nblbds_list){
 //            nbls_cc += string;
 //        }
 //    }
     
     
     public static void main(String[] args) {
//         
//         Scanner keyboard = new Scanner(System.in);
//         System.out.println("������");
//         String input = keyboard.nextLine();
//         NBLL nbl = new NBLL(input);
//         int result = 0;
//         nbl.zz_hz();
//         nbl.print();
//         //nbl.nbls_bc();
//         System.out.println("����ǣ�");
//         result = nbl.js_nbl();
//         System.out.println(result);
         //������ȡ
    	 System.out.println(10%10);
     }
     
     
 }