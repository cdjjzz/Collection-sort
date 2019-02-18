package com.utils;

import java.util.AbstractList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class DeLink<E> extends AbstractList<E> implements List<E>,Deque<E> {
	private transient Node<E> head;
	private transient Node<E> tail;
	private transient int size;
	private static class Node<E>{
		private E item;
		private Node<E> next;
		private Node<E> prev;
		public Node(Node<E> prev,E item, Node<E> next) {
			this.item = item;
			this.next = next;
			this.prev = prev;
			
		}
		@Override
		public String toString() {
			return "Node [item=" + item + ", next=" + next + "]";
		}
		
	}
	/***************************queue*********************************/
	//没有返回值,不能进行逻辑判断
	@Override
	public void addFirst(E e) {
		linkFirst(e);
	}

	@Override
	public void addLast(E e) {
		linkLast(e);
		
	}
	//有返回值，可以进行逻辑判断
	@Override
	public boolean offerFirst(E e) {
		linkFirst(e);
		return true;
	}
	
	@Override
	public boolean offerLast(E e) {
		linkLast(e);
		return true;
	}
	/**
	 * 移除元素，没有该元素抛出异常
	 */
	@Override
	public E removeFirst() {
		if (head == null)
            throw new NoSuchElementException();
		return removeFist(head);
	}

	@Override
	public E removeLast() {
		if (tail == null)
            throw new NoSuchElementException();
		return removeLast(tail);
	}
	/**
	 * 移除元素，不存在元素返回null
	 */
	@Override
	public E pollFirst() {
		final Node<E> h=head;
		return (h==null)? null:removeFist(h);
	}

	@Override
	public E pollLast() {
		final Node<E> t=tail;
		return (t==null)? null:removeLast(t);
	}
	//返回元素，不存在抛出异常
	@Override
	public E getFirst() {
		final Node<E> h = head;
        if (h == null)
            throw new NoSuchElementException();
        return h.item;
	}
	@Override
	public E getLast() {
		final Node<E> t = tail;
        if (t == null)
            throw new NoSuchElementException();
        return t.item;
	}
	//返回元素，不存在返回null
	@Override
	public E peekFirst() {
		final Node<E> h = head;
        if (h == null)
            return null;
        return h.item;
	}
	@Override
	public E peekLast() {
		final Node<E> t = tail;
        if (t == null)
        	return null;
        return t.item;
	}
	/**
	 * 移除第一次出现的元素（head->tail）
	 */
	@Override
	public boolean removeFirstOccurrence(Object o) {
		return remove(o);
	}

	@Override
	public boolean removeLastOccurrence(Object o) {
		if(o==null){
			for (Node<E> x=tail;x!=null;x=x.prev) {
				if(x.item==null){
					removeNode(x);
					return true;
				}
			}
		}else{
			for (Node<E> x=tail;x!=null;x=x.prev) {
				if(o.equals(x.item)){
					removeNode(x);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean offer(E e) {
		add(e);
		return true;
	}
	/*******************queue end**************************/

	@Override
	public E remove() {
		return removeLast();
	}
	/*******************stack start**********************************/
	@Override
	public E poll() {
		final Node<E> h=head;
		if(h==null)return null;
		return removeFist(head);
	}
	
	@Override
	public E element() {
		return getFirst();
	}
	//得到头元素值
	@Override
	public E peek() {
		final Node<E> h=head;
		if(h==null)return null;
		return h.item;
	}
    //添加元素到头元素
	@Override
	public void push(E e) {
		linkFirst(e);
	}
	//移除头元素 不存在抛出异常
	@Override
	public E pop() {
		if(head==null)
			throw new NoSuchElementException();
		return removeFist(head);
	}
	/**************************stack end*************************************/
	@Override
	public Iterator<E> descendingIterator() {
		return new Iter(0);
	}
	@Override
	public Iterator<E> iterator() {
		return new Iter(0);
	}
	public Iterator<E> iterator(int index){
		checkElementIndex(index);
		return new Iter(index);
	};
	//迭代器
	private class Iter implements ListIterator<E>{
		private int nextIndex;
		private Node<E> lastMod;
		private Node<E> startNode;
		public Iter(int index) {
			startNode=link(index);
			this.nextIndex=index;
		}
		@Override
		public boolean hasNext() {
			return nextIndex<size;
		}

		@Override
		public E next() {
			if(startNode==null)
				throw new NoSuchElementException();
			lastMod=startNode;
			startNode=startNode.next;
			nextIndex++;
			return lastMod.item;
		}

		@Override
		public boolean hasPrevious() {
			return nextIndex>0;
		}

		@Override
		public E previous() {
			if(startNode==null)
				throw new NoSuchElementException();
			// 调用next() 导致startNode 为null,就用全局tail
			lastMod=(startNode==null)? tail:startNode.prev;
			nextIndex--;
			return lastMod.item;
		}

		@Override
		public int nextIndex() {
			return nextIndex;
		}

		@Override
		public int previousIndex() {
			return nextIndex--;
		}

		@Override
		public void remove() {
			if(lastMod==null)
				throw new IllegalStateException();
		    Node<E> lastNext=lastMod.next;
		    removeNode(lastMod);
		    if(startNode==lastMod){
		    	startNode=lastNext;
		    }else
		    	nextIndex--;
		    lastMod=null;
		}

		@Override
		public void set(E e) {
			if(lastMod==null)
				throw new IllegalStateException();
			lastMod.item=e;
		}

		@Override
		public void add(E e) {
            lastMod = null;
            if (startNode == null)
                linkLast(e);
            else{
            	addBefore(startNode, e);
            }
            nextIndex++;
		}
		
	}
	/************************list-start*****************************/
	@Override
	public boolean add(E e) {
		linkLast(e);
		return true;
	}
	@Override
	public E get(int index) {
		checkElementIndex(index);
		return link(index).item;
	}
	@Override
	public void add(int index, E element) {
		checkElementIndex(index);
		Node<E> node=link(index);
		addBefore(node,element);
	}
	/*************************list-end*****************************/

	@Override
	public int size() {
		return size;
	}
	void linkFirst(E e){
		if(e==null)
			throw new NullPointerException();
		final Node<E> f=head;
		Node<E> node=new Node<E>(null,e,f);
		if(f==null){
			head=tail=node;
		}else{
			head=node;
			f.prev=node;
		}
		size++;
	}
	void linkLast(E e){
		if(e==null)
			throw new NullPointerException();
		final Node<E> t=tail;
		Node<E> node=new Node<E>(t,e, null);
		if(t==null){
			head=tail=node;
		}else{
			tail=node;
			t.next=node;
		}
		size++;
	}
	E removeFist(Node<E> node){
		final E element = node.item;
        final Node<E> next = node.next;
        node.item=null;
        node.next=null;//断开
		head=next;
		if(next==null){
			tail=null;
		}else{
			next.prev=null;
		}
		size--;
		return element;
	}
	
	E removeLast(Node<E> node){
		final E element = node.item;
        final Node<E> prev = node.prev;
        node.item=null;
        node.prev=null;//断开
		tail=prev;
		if(prev==null){
			head=null;
		}else{
			prev.next=null;
		}
		size--;
		return element;
	}
	//找到指定下标的节点
	Node<E> link(int index){
		int mid=size>>1;
		if(index>mid){
			Node<E> x=tail;
			for(int i=size-1;i>index;i--)
				x=x.prev;
			return x;
		}else{
			Node<E> x=head;
			for(int i=0;i<index;i++)
				x=x.next;
			return x;
		}
	}
	E removeNode(Node<E> node){
		 final E element = node.item;
	     final Node<E> next = node.next;
	     final Node<E> prev = node.prev;
	     //node 为第一个节点 1  2  3
	     if(prev==null){
	    	 head=next;
	     }else{
	    	 prev.next=next;
	    	 node.prev=null;
	     }
	     //node为最后一个节点
	     if(next==null){
	    	 tail=prev;
	     }else{
	    	 next.prev=prev;
	    	 node.next=null;
	     }
	     node.item = null;
	     size--;
	     return element;
	}
	void addBefore(Node<E> node,E e){
		 final Node<E> next = node.next;
	     final Node<E> prev = node.prev;
	     if(prev==null){
	    	 linkFirst(e);
	    	 return;
	     }
	     if(next==null){
	    	 linkLast(e);
	    	 return;
	     }
	     Node<E> temp=new Node<E>(prev, e,node);
	     prev.next=temp;
	     node.prev=temp;
	     size++;
	}
	boolean removeObject(Object o){
		if(o==null){
			for(Node<E> x=head;x!=null;x=x.next){
				if(x.item==null){
					removeNode(x);
					return true;
				}
			}
		}else{
			for(Node<E> x=head;x!=null;x=x.next){
				if(x.item==o){
					removeNode(x);
					return true;
				}
			}
		}
		return false;
	}
	  private void checkElementIndex(int index){
		  if(index<0){
			  throw new IndexOutOfBoundsException("don't support under bound:0");
		  }
		  if(index>size){
			  throw new IndexOutOfBoundsException("don't support up bound: "+size);
		  }
	  }
	  public  void  print(){
		  for(Node<E> x=head;x!=null;x=x.next)
			  System.out.print(x.item+"\t");
		  System.out.println();
	  	}
	  public static void main(String[] args) {
		DeLink<Integer> deLink=new DeLink<Integer>();
		deLink.add(5);
		deLink.add(8);
		deLink.add(10);
		deLink.add(2);
		deLink.add(2,15);
		deLink.removeObject(8);
		deLink.print();
	 }

}
