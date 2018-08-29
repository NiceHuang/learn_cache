package cn.huangnengxin.controller;

import cn.huangnengxin.common.core.User;
import cn.huangnengxin.service.impl.DataPortralUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by viruser on 2018/8/29.
 */

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private DataPortralUserServiceImpl dataPortralUserService;
    @Autowired
    private JedisPool jedisPool;

    @RequestMapping(value = "/get_user")
    public List<User> getUser(){
        String cacheKey = "test";
        Map<String, Object> map = new HashMap<>();
        map.put("a", "b");
        List<User> users = dataPortralUserService.fetchUser(cacheKey, map);
        return users;
    }

    @RequestMapping(value = "/redis")
    public String redis(){
        Jedis jedis = jedisPool.getResource();
        String resp = jedis.get("foo");
        return resp;
    }
}
