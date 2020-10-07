package com.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

//5.归并排序
public class MergeSort2 {

	public static void main(String[] args) {
//		int[] arr={8,4,5,7,1,3,6,2};
//		System.out.println("原始数组元素："+Arrays.toString(arr));
//		int[] temp=new int[arr.length];
//		mergeSort(arr, 0, arr.length-1, temp);
//		System.out.println("排序后数组为："+Arrays.toString(arr));
		
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
		
		int[] temp=new int[arr.length];
		long time1=System.currentTimeMillis();
		mergeSort(arr,0,arr.length-1,temp);//归并排序    80000数据 大概所需时间  9ms
		//归并排序   80000数据 大概所需时间  10ms   新电脑
		//归并排序    8000000数据 大概所需时间  1127ms   新电脑
		long time2=System.currentTimeMillis();
		System.out.println("归并排序所需时间(ms)："+(time2-time1));
		Date date2=new Date();
		String date2Str=simpleDateFormat.format(date2);
		System.out.println("排序后的时间是="+date2Str);
		//System.out.println(Arrays.toString(arr));
		
	}

	/**
	 * 分+合算法           分：将数组一步步等分成左右两部分，直到每个部分只有一个数据（即要满足left<right才分）。合：使用递归；在每步先左右等分，再合并排序。 
	 * @param arr     要排序的数组
	 * @param left    排序的起始位置
	 * @param right   排序的结束位置
	 * @param temp    临时中间数组
	 */
	public static void mergeSort(int[] arr,int left,int right,int[] temp){
		if(left<right){//只要满足left<right就继续左右等分拆分
			int mid=(left+right)/2;
			//使用递归继续把左边等分
			mergeSort(arr,left,mid,temp);
			//使用递归继续把右边等分
			mergeSort(arr,mid+1,right,temp);
			//左右等分完成后，即左右两边也已经是有序的了，等分到最后每个部分里只有一个数，也就是可以看成有序的，接下来调用方法将两部分合并成一个有序的
			merge(arr,left,mid,right,temp);
		}
	}
	/**
	 * 合并算法    将两个有序数组（其实是一个数组arr的两部分，一部分从left到mid [left,mid]，一部分从mid+1到right [mid+1,right]）合并成一个有序数组。
	 * @param arr     要排序的数组
	 * @param left    从哪里开始排序，起始下标
	 * @param mid     中间下标
	 * @param right   排序的结束下标
	 * @param temp    临时存放数组
	 */
	public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
		int i=left;     //第一个数组的起始索引
		int j=mid+1;    //第二个数组的起始索引下标
		int t=0;        //临时数组存放的下标
		//(一)将两个有序数组的数据有序放入temp临时数组里面
		while(i<=mid&&j<=right){
			if(arr[i]<=arr[j]){
				temp[t++]=arr[i++];
			}else{
				temp[t++]=arr[j++];
			}
		}
		//(二)将某个有剩余的数组 的剩余数放入temp中
		while(i<=mid){
			temp[t++]=arr[i++];
		}
		while(j<=right){
			temp[t++]=arr[j++];
		}
		//(三)将temp数组中的数据放入arr中
		t=0;
		while(left<=right){
			arr[left++]=temp[t++];
		}
		
	}
}
