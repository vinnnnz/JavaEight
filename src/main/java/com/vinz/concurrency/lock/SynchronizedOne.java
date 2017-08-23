package com.vinz.concurrency.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
/**
 * Content taken from -> http://winterbe.com
 * @author vineet_k
 *
 */
public class SynchronizedOne {
	private int count = 0;

	public void inc() {
		count = count + 1;
	}

	public synchronized void incSynch() {
		count = count + 1;
	}

	public void incSynchBlock() {
		synchronized (this) {
			count = count + 1;
		}
	}

	public void submit() throws InterruptedException {
		ExecutorService service = Executors.newFixedThreadPool(10);
		// IntStream.range(0, 1000).forEach(n -> service.submit(this::inc));
		IntStream.range(0, 1000).forEach(n -> service.submit(this::incSynch));
		IntStream.range(0, 1000).forEach(n -> service.submit(this::incSynchBlock));
		service.awaitTermination(3, TimeUnit.SECONDS);

		System.out.println(count);
	}

	public static void main(String[] args) throws InterruptedException {

		new SynchronizedOne().submit();
	}
}
