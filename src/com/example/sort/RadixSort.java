package com.example.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class RadixSort {

	public static void main(String[] args) {
//		int[] arr={53,3,542,748,14,214};
//		radixSort(arr);
		
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
		radixSort(arr);
		long time2=System.currentTimeMillis();
		
		System.out.println("快速排序所需时间(ms)："+(time2-time1));
		Date date2=new Date();
		String date2Str=simpleDateFormat.format(date2);
		System.out.println("排序后的时间是="+date2Str);
	}

	//基数排序方法
	public static void radixSort(int[] arr){
		//根据前面的推导过程，我们可以得到最终的基数排序代码
		//1.得到数组中最大的数的位数
		int max=arr[0];//假设第一个数就是最大数
		for(int i=1;i<arr.length;i++){
			if(arr[i]>max){
				max=arr[i];
			}
		}
		//得到最大数是几位数
		int maxLength=(max+"").length();
		
		//定义一个二维数组，表示10个桶，每个桶就是一个一维数组
		//说明
		//1.二维数组包含10个一维数组
		//2.为了防止在放入数的时候数据溢出，则每个一维数组（桶）的大小定为arr.length
		//3.基数排序是使用空间换时间的经典算法
		int[][] bucket=new int[10][arr.length];
		//为了记录每个桶中实际存放了多少个数据，定义一个一维数组来记录各个桶每次放入数据的个数
		//可以这样理解
		//bucketElementCounts[0] , 记录的就是bucket[0] 桶的放入数据个数
		int[] bucketElementCounts=new int[10];
		
		//这里我们使用循环将代码处理
		for(int i=0,n=1;i<maxLength;i++,n*=10){
			//第(i+1)轮（针对每个元素的个位进行排序处理）第一次是个位，第二次是十位，第三次是百位...
			for(int j=0;j<arr.length;j++){
				//取出每个元素的个位数
				int digitOfElement=arr[j]/n%10;
				//放入到对应的桶中
				bucket[digitOfElement][bucketElementCounts[digitOfElement]]=arr[j];
				bucketElementCounts[digitOfElement]++;
			}
			//按照这个桶的顺序（一维数组的下标一次取出数据，放入原来数组）
			int index=0;
			//遍历每一桶，并将桶中的数据放入到原数组
			for(int k=0;k<bucketElementCounts.length;k++){
				//如果桶中有数据，我们才放入到原数组
				if(bucketElementCounts[k]!=0){
					//循环该桶，即第k个桶（即第k个一维数组，放入
					for(int t=0;t<bucketElementCounts[k];t++){
						//取出元素放入到arr
						arr[index++]=bucket[k][t];
					}
					bucketElementCounts[k]=0;
				}
			}
			//System.out.println("第"+(i+1)+"轮，对个位的排序处理arr="+Arrays.toString(arr));
		}
		
		
		/*
		//定义一个二维数组，表示10个桶，每个桶就是一个一维数组
		//说明
		//1.二维数组包含10个一维数组
		//2.为了防止在放入数的时候数据溢出，则每个一维数组（桶）的大小定为arr.length
		//3.基数排序是使用空间换时间的经典算法
		int[][] bucket=new int[10][arr.length];
		//为了记录每个桶中实际存放了多少个数据，定义一个一维数组来记录各个桶每次放入数据的个数
		//可以这样理解
		//bucketElementCounts[0] , 记录的就是bucket[0] 桶的放入数据个数
		int[] bucketElementCounts=new int[10];
		//第一轮（针对每个元素的个位进行排序处理）
		for(int j=0;j<arr.length;j++){
			//取出每个元素的个位数
			int digitOfElement=arr[j]%10;
			//放入到对应的桶中
			bucket[digitOfElement][bucketElementCounts[digitOfElement]]=arr[j];
			bucketElementCounts[digitOfElement]++;
		}
		//按照这个桶的顺序（一维数组的下标一次取出数据，放入原来数组）
		int index=0;
		//遍历每一桶，并将桶中的数据放入到原数组
		for(int k=0;k<bucketElementCounts.length;k++){
			//如果桶中有数据，我们才放入到原数组
			if(bucketElementCounts[k]!=0){
				//循环该桶，即第k个桶（即第k个一维数组，放入
				for(int t=0;t<bucketElementCounts[k];t++){
					//取出元素放入到arr
					arr[index++]=bucket[k][t];
				}
				bucketElementCounts[k]=0;
			}
		}
		System.out.println("第一轮，对个位的排序处理arr="+Arrays.toString(arr));
		
		//第二轮（针对每个元素的个位进行排序处理）
		for(int j=0;j<arr.length;j++){
			//取出每个元素的十位数
			int digitOfElement=arr[j]/10%10;
			//放入到对应的桶中
			bucket[digitOfElement][bucketElementCounts[digitOfElement]]=arr[j];
			bucketElementCounts[digitOfElement]++;
		}
		//按照这个桶的顺序（一维数组的下标一次取出数据，放入原来数组）
		index=0;
		//遍历每一桶，并将桶中的数据放入到原数组
		for(int k=0;k<bucketElementCounts.length;k++){
			//如果桶中有数据，我们才放入到原数组
			if(bucketElementCounts[k]!=0){
				//循环该桶，即第k个桶（即第k个一维数组，放入
				for(int t=0;t<bucketElementCounts[k];t++){
					//取出元素放入到arr
					arr[index++]=bucket[k][t];
				}
				bucketElementCounts[k]=0;
			}
		}
		System.out.println("第二轮，对个位的排序处理arr="+Arrays.toString(arr));
	
		//第三轮（针对每个元素的个位进行排序处理）
		for(int j=0;j<arr.length;j++){
			//取出每个元素的百位数
			int digitOfElement=arr[j]/100%10;
			//放入到对应的桶中
			bucket[digitOfElement][bucketElementCounts[digitOfElement]]=arr[j];
			bucketElementCounts[digitOfElement]++;
		}
		//按照这个桶的顺序（一维数组的下标一次取出数据，放入原来数组）
		index=0;
		//遍历每一桶，并将桶中的数据放入到原数组
		for(int k=0;k<bucketElementCounts.length;k++){
			//如果桶中有数据，我们才放入到原数组
			if(bucketElementCounts[k]!=0){
				//循环该桶，即第k个桶（即第k个一维数组，放入
				for(int t=0;t<bucketElementCounts[k];t++){
					//取出元素放入到arr
					arr[index++]=bucket[k][t];
				}
				bucketElementCounts[k]=0;
			}
		}
		System.out.println("第三轮，对个位的排序处理arr="+Arrays.toString(arr));
		*/
	}
}
