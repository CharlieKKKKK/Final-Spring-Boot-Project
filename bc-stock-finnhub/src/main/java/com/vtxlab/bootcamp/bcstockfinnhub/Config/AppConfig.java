package com.vtxlab.bootcamp.bcstockfinnhub.Config;

//import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vtxlab.bootcamp.bcstockfinnhub.infra.RedisHelper;

// Library (dependency) or your own classess -> object (bean) -> Spring context
@Configuration
public class AppConfig {

  @Bean
  RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Bean
  RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory,
      ObjectMapper objectMapper) {
    RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(factory);
    redisTemplate.setKeySerializer(RedisSerializer.string());
    redisTemplate.setValueSerializer(RedisSerializer.json());
    redisTemplate.afterPropertiesSet();
    // Jackson2JsonRedisSerializer<Object> serializer =
    // new Jackson2JsonRedisSerializer<>(objectMapper, Object.class);
    // redisTemplate.setValueSerializer(serializer);
    return redisTemplate;
  }

  @Bean
  RedisHelper redisHelper(RedisConnectionFactory factory,
      ObjectMapper objectMapper) {
    return new RedisHelper(factory, objectMapper);
  }

  @Bean
  ObjectMapper ObjectMapper() {
    return new ObjectMapper();
  }

    @Bean
  RedisTemplate<String, Object> defaultRedisTemplate(
      RedisConnectionFactory factory, ObjectMapper redisObjectMapper) {
    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(factory);
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.setValueSerializer(
        new GenericJackson2JsonRedisSerializer(redisObjectMapper));
    redisTemplate.afterPropertiesSet();
    return redisTemplate;
  }

  }


