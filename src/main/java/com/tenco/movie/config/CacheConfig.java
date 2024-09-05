package com.tenco.movie.config;

import java.util.concurrent.TimeUnit;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Caffeine;

@Configuration
@EnableCaching
public class CacheConfig {
	
	@Bean
	public CaffeineCacheManager cacheManager() {
		CaffeineCacheManager cacheManager = new CaffeineCacheManager();
		cacheManager.setCaffeine(Caffeine.newBuilder()
				.expireAfterWrite(30, TimeUnit.DAYS) // 캐시 만료 시간
				.maximumSize(100)); // 캐시 최대 크기
		return cacheManager;
	}
}
