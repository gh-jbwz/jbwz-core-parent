package com.jbwz.core.cache;

import java.util.Set;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@SpringBootApplication
@ComponentScan("com.sponge")
public class CacheServiceTest {

  @Autowired
  RedisTemplate<String, UserTest> redisTemplate;
  @Autowired
  StringRedisTemplate stringRedisTemplate;

  String keyAuthcBase = "test:authc:key";

  @Test
  public void put() {
    for (int i = 0; i < 6; i++) {
      int num = RandomUtils.nextInt(1, 999);
      String usKey = keyAuthcBase + num;
      UserTest userTest = new UserTest("test", num);
      redisTemplate.opsForValue().set(usKey, userTest);
    }
    getKeys();
  }

  @Test
  public void testPutOneRewrite() {
    int num = RandomUtils.nextInt(1, 999);
    UserTest userTest = new UserTest("test", num);
    System.out.println(userTest);
    redisTemplate.opsForValue().set("test-rewrite", userTest);
    System.out.println(redisTemplate.opsForValue().get("test-rewrite"));
  }

  @Test
  public void getKeys() {
    Set<String> keys = redisTemplate.opsForValue().getOperations().keys(keyAuthcBase + "*");
    System.out.printf("authc keys =%s\n", keys);
    keys.forEach(
        s -> System.out.printf(" key =%s,value=%s\n", s, redisTemplate.opsForValue().get(s)));
  }

  @Test
  public void delete() {
    Set<String> keys = redisTemplate.opsForValue().getOperations().keys(keyAuthcBase + "*");
    redisTemplate.delete(keys);
  }
}