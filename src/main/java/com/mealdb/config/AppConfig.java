package com.mealdb.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.github.benmanes.caffeine.cache.Caffeine;

@Configuration
public class AppConfig {
	public static final String EXP_CACHE = "ExpCache";

	@Value("${cache.exp.max-size}")
	private long cacheMaxSize;

	@Value("${cache.exp.ttl-hours}")
	private int cacheTtlHours;

	@Bean
	public CacheManager cacheManager() {
		CaffeineCacheManager cacheManager = new CaffeineCacheManager();

		// Configure Caffeine for LRU and TTL
		Caffeine<Object, Object> caffeineBuilder = Caffeine.newBuilder().maximumSize(cacheMaxSize) // Max size: 50
				.expireAfterWrite(Duration.ofHours(cacheTtlHours)); // TTL: 24 hours

		cacheManager.setCaffeine(caffeineBuilder);
		cacheManager.setCacheNames(java.util.Collections.singletonList(EXP_CACHE));

		return cacheManager;
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
