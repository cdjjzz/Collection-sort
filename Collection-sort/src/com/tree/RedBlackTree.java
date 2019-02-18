package com.tree;

import com.tree.BinaryTree;
/**
 * �������
 * @author pet-lsf
 *  O(2logN)
 *  O(n)
 * @param <T>
 */
public   class RedBlackTree<T extends Comparable<? super T>> extends BinaryTree<T>{
	/**
	 * �������
	 * 1 ÿ���ڵ㲻�Ǻ�ɫ���Ǻ�ɫ
	 * 2 ���ڵ�(root) ���Ǻ�ɫ
	 * 3 ������ڵ��Ǻ�ɫ����˫�ӽڵ����Ϊ��ɫ����·���в����������ĺ�ڵ㣩
	 * 4 �Ӹ��ڵ�(root)��Ҷ�ӽڵ��·����ӵ����ͬ��Ŀ�ĺڽڵ�
	 * �Ӹ��ڵ㵽Ҷ�ڵ��·���ϵĺ�ɫ�ڵ����Ŀ��Ϊ��ɫ�߶ȣ����� 4 ��һ�ֱ�ʾ���ǴӸ���Ҷ�ڵ�·���ϵĺ�ɫ�߶ȱ�����ͬ��
	 * �²���Ľڵ���ɫ���Ǻ�ɫ�ģ�������Ϊ����һ����ɫ�ڵ�Ȳ���һ����ɫ�ڵ�Υ����-�ڹ���Ŀ����Ը�С��
	 * ԭ���ǲ����ɫ�ڵ��ܻ�ı��ɫ�߶ȣ�Υ������4�������ǲ����ɫ�ڵ�ֻ��һ��Ļ����Υ������3
	 * ����Ϊ���ڵ��Ǻ�ɫ��û�£����ڵ��Ǻ�ɫ�ľ�Υ������3��������Υ������3��Υ������4Ҫ������������������һ���µĽڵ�ʱ�����ܻ��ƻ�����ƽ����
	 * 
	 * ���������
	 * ����ԭ������½ڵ�Ϊ��ɫ���п��ܻ�Υ������3���������ĺ�ڵ㣬����Ҫͨ����ת���ı�ڵ���ɫ��ƽ��
	 * 1 �ı���ɫ,ʹ���¼���ڵ�ĸ��ڵ㼰�ֵܽڵ㶼�Ǻ�ɫ�ڵ�
	 * ��ԭͼ��
	 * 		      10��ɫ
	 * 
	 *      7��ɫ             11��ɫ
	 *            10��ɫ
	 *     7��ɫ                   11��ɫ
	 *   5��ɫ
	 * 
	 * 2 ���� ������Ҫ˵�����ǽڵ㱾���ǲ�����ת�ģ���ת�ı���ǽڵ�֮��Ĺ�ϵ��ѡ��һ���ڵ���Ϊ��ת�Ķ��ˣ�
	 * 	�����һ��������������˽ڵ�����º������ƶ��������ӽڵ��λ�ã��������ӽڵ�����Ƶ���ԭ����λ�á������Ķ��˽ڵ����Ҫ�����ӽڵ㡣
	 * 
	 * 3 ����  �����Ķ��˽ڵ����������ӽڵ�
	 * 
	 * ���Ǹı���ɫҲ��Ϊ�˰��������жϺ�ʱִ��ʲô��ת��
	 * ����ת��Ϊ�˱�֤����ƽ�⡣��ı�ڵ���ɫ�ǲ������κ����õģ�
	 * ��ת���ǹؼ��Ĳ������������ڵ����ɾ���ڵ�֮�󣬿��ܻ��ƻ���������ƽ�⡣
	 */
	@Override
	public boolean insert(T data) {
	  Node<T> node=new Node<T>(true, data);
      boolean result=super.insert(node);
      if(result){
    	  fixUp(node);
      }
      return result;
	}
	/**
	 * ɾ���󣬲�ƽ�⣬��Ҫ�޸�ƽ����
	 */
	@Override
	public boolean delete(T data) {
		Node<T> node=find(data);
		boolean result=super.delete(node);
		if(result){
			fixUp(node);
		}
		return result;
	}
	/**
	 * �����ڵ���������ƽ�⣬��Ҫ�޸���ƽ����
	 */
	/**
	 * 
	 * 
	 * 
	 * @param node
	 */
	private void fixUp(Node<T> node){
		//����ڵ� �� ���ڵ������ڵ㶼�Ǻ�ɫ����Ҫ��ɫ����ת  
		 //����ǰ�ĸ��ڵ������ڵ�Ϳ�ڣ��游�ڵ�Ϳ��
		Node<T> gparent;//�游�ڵ�
		Node<T> parent;//���ڵ�
		Node<T> uncle;
		//���ڵ��Ǻ�ɫ
		while((parent=node.getParentNode())!=null&&parent.isRed()){
			   gparent=parent.getParentNode();
			   if(gparent==null){
				   break;
			   }
			   //���ڵ����游���ӽڵ㣬������ڵ�������ӽڵ�
			   if(parent==gparent.getLeftChild()){
				   uncle=gparent.getRightChild();
				    //����ڵ�Ҳ�Ǻ�ɫ
				   if(uncle!=null&&uncle.isRed()){
					   parent.setRed(false);
					   uncle.setRed(false);
					   gparent.setRed(true);
					   node=gparent;
					   continue;
				   }
				   //����ڵ��Ǻ�ɫ����ǰ�ڵ��Ǹ��ڵ�����ӽڵ�  //��Ҫ���ڵ�����
				   if(node==parent.getRightChild()){
					   leftRate(parent);
					   Node<T> tmp=node;
					   //�ı�ָ��
					   node=parent;
					   parent=tmp;
				   }
				   if(node==parent.getLeftChild()){
					   parent.setRed(false);
					   gparent.setRed(true);
					   rightRate(gparent);
				   }
			   }else{//���ڵ����游���ӽڵ㡣������ڵ�������ӽڵ�
				    uncle=gparent.getLeftChild();
				    //����ڵ�Ҳ�Ǻ�ɫ�� 
				    if(uncle!=null&&uncle.isRed()){
				    	parent.setRed(false);
						uncle.setRed(false);
						gparent.setRed(true);
						node=gparent;
						continue;
				    }
				    //����ڵ��Ǻ�ɫ���ҵ�ǰ�ڵ��Ǹ��ڵ�����ӽڵ㣬��Ҫ����
				    if(node==parent.getLeftChild()){
				    	rightRate(parent);
				    	 Node<T> tmp=node;
						   //�ı�ָ��
						 node=parent;
						 parent=tmp;
				    }
				    if(node==parent.getRightChild()){
				    	parent.setRed(false);
				    	gparent.setRed(true);
				    	leftRate(gparent);
				    }
			   }
		}
		root.setRed(false);
		//����ڵ� �� ���ڵ��Ǻ�ɫ�ģ������Ǻ�ɫ�ģ��Ҳ���ڵ��Ǹ��ڵ�����ӽڵ�
		//����ڵ� �� ���ڵ��Ǻ�ɫ�ģ������Ǻ�ɫ�ģ��Ҳ���ڵ��Ǹ��ڵ�����ӽڵ㡣
	}
	/**
	 * ����
	 * 1 ��y�����ӽڵ㸳��x�����ӽڵ㣬����x����y�����ӽڵ�ĸ��ӽڵ�
	 * 2 ��x�ĸ��ڵ㸳��y�ĸ��ڵ㣬ͬʱ���ø��ڵ���ӽڵ�Ϊy(���ڸ��ӽڵ�)
	 * 3 ��x�ĸ��ӽڵ�����Ϊy��y�����ӽڵ�����Ϊx
	 * @param node
	 */
	private void leftRate(Node<T> x){
		//��һ�� ����x�����ӽڵ�Ϊy�����ӽڵ㣬������y�����ӽڵ�ĸ��ڵ�Ϊx
		Node<T> y=x.getRightChild();
		x.setRightChild(y.getLeftChild());
		if(y.getLeftChild()!=null){
			y.getLeftChild().setParentNode(x);
		}
		//�ڶ��� ��x�ĸ��ڵ㸳��y
		y.setParentNode(x.getParentNode());
		if(x.getParentNode()==null){
			this.root=y;
		}else{
			//���ԭx�Ǹ��ڵ�����ӽڵ㣬��ôy����Ϊ����
			if(x==x.getParentNode().getLeftChild()){
				x.getParentNode().setLeftChild(y);
			}else{
				x.getParentNode().setRightChild(y);
			}
		}
		//������ ����x�ĸ��ڵ�Ϊy,y�����ӽڵ�����Ϊx
		x.setParentNode(y);
		y.setLeftChild(x);
	}
	/**
	 * ����
	 * 1 ��x�����ӽڵ㸳��y�����ӽڵ㣬����x�����ӽڵ�ĸ��ڵ�Ϊy
	 * 2 ��y�ĸ��ڵ㸳��x�ĸ��ڵ�
	 * @param y
	 */
	private void rightRate(Node<T> y){
		// ��x�����ӽڵ㸳��y�����ӽڵ㣬����x�����ӽڵ�ĸ��ڵ�Ϊy
		Node<T> x=y.getLeftChild();
		y.setLeftChild(x.getRightChild());
		if(x.getRightChild()!=null){
			x.getRightChild().setParentNode(y);
		}
		
		// ��y�ĸ��ڵ㸳��x
		x.setParentNode(y.getParentNode());
		if(y.getParentNode()==null){
			this.root=x;
		}else{
			if(y==y.getParentNode().getLeftChild()){
				y.getParentNode().setLeftChild(x);
			}else{
				y.getParentNode().setRightChild(x);
			}
		}
		y.setParentNode(x);
		x.setRightChild(y);
	}
	public static void main(String[] args) {
		RedBlackTree<Integer> blackTree=new RedBlackTree<Integer>();
		blackTree.insert(13);
		blackTree.insert(8);
		blackTree.insert(11);
		blackTree.insert(15);
		blackTree.insert(25);
		blackTree.insert(27);
		blackTree.insert(1);
		blackTree.insert(6);
		blackTree.insert(22);
		blackTree.insert(17);
		blackTree.insert(7);
		blackTree.preOrder();
		System.out.println();
		blackTree.midOrder();
		System.out.println();
		blackTree.postOrder();
		blackTree.print();
		blackTree.delete(15);
		System.out.println();
		blackTree.print();
	}
}
