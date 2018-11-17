package cn.com.myproject.adminuser.controller;


import cn.com.myproject.adminuser.service.ISysUserDepartmentService;
import cn.com.myproject.adminuser.service.ISysUserRoleService;
import cn.com.myproject.adminuser.service.ISysUserService;
import cn.com.myproject.adminuser.util.Validation;
import cn.com.myproject.adminuser.vo.SysUserDepartmentVO;
import cn.com.myproject.adminuser.vo.SysUserRoleVO;
import cn.com.myproject.adminuser.vo.SysUserVO;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @param
 * @author Tianfusheng
 * @date 2018.03.30
 * @desc
 **/
@Controller
@RequestMapping("/sysUser")
public class SysUserController {
    private static final Logger logger = LoggerFactory.getLogger(SysUserController.class);

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysUserRoleService userRoleService;

    @Autowired
    private ISysUserDepartmentService sysUserDepartmentService;

    /**
     * send to userList
     *
     * @return
     */
    @RequestMapping("/")
    public String index() {
        return "sys/user";
    }

    /**
     * userRecycleBin 跳转
     *
     * @return
     */
    @RequestMapping("/BlacklistUser")
    public String BlacklistUser() {
        return "sys/userBlacklist";
    }

    /**
     * send to recycleBinUserList
     *
     * @return
     */
    @RequestMapping("/recycleBinUser")
    public String recycleBinUser() {
        return "sys/userRecycleBin";
    }

    @ResponseBody
    @RequestMapping("/list")
    public PageInfo<SysUserVO> list(Integer rows, Integer page, String keyword, String departmentId) {
        PageInfo<SysUserVO> info = sysUserService.getPage(page, rows, keyword, departmentId);
        return info;
    }

    /**
     * 黑名单用户list
     *
     * @param rows
     * @param page
     * @param keyword
     * @return PageINfo
     */
    @ResponseBody
    @RequestMapping("/userBlacklist")
    public PageInfo<SysUserVO> userBlacklist(Integer rows, Integer page, String keyword, String departmentId) {
        PageInfo<SysUserVO> info = sysUserService.userBlacklist(page, rows, keyword, departmentId);
        return info;
    }

    /**
     * 回收站用户list
     *
     * @param rows
     * @param page
     * @param keyword
     * @return
     */
    @ResponseBody
    @RequestMapping("/recycleBinUserList")
    public PageInfo<SysUserVO> recycleBinUserList(Integer rows, Integer page, String keyword, String departmentId) {
        PageInfo<SysUserVO> info = sysUserService.recycleBinUserList(page, rows, keyword, departmentId);
        return info;
    }

    @ResponseBody
    @RequestMapping("/addUsers")
    public Map<String, Object> addUsers(SysUserVO sysUser, String departmentId) {

        Map<String, Object> result = new HashMap();
        try {

            Boolean flag = Validation.isPermitName(sysUser.getLoginName());
            if (!flag) {
                result.put("status", "fail");
                result.put("message", "登录名不可含有特殊字符！");
                return result;
            }
            Integer loginNameCount = null;
            try {
                loginNameCount = sysUserService.checkUsersByLoginName(sysUser.getLoginName());
            } catch (Exception ex) {
                logger.error("[{}]-[{}]时，发生异常，异常信息为[{}]", "gumiho-w-admin-user", "checkUsersByLoginName", ex.getMessage());
            }
            if (loginNameCount == null) {
                result.put("status", "fail");
                result.put("message", "系统异常稍后重试[效验登录名失败！]");
                return result;
            }
            if (0 != loginNameCount) {
                result.put("status", "fail");
                result.put("message", "登录名已占用");
                return result;
            }
            if (!Validation.isMobile(sysUser.getPhone())) {
                result.put("status", "fail");
                result.put("message", "手机号格式不正确");
                return result;
            }
            Integer count = null;
            try {
                count = sysUserService.checkUsersByPhone(sysUser.getPhone());
            } catch (Exception ex) {
                logger.error("[{}]-[{}]时，发生异常，异常信息为[{}]", "gumiho-w-admin-user", "checkUsersByPhone", ex.getMessage());
            }
            if (count == null) {
                result.put("status", "fail");
                result.put("message", "系统异常稍后重试");
                return result;
            }
            if (0 != count) {
                result.put("status", "fail");
                result.put("message", "手机号已占用");
                return result;
            }
            sysUser.setPassword(new BCryptPasswordEncoder(4).encode("888888"));
            String uuid = UUID.randomUUID().toString().replace("-", "");
            sysUser.setUserId(uuid);
            sysUser.setCreateTime(System.currentTimeMillis());
            sysUserService.addUsers(sysUser);
            SysUserDepartmentVO sysUserDepartmentVO = new SysUserDepartmentVO();
            sysUserDepartmentVO.setUserId(uuid);
            sysUserDepartmentVO.setDepartmentId(departmentId);
            sysUserDepartmentService.addSysUserDepartment(sysUserDepartmentVO);
            result.put("status", "success");
        } catch (Exception ex) {
            logger.error("[{}]-[{}]时，发生异常，异常信息为[{}]", "gumiho-w-admin-user", "addUser", ex.getMessage());
            result.put("status", "fail");
            result.put("message", "操作失败");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/updateUser")
    public Map<String, Object> updateUsers(SysUserVO sysUser) {
        Map<String, Object> result = new HashMap<>();
        try {
            if(null != sysUser.getLoginName()){
                result.put("status", "fail");
                result.put("message", "登录名不可修改");
                return result;
            }

            if (null != sysUser.getPhone()) {
                result.put("status", "fail");
                result.put("message", "手机号不可修改");
                return result;
            }

            sysUserService.updateUsers(sysUser);
            result.put("status", "success");

        } catch (Exception e) {
            logger.error("[{}]-[{}]时，发生异常，异常信息为[{}]", "后台", "修改后台账户", e.getMessage());
            result.put("status", "fail");
            result.put("message", "修改失败");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/updateSysUserRole")
    public Map<String, Object> updateSysUserRole(String userId, String roleIds) {
        System.out.println(userId + roleIds);
        Map<String, Object> result = new HashMap<>();
        List<SysUserRoleVO> sysUserRoleVOList = new ArrayList<>();

        try {
            if (StringUtils.isNotBlank(roleIds)) {
                roleIds = roleIds.substring(roleIds.indexOf(",") + 1);
                String[] roleIdList = roleIds.split(",");
                SysUserRoleVO sysUserRoleVO = null;
                for (String roleId : roleIdList) {
                    sysUserRoleVO = new SysUserRoleVO();
                    sysUserRoleVO.setRoleId(roleId);
                    sysUserRoleVO.setUserId(userId);
                    sysUserRoleVOList.add(sysUserRoleVO);
                }
            }
            userRoleService.deleteSysUserRole(userId);
            userRoleService.batchAddSysUserRole(sysUserRoleVOList);
            result.put("status", "success");
        } catch (Exception e) {
            logger.error("[{}]-[{}]时，发生异常，异常信息为[{}]", "SysUserController", "修改后台账户", e.getMessage());
            result.put("status", "fail");
            result.put("message", "修改失败");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/updateUserPwd")
    public Map<String, Object> updateUserPwd(SysUserVO sysUser) {
        Map<String, Object> result = new HashMap<>();
        if (!Validation.isPermitPassword(sysUser.getPassword())) {
            result.put("status", "fail");
            result.put("message", "密码仅为6至16位,且必须包含英文字母和数字,不可含有汉字或特殊字符！");
            return result;
        }
        try {
            if (StringUtils.isNotBlank(sysUser.getPassword())) {
                sysUser.setPassword(new BCryptPasswordEncoder(4).encode(sysUser.getPassword()));
            }
            sysUserService.updateUsers(sysUser);
            result.put("status", "success");
        } catch (Exception e) {
            logger.error("[{}]-[{}]时，发生异常，异常信息为[{}]", "后台", "修改后台账户密码", e.getMessage());
            result.put("status", "fail");
            result.put("message", "修改失败");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/delUsers")
    public void delUsers(@RequestParam("id") Integer id) {
        sysUserService.delUsers(id);
    }

    @ResponseBody
    @RequestMapping("/selectByUserId")//添加查询userRoleList的方法
    public Map<String, Object> selectByUserId(@RequestParam("userId") String userId) {
        Map<String, Object> result = new HashMap<>();
        SysUserVO user = sysUserService.findByUserId(userId);
        result.put("user", user);
        result.put("status", "success");
        return result;
    }

    /**
     * 修改用户可用状态
     *
     * @param userId
     * @param status ，1可用，0黑名单，
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateStatus")
    public Map<String, Object> updateStatus(@RequestParam("userId") String userId, @RequestParam("status") Short status) {
        Map<String, Object> result = new HashMap<>();

        if (StringUtils.isBlank(userId) || status == null) {
            result.put("status", "error");
            result.put("message", "系统传参失败,稍后重试！");
            return result;
        }
        try {
            result = sysUserService.updateStatus(userId,status);
            return result;
        } catch (Exception e) {
            logger.error("[{}]-[{}]时，发生异常，异常信息为[{}]", "SysUserController", "updateStatus", e.getMessage());
            result.put("status", "error");
            result.put("message", "系统异常,稍后重试！");
            return result;
        }
    }

    /**
     * 修改回收站状态
     *
     * @param userId
     * @param recycleBinStatus 1为可用，0为回收站用户，2为弃用
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateRecycleBinStatus")
    public Map<String, Object> updateRecycleBinStatus(@RequestParam("userId") String userId, @RequestParam("recycleBinStatus") Short recycleBinStatus) {
        Map<String, Object> result = new HashMap<>();
        String[] userIds = userId.split(",");


        if (StringUtils.isBlank(userId) || recycleBinStatus == null) {
            result.put("status", "error");
            result.put("message", "系统传参失败,稍后重试！");
            return result;
        }
        try {
            sysUserService.updateRecycleBinStatus(userIds, recycleBinStatus);
            result.put("status", "success");
            result.put("message", "操作成功！");
        } catch (Exception e) {
            logger.error("[{}]-[{}]时，发生异常，异常信息为[{}]", "后台", "修改后台账户回收站状态失败", e.getMessage());
            result.put("status", "error");
            result.put("message", "系统异常,稍后重试！");
        }
        return result;
    }


}