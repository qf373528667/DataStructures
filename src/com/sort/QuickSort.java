package com.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
//6.快速排序
public class QuickSort {

	public static void main(String[] args) {
//		int[] arr={-9,78,0,23,-567,70};
//		quickSort(arr,0,5);
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
		quickSort(arr,0,arr.length-1);//快速排序    80000数据 大概所需时间  9ms
		//快速排序    80000数据 大概所需时间  12-29ms   新电脑
		//快速排序    8000000数据 大概所需时间  1035ms   新电脑
		long time2=System.currentTimeMillis();
		System.out.println("快速排序所需时间(ms)："+(time2-time1));
		Date date2=new Date();
		String date2Str=simpleDateFormat.format(date2);
		System.out.println("排序后的时间是="+date2Str);
		//System.out.println(Arrays.toString(arr));
	}
	
	public static void quickSort(int[] arr,int left,int right){
		int l=left;
		int r=right;
		int pivot=arr[(l+r)/2];
		int temp=0;
		//进行循环 保证pivot左边的都比pivot的值小，pivot右边的都比pivot的值大
		while(l<r){
			while(arr[l]<pivot){
				l+=1;
			}
			while(arr[r]>pivot){
				r-=1;
			}
			//判断是不是结束
			if(l>=r){
				break;
			}
			//交换
			temp=arr[l];
			arr[l]=arr[r];
			arr[r]=temp;
			//如果 交换后，arr[l]==pivot 那么就把r-- 往前移一位 交换过的没必要再判断
			if(arr[l]==pivot){
				r-=1;
			}
			//如果 交换后，arr[r]==pivot 那么就把l++ 往后移一位 交换过的没必要再判断
			if(arr[r]==pivot){
				l+=1;
			}
			
		}
		//循环结束后 进行了一次左右排
		if(l==r){
			l+=1;
			r-=1;
		}
		//使用递归进行 左快排 
		if(r>left){
			quickSort(arr, left, r);
		}
		//使用递归进行 右快排
		if(l<right){
			quickSort(arr, l, right);
		}
		
	}

}
