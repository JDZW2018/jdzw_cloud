package cn.com.myproject.adminuser.service;

import cn.com.myproject.adminuser.po.SysUserDepartment;

import java.util.List;

/**
 * @author tianfusheng
 * @date 2018/3/14
 */
public interface ISysUserDepartmentService {
    int addSysUserDepartment(SysUserDepartment sysUserDepartment);
    int deleteByUserId(String userId);
    List<SysUserDepartment> selectByDepartmentId(String departmentId);
    SysUserDepartment selectByUserId(String userId);
}
