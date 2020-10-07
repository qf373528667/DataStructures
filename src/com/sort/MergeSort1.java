package com.sort;

import java.util.Arrays;

//归并排序
//使用递归   先分后合
public class MergeSort1 {

	public static void main(String[] args) {
		int[] arr={8,4,5,7,1,3,6,2};
		System.out.println("原始数组："+Arrays.toString(arr));
		int[] temp=new int[arr.length];
		mergeSort(arr, 0, arr.length-1, temp);
		System.out.println("排序后的："+Arrays.toString(arr));
	}
	
	/**
	 * 拆和合算法
	 * @param arr
	 * @param left
	 * @param right
	 * @param temp
	 */
	public static void mergeSort(int[] arr,int left,int right,int[] temp){
		int l=left;
		int mid=(left+right)/2;
		int r=right;
		if(left<right){
			//对左边进行拆和合算法
			mergeSort(arr, left, mid, temp);
			//对右边进行拆和合算法
			mergeSort(arr, mid+1, right, temp);
			//真正合并
			merge(arr, left, mid, right, temp);
			
		}
	}
	
	/**
	 * 合并算法    把两个有序合并成一个（即把原始数组看成两个数组，一个是从left到mid，另一个是从mid+1到right）
	 * 先用递归把原始数组拆分成 到最小一个数组里一个数（即可看做是有序数组），然后用这个方法合并，在拆分的每一步都进行合并，使用递归调到最底，就是看成每个数组只有一个数的两个数组
	 * @param arr    要排序的数组
	 * @param left   合并的起始位置
	 * @param mid
	 * @param right
	 * @param temp
	 */
	public static void merge(int[] arr,int left,int mid,int right,int[] temp){
		int i=left;     //合并的看做的数组第一个索引下标
		int j=mid+1;    //合并的看做的数组第二个索引下标
		int t=0;        //合并存放到临时数组temp的下标
		while(i<=mid&&j<=right){
			if(arr[i]<arr[j]){
				temp[t++]=arr[i++];
			}else{
				temp[t++]=arr[j++];
			}
		}
		while(i<=mid){
			temp[t++]=arr[i++];
		}
		while(j<=right){
			temp[t++]=arr[j++];
		}
		//把排好序的临时数组里面的数存回arr数组
		t=0;
		while(left<=right){
			arr[left++]
					=temp[t++];
		}
	}

}
