package com.algorithm.tree;

public class AVLTree<AnyType extends Comparable<? super AnyType>>{
	
	public class AVLNode{
		AnyType element;
		AVLNode left;
		AVLNode right;
		int height;
		AVLNode(AnyType theElement){
			this(theElement,null,null);
		}
		
		AVLNode(AnyType theElement,AVLNode lt,AVLNode rt){
			element=theElement;
			left=lt;
			right=rt;
			height=0;
		}
	}
	
	private int height(AVLNode t){
		return t==null?-1:t.height;
	}
	
	private AVLNode rotateWithLeftChild(AVLNode k2){
		AVLNode k1=k2.left;
		k2.left=k1.right;
		k1.right=k2;
		k2.height=Math.max(height(k2.left), height(k2.right))+1;
		k1.height=Math.max(height(k1.left), k2.height)+1;
		return k1;
	}
	
	private AVLNode rotateWithRightChild(AVLNode k1){
		AVLNode k2=k1.right;
		k1.right=k2.left;
		k2.left=k1;
		k1.height=Math.max(height(k1.left), height(k1.right));
		k2.height=Math.max(height(k2.right), k1.height);
		return k2;
	}
	
	private AVLNode doubleWithLeftChild(AVLNode k3){
		k3.left=rotateWithRightChild(k3.left);
		return rotateWithLeftChild(k3);
	}
	
	private AVLNode doubleWithRightChild(AVLNode k1){
		k1.right=rotateWithLeftChild(k1.right);
		return rotateWithRightChild(k1);
	}
	
	private int compare(AnyType x,AnyType element){
		return x.compareTo(element);
	}
	
	public AVLNode insert(AnyType x,AVLNode t){
		//若为空树，则返回根节点
		if(t==null){
			return new AVLNode(x,null,null);
		}
		//不为空树，则依据大小进行旋转插入
		int compareResult=compare(x,t.element);
		if(compareResult<0){
			t.left=insert(x,t.left);
			if(height(t.left)-height(t.right)==2){
				if(compare(x,t.left.element)<0){
					t=rotateWithLeftChild(t);
				}else{
					t=doubleWithLeftChild(t);
				}
			}
		}else if(compareResult>0){
			t.right=insert(x,t.right);
			if(height(t.right)-height(t.left)==2){
				if(compare(x,t.right.element)>0){
					t=rotateWithRightChild(t);
				}else{
					t=doubleWithRightChild(t);
				}
			}
		}
		t.height=Math.max(height(t.left), height(t.right))+1;
		return t;
		
	}
	
	private void printTree(AVLNode t){
		if(t!=null){
			printTree(t.left);
			System.out.println(t.element);
			printTree(t.right);
		}
	}
	
	public int detectDepth(AVLNode t){
		Object levelNode[]=new Object[1100000];
		int front=0,rear=0;
		int depth=0;
		AVLNode flag=new AVLNode(null);
		AVLNode temp=null;
		levelNode[rear++]=t;
		levelNode[rear++]=flag;
		while(front!=rear){
			temp=(AVLTree<AnyType>.AVLNode) levelNode[front++];
			if(temp==flag){
				depth++;
				front++;
				levelNode[rear++]=flag;
				//System.out.println("-----------进入下一层----------");
				continue;
			}
			//System.out.println("当前节点:"+temp.user);
			if(temp.left!=null){
				levelNode[rear++]=temp.left;
			}
			if(temp.right!=null){
				levelNode[rear++]=temp.right;
			}
		}
		return depth;
		//System.out.println("AVL树深度为:"+depth);
	}
	
	public AVLNode searchKey(AnyType x,AVLNode t){
		AVLNode result = null;
		if(t!=null){
			int compareResult=compare(x,t.element);
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
}
