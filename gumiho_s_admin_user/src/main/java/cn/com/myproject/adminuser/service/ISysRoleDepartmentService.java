package cn.com.myproject.adminuser.service;

import cn.com.myproject.adminuser.po.SysRoleDepartment;

import java.util.List;

/**
 * @author tianfusheng
 * @date 2018/3/14
 */
public interface ISysRoleDepartmentService {
    int addSysRoleDepartment(SysRoleDepartment sysRoleDepartment);
    void deleteByRoleId(String roleId);
    List<SysRoleDepartment> selectByDepartmentId(String departmentId);
}
