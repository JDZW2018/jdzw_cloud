package cn.com.myproject.adminuser.externalinterface;


import cn.com.myproject.adminuser.po.SysUserRole;
import cn.com.myproject.adminuser.service.ISysUserRoleService;
import cn.com.myproject.adminuser.vo.SysUserRoleVO;
import cn.com.myproject.util.BeanCopyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/sysUserRole")
public class SysUserRoleProvide {

    private static final Logger logger = LoggerFactory.getLogger(SysUserRoleProvide.class);

    @Autowired
    private ISysUserRoleService service;

    @PostMapping("/addSysUserRole")
    public void addSysUserRole(@RequestBody SysUserRoleVO sysUserRoleVO){
        if(null != sysUserRoleVO) {
            SysUserRole sysUserRole = new SysUserRole();
            BeanUtils.copyProperties(sysUserRoleVO,sysUserRole);
            service.addSysUserRole(sysUserRole);
        }

    }

    @PostMapping("/updateSysUserRole")
    public void updateSysUserRole(@RequestBody SysUserRoleVO sysUserRoleVO){
        if(null != sysUserRoleVO) {
            SysUserRole sysUserRole = new SysUserRole();
            BeanUtils.copyProperties(sysUserRoleVO,sysUserRole);
            service.updateSysUserRole(sysUserRole);
        }
    }

    @PostMapping("/findSysUserRoleByUserId")
    public List<SysUserRoleVO> findSysUserRoleByUserId(@RequestBody String userId){
        List<SysUserRole> list =  service.findSysUserRoleByUserId(userId);
        return BeanCopyUtils.copyList(list,SysUserRoleVO.class);
    }

    @PostMapping("/deleteSysUserRole")
    public void deleteSysUserRole(@RequestBody String userId){
        service.deleteSysUserRole(userId);
    }

    @PostMapping("/batchAddSysUserRole")
    public  void batchAddSysUserRole(@RequestBody List<SysUserRoleVO> sysUserRoleVOList){
        if(null != sysUserRoleVOList && sysUserRoleVOList.size()>0){
            List<SysUserRole> sysUserRoleList = new ArrayList<>();
            SysUserRole sysUserRole;
            for(SysUserRoleVO sysUserRoleVO:sysUserRoleVOList){
                sysUserRoleVO.setCreateTime(System.currentTimeMillis());
                sysUserRole = new SysUserRole();
                BeanUtils.copyProperties(sysUserRoleVO,sysUserRole);
                sysUserRoleList.add(sysUserRole);
            }
            service.batchAddSysUserRole(sysUserRoleList);
        }
    }
}
