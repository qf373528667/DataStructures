package com.example.recursion;

public class Queue8 {
	int count=0;
	//定义一个max表示共有多少个皇后
	int max=8;
	//定义数组array，保存皇后位置的结果，比如arr={0,4,7,5,2,6,1,3}
	int[] array=new int[max];
	public static void main(String[] args) {
		//测试，8皇后是否正确
		Queue8 queue8=new Queue8();
		queue8.check(0);
		System.out.println("一共有多少种解法："+queue8.count);
		
	}
	
	//编写一个方法，放置第n个皇后
	//特别注意：check 是每一次递归时，进入到check都有for循环  是获取到所有可能的情况
	private void check(int n){
		if(n==max){   //n=8,时其实8个皇后已经放好了
			print();
			return;
		}
		//依次放入皇后，并判断是否冲突
		for(int i=0;i<max;i++){
			//先把当前这个皇后n，放到改行的第一列
			array[n]=i;
			//判断当放置第n个皇后到i列时，是否冲突
			if(judge(n)){  //返回true  表示不冲突
				//接着放n+1个皇后，即开始递归
				check(n+1);
			}//如果冲突，就继续循环，执行array[n]=i，去试下一个位置
		}
	}

	//查看当我们放置第n个皇后，检测该皇后是否和前面已经放置的皇后冲突（不能在同一行、不能在同一列、不能在同一斜线）
	/**
	 * 
	 * @param n 表示第n个皇后
	 * @return
	 */
	private boolean judge(int n){
		for(int i=0;i<n;i++){
			//说明
			//1. array[i]==array[n]  表示判断 第n个皇后是否和前面的 第i个皇后在同一列
			//2. Math.abs(n-i)==Math.abs(array[n]-array[i])  表示判断第n个皇后是否和第i个皇后在同一斜线
			//3. 判断同一行不需要了，不同的下标表示不同的行
			if(array[i]==array[n]||Math.abs(n-i)==Math.abs(array[n]-array[i])){
				return false;
			}
		}
		return true;
	}
	//写一个方法，可以将皇后摆放的位置输出
	private void print(){
		count++;
		for(int i=0;i<array.length;i++){
			System.out.print(array[i]+" ");
		}
		System.out.println();
	}
	
}
