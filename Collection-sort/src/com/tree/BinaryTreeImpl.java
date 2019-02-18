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
		 * 打印二叉树
		 */
		binaryTreeImpl.print();
		//在二叉树的第i层上最多有2 i-1 个节点 。（i>=1
		//二叉树中如果深度为k(有k层),那么最多有2k-1个节点。(k>=1）
		//若二叉树按照从上到下从左到右依次编号，则若某节点编号为k，则其左右子树根节点编号分别为2k和2k+1;
		//满二叉树 ，完全二叉树
		//n个有限节点通过边连接的的层次关系
		//深度 a节点到根节点的唯一路径长
		//高度 a节点到叶子节点的唯一路径长
		//serach logN
		//z葛麻 
		//2K个球 rigth(k>0 n属于N) 2k+1 left
	}

}
