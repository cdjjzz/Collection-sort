package com.tree;


/**
 * 二叉树节点
 * @author pet-lsf
 *
 */
public class Node<T> {
	private T data;
	private Node<T> leftChild;
	private Node<T> rightChild;
	private Node<T> parentNode;
	private boolean isRed;
	
	
	//空参构造
	public Node() {
		
	}
	public Node(T data){
		this.data=data;
	}
	public Node(boolean isRed,T data){
		this.isRed=isRed;
		this.data=data;
	}
	public Node(T data,Node<T> leftChild,Node<T> rightChild){
		this.data=data;
		this.leftChild=leftChild;
		this.rightChild=rightChild;
	}
	public Node(T data,Node<T> leftChild,Node<T> rightChild,Node<T> parentNode,boolean isRed){
		this.data=data;
		this.leftChild=leftChild;
		this.rightChild=rightChild;
		this.parentNode=parentNode;
		this.isRed=isRed;
	}
	
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public Node<T> getLeftChild() {
		return leftChild;
	}
	public void setLeftChild(Node<T> leftChild) {
		this.leftChild = leftChild;
	}
	public Node<T> getRightChild() {
		return rightChild;
	}
	public void setRightChild(Node<T> rightChild) {
		this.rightChild = rightChild;
	}
	
	public Node<T> getParentNode() {
		return parentNode;
	}
	public void setParentNode(Node<T> parentNode) {
		this.parentNode = parentNode;
	}
	public void display(){
		System.out.println(data);
	}
	public boolean isRed() {
		return isRed;
	}
	public void setRed(boolean isRed) {
		this.isRed = isRed;
	}
	
	
	
}
