package com.example.avl;

public class AVLTreeDemo {

	public static void main(String[] args) {

		//int[] arr= {4,3,6,5,7,8};
		//int[] arr= {10,12,8,9,7,6};
		int[] arr= {10,11,7,6,8,9};
		//创建一个AVLTree对象
		AVLTree avlTree=new AVLTree();
		//添加结点
		for(int i=0;i<arr.length;i++) {
			avlTree.add(new Node(arr[i]));
		}
		
		//中序遍历
		System.out.println("中序遍历");
		avlTree.infixOrder();
		
		//
		System.out.println("在平衡处理后");
		System.out.println("树的高度："+avlTree.getRoot().height()); //高度是4
		System.out.println("树的左子树的高度："+avlTree.getRoot().leftHeight());
		System.out.println("树的右子树的高度："+avlTree.getRoot().rightHeight());
		System.out.println("当前的根结点="+avlTree.getRoot());
		System.out.println("根结点的左子结点="+avlTree.getRoot().left);
		System.out.println("根结点的右子结点="+avlTree.getRoot().right);
	}

}

//创建AVLTree
class AVLTree{
	private Node root;
	
	
	public Node getRoot() {
		return root;
	}

	//查找要删除的结点
	public Node search(int value) {
		if(root==null) {
			return null;
		}else {
			return root.search(value);
		}
	}
	
	//查找父结点
	public Node searchParent(int value) {
		if(root==null) {
			return null;
		}else {
			return root.searchParent(value);
		}
	}
	
	/**
	 *   //编写方法
	 *   //1.返回 以node为根结点的二叉排序树的最小结点的值 
	 *   //2.删除node 为根结点的二叉排序树的最小结点
	 * @param node   传入的结点（当作二叉排序树的根节点）
	 * @return       返回 以node为根结点的二叉排序树的最小结点的值   
	 */
	public int delRightTreeMin(Node node) {
		Node target=node;
		//循环的查找左结点，就会找到最小值
		while(target.left!=null) {
			target=target.left;
		}
		//这时 target就指向了最小结点
		delNode(target.value);
		return target.value;
	}
	
	//删除结点
	public void delNode(int value) {
		if(root==null) {
			return;
		}else {
			//1. 需求先去找到要删除的结点 targetNode
			Node targetNode=search(value);
			//如果没有找到要删除的结点
			if(targetNode==null) {
				return;
			}
			//如果我们发现当前这颗二叉排序树只有一个结点
			if(root.left==null&&root.right==null) {
				root=null;
				return;
			}
			//如果我们发现targetNode没有父结点
			//去找到targetNode的父结点
			Node parent=searchParent(value);
			//如果要删除的结点是叶子结点
			if(targetNode.left==null&&targetNode.right==null) {
				//判断targetNode是父结点的左子结点，还是右子结点
				if(parent.left!=null&&parent.left.value==value) { //是左子结点
					parent.left=null;
				}else if(parent.right!=null&parent.right.value==value) {   //是右子结点
					parent.right=null;
				}
			}else if(targetNode.left!=null&&targetNode.right!=null) {
				int minVal=delRightTreeMin(targetNode.right);
				targetNode.value=minVal;
			}else {
				
				//如果要删除的结点 有 左子结点
				if(targetNode.left!=null) {
					if(parent!=null) {
						//如果targetNode是parent的左子结点
						if(parent.left.value==value) {
							parent.left=targetNode.left;
						}else {  //targetNode是parent的右子结点
							parent.right=targetNode.left;
						}
					}else {
						root=targetNode.left;
					}
				}else {
					if(parent!=null) {
						if(parent.left.value==value) {
							parent.left=targetNode.right;
						}else {
							parent.right=targetNode.right;
						}
					}else {
						root=targetNode.right;
					}
				}
			}
			
		}
	}
	
	//添加节点的方法
	public void add(Node node) {
		if(root==null) {
			root=node;//如果root为空则直接让root指向node
		}else {
			root.add(node);
		}
	}
	//中序遍历
	public void infixOrder() {
		if(root!=null) {
			root.infixOrder();
		}else {
			System.out.println("当前二叉树为空，不能遍历");
		}
	}
}

class Node{
	int value;
	Node left;
	Node right;
	public Node(int value) {
		super();
		this.value = value;
	}
	
	//返回左子树的高度
	public int leftHeight() {
		if(left==null) {
			return 0;
		}
		return left.height();
	}
	//返回右子树的高度
	public int rightHeight() {
		if(right==null) {
			return 0;
		}
		return right.height();
	}
	
	
	//返回当前结点作为根结点的树的高度
	public int height() {
		return Math.max(left==null?0:left.height(),right==null?0:right.height())+1;
	}
	
	//左旋转
	private void leftRotate() {
		//创建新的结点，以当前根结点的值为值
		Node newNode = new Node(value);
		//把新的结点的左子树 设置为当前结点的左子树
		newNode.left=left;
		//把新的结点的右子树 设置为当前结点的右子树的 左子树
		newNode.right=right.left;
		//把当前结点的值替换成右子结点的值
		value=right.value;
		//把当前结点的右子树设置成当前结点的右子树的右子树
		right=right.right;
		//把当前结点的左子树设置成 刚刚创建的新结点
		left=newNode;
	}
	
	//右旋转
	private void rightRotate() {
		Node newNode=new Node(value);
		newNode.right=right;
		newNode.left=left.right;
		value=left.value;
		left=left.left;
		right=newNode;
	}
	
	/**
	 *     查找要删除的结点
	 * @param value   希望删除的结点的值
	 * @return        如果找到返回该结点，否则返回null
	 */
	public Node search(int value) {
		if(value==this.value) {  //找到就是该结点
			return this;
		}else if(value<this.value) {  //如果查找的值小于当前结点，向左子树递归查找
			//如果左子结点为null  ,说明找不到了
			if(this.left==null) {
				return null;
			}
			return this.left.search(value);
		}else {  //如果查找的值不小于当前结点，向右子树递归查找
			if(this.right==null) {
				return null;
			}
			return this.right.search(value);
		}
	}
	/**
	 *     查找要删除结点的父结点
	 * @param value     要查找的结点的值
	 * @return          返回的时要删除的结点的父结点，如果没有就返回null
	 */
	public Node searchParent(int value) {
		//如果当前结点就是要删除的结点的父结点，就返回
		if((this.left!=null&&this.left.value==value)||((this.right!=null&&this.right.value==value))){
			return this;
		}else {
			//如果查找的值小于当前结点的值，并且当前结点的左子结点不为空
			if(value<this.value&&this.left!=null) {
				return this.left.searchParent(value);
			}else if(value>=this.value&&this.right!=null) {
				return this.right.searchParent(value);
			}else {
				return null;  //没有找到父结点
			}
		}
	}
	
	
	@Override
	public String toString() {
		return "Node [value=" + value + "]";
	}


	//添加结点的方法
	//递归的形式添加结点，注意需要满足二叉排序树的要求
	public void add(Node node) {
		if(node==null) {
			return;
		}
		//判断传入的结点的值，和当前子树的根结点的值关系
		if(node.value<this.value) {
			//如果当前结点的左子结点为null
			if(this.left==null) {
				this.left=node;
			}else {
				//递归的向左子树添加
				this.left.add(node);
			}
		}else {  //添加的结点的值大于 当前结点的值
			if(this.right==null) {
				this.right=node;
			}else {
				//递归的向右子树添加
				this.right.add(node);
			}
		}
		
		//当添加完一个结点后，如果：（右子树的高度-左子树的高度）>1   ,就左旋转
		if(rightHeight()-leftHeight()>1) {
			//如果它的右子树的左子树高度大于它的右子树的右子树高度
			if(right!=null&&right.rightHeight()<right.leftHeight()) {
				//先对当前结点的右子树进行右旋转
				right.rightRotate();
			}
			leftRotate();//左旋转 
			return;
		}
		//当添加完一个结点后，如果：（左子树的高度-右子树的高度）>1   ,就右旋转
		if(leftHeight()-rightHeight()>1) {
			//如果它的左子树的右子树高度大于它的左子树的左子树的高度
			if(left!=null&&left.rightHeight()>left.leftHeight()) {
				//先对当前结点的左子树进行左旋转
				left.leftRotate();
			}
			rightRotate();
		}
	}
	
	//中序遍历
	public void infixOrder() {
		if(this.left!=null) {
			this.left.infixOrder();
		}
		System.out.println(this);
		if(this.right!=null) {
			this.right.infixOrder();
		}
	}
}