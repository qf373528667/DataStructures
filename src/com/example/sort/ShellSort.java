package com.example.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ShellSort {

	public static void main(String[] args) {

		//		int[] arr={8,9,1,7,2,3,5,4,6,0};

		//测试一下冒泡排序的速度O(n^2),给80000个数据，测试
		int[] arr=new int[80000];
		for(int i=0;i<80000;i++){
			arr[i]=(int)(Math.random()*8000000);//生成一个[0,8000000)的随机数
		}

		Date date1=new Date();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1Str=simpleDateFormat.format(date1);
		System.out.println("排序前的时间是="+date1Str);
		long time1=System.currentTimeMillis();
		//shellSort(arr);//交换式
		shellSort2(arr);//移位式     80000数据  大概所需时间   15ms
		long time2=System.currentTimeMillis();
		System.out.println("希尔排序所需时间(ms)："+(time2-time1));
		Date date2=new Date();
		String date2Str=simpleDateFormat.format(date2);
		System.out.println("排序后的时间是="+date2Str);
		//System.out.println(Arrays.toString(arr));

	}
	//使用逐步推导的方式来编写希尔排序
	//希尔排序时，对有序序列在插入时 采用交换法
	public static void shellSort(int[] arr){
		int temp=0;
		int count=0;
		//根据前面的逐步分析，使用循环处理
		for(int gap=arr.length/2;gap>0;gap/=2){
			//希尔排序的第一轮排序
			//因为第一轮排序，是将10个数据分成了10/2=5组
			for(int i=gap;i<arr.length;i++){
				//遍历各组中所有的元素（共5组，每组有2个元素，步长5
				//遍历各组中所有的元素（共gap组，每组有length/gap个元素），步长gap
				for(int j=i-gap;j>=0;j-=gap){
					//如果当前元素大于加上步长后的那个元素，说明交换
					if(arr[j]>arr[j+gap]){
						temp=arr[j];
						arr[j]=arr[j+gap];
						arr[j+gap]=temp;
					}
				}
			}
//			System.out.println("希尔排序第"+(++count)+"轮="+Arrays.toString(arr));
		}

		/*
		//希尔排序的第一轮排序
		//因为第一轮排序，是将10个数据分成了10/2=5组
		for(int i=5;i<arr.length;i++){
			//遍历各组中所有的元素（共5组，每组有2个元素，步长5
			for(int j=i-5;j>=0;j-=5){
				//如果当前元素大于加上步长后的那个元素，说明交换
				if(arr[j]>arr[j+5]){
					temp=arr[j];
					arr[j]=arr[j+5];
					arr[j+5]=temp;
				}
			}

		}
		System.out.println("希尔排序1轮后="+Arrays.toString(arr));

		//希尔排序的第2轮排序
		//因为第2轮排序，是将10个数据分成了5/2=2组
		for(int i=2;i<arr.length;i++){
			//遍历各组中所有的元素（共5组，每组有2个元素，步长5
			for(int j=i-2;j>=0;j-=2){
				//如果当前元素大于加上步长后的那个元素，说明交换
				if(arr[j]>arr[j+2]){
					temp=arr[j];
					arr[j]=arr[j+2];
					arr[j+2]=temp;
				}
			}

		}
		System.out.println("希尔排序2轮后="+Arrays.toString(arr));

		//希尔排序的第3轮排序
		//因为第3轮排序，是将10个数据分成了2/2=1组
		for(int i=1;i<arr.length;i++){
			//遍历各组中所有的元素（共5组，每组有2个元素，步长5
			for(int j=i-1;j>=0;j-=1){
				//如果当前元素大于加上步长后的那个元素，说明交换
				if(arr[j]>arr[j+1]){
					temp=arr[j];
					arr[j]=arr[j+1];
					arr[j+1]=temp;
				}
			}

		}
		System.out.println("希尔排序3轮后="+Arrays.toString(arr));
		 */
	}
	
	//对交换式的希尔排序进行优化->移位法
	public static void shellSort2(int[] arr){
		//根据前面的逐步分析，使用循环处理
		//增量gap,并逐步的缩小增量
		for(int gap=arr.length/2;gap>0;gap/=2){
			//从第gap个元素，逐个对其所在的组进行直接插入排序
			for(int i=gap;i<arr.length;i++){
				int j=i;
				int temp=arr[i];
				if(arr[j]<arr[j-gap]){
					while(j-gap>=0&&temp<arr[j-gap]){
						//移动
						arr[j]=arr[j-gap];
						j-=gap;
					}
					//当退出while后，就给temp找到插入的位置
					arr[j]=temp;
				}
			}
		}
	}

}
