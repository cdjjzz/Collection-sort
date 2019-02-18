package com.tree;

import com.tree.BinaryTree;
/**
 * 红黑树类
 * @author pet-lsf
 *  O(2logN)
 *  O(n)
 * @param <T>
 */
public   class RedBlackTree<T extends Comparable<? super T>> extends BinaryTree<T>{
	/**
	 * 红黑特征
	 * 1 每个节点不是红色就是黑色
	 * 2 根节点(root) 总是黑色
	 * 3 如果父节点是红色，那双子节点必须为黑色（在路径中不存在连续的红节点）
	 * 4 从根节点(root)到叶子节点的路径上拥有相同数目的黑节点
	 * 从根节点到叶节点的路径上的黑色节点的数目称为黑色高度，规则 4 另一种表示就是从根到叶节点路径上的黑色高度必须相同。
	 * 新插入的节点颜色总是红色的，这是因为插入一个红色节点比插入一个黑色节点违背红-黑规则的可能性更小，
	 * 原因是插入黑色节点总会改变黑色高度（违背规则4），但是插入红色节点只有一半的机会会违背规则3
	 * （因为父节点是黑色的没事，父节点是红色的就违背规则3）。另外违背规则3比违背规则4要更容易修正。当插入一个新的节点时，可能会破坏这种平衡性
	 * 
	 * 红黑树修正
	 * 修正原因：添加新节点为红色，有可能会违背规则3存在连续的红节点，故需要通过旋转达或改变节点颜色到平衡
	 * 1 改变颜色,使用新加入节点的父节点及兄弟节点都是红色节点
	 * 如原图：
	 * 		      10黑色
	 * 
	 *      7红色             11红色
	 *            10黑色
	 *     7黑色                   11黑色
	 *   5红色
	 * 
	 * 2 右旋 　首先要说明的是节点本身是不会旋转的，旋转改变的是节点之间的关系，选择一个节点作为旋转的顶端，
	 * 	如果做一次右旋，这个顶端节点会向下和向右移动到它右子节点的位置，它的左子节点会上移到它原来的位置。右旋的顶端节点必须要有左子节点。
	 * 
	 * 3 左旋  左旋的顶端节点必须存在右子节点
	 * 
	 * 我们改变颜色也是为了帮助我们判断何时执行什么旋转，
	 * 而旋转是为了保证树的平衡。光改变节点颜色是不能起到任何作用的，
	 * 旋转才是关键的操作，在新增节点或者删除节点之后，可能会破坏二叉树的平衡。
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
	 * 删除后，不平衡，需要修改平衡性
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
	 * 新增节点后，造成树不平衡，需要修复成平衡树
	 */
	/**
	 * 
	 * 
	 * 
	 * @param node
	 */
	private void fixUp(Node<T> node){
		//插入节点 的 父节点和叔叔节点都是红色，需要变色或旋转  
		 //将当前的父节点和叔叔节点涂黑，祖父节点涂红
		Node<T> gparent;//祖父节点
		Node<T> parent;//父节点
		Node<T> uncle;
		//父节点是红色
		while((parent=node.getParentNode())!=null&&parent.isRed()){
			   gparent=parent.getParentNode();
			   if(gparent==null){
				   break;
			   }
			   //父节点是祖父左子节点，那叔叔节点就是右子节点
			   if(parent==gparent.getLeftChild()){
				   uncle=gparent.getRightChild();
				    //叔叔节点也是红色
				   if(uncle!=null&&uncle.isRed()){
					   parent.setRed(false);
					   uncle.setRed(false);
					   gparent.setRed(true);
					   node=gparent;
					   continue;
				   }
				   //叔叔节点是黑色，当前节点是父节点的右子节点  //需要父节点左旋
				   if(node==parent.getRightChild()){
					   leftRate(parent);
					   Node<T> tmp=node;
					   //改变指针
					   node=parent;
					   parent=tmp;
				   }
				   if(node==parent.getLeftChild()){
					   parent.setRed(false);
					   gparent.setRed(true);
					   rightRate(gparent);
				   }
			   }else{//父节点是祖父右子节点。那叔叔节点就是左子节点
				    uncle=gparent.getLeftChild();
				    //叔叔节点也是红色的 
				    if(uncle!=null&&uncle.isRed()){
				    	parent.setRed(false);
						uncle.setRed(false);
						gparent.setRed(true);
						node=gparent;
						continue;
				    }
				    //叔叔节点是黑色，且当前节点是父节点的左子节点，需要右旋
				    if(node==parent.getLeftChild()){
				    	rightRate(parent);
				    	 Node<T> tmp=node;
						   //改变指针
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
		//插入节点 的 父节点是红色的，叔叔是黑色的，且插入节点是父节点的右子节点
		//插入节点 的 父节点是红色的，叔叔是黑色的，且插入节点是父节点的左子节点。
	}
	/**
	 * 左旋
	 * 1 将y的左子节点赋予x的右子节点，并将x设置y的左子节点的父子节点
	 * 2 将x的父节点赋给y的父节点，同时设置父节点的子节点为y(存在父子节点)
	 * 3 将x的父子节点设置为y，y的左子节点设置为x
	 * @param node
	 */
	private void leftRate(Node<T> x){
		//第一步 设置x的右子节点为y的左子节点，并设置y的左子节点的父节点为x
		Node<T> y=x.getRightChild();
		x.setRightChild(y.getLeftChild());
		if(y.getLeftChild()!=null){
			y.getLeftChild().setParentNode(x);
		}
		//第二步 将x的父节点赋给y
		y.setParentNode(x.getParentNode());
		if(x.getParentNode()==null){
			this.root=y;
		}else{
			//如果原x是父节点的左子节点，那么y设置为左子
			if(x==x.getParentNode().getLeftChild()){
				x.getParentNode().setLeftChild(y);
			}else{
				x.getParentNode().setRightChild(y);
			}
		}
		//第三步 设置x的父节点为y,y的左子节点设置为x
		x.setParentNode(y);
		y.setLeftChild(x);
	}
	/**
	 * 右旋
	 * 1 将x的右子节点赋予y的左子节点，设置x的右子节点的父节点为y
	 * 2 将y的父节点赋给x的父节点
	 * @param y
	 */
	private void rightRate(Node<T> y){
		// 将x的右子节点赋予y的左子节点，设置x的右子节点的父节点为y
		Node<T> x=y.getLeftChild();
		y.setLeftChild(x.getRightChild());
		if(x.getRightChild()!=null){
			x.getRightChild().setParentNode(y);
		}
		
		// 将y的父节点赋给x
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
