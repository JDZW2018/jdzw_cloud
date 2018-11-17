package cn.com.myproject.adminuser.service;

import cn.com.myproject.adminuser.service.fallback.SysRoleDepartmentServiceFallbackFactory;
import cn.com.myproject.adminuser.vo.SysRoleDepartmentVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author tianfusheng
 * @date 2018/3/14
 */
@FeignClient(name = "gumiho-s-admin-user",url = "${feignclient.url}",fallbackFactory = SysRoleDepartmentServiceFallbackFactory.class)
public interface ISysRoleDepartmentService {
    @PostMapping("/SysRoleDepartment/addSysRoleDepartment")
    void addSysRoleDepartment(@RequestBody SysRoleDepartmentVO sysRoleDepartmentVO);

    @PostMapping("/SysRoleDepartment/deleteByRoleId")
    void deleteByRoleId(@RequestBody String roleId);
}
