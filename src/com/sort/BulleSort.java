package com.sort;

import java.util.Arrays;
//1.冒泡排序
public class BulleSort {

	public static void main(String[] args) {

		//int [] arr={89,23,47,5,9};
		int[] arr=new int[80000];
		for(int i=0;i<80000;i++){
			arr[i]=(int)(Math.random()*8000000);
		}
		long time1=System.currentTimeMillis();
		System.out.println("冒泡排序前---");
//		System.out.println(Arrays.toString(arr));
		bulleSort(arr);
		long time2=System.currentTimeMillis();
		System.out.println(time2-time1);//80000数据     冒泡排序大概需要7.7秒（7759）
		System.out.println("冒泡排序后---");
//		System.out.println(Arrays.toString(arr));
	}
	
	public static void bulleSort(int[] arr){
		int temp=0;
		boolean flag=false;
		for(int i=0;i<arr.length-1;i++){
			//第一轮冒泡排序
			for(int j=0;j<arr.length-1-i;j++){
				if(arr[j]>arr[j+1]){
					flag=true;
					temp=arr[j];
					arr[j]=arr[j+1];
					arr[j+1]=temp;
				}
			}
			if(!flag){
				break;
			}else{
				flag=false;
			}
		}
		/*
		//第一轮冒泡排序
		for(int j=0;j<arr.length-1-0;j++){
			if(arr[j]>arr[j+1]){
				temp=arr[j];
				arr[j]=arr[j+1];
				arr[j+1]=temp;
			}
		}
		//第二轮冒泡排序
		for(int j=0;j<arr.length-1-1;j++){
			if(arr[j]>arr[j+1]){
				temp=arr[j];
				arr[j]=arr[j+1];
				arr[j+1]=temp;
			}
		}*/
	}

}
