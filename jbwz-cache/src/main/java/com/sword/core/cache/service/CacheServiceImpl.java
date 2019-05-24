package com.jbwz.core.cache.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class CacheServiceImpl<T> implements CacheService<T> {

  private static final String ALL_PATTERN = "*";
  
  @Resource
  RedisTemplate<String, Object> redisTemplate;

  @Override
  public List<T> getValues(String keyPrefix) {
    List<T> list = new ArrayList<>();
    getKeys(keyPrefix).forEach(k -> list.add(get(k)));
    return list;
  }

  @Override
  public Set<String> getKeys(String keyPrefix) {
    return redisTemplate.opsForValue().getOperations().keys(keyPrefix + ALL_PATTERN);
  }

  @Override
  public void put(String key, Object value) {
    redisTemplate.opsForValue().set(key, value);
  }

  @Override
  public void put(String key, Object value, long timeout) {
    redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
  }

  @Override
  public void put(String key, Object value, long timeout, TimeUnit timeUnit) {
    redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
  }

  @Override
  public void delete(String key) {
    redisTemplate.delete(key);
  }

  @Override
  public void deleteByPrefix(String keyPrefix) {
    redisTemplate.delete(getKeys(keyPrefix));
  }

  @Override
  public <T> T get(String key) {
    return (T) redisTemplate.opsForValue().get(key);
  }
}
