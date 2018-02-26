package cn.com.myproject.adminuser.controller;


import cn.com.myproject.adminuser.vo.SysUserRoleVO;
import cn.com.myproject.adminuser.vo.SysUserVO;
import cn.com.myproject.service.ISysUserRoleService;
import cn.com.myproject.service.ISysUserService;
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

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liyang-macbook on 2017/7/11.
 */
@Controller
@RequestMapping("/sysUser")
public class SysUserController {
    private static final Logger logger = LoggerFactory.getLogger(SysUserController.class);

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysUserRoleService userRoleService;

    @RequestMapping("/")
    public String index() {
        return "sys/user";
    }

    @RequestMapping("/add")
    public String add() {
       return "sys/user_add";
    }

    @RequestMapping("/getUser")
    public String getUser(String userId,HttpServletRequest request,SysUserVO model){
        request.setAttribute("user", sysUserService.findByUserId(userId));
        return "/editUser";
    }

    @ResponseBody
    @RequestMapping("/list")
    public PageInfo<SysUserVO> list(Integer rows, Integer page, String keyword) {
        PageInfo<SysUserVO> info = sysUserService.getPage(page,rows,keyword);
        return info;
    }


    @ResponseBody
    @RequestMapping("/addUsers")
    public Map<String,Object> addUsers(SysUserVO sysUser,String roleIds,String linkUserId_add) {

        Map<String,Object> result = new HashMap();
        try {
            SysUserVO sysUserVO = sysUserService.getSysUserByUserName(sysUser.getUserName());
            if(null != sysUserVO){
                result.put("status","fail");
                result.put("message","用户名已存在");
                return result;
            }
            sysUserVO = sysUserService.getSysUserByLoginName(sysUser.getLoginName());
            if(null != sysUserVO){
                result.put("status","fail");
                result.put("message","登录名已存在");
                return result;
            }
            List<SysUserRoleVO> sysUserRoleList = new ArrayList<>();
            if(StringUtils.isNotBlank(roleIds)){
                roleIds = roleIds.substring(roleIds.indexOf(",")+1);
                String[] roleIdList = roleIds.split(",");
                for(String roleId : roleIdList){
                    SysUserRoleVO sysUserRole = new SysUserRoleVO();
                    sysUserRole.setRoleId(roleId);
                    sysUserRoleList.add(sysUserRole);
                }
            }
            sysUser.setSysUserRoleVOList(sysUserRoleList);
            sysUser.setPassword(new BCryptPasswordEncoder(4).encode("888888"));
            sysUser.setCreateTime(System.currentTimeMillis());
            sysUserService.addUsers(sysUser);
            result.put("status","success");
        } catch (Exception ex) {
            logger.error("[{}]-[{}]时，发生异常，异常信息为[{}]","后台","添加后台账户",ex.getMessage());
            result.put("status","fail");
            result.put("message","操作失败");
        }
        return result;
    }

    public int checkUsers(String userName){
        int result = 0;
        try {
            Integer info = sysUserService.checkUsers(userName);
            if (null != info && info.intValue()>0) {
                result = 1;
            }
        }catch (Exception ex){
            logger.error("[{}]-[{}]时，发生异常，异常信息为[{}]","后台","添加后台账户，检验用户名",ex.getMessage());
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/updateUser")
    public Map<String,Object> updateUsers(SysUserVO sysUser,String roleIds,String linkUserId_update) {
        Map<String,Object> result = new HashMap<>();
        try {
            SysUserVO dataSysUser =  sysUserService.findByUserId(sysUser.getUserId());

            SysUserVO sysUserVO = sysUserService.getSysUserByUserName(sysUser.getUserName());

            if(null != sysUserVO && !(sysUserVO.getUserId().equals(dataSysUser.getUserId())) && sysUserVO.getUserName().equals(sysUser.getUserName())){
                result.put("status","fail");
                result.put("message","用户名已存在");
                return result;
            }

            sysUserVO = sysUserService.getSysUserByLoginName(sysUser.getLoginName());
            if(null != sysUserVO && !(sysUserVO.getUserId().equals(dataSysUser.getUserId())) && sysUserVO.getLoginName().equals(sysUser.getLoginName())){
                result.put("status","fail");
                result.put("message","登录名已存在");
                return result;
            }

            List<SysUserRoleVO> sysUserRoleList = new ArrayList<>();
            if(StringUtils.isNotBlank(roleIds)){
                roleIds = roleIds.substring(roleIds.indexOf(",")+1);
                String[] roleIdList = roleIds.split(",");
                for(String roleId : roleIdList){
                    SysUserRoleVO sysUserRole = new SysUserRoleVO();
                    sysUserRole.setRoleId(roleId);
                    sysUserRoleList.add(sysUserRole);
                }
            }
            sysUser.setSysUserRoleVOList(sysUserRoleList);
            sysUserService.updateUsers(sysUser);
            result.put("status","success");

        }catch (Exception e){
            logger.error("[{}]-[{}]时，发生异常，异常信息为[{}]","后台","修改后台账户",e.getMessage());
            result.put("status","fail");
            result.put("message","修改失败");
        }
        return result;
    }


    @ResponseBody
    @RequestMapping("/updateUserPwd")
    public Map<String,Object> updateUserPwd(SysUserVO sysUser) {
        Map<String,Object> result = new HashMap<>();
        try {
            if(StringUtils.isNotBlank(sysUser.getPassword())){
                sysUser.setPassword(new BCryptPasswordEncoder(4).encode(sysUser.getPassword()));
            }
            sysUserService.updateUsers(sysUser);
            result.put("status","success");
        }catch (Exception e){
            logger.error("[{}]-[{}]时，发生异常，异常信息为[{}]","后台","修改后台账户密码",e.getMessage());
            result.put("status","fail");
            result.put("message","修改失败");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/delUsers")
    public void delUsers(@RequestParam(value = "id") Integer id) {
        sysUserService.delUsers(id);
    }

    @ResponseBody
    @RequestMapping("/selectUser")
    public Map<String,Object> selectUsers(@RequestParam("id") Integer id) {
        Map<String,Object> result = new HashMap<>();
        SysUserVO user = sysUserService.selectUsers(id);
        if(null != user){
            List<SysUserRoleVO> sysUserRoleList = userRoleService.findSysUserRoleByUserId(user.getUserId());
            result.put("userRoleList",sysUserRoleList);
        }
        result.put("user",user);
        result.put("status","success");
        return result;
    }


}