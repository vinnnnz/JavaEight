package com.vinz.concurrency.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

/**
 * Content taken from -> http://winterbe.com
 * @author vineet_k Lock Implementations => ReentrantLock : The class
 *         ReentrantLock is a mutual exclusion lock with the same basic behavior
 *         as the implicit monitors accessed via the synchronized keyword but
 *         with extended capabilities. As the name suggests this lock implements
 *         reentrant characteristics just as implicit monitors.
 * 
 *         => ReadWriteLock : The interface ReadWriteLock specifies another type
 *         of lock maintaining a pair of locks for read and write access. The
 *         idea behind read-write locks is that it's usually safe to read
 *         mutable variables concurrently as long as nobody is writing to this
 *         variable. So the read-lock can be held simultaneously by multiple
 *         threads as long as no threads hold the write-lock. This can improve
 *         performance and throughput in case that reads are more frequent than
 *         writes.
 * 
 *         => StampedLock : Java 8 ships with a new kind of lock called
 *         StampedLock which also support read and write locks just like in the
 *         example above. In contrast to ReadWriteLock the locking methods of a
 *         StampedLock return a stamp represented by a long value. You can use
 *         these stamps to either release a lock or to check if the lock is
 *         still valid. Additionally stamped locks support another lock mode
 *         called optimistic locking.
 */
public class LockOne {

	ReentrantLock lock = new ReentrantLock();
	int count = 0;

	void increment() {
		lock.lock();
		try {
			count++;
		} finally {
			lock.unlock();
		}
	}

	void submit() {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		ReentrantLock lock = new ReentrantLock();

		executor.submit(() -> {
			lock.lock();
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		});

		executor.submit(() -> {
			System.out.println("Locked: " + lock.isLocked());
			System.out.println("Held by me: " + lock.isHeldByCurrentThread());
			boolean locked = lock.tryLock();
			System.out.println("Lock acquired: " + locked);
		});
	}

	void readWriteLock() {

		ExecutorService executor = Executors.newFixedThreadPool(2);
		Map<String, String> map = new HashMap<>();
		ReadWriteLock lock = new ReentrantReadWriteLock();

		executor.submit(() -> {
			lock.writeLock().lock();
			try {
				TimeUnit.SECONDS.sleep(1);
				map.put("foo", "bar");
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.writeLock().unlock();
			}
		});

		Runnable readTask = () -> {
			lock.readLock().lock();
			try {
				System.out.println(map.get("foo"));
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.readLock().unlock();
			}
		};

		executor.submit(readTask);
		executor.submit(readTask);
	}
	
	void stampedLock() {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		Map<String, String> map = new HashMap<>();
		StampedLock lock = new StampedLock();
		
		executor.submit(() -> {
		    long stamp = lock.writeLock();
		    try {
		        TimeUnit.SECONDS.sleep(1);
		        map.put("foo", "bar");
		    } catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
		        lock.unlockWrite(stamp);
		    }
		});
		
		Runnable readTask = () -> {
		    long stamp = lock.readLock();
		    try {
		        System.out.println(map.get("foo"));
		        TimeUnit.SECONDS.sleep(1);
		    } catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
		        lock.unlockRead(stamp);
		    }
		};

		executor.submit(readTask);
		executor.submit(readTask);
	}
	
	void optimisticLock() {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		StampedLock lock = new StampedLock();

		executor.submit(() -> {
		    long stamp = lock.tryOptimisticRead();
		    try {
		        System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
		        TimeUnit.SECONDS.sleep(1);
		        System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
		        TimeUnit.SECONDS.sleep(1);
		        System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
		    } catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
		        lock.unlock(stamp);
		    }
		});

		executor.submit(() -> {
		    long stamp = lock.writeLock();
		    try {
		        System.out.println("Write Lock acquired");
		        TimeUnit.SECONDS.sleep(1);
		    } catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
		        lock.unlock(stamp);
		        System.out.println("Write done");
		    }
		});
	}

	public static void main(String[] args) {
		new LockOne().optimisticLock();
	}
}
