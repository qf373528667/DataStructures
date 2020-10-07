package com.example.search;

import java.util.ArrayList;
import java.util.List;

//注意：使用二分查找的前提是 该数组是有序的
public class BinarySearch {

	public static void main(String[] args) {
		int[] arr={1,8,10,89,1000,1234};
		int resIndex=binarySearch(arr, 0, arr.length-1, 12345);
		System.out.println("resIndex="+resIndex);
		System.out.println(binarySearch2(arr, 0, arr.length-1, 1));
	}

	/**
	 * 二分查找算法
	 * @param arr      数组
	 * @param left     左边的索引
	 * @param right    右边的索引
	 * @param findVal  要查找的值
	 * @return         如果找到就返回下标，如果没找到，就返回-1
	 */
	public static int binarySearch(int[] arr,int left,int right,int findVal){
		System.out.println("二分查找");
		if(left>right){ //当left>right时，说明递归整个数组，但是没有找到
			return right;
		}
		int mid=(left+right)/2;
		int midVal=arr[mid];
		if(findVal>midVal){//向右递归
			return binarySearch(arr, mid+1, right, findVal);
		}else if(findVal<midVal){ //向左递归
			return binarySearch(arr, left, mid-1, findVal);
		}else{
			return mid;
		}
	}
	
	//完成思考题
	public static List binarySearch2(int[] arr,int left,int right,int findVal){
		if(left>right){ //当left>right时，说明递归整个数组，但是没有找到
			return new ArrayList<Integer>();
		}
		int mid=(left+right)/2;
		int midVal=arr[mid];
		if(findVal>midVal){//向右递归
			return binarySearch2(arr, mid+1, right, findVal);
		}else if(findVal<midVal){ //向左递归
			return binarySearch2(arr, left, mid-1, findVal);
		}else{
			//创建一个ArrayList
			List<Integer> resIndexList=new ArrayList<Integer>();
			//向mid 索引值的左边扫描，将所有满足值的下标添加到集合
			int temp=mid-1;
			while(true){
				if(temp<0||arr[temp]!=findVal){
					break;
				}
				//否则，就将temp放入到resIndexList
				resIndexList.add(temp--);//temp左移
				
			}
			resIndexList.add(mid);
			temp=mid+1;
			while(true){
				if(temp>arr.length-1||arr[temp]!=findVal){
					break;
				}
				//否则，就将temp放入到resIndexList
				resIndexList.add(temp++);//temp右移
				
			}
			return resIndexList;
		}
	}
	
}
