package com.example.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class MergetSort {

	public static void main(String[] args) {

//		int[] arr={8,4,5,7,1,3,6,2};
//		int temp[]=new int[arr.length];//归并排序需要一个额外空间
//		mergeSort(arr, 0, arr.length-1, temp);
//		System.out.println("归并排序后="+Arrays.toString(arr));
		
		//测试一下冒泡排序的速度O(n^2),给80000个数据，测试
		int[] arr=new int[80000];
		for(int i=0;i<80000;i++){
			arr[i]=(int)(Math.random()*8000000);//生成一个[0,8000000)的随机数
		}
		int temp[]=new int[arr.length];//归并排序需要一个额外空间

		Date date1=new Date();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1Str=simpleDateFormat.format(date1);
		System.out.println("排序前的时间是="+date1Str);
		//System.out.println(Arrays.toString(arr));

		long time1=System.currentTimeMillis();
		mergeSort(arr,0,arr.length-1,temp);//快速排序    80000数据 大概所需时间  14ms
		long time2=System.currentTimeMillis();
		System.out.println("归并排序所需时间(ms)："+(time2-time1));
		Date date2=new Date();
		String date2Str=simpleDateFormat.format(date2);
		System.out.println("排序后的时间是="+date2Str);
		//System.out.println(Arrays.toString(arr));
	}
	//分+合方法
	public static void mergeSort(int[] arr,int left,int right,int[] temp){
		if(left<right){
			int mid=(left+right)/2;//中间索引
			//向左递归进行分解
			mergeSort(arr,left,mid,temp);
			//向右递归分解
			mergeSort(arr,mid+1,right,temp);
			//合并
			merge(arr, left, mid, right, temp);
		}
	}
	/**
	 * 合并方法
	 * @param arr     需要排序的数组
	 * @param left    左边有序序列的初始索引
	 * @param min     中间索引
	 * @param right   右边索引
	 * @param temp    中转数组
	 */
	public static void merge(int[] arr,int left,int mid,int right,int[] temp){
		//System.out.println("xxxx");
		int i=left;    //i 左边有序序列的初始索引
		int j=mid+1;   //j 右边有序序列的初始索引
		int t=0;       //t 指向temp数组的当前索引
		
		//（一）先把左右两边（有序）的数据按照规则填充到temp数组，直到左右两边的有序序列，有任意一边处理完毕
		while(i<=mid&&j<=right){   //继续
			//如果左边的有序序列的当前元素小于等于右边有序序列的当前元素
			//即将左边的当前元素拷贝到temp数组中
			if(arr[i]<=arr[j]){
				temp[t++]=arr[i++];
			}else{ //反之，将右边有序序列的当前元素，填充到temp数组
				temp[t++]=arr[j++];
			}
		}
		//（二）把有剩余的一边的 数据拷贝到temp中
		while(i<=mid){  //左边的有序序列还有剩余的元素，就全部填充到temp
			temp[t++]=arr[i++];
		}
		while(j<=right){ //右边的有序序列还有剩余的元素，就全部填充到temp
			temp[t++]=arr[j++];
		}
		//（三）将temp数组的元素拷贝到arr
		//注意：并不是每次都拷贝所有
		t=0;
		int tempLeft=left; //
		//System.out.println("tempLeft="+tempLeft+",right="+right);
		while(tempLeft<=right){  //第一次合并 tempLeft=0,right=1  //第二次 tempLeft=2,right=3
			arr[tempLeft++]=temp[t++];
		}
		
	}

}
