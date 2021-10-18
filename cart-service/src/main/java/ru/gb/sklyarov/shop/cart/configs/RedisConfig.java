package ru.gb.sklyarov.shop.cart.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Objects;

@Configuration
public class RedisConfig {
    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private int redisPort;

    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(Objects.requireNonNull(redisHost), redisPort);
        return new JedisConnectionFactory(configuration);
    }
    /*
        By default, RedisCache and RedisTemplate are configured to use Java native serialization.
        Java native serialization is known for allowing the running of remote code caused by payloads that exploit vulnerable libraries and classes
        injecting unverified bytecode. Manipulated input could lead to unwanted code being run in the application during the deserialization step.
        As a consequence, do not use serialization in untrusted environments.
        In general, we strongly recommend any other message format (such as JSON) instead.

        If you are concerned about security vulnerabilities due to Java serialization, consider the general-purpose serialization filter mechanism
        at the core JVM level, originally developed for JDK 9 but backported to JDK 8, 7, and 6.

     */


    // Т.е получается мы как раз переопределяем template по-умолчанию, чтобы обезопасить себя? Или я не правильно понял?

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setConnectionFactory(redisConnectionFactory());
        return template;
    }
}
