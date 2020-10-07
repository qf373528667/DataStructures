package com.sort;

import java.util.Arrays;

//2.选择排序
public class SelectSort {

	public static void main(String[] args) {
//		int [] arr={89,23,47,5,9};
//		System.out.println("排序前的数据---"+Arrays.toString(arr));
		
		int[] arr=new int[80000];
		for(int i=0;i<80000;i++){
			arr[i]=(int)(Math.random()*8000000);
		}
		long time1=System.currentTimeMillis();
		System.out.println("选择排序前---"+time1);
		
		selectSort(arr);
		
		long time2=System.currentTimeMillis();
		System.out.println("选择排序所需时间："+(time2-time1));//80000数据     选择排序大概需要0.7秒（698）
		System.out.println("选择排序后---"+time2);
		
//		System.out.println("排序后的数据---"+Arrays.toString(arr));
	}
	
	public static void selectSort(int[] arr){
		
		for(int j=0;j<arr.length-1;j++){
			//假定第一位的最小
			int min=arr[j];
			int index=j;//并记录下标
			//循环
			for(int i=index+1;i<arr.length;i++){
				if(min>arr[i]){//如果后面的数比假定的数小，就赋值给min，并记录下标
					min=arr[i];
					index=i;
				}
			}
			if(index!=j){
				//当循环结束，就找到最小的数了，并记录了下标，进行交换
				arr[index]=arr[j];
				arr[j]=min;
			}
		}
		/*
		//第一轮把最小的数与第一位的数交换，即放在第一位
		//假定第一位的最小
		int min=arr[0];
		int index=0;//并记录下标
		//循环
		for(int i=index+1;i<arr.length;i++){
			if(min>arr[i]){//如果后面的数比假定的数小，就赋值给min，并记录下标
				min=arr[i];
				index=i;
			}
		}
		//当循环结束，就找到最小的数了，并记录了下标，进行交换
		arr[index]=arr[0];
		arr[0]=min;
		
		//第二轮把第二小的数与第二位的数交换，即放在第二位
		min=arr[1];//假定第二个第二小
		index=1;//并记录下标
		//循环
		for(int i=index+1;i<arr.length;i++){
			if(min>arr[i]){//如果后面的数比假定的数小，就赋值给min，并记录下标
				min=arr[i];
				index=i;
			}
		}
		//当循环结束，就找到最小的数了，并记录了下标，进行交换
		arr[index]=arr[1];
		arr[1]=min;
		*/
	}

}
