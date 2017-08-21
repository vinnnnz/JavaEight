package com.vinz.collect;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public abstract class CollectorsOne {
	
	@SafeVarargs
	public static <T> List<T> of(T... values) {
		return Stream.of(values).collect(ImmutableCollector.toImmutableList());
	}
	
	@SafeVarargs
	public static <T> List<T> ofx(T... values) {
		return Stream.of(values).collect( Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
	}
	
	public static List<Integer> rangeInt(int low, int high) {
		return IntStream.range(low, high).boxed().collect(ImmutableCollector.toImmutableList());
	}
	
	public static Double averageInt(List<Integer> list) {
		return list.stream().collect(Collectors.averagingInt(n -> Integer.valueOf(n)));
	}
}
