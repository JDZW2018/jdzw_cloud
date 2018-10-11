package cn.com.myproject.adminuser.service;


import cn.com.myproject.adminuser.service.fallback.SysUserServiceFallbackFactory;
import cn.com.myproject.adminuser.vo.SysUserVO;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Created by liyang-macbook on 2017/7/26.
 */
@FeignClient(name = "gumiho-s-admin-user", url = "${feignclient.url}", fallbackFactory = SysUserServiceFallbackFactory.class)
public interface ISysUserService {


    @GetMapping("/sysUser/getByLoginName")
    SysUserVO getByLoginName(@RequestParam("loginName") String loginName);

    @GetMapping("/sysUser/getByUserName")
    SysUserVO getByUserName(@RequestParam("userName") String userName);

    @GetMapping("/sysUser/getPage")
    PageInfo<SysUserVO> getPage(@RequestParam("pageNum") int pageNum,
                                @RequestParam("pageSize") int pageSize,
                                @RequestParam("keyword") String keyword,
                                @RequestParam("departmentId") String departmentId);

    /**
     * 分页查询黑名单用户
     *
     * @param pageNum
     * @param pageSize
     * @param keyword
     * @return
     */
    @GetMapping("/sysUser/userBlacklist")
    PageInfo<SysUserVO> userBlacklist(@RequestParam("pageNum") int pageNum,
                                      @RequestParam("pageSize") int pageSize,
                                      @RequestParam("keyword") String keyword,
                                      @RequestParam("departmentId") String departmentId);

    /**
     * 分页查询回收站用户
     *
     * @param pageNum
     * @param pageSize
     * @param keyword
     * @return
     */
    @GetMapping("/sysUser/recycleBinUserList")
    PageInfo<SysUserVO> recycleBinUserList(@RequestParam("pageNum") int pageNum,
                                           @RequestParam("pageSize") int pageSize,
                                           @RequestParam("keyword") String keyword,
                                           @RequestParam("departmentId") String departmentId);

    @PostMapping("/sysUser/addUser")
    void addUsers(@RequestBody SysUserVO sysUserVO);


    @PostMapping("/sysUser/updateUser")
    void updateUsers(@RequestBody SysUserVO sysUserVO);

    @PostMapping("/sysUser/delUsers")
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

    /**
     * get userVO By Phone
     *
     * @param phone
     * @return
     */
    @GetMapping("/sysUser/getByPhone")
    SysUserVO getByPhone(@RequestParam("phone") String phone);

    /**
     * checkUsersByPhone
     *
     * @param phone
     * @return
     */
    @GetMapping("/sysUser/checkUsersByPhone")
    Integer checkUsersByPhone(@RequestParam("phone") String phone);

    /**
     * checkUsersByLoginName
     *
     * @param loginName
     * @return
     */
    @GetMapping("/sysUser/checkUsersByLoginName")
    Integer checkUsersByLoginName(@RequestParam("loginName") String loginName);

    /**
     * 批量删除回收站用户，更改状态非真删除
     *
     * @param userIds
     * @param recycleBinStatus
     */
    @PostMapping("/sysUser/updateRecycleBinStatus")
    void updateRecycleBinStatus(@RequestBody String[] userIds, @RequestParam("recycleBinStatus") Short recycleBinStatus);

    /**
     * updateStatus
     * @param userId
     * @param status
     * @return
     */
    @PostMapping("/sysUser/updateStatus")
    Map<String, Object> updateStatus(@RequestParam("userId") String userId, @RequestParam("status") Short status);
}