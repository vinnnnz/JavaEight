package com.vinz.collect;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;

public class ImmutableCollector {

	public static <T> Collector<T, List<T>, List<T>> toImmutableList() {
        return Collector.of(ArrayList::new, List::add, (l, r) -> {
            l.addAll(r);
            return l;
        }, Collections::unmodifiableList);
    }
}
