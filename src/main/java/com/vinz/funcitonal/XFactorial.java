package com.vinz.funcitonal;

import java.util.function.UnaryOperator;

public class XFactorial {
	
	static UnaryOperator<Long> fac = number -> number == 0 ? 1 : number * XFactorial.fac.apply( number - 1);
	
	static Long factorial(Long number) {
		return fac.apply(number);
	}
	
	public static void main(String[] args) {
		System.out.println(factorial(35L));
	}
}
