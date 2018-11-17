package cn.com.myproject.adminuser.externalinterface;

import cn.com.myproject.adminuser.service.ISecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liyang-macbook on 2017/8/16.
 */
@RestController
@RequestMapping("/refresh")
public class RefreshProvide {

    @Autowired
    private ISecurityService securityService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/resource")
    public String resource() {
        securityService.init();
        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        connection.select(0);
        connection.del(new StringRedisSerializer().serialize("cn.com.myproject.cn.com.myproject.adminuser.mapper.SysResourceMapper"));
        connection.close();
        return "success";
    }
}
