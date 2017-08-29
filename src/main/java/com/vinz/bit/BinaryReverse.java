package com.vinz.bit;

public class BinaryReverse {

	public static int reverse(int number) {
		
		int result = 0;
		while(number > 0) {
			result = result << 1;
			result = result | (number & 1);
			number = number >> 1;
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(reverse(4));
	}
}
