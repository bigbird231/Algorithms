package com.algorithm.tree;

public class AATree<AnyType extends Comparable<? super AnyType>>{
	private AANode root;
	private AANode nullNode;
	
	public AATree(){
		nullNode=new AANode(null,null,null);
		nullNode.left=nullNode.right=nullNode;
		nullNode.level=0;
		root=nullNode;
	}
	
	public class AANode{
		AnyType element;
		AANode left;
		AANode right;
		int level;
		
		AANode(AnyType theElement,AANode lt,AANode rt){
			element=theElement;
			left=lt;
			right=rt;
			level=1;
		}
	}
	
	private AANode skew(AANode t){
		if(t.left.level==t.level){
			t=rotateWithLeftChild(t);
		}
		return t;
	}
	
	private AANode split(AANode t){
		if(t.right.right.level==t.level){
			t=rotateWithRightChild(t);
			t.level++;
		}
		return t;
	}
	
	private AANode rotateWithLeftChild(AANode k2){
		AANode k1=k2.left;
		k2.left=k1.right;
		k1.right=k2;
		return k1;
	}
	
	private AANode rotateWithRightChild(AANode k1){
		AANode k2=k1.right;
		k1.right=k2.left;
		k2.left=k1;
		return k2;
	}
	
	public AANode insert(AnyType x){
		return root=insert(x,root);
	}
	
	private AANode insert(AnyType x,AANode t){
		if(t==nullNode){
			return new AANode(x,nullNode,nullNode);
		}
		if(x.compareTo(t.element)<0){
			t.left=insert(x,t.left);
		}else if(x.compareTo(t.element)>0){
			t.right=insert(x,t.right);
		}else{
			return t;
		}
		t=skew(t);
		t=split(t);
		return t;
	}
	
	private void printTree(AANode t){
		if(t!=nullNode){
			printTree(t.left);
			System.out.println(t.element);
			printTree(t.right);
		}
	}
	
	public void printTree(){
		printTree(root);
	}
	
	public int detectDepth(){
		Object levelNode[]=new Object[2000];
		int front=0,rear=0;
		int depth=0;
		AANode flag=new AANode(null,null,null);
		AANode temp=null;
		levelNode[rear++]=root;
		levelNode[rear++]=flag;
		while(front!=rear){
			temp=(AATree<AnyType>.AANode) levelNode[front++];
			if(temp==flag){
				depth++;
				front++;
				levelNode[rear++]=flag;
				continue;
			}
			if(temp.left!=nullNode){
				levelNode[rear++]=temp.left;
			}
			if(temp.right!=nullNode){
				levelNode[rear++]=temp.right;
			}
		}
		return depth;
		//System.out.println("AA树深度为:"+depth);
	}
	
	public AANode searchKey(AnyType x){
		return searchKey(x,root);
	}
	
	private AANode searchKey(AnyType x,AANode t){
		AANode result=null;
		if(t!=null){
			int compareResult=x.compareTo(t.element);
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
