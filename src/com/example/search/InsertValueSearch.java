package com.example.search;

import java.util.Arrays;

public class InsertValueSearch {

	public static void main(String[] args) {

//		int[] arr=new int[100];
//		for(int i=0;i<100;i++){
//			arr[i]=i+1;
//		}
		int[] arr={1,8,10,89,1000,1234};
		System.out.println(Arrays.toString(arr));
		
		int index=insertValSearch(arr, 0, arr.length-1,89);
		
		System.out.println("index="+index);
		
	}

	/**
	 * 编写插值查找算法    也要求数组是有序的
	 * @param arr      数组
	 * @param left     左边索引
	 * @param right    右边索引
	 * @param findVal  要查找的值
	 * @return         如果找到，就返回对应的下标，如果没有找到，返回-1
	 */
	public static int insertValSearch(int[] arr,int left,int right,int findVal){
		System.out.println("查找次数");
		//注意：findVal<arr[0]和findVal>arr[arr.length-1] 必须需要，否则我们得到的mid可能越界
		if(left>right||findVal<arr[0]||findVal>arr[arr.length-1]){
			return -1;
		}
		
		//求出mid
		int mid=left+(right-left)*(findVal-arr[left])/(arr[right]-arr[left]);
		int midVal=arr[mid];
		if(findVal>midVal){  //说明应该向右边递归
			return insertValSearch(arr, mid+1, right, findVal);
		}else if(findVal<midVal){
			return insertValSearch(arr, left, mid-1, findVal);
		}else{
			return mid;
		}
	}
}
