package com.vinz.bit;

import java.util.Arrays;

public class MissingDuplicate {

	public static int find(int arr[]) {
		
		return Arrays.stream(arr).reduce(0, (a, b)-> a ^ b);
	}
	
	public static void main(String[] args) {
		int[] A = { 2, 1, 3, 5, 5, 3, 2, 1, 9, 7, 7, 8, 8 };
		System.out.println("Missing duplicate is " + find(A));

	}
}