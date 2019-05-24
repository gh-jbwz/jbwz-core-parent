package com.jbwz.core.cache.config;

import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
public class Config {

    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @Bean
    public RedisTemplate redisTemplate() {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        FastJsonRedisSerializer fastJsonRedisSerializer = new FastJsonRedisSerializer(Object.class);
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        //序列化时设置添加类名称
        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteClassName);
        //反序列化时设置自动匹配Object类型,这个是设置全局的反序列化是自动匹配
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        fastJsonRedisSerializer.setFastJsonConfig(fastJsonConfig);
        redisTemplate.setDefaultSerializer(fastJsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }


    @Bean
    public StringRedisTemplate stringRedisTemplate() {
        return new StringRedisTemplate(redisConnectionFactory);
    }

//  /**
//   * redis连接工厂
//   * @return
//   */
//  @Bean
//  public LettuceConnectionFactory redisConnectionFactory() {
//      return new LettuceConnectionFactory(new RedisStandaloneConfiguration("192.168.2.188", 6379));
//  }


//  @Bean
//  public RedisConnectionFactory redisConnectionFactory() {
//    Map<String, Object> source = new HashMap<String, Object>();
//    source.put("spring.redis.cluster.nodes", environment.getProperty("spring.redis.cluster.nodes"));
//    source.put("spring.redis.cluster.timeout",
//        environment.getProperty("spring.redis.cluster.timeout"));
//    source.put("spring.redis.cluster.max-redirects",
//        environment.getProperty("spring.redis.cluster.max-redirects"));
//    RedisClusterConfiguration redisClusterConfiguration;
//    redisClusterConfiguration =
//        new RedisClusterConfiguration(new MapPropertySource("RedisClusterConfiguration", source));
//    return new LettuceConnectionFactory(redisClusterConfiguration);
//  }

}
