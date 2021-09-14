package zys.com.springcloud;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.JedisCluster;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private JedisCluster jedisCluster;

    @Test
    void contextLoads() {
        System.out.println(jedisCluster.get("name"));
    }

}
