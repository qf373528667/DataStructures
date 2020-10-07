package com.example.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class SelectSort {

	public static void main(String[] args) {
//		int[] arr={101,34,119,1};
		
		//测试一下冒泡排序的速度O(n^2),给80000个数据，测试
		int[] arr=new int[80000];
		for(int i=0;i<80000;i++){
			arr[i]=(int)(Math.random()*8000000);//生成一个[0,8000000)的随机数
		}
		//System.out.println(Arrays.toString(arr));
		Date date1=new Date();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1Str=simpleDateFormat.format(date1);
		System.out.println("排序前的时间是="+date1Str);
		selectSort(arr);
		Date date2=new Date();
		String date2Str=simpleDateFormat.format(date2);
		System.out.println("排序后的时间是="+date2Str);
		//System.out.println(Arrays.toString(arr));
		
		
	}

	//选择排序
	public static void selectSort(int[] arr){
		//选择排序的时间复杂度O(n^2)
		for(int i=0;i<arr.length-1;i++){
			int minIndex=i;
			int min=arr[minIndex];
			for(int j=minIndex+1;j<arr.length;j++){
				if(min>arr[j]){//说明假定的最小值，并不是最小
					min=arr[j];//重置min
					minIndex=j;//重置minIndex
				}
			}
			//判断是否假设的最小值成了，来选择是否进行交换
			if(minIndex!=i){
				//将最小值，放在arr[i]
				arr[minIndex]=arr[i];
				arr[i]=min;
			}
		}
		
		/*
		//使用逐步推到的方式来，讲解选择排序
		//第一轮
		//原始数组：      101,34,119,1
		//第一轮排序：  1,34,119,101
		//算法  先简单--> 再复杂，就是可以把一个复杂的算法，拆分成简单的问题-->逐步解决
		int minIndex=0;
		int min=arr[minIndex];
		for(int j=minIndex+1;j<arr.length;j++){
			if(min>arr[j]){//说明假定的最小值，并不是最小
				min=arr[j];//重置min
				minIndex=j;//重置minIndex
			}
		}
		//判断是否假设的最小值成了，来选择是否进行交换
		if(minIndex!=0){
			//将最小值，放在arr[0]
			arr[minIndex]=arr[0];
			arr[0]=min;
		}
		System.out.println("第一轮后---");
		System.out.println(Arrays.toString(arr));
		
		//第二轮
		minIndex=1;
		min=arr[minIndex];
		for(int j=minIndex+1;j<arr.length;j++){
			if(min>arr[j]){//说明假定的最小值，并不是最小
				min=arr[j];//重置min
				minIndex=j;//重置minIndex
			}
		}
		//判断是否假设的最小值成了，来选择是否进行交换
		if(minIndex!=1){
			//将最小值，放在arr[0]
			arr[minIndex]=arr[1];
			arr[1]=min;
		}
		System.out.println("第一轮后---");
		System.out.println(Arrays.toString(arr));
		
		//第三轮
		minIndex=2;
		min=arr[minIndex];
		for(int j=minIndex+1;j<arr.length;j++){
			if(min>arr[j]){//说明假定的最小值，并不是最小
				min=arr[j];//重置min
				minIndex=j;//重置minIndex
			}
		}
		//判断是否假设的最小值成了，来选择是否进行交换
		if(minIndex!=2){
			//将最小值，放在arr[0]
			arr[minIndex]=arr[2];
			arr[2]=min;
		}
		System.out.println("第一轮后---");
		System.out.println(Arrays.toString(arr));
		*/
	}
}
