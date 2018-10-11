package cn.com.myproject.adminuser.service;


import cn.com.myproject.adminuser.service.fallback.SysRoleServiceFallbackFactory;
import cn.com.myproject.adminuser.vo.SysRoleVO;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 *
 */
@FeignClient(name = "gumiho-s-admin-user", url = "${feignclient.url}",fallbackFactory = SysRoleServiceFallbackFactory.class)
public interface ISysRoleService {

    @GetMapping("/sysRole/getPage")
    PageInfo<SysRoleVO> getPage(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize, @RequestParam("keyword") String keyword, @RequestParam("departmentId") String departmentId);

    @PostMapping("/sysRole/addRoles")
    void addRoles(@RequestBody SysRoleVO sysRoleVO);

    @PostMapping("/sysRole/updateRoles")
    void updateRoles(@RequestBody SysRoleVO sysRoleVO);

    @PostMapping("/sysRole/checkRoles")
    Integer checkRoles(String roleName);

    @GetMapping("/sysRole/delRoles")
    void delRoles(Integer id);

    @PostMapping("/sysRole/selectRoles")
    SysRoleVO selectRoles(Integer id);

    @GetMapping("/sysRole/getRole")
    List<SysRoleVO> getRole();

    @GetMapping("/sysRole/selectRolesByDepartmentId")
    List<SysRoleVO> selectRolesByDepartmentId(@RequestParam("departmentId") String departmentId);
}


