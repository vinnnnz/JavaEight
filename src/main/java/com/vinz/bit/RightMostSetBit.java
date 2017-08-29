package com.vinz.bit;

public class RightMostSetBit {
	public static int find(int n) {
		return (int) (1 + Math.log(n & ~(n-1))/Math.log(2));
	}
	
	public static int findUnset(int n) {
		if(n == 1) return -1;
		return (int) (Math.log(~n & (n+1))/Math.log(2));
	}
	
	 public static void main(String[] args) {
	        int n = 11;
	        System.out.println(find(n));
	        System.out.println(findUnset(n));
	    }
}
