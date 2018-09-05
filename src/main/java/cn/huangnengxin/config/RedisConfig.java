package cn.huangnengxin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

/**
 * Created by viruser on 2018/8/29.
 */
@Configuration
public class RedisConfig {

    @Bean(name = "jedisPool")
    public JedisPool jedisPool(@Value("${spring.redis.pool.host}")String host,
                               @Value("${spring.redis.pool.port}")int port){
        return new JedisPool(host, port);
    }
}
