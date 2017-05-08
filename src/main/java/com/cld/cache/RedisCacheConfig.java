package com.cld.cache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.lang.reflect.Method;

/**
 * 缓存配置类
 * Created by szb on 2017/4/25.
 */
@Configuration
@EnableCaching //启用缓存，这个注解很重要；
public class RedisCacheConfig{


    /**
     * 缓存管理器
     * @param redisTemplate
     * @return
     */
    @Bean
    public CacheManager redisCacheManager(RedisTemplate<?,?> redisTemplate){
        CacheManager  cacheManager= new RedisCacheManager(redisTemplate);
        return cacheManager;
    }

    /**
     * RedisTemplate缓存操作类
     * @param factory
     * @return
     */
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String,String> redisTemplate = new RedisTemplate<String, String>();
        redisTemplate.setConnectionFactory(factory);
        return redisTemplate;
    }

    /**
     * 自定义缓存数据 key 生成策略
     * @return
     */
    @Bean
    public KeyGenerator customKeyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object o, Method method, Object... objects) {
                StringBuilder sb = new StringBuilder();
                sb.append(o.getClass().getName());
                sb.append(method.getName());
                for (Object obj : objects) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }


}
