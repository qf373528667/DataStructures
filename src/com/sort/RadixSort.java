package com.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

//7.基数排序
public class RadixSort {

	public static void main(String[] args) {
//		int[] arr={53,3,542,748,14,214};
//		radixSort(arr);
//		System.out.println("arr="+Arrays.toString(arr));
		
		
		//测试一下冒泡排序的速度O(n^2),给80000个数据，测试
		int[] arr=new int[8000000];
		for(int i=0;i<8000000;i++){
			arr[i]=(int)(Math.random()*8000000);//生成一个[0,8000000)的随机数
		}

		Date date1=new Date();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1Str=simpleDateFormat.format(date1);
		System.out.println("排序前的时间是="+date1Str);
		//System.out.println(Arrays.toString(arr));
		
		long time1=System.currentTimeMillis();
		radixSort(arr);//基数排序   
		//基数排序    80000数据 大概所需时间  11-25ms   新电脑
		//基数排序    8000000数据 大概所需时间  412ms   新电脑
		long time2=System.currentTimeMillis();
		System.out.println("基数排序所需时间(ms)："+(time2-time1));
		Date date2=new Date();
		String date2Str=simpleDateFormat.format(date2);
		System.out.println("排序后的时间是="+date2Str);		
	}
	//基数排序方法
	//将数组中的所有数从个位开始，分离，按照分离出来的值，放到对应的数组（有10个数组下标分别是0-9）里
	//接着依次再赋给数组，
	//然后把数组的每个数的十位分离，按照分离出来的值，把整个数存到对应的数组（有10个数组下标分别是0-9）
	//接着百位，千位，...
	public static void radixSort(int[] arr){
		//首先，创建有十个数组的数组，即二维数组
		//创建十个是因为 每位数都是0-9的十个数；十个数组中 每个数组的长度是arr.length 是因为不确定每个数组中会存几个，最多也就整个arr数组的个数
		int[][] bucket=new int[10][arr.length];
		//创建一个记录十个数组的每个数组中存的数的个数 的数组，里面存了十个数，初始为0
		int[] bucketOfCounts=new int[10];
		
		//具体分离几次，需要看这个数组中最大数是几位
		int max=arr[0]; //假设第一个数是最大数，找最大数
		for(int i=1;i<arr.length;i++){
			if(arr[i]>max){
				max=arr[i];
			}
		}
		
		int length=(max+"").length();
		for(int k=0,n=1;k<length;k++,n*=10){
			for(int i=0;i<arr.length;i++){
				int index=arr[i]/n%10;
				bucket[index][bucketOfCounts[index]++]=arr[i];
			}
			//遍历之后，依次把桶里面的数 放入原数组
			int t=0;
			for(int i=0;i<10;i++){
				if(bucketOfCounts[i]!=0){
					for(int j=0;j<bucketOfCounts[i];j++){
						arr[t++]=bucket[i][j];
					}
					bucketOfCounts[i]=0;//把桶里的数放到原数组后，其对应的标记个数初始为0.为下次存放的个数做记录
				}
			}
		}
		
		/*
		//第一次分离个位，遍历数组
		for(int i=0;i<arr.length;i++){
			int index=arr[i]%10;
			bucket[index][bucketOfCounts[index]++]=arr[i];
		}
		//遍历之后，依次把桶里面的数 放入原数组
		int t=0;
		for(int i=0;i<10;i++){
			if(bucketOfCounts[i]!=0){
				for(int j=0;j<bucketOfCounts[i];j++){
					arr[t++]=bucket[i][j];
				}
				bucketOfCounts[i]=0;//把桶里的数放到原数组后，其对应的标记个数初始为0.为下次存放的个数做记录
			}
		}
		*/
	}

}
