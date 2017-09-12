package com.vinz.funcitonal;

import java.math.BigInteger;
import java.util.function.UnaryOperator;

public class XFactorial {
	
	static UnaryOperator<Long> fac = number -> number == 0 ? 1 : number * XFactorial.fac.apply( number - 1);
	static UnaryOperator<BigInteger> bigfac = number -> number.compareTo(BigInteger.ZERO) == 0 ? BigInteger.ONE : number.multiply(XFactorial.bigfac.apply(number.subtract(BigInteger.ONE)));
	
	static Long factorial(Long number) {
		return fac.apply(number);
	}
	
	static BigInteger factorial(BigInteger number) {
		return bigfac.apply(number);
	}
	
	
	
	public static void main(String[] args) {
		System.out.println(factorial(35L));
		System.out.println(factorial(BigInteger.valueOf(100)));
	}
}
