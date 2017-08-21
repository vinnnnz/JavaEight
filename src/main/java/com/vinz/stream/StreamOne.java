package com.vinz.stream;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StreamOne {

	public static void main(String[] args) {
		System.out.println(Arrays.asList("Apple", "Banana", "Cat", "Cow", "Comb").stream().filter(s -> s.startsWith("C")).map(String::toUpperCase).collect(Collectors.toList()));
	}
}
