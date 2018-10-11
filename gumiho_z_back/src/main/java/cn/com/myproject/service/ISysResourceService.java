package cn.com.myproject.service;


import cn.com.myproject.adminuser.vo.MenuVO;
import cn.com.myproject.adminuser.vo.SysResourceVO;
import cn.com.myproject.service.fallback.SysResourceServiceFallbackFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedHashSet;

/**
 * @author Tianfusheng
 * @date 2018/4/11 18:50
 **/
@FeignClient(value = "gumiho-s-admin-user",url = "${feignclient.url}",fallbackFactory = SysResourceServiceFallbackFactory.class)
public interface ISysResourceService {
    /**
     * getMenu
     * @param menuId
     * @param loginName
     * @param str
     * @return
     */
    @GetMapping("/sysResource/getMenu")
    LinkedHashSet<MenuVO> getMenu(@RequestParam("menuId") String menuId, @RequestParam("loginName") String loginName, @RequestParam("str") String str);

    /**
     * getSysResourceByResourceId
     * @param resourceId
     * @return
     */
    @PostMapping("/sysResource/getSysResourceByResourceId")
    SysResourceVO getSysResourceByResourceId(@RequestBody String resourceId);




}


