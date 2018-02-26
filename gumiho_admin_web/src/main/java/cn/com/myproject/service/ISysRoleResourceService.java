package cn.com.myproject.service;

import cn.com.myproject.adminuser.vo.SysRoleResourceVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="gumiho-admin-user",url = "${feignclient.url}")
public interface ISysRoleResourceService {

    @PostMapping("/sysRoleResource/getSysRoleResource")
    List<SysRoleResourceVO> getSysRoleResource(@RequestBody SysRoleResourceVO sysRoleResourceVO);

    @PostMapping("/sysRoleResource/getListByResourceIds")
    List<SysRoleResourceVO> getListByResourceIds(@RequestBody String resourceIds);

    @PostMapping("/sysRoleResource/batchInsert")
    void batchInsert(@RequestBody List<SysRoleResourceVO> sysRoleResourceVOS);

    @PostMapping("/sysRoleResource/batchDelete")
    void batchDelete(@RequestBody List<SysRoleResourceVO> sysRoleResourceVOS);

    @GetMapping("/sysRoleResource/batchEditSysRoleResource")
    boolean batchEditSysRoleResource(@RequestParam("resourceIds") String resourceIds, @RequestParam("roleId") String roleId);

}


