package com.tree;

/**
 * �������νӿڣ����з���
 * @author pet-lsf
 * ��� ���ڵ㵽��ǰ�ڵ�
 * �߶� ��ǰ�ڵ㵽���Ҷ�ӽڵ� ������Ҷ�߶�Ϊ0
 */
public interface Tree<T> {
	/**
	 * ��������ֵ�ҽڵ�(�����������ϵ��£�������)�ҵ���һ��ƥ��ڵ�
	 * @param data
	 * @return
	 */
	public Node<T> find(T data);
	/**
	 * ������ֵ����ڵ���
	 * @param data
	 * @return
	 */
	public boolean insert(T data);
	/**
	 * ���ڵ���뵽����
	 * @param node
	 * @return
	 */
	public boolean insert(Node<T> node);
	/**
	 * ɾ��ָ������ֵ����һ��ƥ�������ֵ
	 * @param data
	 * @return
	 */
	public boolean delete(T data);
	/**
	 * ɾ��ָ���ڵ�
	 * @param node
	 * @return
	 */
	public boolean delete(Node<T> node);
	/**
	 * ��������
	 * @param current
	 */
	public void midOrder(Node<T> current);
	/**
	 * ��������
	 * @param current
	 */
	public void preOrder(Node<T> current);
	/**
	 * ��������
	 * @param current
	 */
	public void postOrder(Node<T> current);
	/**
	 * �������ֵ�ڵ㣬������ֵ���ڶ�������ص�һ��ƥ��
	 * @return
	 */
	public Node<T> findMax();
	/**
	 * ������Сֵ�ڵ㣬�����Сֵ���ڶ�������ص�һ��ƥ��
	 * @return
	 */
	public Node<T> findMin();
	
}
