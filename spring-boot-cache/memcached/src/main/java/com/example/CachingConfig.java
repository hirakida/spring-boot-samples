package com.example;

import java.util.Collections;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.code.ssm.CacheFactory;
import com.google.code.ssm.config.DefaultAddressProvider;
import com.google.code.ssm.providers.CacheConfiguration;
import com.google.code.ssm.providers.spymemcached.MemcacheClientFactoryImpl;
import com.google.code.ssm.spring.SSMCache;
import com.google.code.ssm.spring.SSMCacheManager;

@EnableCaching
@Configuration
public class CachingConfig extends CachingConfigurerSupport {

    public static final String CACHE_NAME = "default";
    private static final String ADDRESS = "127.0.0.1:11211";
    private static final int EXPIRATION = 60;   // sec

    @Bean
    @Override
    public CacheManager cacheManager() {
        SSMCacheManager ssmCacheManager = new SSMCacheManager();
        try {
            SSMCache ssmCache = new SSMCache(cacheFactory().getObject(), EXPIRATION, true);
            ssmCacheManager.setCaches(Collections.singletonList(ssmCache));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return ssmCacheManager;
    }

    @Bean
    public CacheFactory cacheFactory() {
        CacheFactory cacheFactory = new CacheFactory();
        cacheFactory.setCacheName(CACHE_NAME);
        cacheFactory.setCacheClientFactory(new MemcacheClientFactoryImpl());
        cacheFactory.setAddressProvider(new DefaultAddressProvider(ADDRESS));
        CacheConfiguration cacheConfig = new CacheConfiguration();
        cacheConfig.setConsistentHashing(true);
        cacheConfig.setUseBinaryProtocol(true);
        cacheFactory.setConfiguration(cacheConfig);
        return cacheFactory;
    }
}
