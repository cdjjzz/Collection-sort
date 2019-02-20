package com.sort;

import java.util.Arrays;
import java.util.Collection;
import java.util.PriorityQueue;

/**
 * 求前k个最大序列值
 * @author pet-lsf
 *
 */
public class MaxK<E> {
	private PriorityQueue<E> queue;
	private int k;
	public void addAll(Collection<? extends E> list){
		for(E e:list){
			add(e);
		}
	}
	public void add(E e){
		if(queue.size()<k){
			queue.add(e);
			return;
		}
	  Comparable<? super E>  head=(Comparable<? super E>)queue.peek();
	  if(head.compareTo(e)>0){
		  return;
	  }
	  queue.poll();
	  queue.add(e);
	}
	public E getEatK(){
		return queue.peek();
	}
	public MaxK(int k) {
		this.queue=new PriorityQueue<E>();
		this.k=k;
	}
	public <T> T[] toArray(T[] a){
	        return queue.toArray(a);
	}
	public static void main(String[] args) {
		MaxK<Integer> top5 = new MaxK<>(5);
		top5.addAll(Arrays.asList(new Integer[]{
		        100, 1, 2, 5, 6, 7, 34, 9, 3, 4, 5, 8, 23, 21, 90, 1, 0
		}));
		System.out.println(Arrays.toString(top5.toArray(new Integer[0])));
		System.out.println(top5.getEatK());
	}
}
