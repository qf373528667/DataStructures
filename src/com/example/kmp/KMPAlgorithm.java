package com.example.kmp;

import java.util.Arrays;

public class KMPAlgorithm {

	public static void main(String[] args) {
		String str1="";
		String str2="";
		
		int[] next=kmpNext(str2);//[0,1,2,0]
		System.out.println("next="+Arrays.toString(next));
		int index=kmpSearch(str1,str2,next);
		System.out.println("index="+index); //15了
	}

	/**
	 * 
	 * @param str1   源字符串
	 * @param str2   子串
	 * @param next  部分匹配表，是子串对应的部分匹配表
	 * @return
	 */
	private static int kmpSearch(String str1, String str2, int[] next) {
		// TODO Auto-generated method stub
		return 0;
	}

	private static int[] kmpNext(String str2) {
		// TODO Auto-generated method stub
		return null;
	}
}
