package cn.com.myproject.adminuser.externalinterface;


import cn.com.myproject.adminuser.po.SysUser;
import cn.com.myproject.adminuser.po.SysUserRole;
import cn.com.myproject.adminuser.service.ISysUserService;
import cn.com.myproject.adminuser.vo.SysUserRoleVO;
import cn.com.myproject.adminuser.vo.SysUserVO;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 *
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserProvide {

    private static final Logger logger = LoggerFactory.getLogger(SysUserProvide.class);

    @Autowired
    private ISysUserService service;

    @GetMapping("/getPage")
    public PageInfo<SysUserVO> getPage(int pageNum, int pageSize, String keyword) {
        return service.getPage(pageNum,pageSize,keyword);
    }

    @GetMapping("/getByLoginName")
    public SysUserVO getByLoginName(String loginName) {
        return service.getByLoginName(loginName);
    }

    @GetMapping("/getByUserName")
    public SysUserVO getByUserName(String userName) {
        return service.getByUserName(userName);
    }


    @GetMapping("/findByUserId")
    public SysUserVO findById(String userId) {
        SysUser user = service.findByUserId(userId);
        if(null == user) {
            return null;
        }
        SysUserVO sysUserVO = new SysUserVO();
        BeanUtils.copyProperties(user,sysUserVO);
        return sysUserVO;
    }

    @GetMapping("/checkUser")
    public Integer checkUser(String userName) {
        Integer info = service.checkUsers(userName);
        if (null != info && 1 <= info) {
            return 1;
        }
        return 0;
    }

    @PostMapping("/selectUser")
    public SysUserVO selectUser(@RequestBody Integer id) {
        SysUser user  = service.selectUsers(id);
        if(null == user) {
            return null;
        }
        SysUserVO sysUserVO = new SysUserVO();
        BeanUtils.copyProperties(user,sysUserVO);
        return sysUserVO;
    }

    @PostMapping("/delUsers")
    public void delUsers(@RequestBody Integer id) {
        service.delusers(id);
    }

    @PostMapping("/updateUser")
    public String updateUsers(@RequestBody SysUserVO sysUserVO) {
        String result = "修改失败！";
        if(null == sysUserVO) {
            return result;
        }
        try {
            List<SysUserRoleVO> sysUserRoleVOList = sysUserVO.getSysUserRoleVOList();
            List<SysUserRole> sysUserRoleList = new ArrayList<>();
            if(null != sysUserRoleVOList && sysUserRoleVOList.size() > 0){
                SysUserRole sysUserRole;
                for(SysUserRoleVO sysUserRoleVO:sysUserRoleVOList){
                    sysUserRoleVO.setUserId(sysUserVO.getUserId());
                    sysUserRoleVO.setCreateTime(System.currentTimeMillis());
                    sysUserRole = new SysUserRole();
                    BeanUtils.copyProperties(sysUserRoleVO,sysUserRole);
                    sysUserRoleList.add(sysUserRole);
                }
            }
            SysUser sysUser = new SysUser();
            BeanUtils.copyProperties(sysUserVO,sysUser);
            service.updateUsers(sysUser,sysUserRoleList);
            result = "修改成功!";
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @PostMapping("/addUser")
    public int addUsers(@RequestBody SysUserVO sysUserVO) {
        int result = 0;
        try {
            List<SysUserRoleVO> sysUserRoleVOList = sysUserVO.getSysUserRoleVOList();
            List<SysUserRole> sysUserRoleList = new ArrayList<>();
            if (StringUtils.isNotBlank(sysUserVO.getUserName())) {
                String uuid = UUID.randomUUID().toString().replace("-", "");
                sysUserVO.setUserId(uuid);
                sysUserVO.setVersion(1);
                sysUserVO.setStatus((short) 1);
                if(null != sysUserRoleList && sysUserRoleList.size() > 0){
                    SysUserRole sysUserRole;
                    for(SysUserRoleVO sysUserRoleVO:sysUserRoleVOList){
                        sysUserRoleVO.setUserId(uuid);
                        sysUserRoleVO.setCreateTime(System.currentTimeMillis());
                        sysUserRole = new SysUserRole();
                        BeanUtils.copyProperties(sysUserRoleVO,sysUserRole);
                        sysUserRoleList.add(sysUserRole);
                    }
                }
                SysUser sysUser = new SysUser();
                BeanUtils.copyProperties(sysUserVO,sysUser);
                service.addUsers(sysUser,sysUserRoleList);
            }
            result = 1;
        } catch (Exception ex) {
            ex.printStackTrace();
            result = 0;
        }
        return result;
    }

    @GetMapping("/getSysUserByLoginName")
    public SysUserVO getSysUserByLoginName(String loginName) {
        SysUser sysUser = service.getSysUserByLoginName(loginName);
        if(null == sysUser) {
            return null;
        }
        SysUserVO sysUserVO = new SysUserVO();
        BeanUtils.copyProperties(sysUser,sysUserVO);
        return sysUserVO;
    }

    @GetMapping("/getSysUserByUserName")
    public SysUserVO getSysUserByUserName(String userName) {
        SysUser sysUser =  service.getSysUserByUserName(userName);
        if(null == sysUser) {
            return null;
        }
        SysUserVO sysUserVO = new SysUserVO();
        BeanUtils.copyProperties(sysUser,sysUserVO);
        return sysUserVO;
    }



}