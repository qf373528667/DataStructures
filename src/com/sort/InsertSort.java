package com.sort;

import java.util.Arrays;
//3.插入排序
public class InsertSort {

	public static void main(String[] args) {

//		int [] arr={89,23,47,5,9};
//		System.out.println("排序前的数据---"+Arrays.toString(arr));
//		insertSort(arr);
//		System.out.println("排序后的数据---"+Arrays.toString(arr));
		
		int[] arr=new int[80000];
		for(int i=0;i<80000;i++){
			arr[i]=(int)(Math.random()*8000000);
		}
		long time1=System.currentTimeMillis();
		System.out.println("选择排序前---"+time1);
		
		insertSort(arr);
		
		long time2=System.currentTimeMillis();
		System.out.println("选择排序所需时间："+(time2-time1));//80000数据     选择排序大概需要1.5秒（1446）
		System.out.println("选择排序后---"+time2);

	}

	//把需要排序的数组（{89,23,47,5,9}）看成两个表，一个有序表（{89}最初只有一个元素），一个无序表（{23,47,5,9}最初有n-1个元素），
	//遍历无序表，从无序表中一个一个插入到有序表       只是看做并非是，还是当做一个数组处理
	public static void insertSort(int[] arr){
		//使用循环
		for(int i=1;i<arr.length;i++){
			//第一次插入
			int index=i;//第一次要插入的元素下标是1
			int temp=arr[index];//要插入的元素放入temp
			//循环 判断 寻找要插入的位置     说明：temp<arr[index-1]  要插入的数小于有序表的最后一个，就是没找到要继续找
			while(index>0&&temp<arr[index-1]){
				arr[index]=arr[index-1];//表示把 比 要插入的数(arr[index]) 大的数 往后移一位  arr[index]已经存入temp不会丢失
				index--;
			}
			//当循环结束，即找到了要插入的位置  index  判断index是否等于1，如果等于1，即要插入的元素的所在位置就是对的位置，不需要插入
			if(index!=i){
				arr[index]=temp;
			}
		}
		
		/*
		//第一次插入
		int index=1;//第一次要插入的元素下标是1
		int temp=arr[index];//要插入的元素放入temp
		//循环 判断 寻找要插入的位置     说明：temp<arr[index-1]  要插入的数小于有序表的最后一个，就是没找到要继续找
		while(index>0&&temp<arr[index-1]){
			arr[index]=arr[index-1];//表示把 比 要插入的数(arr[index]) 大的数 往后移一位  arr[index]已经存入temp不会丢失
			index--;
		}
		//当循环结束，即找到了要插入的位置  index  判断index是否等于1，如果等于1，即要插入的元素的所在位置就是对的位置，不需要插入
		if(index!=1){
			arr[index]=temp;
		}
		//第二次插入
		index=2;//第一次要插入的元素下标是1
		temp=arr[index];//要插入的元素放入temp
		//循环 判断 寻找要插入的位置     说明：temp<arr[index-1]  要插入的数小于有序表的最后一个，就是没找到要继续找
		while(index>0&&temp<arr[index-1]){
			arr[index]=arr[index-1];//表示把 比 要插入的数(arr[index]) 大的数 往后移一位  arr[index]已经存入temp不会丢失
			index--;
		}
		//当循环结束，即找到了要插入的位置  index  判断index是否等于1，如果等于1，即要插入的元素的所在位置就是对的位置，不需要插入
		if(index!=2){
			arr[index]=temp;
		}
		//第三次插入
		index=3;//第一次要插入的元素下标是1
		temp=arr[index];//要插入的元素放入temp
		//循环 判断 寻找要插入的位置     说明：temp<arr[index-1]  要插入的数小于有序表的最后一个，就是没找到要继续找
		while(index>0&&temp<arr[index-1]){
			arr[index]=arr[index-1];//表示把 比 要插入的数(arr[index]) 大的数 往后移一位  arr[index]已经存入temp不会丢失
			index--;
		}
		//当循环结束，即找到了要插入的位置  index  判断index是否等于1，如果等于1，即要插入的元素的所在位置就是对的位置，不需要插入
		if(index!=3){
			arr[index]=temp;
		}
		//第四次插入
		index=4;//第一次要插入的元素下标是1
		temp=arr[index];//要插入的元素放入temp
		//循环 判断 寻找要插入的位置     说明：temp<arr[index-1]  要插入的数小于有序表的最后一个，就是没找到要继续找
		while(index>0&&temp<arr[index-1]){
			arr[index]=arr[index-1];//表示把 比 要插入的数(arr[index]) 大的数 往后移一位  arr[index]已经存入temp不会丢失
			index--;
		}
		//当循环结束，即找到了要插入的位置  index  判断index是否等于1，如果等于1，即要插入的元素的所在位置就是对的位置，不需要插入
		if(index!=4){
			arr[index]=temp;
		}
		*/
	}
}
