package com.sort;

import java.util.Arrays;

//4.希尔排序
//希尔排序交换式  80000数据  大概需要7.8秒（7831毫秒）     shellSort(int[] arr)
//希尔排序插入式  80000数据  大概需要0.015秒（15）             shellSort2(int[] arr)
public class ShellSort {

	public static void main(String[] args) {
//		int [] arr={8,9,1,7,2,3,5,4,6,0};
//		System.out.println("排序前的数据---"+Arrays.toString(arr));
//		shellSort(arr);
//		System.out.println("排序后的数据---"+Arrays.toString(arr));
		
		
//		int[] arr=new int[80000];
//		for(int i=0;i<80000;i++){
//			arr[i]=(int)(Math.random()*8000000);
//		}
//		long time1=System.currentTimeMillis();
//		System.out.println("选择排序前---"+time1);
//		
//		shellSort(arr);  //希尔排序交换式  80000数据  需要大概7.8秒（7831毫秒）
//		
//		long time2=System.currentTimeMillis();
//		System.out.println("选择排序所需时间："+(time2-time1));//80000数据     希尔排序交换式 7.8秒（7831毫秒）
//		System.out.println("选择排序后---"+time2);
		
		int[] arr=new int[80000];
		for(int i=0;i<80000;i++){
			arr[i]=(int)(Math.random()*8000000);
		}
		long time1=System.currentTimeMillis();
		System.out.println("选择排序前---"+time1);
		//System.out.println("排序前的数据---"+Arrays.toString(arr));
		
		shellSort2(arr);  //希尔排序插入式  80000数据  大概需要0.015秒（15）
		//希尔排序插入式  80000数据  大概需要0.015秒（15）    新电脑也是
		long time2=System.currentTimeMillis();
		System.out.println("选择排序所需时间："+(time2-time1));//80000数据     希尔排序插入式 大概需要0.015秒（15）
		System.out.println("选择排序后---"+time2);
		//System.out.println("排序后的数据---"+Arrays.toString(arr));
	}
	//希尔排序是在插入排序的基础上进行的优化排序。    在插入排序中，在一些情况 （较小的数比较靠后，那么在插入时，需要移位的次数比较多，降低了排序效率
	//希尔排序时，对有序序列在插入时 采用交换法
	public static void shellSort(int[] arr){
		for(int tag=arr.length/2;tag>0;tag/=2){
			int temp=0;
			for(int i=0;i<(arr.length-tag);i++){
				for(int j=i;j>=0;j-=tag){
					if(arr[j]>arr[j+tag]){
						temp=arr[j];
						arr[j]=arr[j+tag];
						arr[j+tag]=temp;
					}
				}
			}
		}
		
		/*
		int temp=0;
		//第一轮 把数分成arr.length/2=10/2=5组，每组2个数  （每组只需比较一次） 需要5组次
		for(int i=0;i<arr.length/2;i++){
			for(int j=0+i;j>=0;j-=arr.length/2){
				if(arr[j]>arr[j+arr.length/2]){
					temp=arr[j];
					arr[j]=arr[j+arr.length/2];
					arr[j+arr.length/2]=temp;
				}
			}
		}
		System.out.println("==="+Arrays.toString(arr));
		
		//第二轮 把数分成arr.length/2/2=10/2/2=5/2=2组，每组5个数  （每组需要4轮排序）  需要两组次
		for(int i=0;i<8;i++){
			for(int j=0+i;j>=0;j-=arr.length/2/2){
				if(arr[j]>arr[j+arr.length/2/2]){
					temp=arr[j];
					arr[j]=arr[j+arr.length/2/2];
					arr[j+arr.length/2/2]=temp;
				}
			}
		}
		System.out.println("==="+Arrays.toString(arr));
		
		//第三轮 把数分成arr.length/2/2/2=10/2/2/2=5/2/2=2/2=1组，每组10个数 （每组需要9轮排序）  一组
		for(int i=0;i<9;i++){
			for(int j=0+i;j>=0;j-=arr.length/2/2/2){
				if(arr[j]>arr[j+arr.length/2/2/2]){
					temp=arr[j];
					arr[j]=arr[j+arr.length/2/2/2];
					arr[j+arr.length/2/2/2]=temp;
				}
			}
		}
		System.out.println("==="+Arrays.toString(arr));
		*/
	}
	
	//希尔排序是在插入排序的基础上进行的优化排序。    在插入排序中，在一些情况 （较小的数比较靠后，那么在插入时，需要移位的次数比较多，降低了排序效率
	//希尔排序时，对有序序列在插入时 采用插入式
	public static void shellSort2(int[] arr){
		for(int tag=arr.length/2;tag>0;tag/=2){
			//第一轮 把数分成arr.length/2=10/2=5组，每组2个数  （每组只需比较一次） 需要5组次
			for(int i=0;i<arr.length-tag;i++){
				int val=arr[i+tag];
				int valIndex=i+tag;
				while(valIndex-tag>=0&&val<arr[valIndex-tag]){
					arr[valIndex]=arr[valIndex-tag];
					valIndex-=tag;
				}
				if(valIndex!=i+tag){
					arr[valIndex]=val;
				}
				
			}
		}
		/*
		//第一轮 把数分成arr.length/2=10/2=5组，每组2个数  （每组只需比较一次） 需要5组次
		for(int i=0;i<arr.length/2;i++){
			int val=arr[i+5];
			int valIndex=i+5;
			while(valIndex>=0&&val<arr[valIndex-5]){
				arr[valIndex]=arr[valIndex-5];
				valIndex-=5;
			}
			if(valIndex!=i+5){
				arr[valIndex]=val;
			}
			
		}
		*/
	}
}
