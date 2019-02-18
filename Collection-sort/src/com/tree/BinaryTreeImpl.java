package com.tree;

public class BinaryTreeImpl<T extends Comparable<? super T>> extends BinaryTree<T> {

	@Override
	public Node<T> find(T data) {
		return super.find(data);
	}
	@Override
	public boolean insert(T data) {
		return super.insert(data);
	}
	@Override
	public boolean insert(Node<T> node) {
		return super.insert(node);
	}
	
	@Override
	public boolean delete(T data) {
		return super.delete(data);
	}

	@Override
	public void midOrder(Node<T> current) {
		super.midOrder(current);
	}

	@Override
	public void preOrder(Node<T> current) {
		super.preOrder(current);
	}

	@Override
	public void postOrder(Node<T> current) {
		super.postOrder(current);
	}

	@Override
	public Node<T> findMax() {
		return super.findMax();
	}

	@Override
	public Node<T> findMin() {
		return super.findMin();
	}
	@Override
	public void print() {
		super.print();
	}
	public static void main(String[] args) {
		BinaryTreeImpl<Integer> binaryTreeImpl=new BinaryTreeImpl<Integer>();
		binaryTreeImpl.insert(1);
		binaryTreeImpl.insert(-2);
		binaryTreeImpl.insert(5);
		binaryTreeImpl.insert(4);
		binaryTreeImpl.insert(3);
		binaryTreeImpl.insert(2);
		binaryTreeImpl.insert(6);
		binaryTreeImpl.insert(10);
		binaryTreeImpl.insert(7);
		binaryTreeImpl.insert(9);
		binaryTreeImpl.insert(16);
		binaryTreeImpl.midOrder();
		System.out.println();
		binaryTreeImpl.postOrder();
		System.out.println();
		binaryTreeImpl.preOrder();
		binaryTreeImpl.print();
		System.out.println("------------");
		binaryTreeImpl.delete(7);
		System.out.println();
		binaryTreeImpl.postOrder();
		binaryTreeImpl.print();
		/**
		 * ��ӡ������
		 */
		binaryTreeImpl.print();
		//�ڶ������ĵ�i���������2 i-1 ���ڵ� ����i>=1
		//��������������Ϊk(��k��),��ô�����2k-1���ڵ㡣(k>=1��
		//�����������մ��ϵ��´��������α�ţ�����ĳ�ڵ���Ϊk�����������������ڵ��ŷֱ�Ϊ2k��2k+1;
		//�������� ����ȫ������
		//n�����޽ڵ�ͨ�������ӵĵĲ�ι�ϵ
		//��� a�ڵ㵽���ڵ��Ψһ·����
		//�߶� a�ڵ㵽Ҷ�ӽڵ��Ψһ·����
		//serach logN
		//z���� 
		//2K���� rigth(k>0 n����N) 2k+1 left
	}

}
