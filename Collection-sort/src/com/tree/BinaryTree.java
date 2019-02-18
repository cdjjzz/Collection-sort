package com.tree;


/**
 * 
 * @author pet-lsf
 * ������ÿһ�����2N-1���ڵ�
 * @param <T>
 */
public  abstract class BinaryTree<T extends Comparable<? super T>> implements Tree<T>{
	//���ڵ�
	public  Node<T> root;
	/**
	 * �������ݲ��ҽڵ�
	 */
	@Override
	public Node<T> find(T data) {
		Node<T> current=root;
		while(current!=null){
			//��ǰ�ڵ��ֵ����Ѱ��ֵ��
			if(current.getData().compareTo(data)==1){
				current=current.getLeftChild();
			}else if(current.getData().compareTo(data)==-1){
				current=current.getRightChild();
			}else if(current.getData().compareTo(data)==0){
				return current;
			}
		}
		return null;
	}
	/**
	 * ��ӽڵ�
	 */
	@Override
	public boolean insert(T data) {
		Node<T> node=new Node<T>(data);
		return insert(node);
	}
	@Override
	public boolean insert(Node<T> node) {
		if(root==null){
			root=node;
			return true;
		}else{
			Node<T> current=root;
			while(current!=null){
				if(current.getData().compareTo(node.getData())>0){
					//��ȡ���ӽڵ�
					if(current.getLeftChild()==null){
						current.setLeftChild(node);
						node.setParentNode(current);
						return true;
					}
					current=current.getLeftChild();
				}else{
					if(current.getRightChild()==null){
						current.setRightChild(node);
						node.setParentNode(current);
						return true;
					}
					current=current.getRightChild();
				}
			}
		}
		return false;
	}
	/**
	 * ɾ��ָ���ڵ�
	 * 1 ɾ��Ҷ�ӽڵ� ���
	 * 2 �ýڵ�ӵ�������ӽڵ�
	 * 3 �ýڵ�ӵ��һ���ӽڵ�
	 */
	@Override
	public boolean delete(T data) {
		Node<T> node=find(data);
		if(node!=null){
			return delete(node);
		}else{
			return false;
		}
	}
	@Override
	public boolean delete(Node<T> now) {
		Node<T> current=root;
		//�ҳ���Ҫɾ���Ľڵ�
		boolean  isLeftChild=false;
		while(current!=null){
			//��ǰ�ڵ����ɾ��ֵ�������������
			if(current.getData().compareTo(now.getData())==1){
				isLeftChild=true;
				current=current.getLeftChild();
			}else if(current.getData().compareTo(now.getData())==-1){
				isLeftChild=false;
				//��ǰ�ڵ�С��ɾ��ֵ���������ұ���
				current=current.getRightChild();
			}else{
				//��ֵ��� ����ѭ��
				break;
			}
		}
		if(current==null){
			return false;
		}
		//��һ���������ǰ�ڵ���Ҷ�ӽڵ�,���������ӽڵ�
		if(current.getLeftChild()==null&&current.getRightChild()==null){
			//ֱ��ɾ���ýڵ�
			if(current==root){
				root=null;
			}else if(isLeftChild){
				//�ȴ�gc����
				current.getParentNode().setLeftChild(null);
			}else{
				current.getParentNode().setRightChild(null);
			}
			current.setParentNode(null);
			return true;
		}else if(current.getLeftChild()==null&&current.getRightChild()!=null){
			//���ӽڵ㲻���ڣ��������ӽڵ�
			if(current==root){
				root=current.getRightChild();
			}else if(isLeftChild){
				current.getParentNode().setLeftChild(current.getRightChild());
			}else{
				current.getParentNode().setRightChild(current.getRightChild());
			}
			current.getRightChild().setParentNode(current.getParentNode());
			current.setRightChild(null);
			current.setParentNode(null);
			return true;
		}else if(current.getLeftChild()!=null&&current.getRightChild()==null){
			//���ӽڵ���ڣ����������ӽڵ�
			if(current==root){
				root=current.getLeftChild();
			}else if(isLeftChild){
				current.getParentNode().setLeftChild(current.getLeftChild());
			}else{
				current.getParentNode().setRightChild(current.getLeftChild());
			}
			current.getLeftChild().setParentNode(current.getParentNode());
			//�ȴ�gc
			current.setParentNode(null);
			current.setLeftChild(null);
			return true;
		}else{
			//����˫�ӽڵ� ɾ���ڵ㣬��Ҫ��ת������֤������ƽ��
			Node<T> node=getNextNode(current);
			if(current == root){
				node.getRightChild().setParentNode(node);
                root=node;
            }else if(isLeftChild){
            	//����ǵ�ǰ�ڵ������ӽڵ�,��Ҫ�滻ɾ���ڵ�ĸ��ӽڵ�����ӽڵ�
            		current.getParentNode().setLeftChild(node);
            }else{
            		current.getParentNode().setRightChild(node);
            }
			//�����ӽڵ��滻�ɸ��ӽڵ����Ҫ����ԭ���ӽڵ�Ϊ�����ӽڵ�����ӽڵ�
			node.setLeftChild(current.getLeftChild());
			//���ø��ӹ�ϵ
			node.setParentNode(current.getParentNode());
			current.getLeftChild().setParentNode(node);
			//�ȴ�gc����
			current.setLeftChild(null);
			current.setParentNode(null);
			current.setRightChild(null);
			return true;
		}
	}
	//ɾ�������ڵ㣬�����滻�ڵ�
	/**
	 * ��ת������ƽ��ڵ�ֵ
	 * �ҵ�Ҷ�ӽڵ㣬�������ӽڵ�
	 * @param current
	 * @return
	 */
	private Node<T> getNextNode(Node<T> delNode) {
		Node<T> returnNode=delNode;//�󼯽ڵ�
		Node<T> current=delNode.getRightChild();
		/**
		 * now        delNode  
		 * successor  delNode.right
		 * current   successor.left
		 */
		while(current!=null){
			returnNode=current;
			current=current.getLeftChild();
		}
		//
		if(returnNode!=delNode.getRightChild()){
			//ѡ�����ӽڵ㣬��Ҫ�����ֵܽڵ��������ǰ�󼯽ڵ�
			returnNode.getParentNode().setLeftChild(returnNode.getRightChild());
			//�󼯽ڵ���ҽڵ�����Ϊɾ���ڵ���ҽڵ�
			returnNode.setRightChild(delNode.getRightChild());
		}
		return returnNode;
	}
	/**
	 * �������������������->���ڵ�->��������˳�����  ����Ǵ�С����
	 * ��������
	 */
	@Override
	public void midOrder(Node<T> current) {
		if(current!=null){
			midOrder(current.getLeftChild());
			System.out.print(current.getData()+"\t");
			midOrder(current.getRightChild());
		}
	}
	public void midOrder(){
		System.out.print("��������: ");
		midOrder(root);
	}
	/**
	 * 
	 * ��1�����ʸ��ڵ㣻��2����������ݹ��������������3����������ݹ������������
	 * ǰ������
	 */
	@Override
	public void preOrder(Node<T> current) {
		if(current!=null){
			System.out.print(current.getData()+"\t");
			preOrder(current.getLeftChild());
			preOrder(current.getRightChild());
		}
	}
	public void preOrder(){
		System.out.print("��������: ");
		preOrder(root);
	}
	/**
	 * 1�����ú���ݹ��������������2�����ú���ݹ��������������3�����ʸ��ڵ�
	 */
	@Override
	public void postOrder(Node<T> current) {
		if(current!=null){
			postOrder(current.getLeftChild());
			postOrder(current.getRightChild());
			System.out.print(current.getData()+"\t");
		}
	}
	public void postOrder(){
		System.out.print("��������: ");
		postOrder(root);
	}
	/**
	 * �������ֵ
	 */
	@Override
	public Node<T> findMax() {
		Node<T> max=root;
		while(max.getRightChild()!=null){
			 max=max.getRightChild();
		}
		return max;
	}
	/**
	 * ������Сֵ
	 */
	@Override
	public Node<T> findMin() {
		Node<T> min=root;
		while(min.getLeftChild()!=null){
			min=min.getLeftChild();
		}
		return min;
	}
	private void print(Node<T> tree,T key,int direction) {
        if(tree != null) {
            if(direction==0){
            	// tree�Ǹ��ڵ�
            	System.out.println();
            	System.out.printf("%2d is root isRed:%s \n", tree.getData(),tree.isRed());
            }else{
            	System.out.printf("%2d is %2d's %6s child  parent:%s  isRed:%s \n", tree.getData(), key, direction==1?"right" : "left",tree.getParentNode().getData(),tree.isRed());
            }

            print(tree.getLeftChild(), tree.getData(), -1);
            print(tree.getRightChild(),tree.getData(),  1);
        }
    }
	public  void print(){
		if(root!=null)
		print(root,root.getData(),0);
	}
}
