package com.vinz.bit;

import java.util.Arrays;

public class BitCombination {
	static int[] arr;
	
	BitCombination(int n) {
		arr = new int[n];
	}

	public void nBits(int n) {
		if (n <= 0) {
			System.out.println(Arrays.toString(arr));
		} else {
			arr[n - 1] = 0;
			nBits(n - 1);
			arr[n - 1] = 1;
			nBits(n - 1);
		}
	}

	public static void main(String[] args) throws java.lang.Exception {
		int n = 10;
		BitCombination i = new BitCombination(n);
		i.nBits(n);
	}
}
