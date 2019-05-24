package com.jbwz.core.cache.service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public interface CacheService<T> {

  List<T> getValues(String keyPrefix);

  Set<String> getKeys(String keyPrefix);

  void put(String key, Object value);

  void put(String key, Object value, long timeout);

  void put(String key, Object value, long timeout, TimeUnit timeUnit);
  
  <T> T get(String key);

  void delete(String key);

  void deleteByPrefix(String keyPrefix);
}
