package cn.com.myproject.adminuser.service;

import cn.com.myproject.adminuser.service.fallback.SysRoleResourceServiceFallbackFactory;
import cn.com.myproject.adminuser.vo.SysRoleResourceVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="gumiho-s-admin-user",url = "${feignclient.url}",fallbackFactory = SysRoleResourceServiceFallbackFactory.class)
public interface ISysRoleResourceService {

    @PostMapping("/sysRoleResource/getSysRoleResource")
    List<SysRoleResourceVO> getSysRoleResource(@RequestBody SysRoleResourceVO sysRoleResourceVO);

    @PostMapping("/sysRoleResource/getListByResourceIds")
    List<SysRoleResourceVO> getListByResourceIds(@RequestBody String resourceIds);

    @PostMapping("/sysRoleResource/batchInsert")
    void batchInsert(@RequestBody List<SysRoleResourceVO> sysRoleResourceVOS);

    @PostMapping("/sysRoleResource/batchDelete")
    void batchDelete(@RequestBody List<SysRoleResourceVO> sysRoleResourceVOS);

    @PostMapping("/sysRoleResource/batchEditSysRoleResource")
    boolean batchEditSysRoleResource(@RequestBody String resourceIds, @RequestParam("roleId") String roleId);

}


