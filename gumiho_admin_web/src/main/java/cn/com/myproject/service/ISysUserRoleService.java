package cn.com.myproject.service;



import cn.com.myproject.adminuser.vo.SysUserRoleVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "gumiho-admin-user",url = "${feignclient.url}")
public interface ISysUserRoleService {

    @PostMapping("/sysUserRole/addSysUserRole")
    void addSysUserRole(@RequestBody SysUserRoleVO sysUserRole);

    @PostMapping("/sysUserRole/updateSysUserRole")
    void updateSysUserRole(@RequestBody SysUserRoleVO sysUserRole);

    @PostMapping("/sysUserRole/findSysUserRoleByUserId")
    List<SysUserRoleVO> findSysUserRoleByUserId(String userId);

    @PostMapping("/sysUserRole/deleteSysUserRole")
    void deleteSysUserRole(String userId);

    @PostMapping("/sysUserRole/batchAddSysUserRole")
    void batchAddSysUserRole(List<SysUserRoleVO> sysUserRoleList);
}
