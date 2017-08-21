package com.vinz.collect;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;

import org.junit.Test;

public class CollectorsOneTest {

	@Test
	public void testOf() {
		assertNotNull(CollectorsOne.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
	}

	@Test
	public void testOfx() {
		assertNotNull(CollectorsOne.ofx(1, 2, 3, 4, 5, 6, 7, 8, 9));
	}

	@Test
	public void testRangeInt() {
		assertNotNull(CollectorsOne.rangeInt(1, 100));
		assertEquals(CollectorsOne.rangeInt(1, 100).size(), 99);
	}
	
	@Test
	public void testAverageInt() {
		assertEquals(CollectorsOne.averageInt(Arrays.asList(1, 2, 3)), Double.valueOf(2));
	}

}
