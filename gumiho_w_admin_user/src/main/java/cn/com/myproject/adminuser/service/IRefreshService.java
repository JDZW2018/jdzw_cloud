package cn.com.myproject.adminuser.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by liyang-macbook on 2017/8/16.
 */
@FeignClient(name = "gumiho-s-admin-user",url = "${feignclient.url}")
public interface IRefreshService {
    @GetMapping("/refresh/resource")
    String resource();
}
