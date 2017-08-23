package com.vinz.stream;

import java.util.Arrays;
import java.util.stream.IntStream;

public class IntStreamOne {

	public static void main(String[] args) {
		final int[] a;
		a = IntStream.range(0, 10).map(i -> i).toArray();
		
		final int[] b = new int[20];
		IntStream.range(0, b.length).forEach(i -> b[i] = i);
		
		final int[] oddTo100;
		oddTo100 = IntStream.rangeClosed(0, 100).filter(i -> i % 2 != 0).map(i -> i*i).limit(3).toArray();
		
		final int[] odds;
		odds = IntStream.of(1, 2, 3, 5, 6, 7, 12, 11, 23, 11, 23, 45).filter(n -> n % 2 != 0).map(i -> i).toArray();
		
		Arrays.stream(a).forEach(System.out::println);
		System.out.println("######################################");
		Arrays.stream(b).forEach(System.out::println);
		System.out.println("######################################");
		Arrays.stream(oddTo100).forEach(System.out::println);
		System.out.println("######################################");
		Arrays.stream(odds).forEach(System.out::println);
	}
}
