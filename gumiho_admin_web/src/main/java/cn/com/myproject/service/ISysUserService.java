package cn.com.myproject.service;


import cn.com.myproject.adminuser.vo.SysUserVO;
import cn.com.myproject.service.fallback.SysUserServiceFallbackFactory;
import com.github.pagehelper.PageInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by liyang-macbook on 2017/7/26.
 */
@FeignClient(name = "gumiho-admin-user",url = "${feignclient.url}",fallbackFactory = SysUserServiceFallbackFactory.class)
public interface ISysUserService {


    @GetMapping("/sysUser/getByLoginName")
    SysUserVO getByLoginName(@RequestParam("loginName") String loginName);

    @GetMapping("/sysUser/getByUserName")
    SysUserVO getByUserName(@RequestParam("userName") String userName);

    @GetMapping("/sysUser/getPage")
    PageInfo<SysUserVO> getPage(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize, @RequestParam("keyword") String keyword);

    @PostMapping("/sysUser/addUser")
    void addUsers(@RequestBody SysUserVO sysUserVO);


    @PostMapping("/sysUser/updateUser")
    void updateUsers(@RequestBody SysUserVO sysUserVO);

    @PostMapping("/sysUser/delusers")
    void delUsers(@RequestBody Integer id);

    @GetMapping("/sysUser/findByUserId")
    SysUserVO findByUserId(@RequestParam("userId") String userId);


    @GetMapping("/sysUser/checkUser")
    Integer checkUsers(@RequestParam("userName") String userName);

    @PostMapping("/sysUser/selectUser")
    SysUserVO selectUsers(Integer id);

    @GetMapping("/sysUser/getSysUserByLoginName")
    SysUserVO getSysUserByLoginName(@RequestParam("loginName") String loginName);

    @GetMapping("/sysUser/getSysUserByUserName")
    SysUserVO getSysUserByUserName(@RequestParam("userName") String userName);


}