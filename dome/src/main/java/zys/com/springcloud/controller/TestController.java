package zys.com.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisCluster;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private JedisCluster jedisCluster;

    @GetMapping("/get")
    public String test1(){
        return jedisCluster.get("name");
    }


}
