package com.algorithm.sort;

public class QuickSort<AnyType extends Comparable<? super AnyType>>{
	private static final int CUTOFF=10;
	
	public void quickSelect(AnyType a[],int k){
		quickSelect(a,0,a.length-1,k);
	}
	
	private void quickSelect(AnyType a[],int left,int right,int k){
		if((left+CUTOFF)<right){
			AnyType pivot=median(a,left,right);
			int i=left,j=right-1;
			for(;;){
				while(a[--j].compareTo(pivot)<0){}
				while(a[++i].compareTo(pivot)>0){}
				if(i<j)
					swapReferences(a,i,j);
				else
					break;
			}
			swapReferences(a,i,right-1);
			if(k<=i){
				quickSelect(a,left,i-1,k);
			}else if(k>i+1){
				quicksort(a,left,i-1);
				quickSelect(a,i+1,right,k-1-i);
				//quickSelect(a,i+1,right,k);
			}else{
				quicksort(a,left,i-1);
			}
		}else{
			insertionSort(a,left,right);
		}
	}
	
	public void quicksort(AnyType a[]){
		quicksort(a,0,a.length-1);
	}
	
	private void quicksort(AnyType a[],int left,int right){
		if((left+CUTOFF)<right){
			AnyType pivot=median(a,left,right);
			int i=left,j=right-1;
			for(;;){
				while(a[--j].compareTo(pivot)<0){}
				while(a[++i].compareTo(pivot)>0){}
				if(i<j)
					swapReferences(a,i,j);
				else
					break;
			}
			swapReferences(a,i,right-1);
			quicksort(a,left,i-1);
			quicksort(a,i+1,right);
		}else{
			insertionSort(a,left,right);
		}
	}
	
	private AnyType median(AnyType a[],int left,int right){
		int center=(left+right)/2;
		if(a[center].compareTo(a[right])<0)
			swapReferences(a,center,right);
		if(a[left].compareTo(a[right])<0)
			swapReferences(a,left,right);
		if(a[left].compareTo(a[center])<0)
			swapReferences(a,left,center);
		swapReferences(a,center,right-1);
		return a[right-1];
	}
	
	private final void swapReferences(AnyType a[],int swapa,int swapb){
		AnyType tmp=a[swapa];
		a[swapa]=a[swapb];
		a[swapb]=tmp;
	}
	
	public void insertionSort(AnyType[] a,int left,int right){
		int j;
		for(int p=left+1;p<=right;p++){
			AnyType tmp=a[p];
			for(j=p;j>0&&tmp.compareTo(a[j-1])>0;j--)
				a[j]=a[j-1];
			a[j]=tmp;
		}
	}
	
}
