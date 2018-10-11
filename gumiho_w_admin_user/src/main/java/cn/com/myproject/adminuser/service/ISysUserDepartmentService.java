package cn.com.myproject.adminuser.service;

import cn.com.myproject.adminuser.service.fallback.SysUserDepartmentServiceFallbackFactory;
import cn.com.myproject.adminuser.vo.SysUserDepartmentVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author tianfusheng
 * @date 2018/3/15
 */
@FeignClient(name = "gumiho-s-admin-user",url = "${feignclient.url}",fallbackFactory = SysUserDepartmentServiceFallbackFactory.class)
public interface ISysUserDepartmentService {
    @PostMapping("/sysUserDepartment/addSysUserDepartment")
    void addSysUserDepartment(@RequestBody SysUserDepartmentVO sysUserDepartmentVO);

    @PostMapping("/sysUserDepartment/deleteByUserId")
    void deleteByUserId(@RequestBody String userId);
}
