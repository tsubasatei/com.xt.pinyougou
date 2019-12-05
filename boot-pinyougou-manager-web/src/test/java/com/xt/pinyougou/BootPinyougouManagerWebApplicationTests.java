package com.xt.pinyougou;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Arrays;

@SpringBootTest
class BootPinyougouManagerWebApplicationTests {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Test
    void contextLoads() {
//        Object content = redisTemplate.opsForValue().get("content::1");
//        System.out.println(content);
//
//        BoundValueOperations<Object, Object> value = redisTemplate.boundValueOps("content::1");
//        System.out.println(value.get());


        Boolean delete = redisTemplate.delete("content::1");
        System.out.println(delete);
    }

}
