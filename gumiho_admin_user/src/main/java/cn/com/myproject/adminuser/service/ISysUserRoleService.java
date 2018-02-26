package cn.com.myproject.adminuser.service;


import cn.com.myproject.adminuser.po.SysUserRole;

import java.util.List;

/**
 *
 */
public interface ISysUserRoleService {

    void addSysUserRole(SysUserRole sysUserRole);

    void updateSysUserRole(SysUserRole sysUserRole);

    List<SysUserRole> findSysUserRoleByUserId(String userId);

    void deleteSysUserRole(String userId);

    void batchAddSysUserRole(List<SysUserRole> sysUserRoleList);

}


