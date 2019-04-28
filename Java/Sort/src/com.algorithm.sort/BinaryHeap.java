package com.algorithm.sort;

import java.util.ArrayList;

/*
 * 进行堆排序的数据结构，可以进行优先队列的操作
 * 例如Top k算法的实现
 */
public class BinaryHeap<AnyType extends Comparable<? super AnyType>>{
	private ArrayList<AnyType> array=new ArrayList<AnyType>();
	
	public BinaryHeap(){
		array.add(null);
	}
	
	public BinaryHeap(AnyType[] items){
		array.add(null);
		for(AnyType item:items){
			array.add(item);
		}
		buildHeap();
	}
	
	public void insert(AnyType x){
		array.add(null);
		int hole=array.size()-1;
		for(;hole>1&&x.compareTo(array.get(hole/2))>0;hole/=2){
			array.set(hole, array.get(hole/2));
		}
		array.set(hole,x);
	}
	
	public AnyType findMin(){
		return array.get(array.size()-1);
	}
	
	public void deleteEnd(){
		array.remove(array.size()-1);
	}
	
	public AnyType deleteMax(){
		if(array.size()<2){
			//System.out.println("isEmpty");
			return null;
		}
		if(array.size()==2){
			return array.remove(1);
		}
		AnyType maxItem=findMax();
		array.set(1, array.remove(array.size()-1));
		percolateDown(1);
		return maxItem;
	}
	
	private AnyType findMax(){
		return array.get(1);
	}
	
	private void percolateDown(int hole){
		int child;
		AnyType tmp=array.get(hole);
		for(;hole*2<=(array.size()-1);hole=child){
			child=hole*2;
			if(child!=(array.size()-1)&&array.get(child+1).compareTo(array.get(child))>0){
				child++;
			}
			if(array.get(child).compareTo(tmp)>0){
				array.set(hole, array.get(child));
			}else{
				break;
			}
		}
		array.set(hole, tmp);
	}
	
	private void buildHeap(){
		for(int i=(array.size()-1)/2;i>0;i--){
			percolateDown(i);
		}
	}
	
	public static void main(String args[]){
		ArrayList<Integer> a=new ArrayList<Integer>();
		a.add(null);
		int[] b=new int[4];
		System.out.println(b.length);
	}
}
