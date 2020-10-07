package com.example.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class QuickSort {

	public static void main(String[] args) {

		//		int[] arr={-9,78,0,23,-567,70};
		//		System.out.println("arr="+Arrays.toString(arr));
		//		quickSort(arr, 0, arr.length-1);
		//		System.out.println("arr="+Arrays.toString(arr));


		//测试一下冒泡排序的速度O(n^2),给80000个数据，测试
		int[] arr=new int[80000];
		for(int i=0;i<80000;i++){
			arr[i]=(int)(Math.random()*8000000);//生成一个[0,8000000)的随机数
		}

		Date date1=new Date();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1Str=simpleDateFormat.format(date1);
		System.out.println("排序前的时间是="+date1Str);
		//System.out.println(Arrays.toString(arr));

		long time1=System.currentTimeMillis();
		quickSort(arr, 0, arr.length-1);//快速排序    80000数据 大概所需时间  9ms
		long time2=System.currentTimeMillis();
		System.out.println("快速排序所需时间(ms)："+(time2-time1));
		Date date2=new Date();
		String date2Str=simpleDateFormat.format(date2);
		System.out.println("排序后的时间是="+date2Str);
		//System.out.println(Arrays.toString(arr));
	}
	public static void quickSort(int[] arr,int left,int right){
		int l=left;//左下标
		int r=right;//右下标
		//pivot 中轴值
		int pivot=arr[(l+r)/2];
		int temp=0; //临时变量，作为交换时使用
		//while循环的目的是让比pivot值小的 放到左边 比pivot值大的 放到右边
		while(l<r){
			//在pivot的左边一直找，找到大于等于pivot值，才退出
			while(arr[l]<pivot){
				l+=1;
			}
			//在pivot的右边一直找，找到小于等于pivot值，才退出
			while(arr[r]>pivot){
				r-=1;
			}
			//如果l>=r说明pivot的左右两边的值，已经按照左边全部是小于等于pivot的值，右边全部是大于等于pivot的值
			if(l>=r){
				break;
			}
			//交换
			temp=arr[l];
			arr[l]=arr[r];
			arr[r]=temp;
			//如果交换完后，发现这个arr[l]==pivot值   r--前移一下
			if(arr[l]==pivot){
				r-=1;
			}
			//如果交换完后，发现这个arr[r]==pivot值   l++后移一下
			if(arr[r]==pivot){
				l+=1;
			}
		}
		//如果l==r,必须l++,r--，否则会出现栈溢出
		if(l==r){
			l+=1;
			r-=1;
		}
		//向左递归
		if(left<r){
			quickSort(arr, left, r);
		}
		//向左递归
		if(right>l){
			quickSort(arr, l, right);
		}
	}

}
