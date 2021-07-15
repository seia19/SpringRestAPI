package com.eia.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;

@Configuration
@EnableCaching
public class CacheConfig {

	/** Gestor de Caché de Spring*/
	@Bean
	public CacheManager getCacheManager() {
		
		//creando un mapa concurrente del caché
		return new ConcurrentMapCacheManager("users"); // users --> UserService getUserByName @Cacheable(value = "users")
	}
}
