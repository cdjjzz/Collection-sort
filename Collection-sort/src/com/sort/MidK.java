package com.sort;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 求中值
 * 使用优先级队列求中值
 * @author pet-lsf
 *
 */
public class MidK<E> {
	private PriorityQueue<E> minP;
	private PriorityQueue<E> maxP;
	private E mid;
	
	public  MidK() {
		this.maxP=new PriorityQueue<E>(Collections.reverseOrder());
		this.minP=new PriorityQueue<E>();
	}
	private int compare(E e,E mid){
		Comparable<? super E> comp=(Comparable<? super E>)e;
		return comp.compareTo(mid);
	}
	/**
	 * 添加节点
	 * @param e
	 */
	public void add(E e){
		if(mid==null){
			mid=e;
			return;
		}
		if(compare(e,mid)<=0){
			maxP.add(e);
		}else{
			minP.add(e);
		}
		if((minP.size()-maxP.size())>=2){
			maxP.add(mid);
			this.mid=minP.poll();
		}else if((maxP.size()-minP.size())>=2){
			minP.add(mid);
			this.mid=maxP.poll();
		}
	}
	public void addAll(Collection<? extends E> list){
		for (E e:list) {
			add(e);
		}
	}
	public E getEatM(){
		return mid;
	}
	public static void main(String[] args) {
		MidK<Integer> median = new MidK<>();
		List<Integer> list = Arrays.asList(new Integer[]{
		        34, 90, 67, 45, 1, 4, 5, 6, 7, 9, 10,20,11
		});
		median.addAll(list);
		System.out.println(median.getEatM());
	}
}
