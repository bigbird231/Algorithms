package com.algorithm.tree;


public class RedBlackTree <AnyType extends Comparable<? super AnyType>>{
	private static final int BLACK=1;
	private static final int RED=0;
	
	private RedBlackNode header;
	private RedBlackNode nullNode;
	
	public RedBlackTree(){
		nullNode=new RedBlackNode(null);
		nullNode.left=nullNode.right=nullNode;
		header=new RedBlackNode(null);
		header.left=header.right=nullNode;
	}
	
	private class RedBlackNode{
		AnyType element;
		RedBlackNode left;
		RedBlackNode right;
		int color;
		
		RedBlackNode(AnyType theElement){
			this(theElement,null,null);
		}
		RedBlackNode(AnyType theElement,RedBlackNode lt,RedBlackNode rt){
			element=theElement;
			left=lt;
			right=rt;
			color=RedBlackTree.BLACK;
		}
	}
	
	private RedBlackNode rotate(AnyType x,RedBlackNode parent){
		if(compare(x,parent)<0){
			return parent.left=compare(x,parent.left)<0?rotateWithLeftChild(parent.left):rotateWithRightChild(parent.left);
		}else{
			return parent.right=compare(x,parent.right)<0?rotateWithLeftChild(parent.right):rotateWithRightChild(parent.right);
		}
	}
	
	private final int compare(AnyType x,RedBlackNode t){
		return x.compareTo(t.element);
	}
	
	private RedBlackNode rotateWithLeftChild(RedBlackNode k2){
		RedBlackNode k1=k2.left;
		k2.left=k1.right;
		k1.right=k2;
		return k1;
	}
	
	private RedBlackNode rotateWithRightChild(RedBlackNode k1){
		RedBlackNode k2=k1.right;
		k1.right=k2.left;
		k2.left=k1;
		return k2;
	}
	
	private RedBlackNode current;
	private RedBlackNode parent;
	private RedBlackNode grand;
	private RedBlackNode great;
	
	private void handleReorient(AnyType x){
		current.color=RED;
		current.left.color=BLACK;
		current.right.color=BLACK;
		if(parent.color==RED){
			grand.color=RED;
			if((compare(x,grand)<0)!=(compare(x,parent)<0)){
				parent=rotate(x,grand);
			}
			current=rotate(x,great);
			current.color=BLACK;
		}
		header.right.color=BLACK;
	}
	
	public void insert(AnyType x){
		current=parent=grand=header;
		nullNode.element=x;
		while(compare(x,current)!=0){
			great=grand;grand=parent;parent=current;
			current=compare(x,current)<0?current.left:current.right;
			if(current.left.color==RED&&current.right.color==RED){
				handleReorient(x);
			}
		}
		if(current!=nullNode){
			return;
		}
		current=new RedBlackNode(x,nullNode,nullNode);
		if(compare(x,parent)<0){
			parent.left=current;
		}else{
			parent.right=current;
		}
		handleReorient(x);
	}
	
	private void printTree(RedBlackNode t){
		if(t!=nullNode){
			printTree(t.left);
			System.out.println(t.element);
			printTree(t.right);
		}
	}
	
	public void printTree(){
		printTree(header.right);
	}
	
	public RedBlackNode searchKey(AnyType x){
		RedBlackNode result=searchKey(x,header.right);
		return result;
	}
	
	private RedBlackNode searchKey(AnyType x,RedBlackNode t){
		RedBlackNode result=null;
		if(t!=nullNode){
			int compareResult=compare(x,t);
			if(compareResult<0){
				result=searchKey(x,t.left);
			}else if(compareResult>0){
				result=searchKey(x,t.right);
			}else{
				result=t;
			}
		}else{
			//System.out.println("未找到此用户");
		}
		return result;
	}

	public int detectDepth(){
		Object levelNode[]=new Object[1100000];
		int front=0,rear=0;
		int depth=0;
		RedBlackNode flag=new RedBlackNode(null);
		RedBlackNode temp=null;
		levelNode[rear++]=header.right;
		levelNode[rear++]=flag;
		while(front!=rear){
			temp=(RedBlackTree<AnyType>.RedBlackNode) levelNode[front++];
			if(temp==flag){
				depth++;
				front++;
				levelNode[rear++]=flag;
				//System.out.println("-----------进入下一层----------");
				continue;
			}
			//System.out.println("当前节点:"+temp.user);
			if(temp.left!=nullNode){
				levelNode[rear++]=temp.left;
			}
			if(temp.right!=nullNode){
				levelNode[rear++]=temp.right;
			}
		}
		return depth;
		//System.out.println("红黑树深度为:"+depth);
	}

}
