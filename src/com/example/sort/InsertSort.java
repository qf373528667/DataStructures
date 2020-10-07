package com.example.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class InsertSort {

	public static void main(String[] args) {

		//int[] arr={101,34,119,1};
		
		//测试一下冒泡排序的速度O(n^2),给80000个数据，测试
		int[] arr=new int[80000];
		for(int i=0;i<80000;i++){
			arr[i]=(int)(Math.random()*8000000);//生成一个[0,8000000)的随机数
		}
		
		Date date1=new Date();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1Str=simpleDateFormat.format(date1);
		System.out.println("排序前的时间是="+date1Str);
		
		insertSort(arr);
		
		Date date2=new Date();
		String date2Str=simpleDateFormat.format(date2);
		System.out.println("排序后的时间是="+date2Str);
	}

	//插入排序
	public static void insertSort(int[] arr){
		int insertVal=0;
		int insertIndex=0;
		//使用for循环整理代码
		for(int i=1;i<arr.length;i++){
			//一个有序表，一个无序表，遍历无序表从无序表往有序表里插入
			//第一轮{101,34,119,1}-->{34,101,119,1}   {101}有序表  {34,119,1}无序表  第一轮把34插入有序表
			//定义待插入的数
			insertVal=arr[i];
			insertIndex=i-1;//即arr[1]的前面这个数的下标
			//给insertVal 找到要插入的位置
			//说明
			//1.insertIndex>=0 保证在给insertVal 找插入位置，不越界
			//2.insertVal<arr[insertIndex] 待插入的数insertVal，还没有找到要插入的位置
			//3.就需要将arr[insertIndex] 后移
			while(insertIndex>=0&&insertVal<arr[insertIndex]){
				arr[insertIndex+1]=arr[insertIndex];//数组变成{101,101,119,1}，第二个数已经保存
				insertIndex--;
			}
			//当退出while循环时，说明插入的位置找到，insertIndex+1
			//这里我们判断是否需要赋值
			if(insertIndex+1!=i){
				arr[insertIndex+1]=insertVal;
			}	
//			System.out.println("第"+i+"轮插入");
//			System.out.println(Arrays.toString(arr));
		}
		
		/*
		//使用逐步推到的方式来讲解，便于理解
		//一个有序表，一个无序表，遍历无序表从无序表往有序表里插入
		//第一轮{101,34,119,1}-->{34,101,119,1}   {101}有序表  {34,119,1}无序表  第一轮把34插入有序表
		//定义待插入的数
		int insertVal=arr[1];
		int insertIndex=1-1;//即arr[1]的前面这个数的下标
		//给insertVal 找到要插入的位置
		//说明
		//1.insertIndex>=0 保证在给insertVal 找插入位置，不越界
		//2.insertVal<arr[insertIndex] 待插入的数insertVal，还没有找到要插入的位置
		//3.就需要将arr[insertIndex] 后移
		while(insertIndex>=0&&insertVal<arr[insertIndex]){
			arr[insertIndex+1]=arr[insertIndex];//数组变成{101,101,119,1}，第二个数已经保存
			insertIndex--;
		}
		//当退出while循环时，说明插入的位置找到，insertIndex+1
		arr[insertIndex+1]=insertVal;
		System.out.println("第一轮插入");
		System.out.println(Arrays.toString(arr));
		
		//第二轮
		insertVal=arr[2];
		insertIndex=2-1;//即arr[1]的前面这个数的下标
		//给insertVal 找到要插入的位置
		//说明
		//1.insertIndex>=0 保证在给insertVal 找插入位置，不越界
		//2.insertVal<arr[insertIndex] 待插入的数insertVal，还没有找到要插入的位置
		//3.就需要将arr[insertIndex] 后移
		while(insertIndex>=0&&insertVal<arr[insertIndex]){
			arr[insertIndex+1]=arr[insertIndex];//数组变成{101,101,119,1}，第二个数已经保存
			insertIndex--;
		}
		//当退出while循环时，说明插入的位置找到，insertIndex+1
		arr[insertIndex+1]=insertVal;
		System.out.println("第二轮插入");
		System.out.println(Arrays.toString(arr));
		
		//第三轮
		insertVal=arr[3];
		insertIndex=3-1;//即arr[1]的前面这个数的下标
		//给insertVal 找到要插入的位置
		//说明
		//1.insertIndex>=0 保证在给insertVal 找插入位置，不越界
		//2.insertVal<arr[insertIndex] 待插入的数insertVal，还没有找到要插入的位置
		//3.就需要将arr[insertIndex] 后移
		while(insertIndex>=0&&insertVal<arr[insertIndex]){
			arr[insertIndex+1]=arr[insertIndex];//数组变成{101,101,119,1}，第二个数已经保存
			insertIndex--;
		}
		//当退出while循环时，说明插入的位置找到，insertIndex+1
		arr[insertIndex+1]=insertVal;
		System.out.println("第三轮插入");
		System.out.println(Arrays.toString(arr));
		*/
	}
}
