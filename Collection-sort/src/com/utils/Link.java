package com.utils;

import java.util.AbstractList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class Link<E>  extends AbstractList<E> implements List<E> {
	private transient Node<E> head;
	private transient int size;
	private static class Node<E> implements Cloneable{
		private E item;
		private Node<E> next;
		public Node(E item, Node<E> next) {
			this.item = item;
			this.next = next;
		}
		@Override
		protected Object clone() throws CloneNotSupportedException {
			return super.clone();
		}
		@Override
		public String toString() {
			return "Node [item=" + item + ", next=" + next + "]";
		}
	}
	@Override
	public ListIterator<E> listIterator(int index) {
		return new LinkIter(index);
	}
	@Override
	public int size() {
		return size;
	}
	@Override
	public boolean isEmpty() {
		return size==0;
	}
	@Override
	public boolean contains(Object o) {
		return indexOf(o)!=-1;
	}
	@Override
	public Iterator<E> iterator() {
		return new LinkIter(0);
	}
	
	
	private class LinkIter implements ListIterator<E>{
		//最近影响的节点
		private Node<E> lastMod=null;
		private Node<E> next;
		private int nextIndex;//下一个迭代位置
		//去掉 fast-fail机制
		//private int expectedModCount=modCount;
		public LinkIter(int index) {
			//找出next节点
			next = (index == size) ? null : linkNode(index);
            nextIndex = index;
		}
		@Override
		public boolean hasNext() {
			return nextIndex<size;
		}
		@Override
		public E next() {
			if(next==null){
				return null;
			}
			if (!hasNext())
                throw new NoSuchElementException();
			E oldE=next.item;
			lastMod=next;
			next=next.next;
			nextIndex++;
			return oldE;
		}
		@Override
		public void remove() { 
			 //没有迭代，直接删除 抛出异常
			 if (lastMod == null)
	                throw new IllegalStateException();
			    Node<E> prev=null;
			    Node<E> currt=null;
				for(Node<E> x=head;x!=null;x=x.next){
					if(x.item.equals(lastMod.item)){
						currt=x;
					}
					//这是单链表
					if(currt!=null){
						if(prev!=null){
							prev.next=next=lastMod.next;
							break;
						}else{
							head=prev=next=lastMod.next;
							break;
						}
					}
					prev=x;
				}
			  System.out.println(head);
			  lastMod= null;
		}
		@Override
		public boolean hasPrevious() {
			return false;
		}
		@Override
		public E previous() {
			return null;
		}
		@Override
		public int nextIndex() {
			return 0;
		}
		@Override
		public int previousIndex() {
			return 0;
		}
		@Override
		public void set(E e) {
		}
		@Override
		public void add(E e) {
			
		}
	}
	@Override
	public Object[] toArray() {
		Object[] result = new Object[size];
        int i = 0;
        for (Node<E> x = head; x != null; x = x.next)
            result[i++] = x.item;
        return result;
	}
	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] a) {
		 if (a.length < size)
	            a = (T[])java.lang.reflect.Array.newInstance(
	                                a.getClass().getComponentType(), size);
	        int i = 0;
	        Object[] result = a;
	        for (Node<E> x = head; x != null; x = x.next)
	            result[i++] = x.item;

	        if (a.length > size)
	            a[size] = null;

	        return a;
	}
	@Override
	public boolean add(E e) {
		linkLast(e);
		return true;
	}
	@Override
	public boolean remove(Object o) {
		return false;
	}
	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean retainAll(Collection<?> c) {
		return false;
	}
	@Override
	public void clear() {
		for(Node<E> x=head;x!=null;){
		    Node<E> next = x.next;
			x.item=null;
			x.next=null;
			x=next;
		}
		size=0;
	}
	@Override
	public E get(int index) {
		Node<E> node=linkNode(index);
		if(node==null)
			return null;
		return node.item;
	}
	@Override
	public E set(int index, E element) {
		Node<E> node=linkNode(index);
		if(node==null)
			return null;
		E oldE=node.item;
		node.item=element;
		return oldE;
	}
	@Override
	public void add(int index, E element) {
		linkIndex(index, element);
	}
	public E remove(){
		//找到当前删除的前元素
		Node<E> node=linkNode(size-2);
		E oldE=null;
		if(node==null&&head.next==null){
			oldE=head.item;
			head=null;
		}
		if(node!=null){
			oldE=node.next.item;
			node.next=null;
		}
		size--;
		return oldE;
	}
	@Override
	public E remove(int index) {
		Node<E> node=linkNode(index-1);
		E oldE=null;
		if(node==null&&head.next==null){
			oldE=head.item;
			head=null;
		}
		if(node!=null){
			oldE=node.next.item;
			node.next=null;
		}
		return oldE;
	}
	@Override
	public int indexOf(Object o) {
		int index=0;
		if(o==null){
			for(Node<E> x=head;x!=null;x=x.next){
				if(x.item==null)
					return index;
				index++;
			}
		}else{
			for(Node<E> x=head;x!=null;x=x.next){
				if(o.equals(x.item))
					return index;
				index++;
			}
		}
		return -1;
	}
	@SuppressWarnings("unchecked")
	@Override
	public int lastIndexOf(Object o) {
		//先翻转链表 在找第一个
		Node<E> node_temp=null;
		try {
			node_temp=(Node<E>)head.clone();
		} catch (CloneNotSupportedException e) {
			return -1;
		}
		Node<E> node=dg_reversal(node_temp);
		int index=0;
		if(o==null){
			for(Node<E> x=node;x!=null;x=x.next){
				if(x.item==null)
					return index;
				index++;
			}
		}else{
			for(Node<E> x=node;x!=null;x=x.next){
				if(o.equals(x.item))
					return index;
				index++;
			}
		}
		return -1;
	}
	//翻转链表
	public void reversal(){
		if(head==null||head.next==null)
			return;
		Node<E> node=head;
		Node<E> reversal_Node=null;
		Node<E> temp=null;
		while(node!=null){
			temp=node.next;
			node.next=reversal_Node;
			reversal_Node=node;
			node=temp;
		}
		head=reversal_Node;
	}
	//递归翻转链表
	public Node<E> dg_reversal(Node<E> h){
		if(h==null||h.next==null){
			return h;
		}
		Node<E> node=dg_reversal(h.next);
		h.next.next=h;
		h.next=null;
		return node;
	}
	@Override
	public ListIterator<E> listIterator() {
		return new LinkIter(0);
	}
	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		if(fromIndex<0||toIndex>size||fromIndex>toIndex){
			throw new IndexOutOfBoundsException("数组越界");
		}
		Link<E> link=new Link<E>();
		for(Node<E> x=head;x!=null;x=x.next){
			if(fromIndex<=0&&toIndex>=0){
				link.add(x.item);
				if(toIndex==0)break;
			}
			fromIndex--;
			toIndex--;
		}
		
		return link;
	}
	void linkLast(E e){
		Node<E> node=head;
		final Node<E> add_node=new Node<E>(e,null);
		if(head==null){
			head=add_node;
		}else{
			while(node.next!=null){
				node=node.next;
			}
			node.next=add_node;
		}
		size++;
	}
	void linkIndex(int index,E e){
		if(index<0||index>size){
			throw new IndexOutOfBoundsException("数组越界");
		}
		if(index==size){
			linkLast(e);
		}else{
			Node<E> prev=null;
			for(Node<E> x=head;x!=null;x=x.next){
				if(index==1){
					prev=x;
				}
				if(index==0){
					Node<E> n=new Node<E>(e,null);
					n.next=x;
					if(prev!=null){
						prev.next=n;
					}
					break;
				}
				index--;
			}
			size++;
		}
	}
	Node<E> linkNode(int index){
		if(index<0||index>size){
			return null;
		}
		if(head==null){
			return null;
		}
		Node<E> node=null;
		for(Node<E> x=head;x!=null;x=x.next){
			if(index==0){
				node=x;
				break;
			}
			index--;
		}
		return node;
	}
	public void print(Node<E> node){
		if(node==null){
			node=head;
		}
		for(Node<E> x=node;x!=null;x=x.next){
			System.out.print(x.item+"\t");
		}
		System.out.println();
	}
	public static void main(String[] args) {
		Link<Integer> link=new Link<Integer>();
		link.add(1);
		link.add(10);
		link.add(15);
		link.add(20);
		link.print(null);
		link.add(3, 90);
		link.print(null);
	}
}
