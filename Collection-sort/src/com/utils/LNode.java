package com.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;




public class LNode<T> {
	private Node<T> head;
	private Node<T> last;
	private transient int size;
	private static class Node<T>{
		T t;
		Node<T> next;
		public Node(T t) {
			this.t=t;
		}
	}
	public void addFirst(T t){
		Node<T> node=new Node<T>(t);
		if(head==null)
			head=last=node;
		else{
			last.next=node;
			node.next=head;
			head=node;
		}
		size++;
	}
	public void addLast(T t){
		Node<T> node=new Node<T>(t);
		if(head==null)
			head=last=node;
		else{
			last.next=node;
			node.next=head;
			last=node;
		}
		size++;
	}
	public int length(){
		return size;
	}
	//寻找中位数
	public T findMid(boolean cli){
		Node<T> slow=head;
		Node<T> fast=head;
		while(fast!=null){
			if(fast.next==null||fast.next==head){
				break;
			}
			if(fast.next.next==null||fast.next.next==head){
				if(cli){
					break;
				}else{
					slow=slow.next;
					break;
				}
			}
			fast=fast.next.next;
			slow=slow.next;
		}
		return slow.t;
	}
	/**
	 * 判断链表是否是循环链表
	 * @return
	 */
	public boolean isRing(){
		Node<T> slow=head;
		Node<T> fast=head;
		while(fast!=null&&fast.next!=null){
			slow=slow.next;
			fast=fast.next.next;
			if(slow==fast){
				return true;
			}
		}
		return false;
	}
	public T find(int index){
		if(index<0)throw new IndexOutOfBoundsException();
		if(index>=size)throw new IndexOutOfBoundsException();
		if(index==0)return head.t;
		Node<T> node=head;
		while(index-->0){
			node=node.next;
		}
		return node.t;
	}
	public boolean conteknkey(T t){
		Node<T> node=head;
		while(node!=null){
			if(node.t==t)return true;
			node=node.next;
			if(node==head)break;
		}
		return false;
	}
	public Node<T> create(int n){
		for (int i = 1; i <=n; i++) {
			@SuppressWarnings("unchecked")
			Node<T> node=new Node<T>(((T)new Integer(i)));
			if(head==null)
				head=last=node;
			else{
				last.next=node;
				node.next=head;
				last=node;
			}
			size++;
		}
		return head;
	}
	public boolean finde(T t,int offset){
		Node<T> node=head;
		first:while(node!=null){
			if(node.t==t){
				while(offset-->0){
					node=node.next;
					if(node==head)break first;
				}
				return true;
			};
			node=node.next;
			if(node==head)break;
		}
		return false;
	}
	public void delete(int index){
		Node<T> node=head;
		Node<T> prev=null;
		while(index-->0){
			if(index==1){
				prev=node;
			}else{
				node=node.next;
			}
		}
		prev.next=node.next;
	}
	public void printf(){
		Node<T> node=head;
		while(node!=null){
			System.out.print(node.t+"\t");
			node=node.next;
			if(node==head)break;
		}
		System.out.println();
	}
	public static void main(String[] args) {
		LinkedList<String>  parames=new LinkedList<String>();
		parames.add("罗盛丰");
		parames.add("罗盛丰1");
		parames.add("罗盛丰2");
		parames.add("罗盛丰3");
		Iterator<String> iterator= parames.listIterator(2);
		while(iterator.hasNext()){
			iterator.next();
			iterator.remove();
		}
		System.out.println(parames.size());
		for (int i = 0; i < parames.size(); i++) {
			System.out.println(parames.get(i));
		}
		//LNode<Integer> node=new LNode<Integer>();
//		node.addFirst(1);
//		node.addFirst(2);
//		node.addFirst(6);
//		node.addFirst(0);
//		node.addFirst(19);
////		node.addLast(8);
////		node.addLast(19);
//		node.printf();
//		System.out.println(node.findMid(true));
//		System.out.println(node.isRing());
//		System.out.println(node.find(1));
//		System.out.println(node.conteknkey(19));
//		System.out.println(node.finde(1, 1));
		
//		LNode<Integer> yu=new LNode<Integer>();
//		int m=7;
//		int n=3;
//		Node<Integer> head=node.create(m);
//		Node<Integer> temp=head;
//		node.printf();
//		//node.delete(3);
//		while(yu.length()!=m){
//			if(n>1){
//				for(int i=0;i<(n-2)%m;i++){
//					head=head.next;
//				}	
//				yu.addLast(head.next.t);
//				temp=head.next;
//				head.next=temp.next;
//				temp=null;
//				head=head.next;
//			}else{
//				yu.addLast(head.t);
//				head=head.next;
//			}
//			
//		}
		//node.printf();
//		yu.printf();
//		int result=node.josephus(m, n);
//		System.out.println(result+1);
//		System.out.println(node.forJosephus(m, n));
		//node.createMagic();
		//node.createNum(1000);
		//node.printf();
		//node.checkBlance("[[{'1'}]]");
		//node.move(3, "x", "y", "z");
	}
	/**
	 * 约瑟夫环 递归实现
	 * @param n 总共大小 n
	 * @param m 多少人出队 m
	 * @return
	 */
	int josephus(int n, int m) {
		if(n == 1) {
			return 0;
		}
		else {
			int result=(josephus(n-1, m) + m) % n;
			System.out.print(result+1+"\t");
			return result;
		}
	}	
	
	/**
	 * 约瑟夫环 循环实现
	 * @param n
	 * @param m
	 * @return
	 */
	int forJosephus(int n,int m){
		int result=0;
		for(int i=2;i<=n;i++){
			result=(result+m)%i;
			System.out.print(result+1+"\t");
			
			
		}
		result++;
		return result;
	}
	/**
	 * 魔术师发牌问题
	 */
	public void createMagic(){
		Node<T> p=new Node<T>((((T)new Integer(1))));
		head=p;
		int index=1;
        while(p.next == null && index< 13){
        	Node<T> newNode=new Node<T>((((T)new Integer(0))));
            p.next = newNode;
            p = p.next;
            index++;
        }
        p.next = head; //构建循环链表
        
        Node<T> temp=head;
        for (int i = 2; i <= 13; i++) {
        	for (int j = 0; j <i; j++) {
        		temp=temp.next;
				if(temp.t instanceof Integer&&(Integer)temp.t!=0){
					j--;
				}
			}
        	temp.t=(T) new Integer(i);
		}
	}
	/**
	 * 拉丁矩阵问题
	 * @param n
	 */
	public void createNum(int n){
		Node<T> p=new Node<T>((((T)new Integer(1))));
		head=p;
		int index=2;
        while(p.next == null && index<=n){
        	Node<T> newNode=new Node<T>((((T)new Integer(index))));
            p.next = newNode;
            p = p.next;
            index++;
        }
        p.next = head; //构建循环链表
        
        //输出拉丁矩阵----------------》
        Node<T> node=head;
        while(n!=0){
        	Node<T> temp=node;
        	while(true){
        		System.out.print(node.t+"\t");
        		node=node.next;
        		if(temp==node)break;
        	}
        	System.out.println();
        	node=node.next;
            n--;
        }
	}
	
	public  void move(int leve,String x,String y,String z){
		System.out.println(leve+"---"+x+"---"+y+"----"+z);
		if(leve==1)
			System.out.println(leve+"---"+x+" move "+z+"---");
		else{
			move(leve-1, x, z, y);
			System.out.println(leve+"---"+x+" move "+z);
			move(leve-1, y, x, z);
		}
	}
	
	public  void checkBlance(String parrtten){
	  char c[]=parrtten.toCharArray();
	  Stack<Character> characters=new Stack<Character>();
	  char push[]=new char[]{'{','(','[','\'','"'};
	  char pop[]=new char[]{'}',')',']','\'','"'};
	  first:for (int i = 0; i < c.length; i++) {
		  for (int j = 0; j < push.length; j++) {
			if(c[i]==push[j]){
				if(!characters.isEmpty()){
					Character cc=characters.peek();
					if(cc=='\''||cc=='"') break;
				}
				characters.push(c[i]);
				continue first;
			}
		 }
		  for (int j = 0; j < pop.length; j++) {
			if(c[i]==pop[j]){
				if(c[i]=='}'&&characters.peek()!='{')break first;
				if(c[i]==']'&&characters.peek()!='[')break first;
				if(c[i]==')'&&characters.peek()!='(')break first;
				if(c[i]=='\''&&characters.peek()!='\'')break first;
				if(c[i]=='"'&&characters.peek()!='"')break first;
				characters.pop();
				continue first;
			}
		  }
	  }
	  int  size=characters.size();
	  for (int i = 0; i <size; i++) {
		  System.out.println(characters.pop());
	  }
	}
}
