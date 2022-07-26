package com.cbf.brasileiraoApi.config;

import com.cbf.brasileiraoApi.helpers.RedisKeyGeneratorHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableConfigurationProperties({RedisProperties.class})
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {
    @Value("${spring.redis.ttl}")
    private Long ttl;
    public static final String CACHE_NAME = "configurationsFilterEvent";
    private final RedisProperties redisProperties;
    @Autowired
    public RedisConfig(final RedisProperties redisProperties) {
        this.redisProperties = redisProperties;
    }
    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        final RedisStandaloneConfiguration redisConf = new RedisStandaloneConfiguration();
        redisConf.setHostName(redisProperties.getHost());
        redisConf.setPort(redisProperties.getPort());
        redisConf.setPassword(RedisPassword.of(redisProperties.getPassword()));
        redisConf.setDatabase(redisProperties.getDatabase());
        return new JedisConnectionFactory(redisConf);
    }
    @Bean
    public JedisPoolConfig jedisPool() {
        final JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        final RedisProperties.Pool pool = redisProperties.getJedis().getPool();
        jedisPoolConfig.setMaxIdle(pool.getMaxIdle());
        jedisPoolConfig.setMaxWait(Duration.ofMillis(pool.getMaxWait().toMillis()));
        jedisPoolConfig.setMaxTotal(pool.getMaxActive());
        jedisPoolConfig.setMinIdle(pool.getMinIdle());
        return jedisPoolConfig;
    }
    @Bean
    public Map<String, RedisCacheConfiguration> cacheConfigurations() {
        final Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();
        final RedisCacheConfiguration redisCacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                .disableCachingNullValues()
                .entryTtl(Duration.ofMillis(ttl));
        cacheConfigurations.put(CACHE_NAME, redisCacheConfig);
        return cacheConfigurations;
    }
    @Bean(name = "customCacheManager")
    @Override
    public RedisCacheManager cacheManager() {
        return RedisCacheManager.builder(redisConnectionFactory())
                .withInitialCacheConfigurations(cacheConfigurations())
                .build();
    }
    @Bean(name = "customKeyGenerator")
    @Override
    public KeyGenerator keyGenerator() {
        return (o, method, objects) ->
                RedisKeyGeneratorHelper.keyGenerator(o.getClass().getSimpleName(), method.getName(), objects);
    }
}