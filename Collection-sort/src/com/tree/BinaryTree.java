package com.tree;


/**
 * 
 * @author pet-lsf
 * 二叉树每一层最多2N-1个节点
 * @param <T>
 */
public  abstract class BinaryTree<T extends Comparable<? super T>> implements Tree<T>{
	//根节点
	public  Node<T> root;
	/**
	 * 根据数据查找节点
	 */
	@Override
	public Node<T> find(T data) {
		Node<T> current=root;
		while(current!=null){
			//当前节点的值大于寻找值，
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
	 * 添加节点
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
					//获取左子节点
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
	 * 删除指定节点
	 * 1 删除叶子节点 最简单
	 * 2 该节点拥有两个子节点
	 * 3 该节点拥有一个子节点
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
		//找出需要删除的节点
		boolean  isLeftChild=false;
		while(current!=null){
			//当前节点大于删除值，继续往左边找
			if(current.getData().compareTo(now.getData())==1){
				isLeftChild=true;
				current=current.getLeftChild();
			}else if(current.getData().compareTo(now.getData())==-1){
				isLeftChild=false;
				//当前节点小于删除值，继续往右边找
				current=current.getRightChild();
			}else{
				//当值相等 跳出循环
				break;
			}
		}
		if(current==null){
			return false;
		}
		//第一种情况，当前节点是叶子节点,不存在在子节点
		if(current.getLeftChild()==null&&current.getRightChild()==null){
			//直接删除该节点
			if(current==root){
				root=null;
			}else if(isLeftChild){
				//等待gc回收
				current.getParentNode().setLeftChild(null);
			}else{
				current.getParentNode().setRightChild(null);
			}
			current.setParentNode(null);
			return true;
		}else if(current.getLeftChild()==null&&current.getRightChild()!=null){
			//左子节点不存在，存在右子节点
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
			//左子节点存在，不存在右子节点
			if(current==root){
				root=current.getLeftChild();
			}else if(isLeftChild){
				current.getParentNode().setLeftChild(current.getLeftChild());
			}else{
				current.getParentNode().setRightChild(current.getLeftChild());
			}
			current.getLeftChild().setParentNode(current.getParentNode());
			//等待gc
			current.setParentNode(null);
			current.setLeftChild(null);
			return true;
		}else{
			//存在双子节点 删除节点，需要旋转调整保证二叉树平衡
			Node<T> node=getNextNode(current);
			if(current == root){
				node.getRightChild().setParentNode(node);
                root=node;
            }else if(isLeftChild){
            	//如果是当前节点是左子节点,需要替换删除节点的父子节点的左子节点
            		current.getParentNode().setLeftChild(node);
            }else{
            		current.getParentNode().setRightChild(node);
            }
			//将右子节点替换成父子节点后，需要设置原左子节点为新右子节点的左子节点
			node.setLeftChild(current.getLeftChild());
			//设置父子关系
			node.setParentNode(current.getParentNode());
			current.getLeftChild().setParentNode(node);
			//等待gc回收
			current.setLeftChild(null);
			current.setParentNode(null);
			current.setRightChild(null);
			return true;
		}
	}
	//删除两个节点，查找替换节点
	/**
	 * 旋转，返回平衡节点值
	 * 找到叶子节点，调整右子节点
	 * @param current
	 * @return
	 */
	private Node<T> getNextNode(Node<T> delNode) {
		Node<T> returnNode=delNode;//后集节点
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
			//选中左子节点，需要设置兄弟节点来替代当前后集节点
			returnNode.getParentNode().setLeftChild(returnNode.getRightChild());
			//后集节点的右节点设置为删除节点的右节点
			returnNode.setRightChild(delNode.getRightChild());
		}
		return returnNode;
	}
	/**
	 * 中序遍历：按照左子树->根节点->右子树的顺序访问  输出是从小到大
	 * 中序排序
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
		System.out.print("中序排序: ");
		midOrder(root);
	}
	/**
	 * 
	 * （1）访问根节点；（2）采用先序递归遍历左子树；（3）采用先序递归遍历右子树；
	 * 前序排序
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
		System.out.print("先序排序: ");
		preOrder(root);
	}
	/**
	 * 1）采用后序递归遍历左子树；（2）采用后序递归遍历右子树；（3）访问根节点
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
		System.out.print("后序排序: ");
		postOrder(root);
	}
	/**
	 * 查找最大值
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
	 * 查找最小值
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
            	// tree是根节点
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
