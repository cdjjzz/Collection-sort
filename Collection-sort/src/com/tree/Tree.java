package com.tree;

/**
 * 定义树形接口，共有方法
 * @author pet-lsf
 * 深度 根节点到当前节点
 * 高度 当前节点到最大叶子节点 所有树叶高度为0
 */
public interface Tree<T> {
	/**
	 * 根据数据值找节点(二叉树，从上到下，从左到右)找到第一个匹配节点
	 * @param data
	 * @return
	 */
	public Node<T> find(T data);
	/**
	 * 将数据值插入节点中
	 * @param data
	 * @return
	 */
	public boolean insert(T data);
	/**
	 * 将节点插入到树中
	 * @param node
	 * @return
	 */
	public boolean insert(Node<T> node);
	/**
	 * 删除指定数据值，第一个匹配的数据值
	 * @param data
	 * @return
	 */
	public boolean delete(T data);
	/**
	 * 删除指定节点
	 * @param node
	 * @return
	 */
	public boolean delete(Node<T> node);
	/**
	 * 中序排序
	 * @param current
	 */
	public void midOrder(Node<T> current);
	/**
	 * 先序排序
	 * @param current
	 */
	public void preOrder(Node<T> current);
	/**
	 * 后序排序
	 * @param current
	 */
	public void postOrder(Node<T> current);
	/**
	 * 查找最大值节点，如果最大值存在多个，返回第一个匹配
	 * @return
	 */
	public Node<T> findMax();
	/**
	 * 查找最小值节点，如果最小值存在多个，返回第一个匹配
	 * @return
	 */
	public Node<T> findMin();
	
}
