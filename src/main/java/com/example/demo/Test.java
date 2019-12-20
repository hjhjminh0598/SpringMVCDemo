package com.example.demo;

import java.util.concurrent.TimeUnit;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

public class Test {

	public static void main(String[] args) throws InterruptedException {
		Cache<Integer, Integer> cache = Caffeine.newBuilder().expireAfterWrite(2, TimeUnit.SECONDS).maximumSize(100)
				.build();
		cache.put(1, 10);
		cache.put(2, 20);
		System.out.println(cache.getIfPresent(1) + " " + cache.getIfPresent(2) + " " + cache.estimatedSize());
		TimeUnit.SECONDS.sleep(3);
		System.out.println(cache.getIfPresent(1) + " " + cache.getIfPresent(2) + " " + cache.estimatedSize());
	}
}
